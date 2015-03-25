package com.fca.prodtrackerdash;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JSONRetirementTracker {
	
    private String node;
    private String nodeparent;
    private String tooltip;
    
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
