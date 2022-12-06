package com.ecl.starlife.models.responses;

import java.util.List;

public class listResponse {
	
	private String responseMessage;
	private String responseCode;
	private List data;



	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	 
	 
     

}
