package com.wh.transfer;

public class Wish {
	
	private String whlId;
	private String whId;
	private String whName;
	private String whDesc;
	private String whCat;
	
	public Wish() {
		// Do nothing
	}

	public Wish(String whlId, String whId, String whName, String whDesc, String whCat) {
		this.whlId = whlId;
		this.whId = whId;
		this.whName = whName;
		this.whDesc = whDesc;
		this.whCat = whCat;
	}

	public String getWhlId() {
		return whlId;
	}

	public void setWhlId(String whlId) {
		this.whlId = whlId;
	}

	public String getWhId() {
		return whId;
	}

	public void setWhId(String whId) {
		this.whId = whId;
	}

	public String getWhName() {
		return whName;
	}

	public void setWhName(String whName) {
		this.whName = whName;
	}

	public String getWhDesc() {
		return whDesc;
	}

	public void setWhDesc(String whDesc) {
		this.whDesc = whDesc;
	}

	public String getWhCat() {
		return whCat;
	}

	public void setWhCat(String whCat) {
		this.whCat = whCat;
	}

	
	
	
}
