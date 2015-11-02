package com.fca.annuitydash;

import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonRootName;


@XmlRootElement
public class JSONProductChanges {

    private String Provider;
    private String Product;
    private Date   ChangeDate;
    private String ChangeType;
	
	public String getProvider() {
		return Provider;
	}

	public void setProvider(String Provider) {
		this.Provider = Provider;
	}
    
    public String getProduct() {
		return Product;
	}

	public void setProduct(String Product) {
		this.Product = Product;
	}

    public Date getChangeDate() {
		return ChangeDate;
	}

	public void setChangeDate(Date ChangeDate) {
		this.ChangeDate = ChangeDate;
	}
	
    public String getChangeType() {
		return ChangeType;
	}

	public void setChangeType(String ChangeType) {
		this.ChangeType = ChangeType;
	}
	
	
}

