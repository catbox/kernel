package com.wh.transfer;

public class Semester {
	
	private String semesterId;
	private String semesterName;
	private String timestamp;
	
	public Semester() {
		// Do nothing
	}
	
	public Semester(String semesterId, String semesterName, String timestamp) {
		this.semesterId = semesterId;
		this.semesterName = semesterName;
		this.timestamp = timestamp;
	}
	
	public String getSemesterId() {
		return semesterId;
	}
	public void setSemesterId(String semesterId) {
		this.semesterId = semesterId;
	}
	public String getSemesterName() {
		return semesterName;
	}
	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
