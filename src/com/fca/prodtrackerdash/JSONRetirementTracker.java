package com.fca.prodtrackerdash;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JSONRetirementTracker {
	
    private String node;
    private Integer productCount;
    private Integer providerCount;
    private Integer compProductCount;
    private Integer compProviderCount;
    private String nodeparent;
    private String tooltip;
    
	public Integer getProductCount() {
		return productCount;
	}
	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}
	public Integer getProviderCount() {
		return providerCount;
	}
	public void setProviderCount(Integer providerCount) {
		this.providerCount = providerCount;
	}
	public Integer getCompProductCount() {
		return compProductCount;
	}
	public void setCompProductCount(Integer compProductCount) {
		this.compProductCount = compProductCount;
	}
	public Integer getCompProviderCount() {
		return compProviderCount;
	}
	public void setCompProviderCount(Integer compProviderCount) {
		this.compProviderCount = compProviderCount;
	}
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public String getNodeparent() {
		return nodeparent;
	}
	public void setNodeparent(String nodeparent) {
		this.nodeparent = nodeparent;
	}
	public String getTooltip() {
		return tooltip;
	}
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}
	
}
