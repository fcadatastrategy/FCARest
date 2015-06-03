package com.fca.firmproductviewer;

import javax.xml.bind.annotation.XmlRootElement;

	@XmlRootElement
	public class JSONProduct {
		
		private String ProductId;
	    private String ProductName;
	    private String ProductBK;
	    
		public String getProductId() {
			return ProductId;
		}
		public void setProductId(String productId) {
			ProductId = productId;
		}
		public String getProductName() {
			return ProductName;
		}
		public void setProductName(String productName) {
			ProductName = productName;
		}
		public String getProductBK() {
			return ProductBK;
		}
		public void setProductBK(String productBK) {
			ProductBK = productBK;
		}		
	
	
}
