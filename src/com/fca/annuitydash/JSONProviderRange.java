package com.fca.annuitydash;

import java.sql.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JSONProviderRange {

    private String Provider;
    private Integer ProductCount;
	
	public String getProvider() {
		return Provider;
	}

	public void setProvider(String Provider) {
		this.Provider = Provider;
	}

	public Integer getProductCount() {
		return ProductCount;
	}

	public void setProductCount(Integer ProductCount) {
		this.ProductCount = ProductCount;
	}

	
}
