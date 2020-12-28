package org.mscatdk.bilbasen;

public class CarAdd {

	public CarAdd(String key, String headline, String region, String price, String hk, String trailer, String kml, String km, String year, String bilbasenId) {
		super();
		this.key = key;
		this.headline = headline;
		this.region = region;
		this.price = price;
		this.hk = hk;
		this.trailer = trailer;
		this.kml = kml;
		this.km = km;
		this.year = year;
		this.bilbasenId = bilbasenId;
	}
	
	String key;
	String headline;
	String region;
	String price;
	String hk;
	String trailer;
	String kml;	
	String km;
	String year;
	String bilbasenId;
	
	public static String[] getHeader() {
		return new String[]{"key", "headline", "region", "price", "hk", "trailer", "km/l", "km", "year", "bilbasenId"};
	}
	
	public String[] getData() {
		return new String[]{key, headline, region, price, hk, trailer, kml, km, year, bilbasenId};
	}

	public String getKey() {
		return key;
	}

	public String getHeadline() {
		return headline;
	}

	public String getRegion() {
		return region;
	}

	public String getPrice() {
		return price;
	}

	public String getHk() {
		return hk;
	}

	public String getTrailer() {
		return trailer;
	}

	public String getKml() {
		return kml;
	}

	public String getKm() {
		return km;
	}

	public String getYear() {
		return year;
	}

	public String getBilbasenId() {
		return bilbasenId;
	}
	
}
