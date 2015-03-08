package com.fca.retdash;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class TopFeatureChange {

    private String SubCategory;
    private String Feature;
    private Integer ChangeCount;

	public TopFeatureChange() {
    }
	
	public String getSubCategory() {
		return SubCategory;
	}

	public void setSubCategory(String SubCategory) {
		this.SubCategory = SubCategory;
	}

	public String getFeature() {
		return Feature;
	}

	public void setFeature(String Feature) {
		this.Feature = Feature;
	}
	
	public Integer getChangeCount() {
		return ChangeCount;
	}

	public void setChangeCount(Integer ChangeCount) {
		this.ChangeCount = ChangeCount;
	}	
}

