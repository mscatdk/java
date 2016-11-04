package com.msc.res.jx.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DataObj implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7198185746964978091L;
	
	
	@XmlAttribute
	private final String name;
	@XmlAttribute
	private final String data;

	@SuppressWarnings("unused")
	private DataObj() {
		this.name = null;
		this.data = null;
	}
	
	public DataObj(String name, String data) {
		super();
		this.name = name;
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public String getData() {
		return data;
	}
}
