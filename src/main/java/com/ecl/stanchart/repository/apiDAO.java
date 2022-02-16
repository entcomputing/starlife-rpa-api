package com.ecl.stanchart.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class apiDAO {

    @Autowired
    JdbcTemplate template;

    //login sessions activity
    //add new session login
    public int addLoginSession(String username, String email, String platform, String session_id, String login_date, String logout_date) {
        int resp = 0;
        try {

            String query = "INSERT INTO login_sessions (USERNAME, EMAIL, PLATFORM, SESSION_ID, LOGIN_DATE, LOGOUT_DATE) values (?,?,?,?,?,?)";
            resp = template.update(query, username, email, platform, session_id, login_date, logout_date);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return resp;
    }


    //update session login
    public int updateLoginSession(String logout_date, String session_id) {
        int resp = 0;
        try {

            String query = "UPDATE login_sessions SET LOGOUT_DATE = ? WHERE SESSION_ID = ? ";
            resp = template.update(query, logout_date, session_id);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return resp;
    }


    //bot extraction sessions activity
    //add new session for bot extraction sessions
    public int addBotExtraction(String name, String id_number, String date_of_issue, String date_of_expiry, String bot_deployment_id, String status, String platform) {
        int resp = 0;
        try {

            String query = "INSERT INTO bot_extraction (NAME, ID_NUMBER, DATE_OF_ISSUE, DATE_OF_EXPIRY, BOT_DEPLOYMENT_ID, STATUS, PLATFORM) values (?,?,?,?,?,?,?)";
            resp = template.update(query, name, id_number, date_of_issue, date_of_expiry, bot_deployment_id, status, platform);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return resp;
    }


    //add new webhook data
    public int addWebhookData(String name, String id_number, String date_of_issue, String date_of_expiry, String bot_deployment_id, String status, String platform) {
        int resp = 0;
        try {

            String query = "INSERT INTO bot_webhook_data (NAME, ID_NUMBER, DATE_OF_ISSUE, DATE_OF_EXPIRY, BOT_DEPLOYMENT_ID, STATUS, PLATFORM) values (?,?,?,?,?,?,?)";
            resp = template.update(query, name, id_number, date_of_issue, date_of_expiry, bot_deployment_id, status, platform);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return resp;
    }


    //delete webhook data
    public int deleteWebhookData() {
        int resp = 0;
        try {

            String query = "DELETE FROM bot_webhook_data;";
            resp = template.update(query);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return resp;
    }


    //customer
    //add new customer details
    public int addCustomerDetails(String name, String id_number, String date_of_issue, String date_of_expiry, String account_number, String idType, String cheque_number, String bank_name, String bot_deployment_id, String transaction_id, String teller_id, String platform, String created_by_username, String created_by_email, String created_by_teller_id) {
        int resp = 0;
        try {

            String query = "INSERT INTO customer (NAME, ID_NUMBER, DATE_OF_ISSUE, DATE_OF_EXPIRY, ACCOUNT_NUMBER, ID_TYPE, CHEQUE_NUMBER, BANK_NAME, BOT_DEPLOYMENT_ID, TRANSACTION_ID, TELLER_ID, PLATFORM, CREATED_BY_USERNAME, CREATED_BY_EMAIL, CREATED_BY_TELLER_ID) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            resp = template.update(query, name, id_number, date_of_issue, date_of_expiry, account_number, idType, cheque_number, bank_name, bot_deployment_id, transaction_id, teller_id, platform, created_by_username, created_by_email, created_by_teller_id);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return resp;
    }

    //fetch customer details
    public List fetchAllCustomers() {

            String query = "SELECT * FROM customer ORDER BY DATE_CREATED DESC";
            List results = template.queryForList(query);

        return results;
    }


    //auditService
    //customer entry form
    public int customerEntryFormAudit(String username, String email, String eventType, String eventDesc, String execDate, String execTime, String eventResults, String transactionID, String usedRole, String usedPriviledge, String requestEndpoint, String requestClient, String ipAddress, String hostname) {
        int resp = 0;
        try {

            String query = "INSERT INTO audit_trail (USERNAME, EMAIL, EVENT_TYPE, EVENT_DESC, EXEC_DATE, EXEC_TIME, EVENT_RESULT, TRANSACTION_ID, USED_ROLE, USED_PRIVILEDGE, SERVICE_REQUEST_ENDPOINT, SERVICE_REQUEST_CLIENT, IP_ADDRESS, HOSTNAME, SQL_STATEMENT) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            resp = template.update(query, username, email, eventType, eventDesc, execDate, execTime, eventResults, transactionID, usedRole, usedPriviledge, requestEndpoint, requestClient, ipAddress, hostname, query);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return resp;
    }


    //fetch audit
    public List fetchAllAudit() {

        String query = "SELECT USERNAME, EMAIL, EVENT_TYPE, EVENT_DESC, EXEC_DATE, EXEC_TIME, EVENT_RESULT, TRANSACTION_ID, USED_ROLE, USED_PRIVILEDGE, SERVICE_REQUEST_ENDPOINT, SERVICE_REQUEST_CLIENT, IP_ADDRESS, HOSTNAME FROM audit_trail ORDER BY DATE_CREATED DESC";
        List results = template.queryForList(query);

        return results;
    }





































    // List of all clients
    public List getAllClients(String country) {

        String query = "SELECT company_name, company_tel FROM clients WHERE registration_status = ? AND country = ? ORDER BY company_name ASC";
        List results = template.queryForList(query, "ACTIVE", country);

        return results;

    }

}
