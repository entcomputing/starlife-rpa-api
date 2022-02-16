package com.ecl.stanchart.models.requests;

public class customerRequest {

    private String name;
    private String id_number;
    private String date_of_issue;
    private String date_of_expiry;
    private String account_number;
    private String idType;
    private String cheque_number;
    private String bank_name;
    private String bot_deployment_id;
    private String transaction_id;
    private String teller_id;
    private String platform;
    private String created_by_username;
    private String created_by_email;
    private String created_by_teller_id;
    private String ipAddress;



    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getCreated_by_username() {
        return created_by_username;
    }

    public void setCreated_by_username(String created_by_username) {
        this.created_by_username = created_by_username;
    }

    public String getCreated_by_email() {
        return created_by_email;
    }

    public void setCreated_by_email(String created_by_email) {
        this.created_by_email = created_by_email;
    }

    public String getCreated_by_teller_id() {
        return created_by_teller_id;
    }

    public void setCreated_by_teller_id(String created_by_teller_id) {
        this.created_by_teller_id = created_by_teller_id;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getBot_deployment_id() {
        return bot_deployment_id;
    }

    public void setBot_deployment_id(String bot_deployment_id) {
        this.bot_deployment_id = bot_deployment_id;
    }

    public String getTeller_id() {
        return teller_id;
    }

    public void setTeller_id(String teller_id) {
        this.teller_id = teller_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getDate_of_issue() {
        return date_of_issue;
    }

    public void setDate_of_issue(String date_of_issue) {
        this.date_of_issue = date_of_issue;
    }

    public String getDate_of_expiry() {
        return date_of_expiry;
    }

    public void setDate_of_expiry(String date_of_expiry) {
        this.date_of_expiry = date_of_expiry;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getCheque_number() {
        return cheque_number;
    }

    public void setCheque_number(String cheque_number) {
        this.cheque_number = cheque_number;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
