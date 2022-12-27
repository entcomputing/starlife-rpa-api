package com.ecl.starlife.models.requests;

public class incidentReportRequest {

    private int customerID;
    private String webProdName;
    private String webProdCode;
    private String clntCode;
    private String webClntCode;
    private String webQuoteCode;
    private String subject;
    private String message;
    private String reportedBy;
    private String requestBy;
    private String requestIP;
    private String requestChannel;


    public String getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getWebProdName() {
        return webProdName;
    }

    public void setWebProdName(String webProdName) {
        this.webProdName = webProdName;
    }

    public String getWebProdCode() {
        return webProdCode;
    }

    public void setWebProdCode(String webProdCode) {
        this.webProdCode = webProdCode;
    }

    public String getClntCode() {
        return clntCode;
    }

    public void setClntCode(String clntCode) {
        this.clntCode = clntCode;
    }

    public String getWebClntCode() {
        return webClntCode;
    }

    public void setWebClntCode(String webClntCode) {
        this.webClntCode = webClntCode;
    }

    public String getWebQuoteCode() {
        return webQuoteCode;
    }

    public void setWebQuoteCode(String webQuoteCode) {
        this.webQuoteCode = webQuoteCode;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public String getRequestIP() {
        return requestIP;
    }

    public void setRequestIP(String requestIP) {
        this.requestIP = requestIP;
    }

    public String getRequestChannel() {
        return requestChannel;
    }

    public void setRequestChannel(String requestChannel) {
        this.requestChannel = requestChannel;
    }
}