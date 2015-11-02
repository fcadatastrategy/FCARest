package com.fca.annuitydash;

import java.sql.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JSONProdProviderTime {

	private Date YearMonth;
	private Integer ProviderCount;
    private Integer ProductCount;
	
	public Date getYearMonth() {
		return YearMonth;
	}

	public void setYearMonth(Date YearMonth) {
		this.YearMonth = YearMonth;
	}

	public Integer getProductCount() {
		return ProductCount;
	}

	public void setProductCount(Integer ProductCount) {
		this.ProductCount = ProductCount;
	}
	
	public Integer getProviderCount() {
		return ProviderCount;
	}

	public void setProviderCount(Integer ProviderCount) {
		this.ProviderCount = ProviderCount;
	}	

	
}
