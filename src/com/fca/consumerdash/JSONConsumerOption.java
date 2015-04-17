package com.fca.consumerdash;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JSONConsumerOption {
	

    private Integer weightedVal;
    private Integer weightedAvg;
    private String choiceDescription;
    private String crossTab;
    
	public Integer getWeightedVal() {
		return weightedVal;
	}
	public void setWeightedVal(Integer weightedVal) {
		this.weightedVal = weightedVal;
	}
	public Integer getWeightedAvg() {
		return weightedAvg;
	}
	public void setWeightedAvg(Integer weightedAvg) {
		this.weightedAvg = weightedAvg;
	}
	public String getChoiceDescription() {
		return choiceDescription;
	}
	public void setChoiceDescription(String choiceDescription) {
		this.choiceDescription = choiceDescription;
	}
	public String getCrossTab() {
		return crossTab;
	}
	public void setCrossTab(String crossTab) {
		this.crossTab = crossTab;
	}
    
	
}
