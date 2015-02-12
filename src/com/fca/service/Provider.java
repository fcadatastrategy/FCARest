package com.fca.service;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Provider {

    private int id;
    private String ProviderName;
    private String ProductName;

	public Provider() {
    }
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProviderName() {
		return ProviderName;
	}

	public void setProviderName(String ProviderName) {
		this.ProviderName = ProviderName;
	}
	
	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String ProductName) {
		this.ProductName = ProductName;
	}	
}

