package com.fca.service;

import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.map.annotate.JsonRootName;


@XmlRootElement
@JsonRootName(value= "advertiser")
public class Advertiser {

    private double AdSpend;
    private String AdvertiserProduct;

	public Advertiser() {
    }
	
	public String getAdvertiserProduct() {
		return AdvertiserProduct;
	}

	public void setAdvertiserProduct(String AdvertiserProduct) {
		this.AdvertiserProduct = AdvertiserProduct;
	}

	public double getAdSpend() {
		return AdSpend;
	}

	public void setAdSpend(double AdSpend) {
		this.AdSpend = AdSpend;
	}
}

