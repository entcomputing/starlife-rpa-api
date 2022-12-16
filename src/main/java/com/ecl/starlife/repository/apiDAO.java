package com.ecl.starlife.repository;


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

    //update customer details
    public int updateCustomerDetails(String surname, String middleName, String firstName, String dob, String nationality, String gender, String maritalStatus, String occupation, String mobile1, String mobile2, String email, String permanentAddress, String idType, String idNumber, String dateOfIssue, String dateOfExpiry, String proposalNumber, String webQuoteCode, int validated, String validatedBy, int customerID) {
        int resp = 0;
        try {

            String query = "UPDATE customers SET SURNAME = ?, MIDDLE_NAME = ?, FIRST_NAME = ?, DOB = ?, NATIONALITY = ?, GENDER = ?, MARITAL_STATUS = ?, OCCUPATION = ?, MOBILE1 = ?, MOBILE2 = ?, Email = ?, PERMANENT_ADDRESS = ?, ID_TYPES = ?, ID_NUMBER = ?, DATE_OF_ISSUE = ?, DATE_OF_EXPIRY = ?, PROPOSAL_NUMBER = ?, webQuoteCode = ?, VALIDATED = ?, VALIDATED_BY = ?  WHERE CUST_ID = ? ";
            resp = template.update(query, surname, middleName, firstName, dob, nationality, gender, maritalStatus, occupation, mobile1, mobile2, email, permanentAddress, idType, idNumber, dateOfIssue, dateOfExpiry, proposalNumber, webQuoteCode, validated, validatedBy, customerID);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return resp;
    }


    public int addBeneficiaryDetails(int wbnfCode, String surname, String othernames, String dob, String relationship, String percentage, String contact, String gender, int customerID, int validated, String validatedBy) {
        int resp = 0;
        try {

            String query = "INSERT INTO beneficiary (wbnfCode, Beneficiary_SURNAME, Beneficiary_OTHERNAMES, Beneficiary_DOB, Beneficiary_RELATIONSHIP, Beneficiary_PERCENTAGE, Beneficiary_CONTACT_NO, Beneficiary_GENDER, CUST_ID, VALIDATED, VALIDATED_BY) values (?,?,?,?,?,?,?,?,?,?,?)";
            resp = template.update(query, wbnfCode, surname, othernames, dob, relationship, percentage, contact, gender, customerID, validated, validatedBy);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return resp;
    }

    public int updateBeneficiaryDetails(int wbnfCode, String surname, String othernames, String dob, String relationship, String percentage, String contact, String gender, int validated, String validatedBy, int beneficiaryID) {
        int resp = 0;
        try {

            String query = "UPDATE beneficiary SET wbnfCode = ?, Beneficiary_SURNAME = ?, Beneficiary_OTHERNAMES = ?, Beneficiary_DOB = ?, Beneficiary_RELATIONSHIP = ?, Beneficiary_PERCENTAGE = ?, Beneficiary_CONTACT_NO = ?, Beneficiary_GENDER = ?, VALIDATED = ?, VALIDATED_BY = ?  WHERE BENEF_ID = ? ";
            resp = template.update(query, wbnfCode, surname, othernames, dob, relationship, percentage, contact, gender, validated, validatedBy, beneficiaryID);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return resp;
    }

    public int deleteBeneficiaryDetails(int beneficiaryID) {
        int resp = 0;
        try {

            String query = "DELETE FROM beneficiary WHERE BENEF_ID = ? ";
            resp = template.update(query, beneficiaryID);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return resp;
    }



    //fetch customer details
    public List fetchAllCustomers(String requestBy) {

            String query = "SELECT customers.*, products.PRODUCT_NAME " +
                            "FROM customers, products " +
                            "WHERE customers.EMP_EMAIL = ? " +
                            "AND customers.CUST_ID = products.CUST_ID " +
                            "ORDER BY DATE_CREATED DESC";
            List results = template.queryForList(query, requestBy);

        return results;
    }

    //fetch account holder details
    public List fetchAccountHolders(int customerID) {

        String query = "SELECT * FROM acct_holder WHERE CUST_ID = ? ORDER BY DATE_CREATED DESC";
        List results = template.queryForList(query, customerID);

        return results;
    }

    //fetch cover details
    public List fetchCoverInfo(int customerID) {

        String query = "SELECT * FROM covers_details WHERE CUST_ID = ? ORDER BY DATE_CREATED DESC";
        List results = template.queryForList(query, customerID);

        return results;
    }

    //fetch payment details
    public List fetchPaymentInfo(int customerID) {

        String query = "SELECT * FROM payment_details WHERE CUST_ID = ? ORDER BY DATE_CREATED DESC";
        List results = template.queryForList(query, customerID);

        return results;
    }

    //fetch document details
    public List fetchDocumentInfo(int customerID) {

        String query = "SELECT * FROM documents WHERE CUST_ID = ? ORDER BY DATE_CREATED DESC";
        List results = template.queryForList(query, customerID);

        return results;
    }

    //fetch product details
    public List fetchProductInfo(int customerID) {

        String query = "SELECT * FROM products WHERE CUST_ID = ? ORDER BY DATE_CREATED DESC";
        List results = template.queryForList(query, customerID);

        return results;
    }


    public List fetchBeneficiaryInfo(int customerID) {

        String query = "SELECT * FROM beneficiary WHERE CUST_ID = ? ORDER BY DATE_CREATED DESC";
        List results = template.queryForList(query, customerID);

        return results;
    }

    public List fetchTrusteeInfo(int customerID) {

        String query = "SELECT * FROM trustees WHERE CUST_ID = ? ORDER BY DATE_CREATED DESC";
        List results = template.queryForList(query, customerID);

        return results;
    }


    //auditService
    //customer entry form
    public int auditLogEntry(String username, String email, String eventType, String eventDesc, String execDate, String execTime, String eventResults, String transactionID, String usedRole, String usedPriviledge, String requestEndpoint, String requestClient, String ipAddress, String hostname) {
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

    public List fetchUserByEmail(String email) {

        String query = "SELECT * FROM users WHERE EMAIL = ?";
        List results = template.queryForList(query, email);

        return results;
    }

    public int changePassword(String newPassword, String userEmail) {
        int resp = 0;
        try {

            String query = "UPDATE users SET PASSWORD = ? WHERE EMAIL = ? ";
            resp = template.update(query, newPassword, userEmail);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return resp;
    }





































    // List of all clients
    public List getAllClients(String country) {

        String query = "SELECT company_name, company_tel FROM clients WHERE registration_status = ? AND country = ? ORDER BY company_name ASC";
        List results = template.queryForList(query, "ACTIVE", country);

        return results;

    }

}
