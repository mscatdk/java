package org.mscatdk.bilbasen;

import java.io.IOException;
import java.io.InputStream;

import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

public class BilbasenTest {

	private Bilbasen bilbasen = new Bilbasen();
	
	@Test
	public void isNext() throws IOException {
		InputStream is = BilbasenTest.class.getClassLoader().getResourceAsStream("Example1.html");
		Document doc = bilbasen.loadDocument(is, "");
		
		String expected = "https://www.bilbasen.dk/brugt/bil/skoda/ms-octavia-serie?yearfrom=2010&pricefrom=0&includeengroscvr=false&includeleasing=false&newandused=usedonly&page=2";
		String actual = bilbasen.hasNext(doc).toString();
		Assert.assertNotNull("Next URL not found!", actual);
		Assert.assertEquals("URL doesn't match!", expected, actual);
		
	}
	
}
