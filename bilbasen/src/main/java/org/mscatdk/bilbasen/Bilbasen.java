package org.mscatdk.bilbasen;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Bilbasen {

	private static Logger logger = LoggerFactory.getLogger(Bilbasen.class);
	
	public Document getDocument(URL url) throws IOException {
		return Jsoup.connect(url.toString()).get();
	}
	
	public Document loadDocument(InputStream is, String url) throws IOException {
		return Jsoup.parse(is, "UTF-8", url);
	}
	
	public URL hasNext(Document doc) {
		try {
			Elements links = doc.select("a.next");
		    Element link = links.first();
		    return new URL(link.attr("href"));
	    } catch (Exception e) {
	    	return null;
	    }
	}

	public List<CarAdd> processDocument(Document doc, String key) {
		
		List<CarAdd> carAdds = new ArrayList<>();
		
		Elements links = doc.select("div.bb-listing-clickable");
		for (Element div : links) {
			try {
				
				Element headings = div.select("a.listing-heading").first();
				String headline = headings.text();
				String id = headings.attr("href");
				
				String region = div.select("div.listing-region").text();
				String price = readNumber(div.select("div.listing-price").text().split(" ")[0]);
				Integer.parseInt(price);
				
				Element span = div.selectFirst("span.variableDataColumn");
				
				String hk = span.attr("data-hk").split(" ")[0].trim();
				String trailer = readNumber(span.attr("data-trailer").split(" ")[0]);
				String kml = readNumber(span.attr("data-kml").split(" ")[0]);
				
				Elements divs = div.select("div.col-xs-2.listing-data");
				
				String km = readNumber(divs.get(1).text());
				String year = divs.get(2).text();
				
				carAdds.add(new CarAdd(key, headline, region, price, hk, trailer, kml, km, year, id));
			} catch (Exception e) {
				logger.error("Unable to process record. Div: {}", div, e);
			}
		}
		
		return carAdds;
	}
	
	private String readNumber(String text) {
		return text.replace(".", "").replace(",", ".").trim();
	}
	
	
	
}
