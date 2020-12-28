package org.mscatdk.bilbasen.commands;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.jsoup.nodes.Document;
import org.mscatdk.bilbasen.Bilbasen;
import org.mscatdk.bilbasen.CarAdd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(commandDescription = "Download all search results based on a seach URL")
public class CommandDownload implements CommandInterface {

	private static Logger console = LoggerFactory.getLogger("console");
	private static Logger logger = LoggerFactory.getLogger(CommandDownload.class);
	
	private Bilbasen bilbasen = new Bilbasen();
	
	@Parameter(
			  names = { "--url" },
			  description = "Bilbasen search URL",
			  required = true
			)
	private URL url;
	
	@Parameter(
			  names = { "--output", "-O" },
			  description = "Output file path",
			  required = false
			)
	private String path;
	
	@Parameter(
			  names = { "--key", "-K" },
			  description = "Key used to label the search",
			  required = false
			)
	private String key;
	
	@Override
	public void exec() {
		Document doc = null;
		
		if (path == null) {
			path = "./bilbasen_" + new Date().getTime() + ".csv";
		}
		
		if (key == null) {
			key = new File(path).getName();
		}
		
		console.info("Processing URL: {}", url);
		console.info("Output path: {}", path);
		console.info("Key: {}", key);
		
		try {
        FileWriter fw = new FileWriter(path);
	        try (CSVPrinter csvPrinter = new CSVPrinter(fw, CSVFormat.DEFAULT.withHeader(CarAdd.getHeader())))  {
		        do {
		        	doc = bilbasen.getDocument(url);
		        	
		        	List<CarAdd> carAdds = bilbasen.processDocument(doc, key);
		        	
		        	writeDataToCSV(csvPrinter, carAdds);
		        } while ((url = bilbasen.hasNext(doc)) != null);		
	        }
        } catch (Exception e) {
        	console.error("Unable to process URL. Message: {}", e.getMessage());
        	logger.error("Unable to process URL: {}", url, e);
        }
	}
	
    public static void writeDataToCSV(CSVPrinter csvPrinter, List<CarAdd> carAdds) throws IOException {
    	for (CarAdd carAdd : carAdds) {
    		csvPrinter.printRecord((Object[])carAdd.getData());
    	}
    }
}
