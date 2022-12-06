package com.ecl.starlife.models.requests;

public class beneficiaryRequest {

    private int beneficiaryID;
    private String beneficiarySurname;
    private String beneficiaryOthernames;
    private String beneficiaryDOB;
    private String beneficiaryRelationship;
    private String beneficiaryPercentage;
    private String beneficiaryContact;
    private int customerID;
    private int validated;
    private String validatedBy;

    private String requestBy;
    private String requestIP;
    private String requestChannel;


    public int getBeneficiaryID() {
        return beneficiaryID;
    }

    public void setBeneficiaryID(int beneficiaryID) {
        this.beneficiaryID = beneficiaryID;
    }

    public String getBeneficiarySurname() {
        return beneficiarySurname;
    }

    public void setBeneficiarySurname(String beneficiarySurname) {
        this.beneficiarySurname = beneficiarySurname;
    }

    public String getBeneficiaryOthernames() {
        return beneficiaryOthernames;
    }

    public void setBeneficiaryOthernames(String beneficiaryOthernames) {
        this.beneficiaryOthernames = beneficiaryOthernames;
    }

    public String getBeneficiaryDOB() {
        return beneficiaryDOB;
    }

    public void setBeneficiaryDOB(String beneficiaryDOB) {
        this.beneficiaryDOB = beneficiaryDOB;
    }

    public String getBeneficiaryRelationship() {
        return beneficiaryRelationship;
    }

    public void setBeneficiaryRelationship(String beneficiaryRelationship) {
        this.beneficiaryRelationship = beneficiaryRelationship;
    }

    public String getBeneficiaryPercentage() {
        return beneficiaryPercentage;
    }

    public void setBeneficiaryPercentage(String beneficiaryPercentage) {
        this.beneficiaryPercentage = beneficiaryPercentage;
    }

    public String getBeneficiaryContact() {
        return beneficiaryContact;
    }

    public void setBeneficiaryContact(String beneficiaryContact) {
        this.beneficiaryContact = beneficiaryContact;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getValidated() {
        return validated;
    }

    public void setValidated(int validated) {
        this.validated = validated;
    }

    public String getValidatedBy() {
        return validatedBy;
    }

    public void setValidatedBy(String validatedBy) {
        this.validatedBy = validatedBy;
    }

    public String getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
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