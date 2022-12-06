package com.ecl.starlife.models.responses;

public class objectResponse {
	public String responseCode;
	public String responseMessage;
	public int count;
	public Object data;
	public Object accountHolderInfo;
	public Object coverInfo;
	public Object paymentInfo;
	public Object documentInfo;
	public Object productInfo;
	public Object beneficiaryInfo;


	public Object getBeneficiaryInfo() {
		return beneficiaryInfo;
	}

	public void setBeneficiaryInfo(Object beneficiaryInfo) {
		this.beneficiaryInfo = beneficiaryInfo;
	}

	public Object getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(Object productInfo) {
		this.productInfo = productInfo;
	}

	public Object getDocumentInfo() {
		return documentInfo;
	}

	public void setDocumentInfo(Object documentInfo) {
		this.documentInfo = documentInfo;
	}

	public Object getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(Object paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public Object getAccountHolderInfo() {
		return accountHolderInfo;
	}

	public Object setAccountHolderInfo(Object accountHolderInfo) {
		this.accountHolderInfo = accountHolderInfo;
		return accountHolderInfo;
	}

	public Object getCoverInfo() {
		return coverInfo;
	}

	public void setCoverInfo(Object coverInfo) {
		this.coverInfo = coverInfo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
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
