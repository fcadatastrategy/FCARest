package com.fca.service;

import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.map.annotate.JsonRootName;


@XmlRootElement
public class ConAnnuityOptions {

    private String Product;
    private String Provider;
    private Integer Impaired;
    private Integer Enhanced;
    private Integer Joint;
    private Integer Single;
    private Integer Deferred;
    private Integer Advised;
    
	
	public String getProduct() {
		return Product;
	}

	public void setProduct(String Product) {
		this.Product = Product;
	}

	public String getProvider() {
		return Provider;
	}

	public void setProvider(String Provider) {
		this.Provider = Provider;
	}

	public Integer getImpaired() {
		return Impaired;
	}

	public void setImpaired(Integer Impaired) {
		this.Impaired = Impaired;
	}
	
	public Integer getEnhanced() {
		return Enhanced;
	}

	public void setEnhanced(Integer Enhanced) {
		this.Enhanced = Enhanced;
	}
	
	public Integer getJoint() {
		return Joint;
	}

	public void setJoint(Integer Joint) {
		this.Joint = Joint;
	}
	
	public Integer getSingle() {
		return Single;
	}

	public void setSingle(Integer Single) {
		this.Single = Single;
	}	
	
	public Integer getDeferred() {
		return Deferred;
	}

	public void setDeferred(Integer Deferred) {
		this.Deferred = Deferred;
	}		
	
	public Integer getAdvised() {
		return Advised;
	}

	public void setAdvised(Integer Advised) {
		this.Advised = Advised;
	}		
	
}

