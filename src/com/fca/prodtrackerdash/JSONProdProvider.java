package com.fca.prodtrackerdash;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JSONProdProvider {
	
    private String product;
    private String provider;
    
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
    
}
