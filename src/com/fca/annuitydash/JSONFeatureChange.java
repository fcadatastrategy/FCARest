package com.fca.annuitydash;

import java.sql.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JSONFeatureChange {

	private String Provider;
    private String Product;
	private String SubCategory;
    private String Feature;
    private Date FeatureDate;
    private String CurrentValue;
    private String PreviousValue;
    
    public String getProvider() {
		return Provider;
	}

	public void setProvider(String provider) {
		Provider = provider;
	}

	public String getProduct() {
		return Product;
	}

	public void setProduct(String product) {
		Product = product;
	}

	public String getSubCategory() {
		return SubCategory;
	}

	public void setSubCategory(String subCategory) {
		SubCategory = subCategory;
	}

	public String getFeature() {
		return Feature;
	}

	public void setFeature(String feature) {
		Feature = feature;
	}

	public Date getFeatureDate() {
		return FeatureDate;
	}

	public void setFeatureDate(Date featureDate) {
		FeatureDate = featureDate;
	}

	public String getCurrentValue() {
		return CurrentValue;
	}

	public void setCurrentValue(String currentValue) {
		CurrentValue = currentValue;
	}

	public String getPreviousValue() {
		return PreviousValue;
	}

	public void setPreviousValue(String previousValue) {
		PreviousValue = previousValue;
	}

	public JSONFeatureChange() {
    }
	
	
	
}

