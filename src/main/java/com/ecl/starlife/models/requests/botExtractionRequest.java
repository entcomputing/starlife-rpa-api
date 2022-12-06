package com.ecl.starlife.models.requests;

public class botExtractionRequest {

    private String name;
    private String id_number;
    private String date_of_issue;
    private String date_of_expiry;
    private String bot_deployment_id;
    private String platform;


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

    public String getBot_deployment_id() {
        return bot_deployment_id;
    }

    public void setBot_deployment_id(String bot_deployment_id) {
        this.bot_deployment_id = bot_deployment_id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }



}
