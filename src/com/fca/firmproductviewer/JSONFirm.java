package com.fca.firmproductviewer;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JSONFirm {
	
	private String FirmId;
    private String FirmName;
    private String FirmMarket;
    private String FirmType;
    private String FirmDataDate;
    private Integer FIrmProductCount;
	
    public String getFirmId() {
		return FirmId;
	}
	public void setFirmId(String firmId) {
		FirmId = firmId;
	}
	public String getFirmName() {
		return FirmName;
	}
	public void setFirmName(String firmName) {
		FirmName = firmName;
	}
	public String getFirmMarket() {
		return FirmMarket;
	}
	public void setFirmMarket(String firmMarket) {
		FirmMarket = firmMarket;
	}
	public String getFirmType() {
		return FirmType;
	}
	public void setFirmType(String firmType) {
		FirmType = firmType;
	}
	public String getFirmDataDate() {
		return FirmDataDate;
	}
	public void setFirmDataDate(String firmDataDate) {
		FirmDataDate = firmDataDate;
	}
	public Integer getFIrmProductCount() {
		return FIrmProductCount;
	}
	public void setFIrmProductCount(Integer fIrmProductCount) {
		FIrmProductCount = fIrmProductCount;
	}

    
}
