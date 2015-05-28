package com.fca.firmproductviewer;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JSONFirmProdDate {
	
    private String datadate;
    private Integer id;

	public String getDatadate() {
		return datadate;
	}

	public void setDatadate(String datadate) {
		this.datadate = datadate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
    
}
