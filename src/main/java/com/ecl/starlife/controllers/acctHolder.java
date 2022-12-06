package com.ecl.starlife.controllers;

import com.ecl.starlife.models.requests.acctHolderRequest;
import com.ecl.starlife.models.requests.customerRequest;
import com.ecl.starlife.models.responses.objectResponse;
import com.ecl.starlife.repository.apiDAO;
import com.ecl.starlife.services.auditService;
import com.ecl.starlife.utils.Settings;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("starlife/rpa/api/v1")

public class acctHolder {

    @Autowired
    public apiDAO dao;

    @Autowired
    public auditService audit;

    static Logger log = Logger.getLogger(acctHolder.class.getName());


    @RequestMapping(value = "/account-holder/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public objectResponse addAccountHolderDetails(HttpServletRequest request, HttpServletResponse response,
                                             @RequestBody customerRequest req) {

        objectResponse resp = new objectResponse();

//
//        try {
//
//            int data = dao.addCustomerDetails(req.getName(), req.getId_number(), req.getDate_of_issue(), req.getDate_of_expiry(), req.getAccount_number(), req.getIdType(), req.getCheque_number(), req.getBank_name(), req.getBot_deployment_id(), req.getTransaction_id(), req.getTeller_id(), req.getPlatform(), req.getCreated_by_username(), req.getCreated_by_email(), req.getCreated_by_teller_id());
//
//            if (data > 0) {
//
//                resp.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
//                resp.setResponseMessage(Settings.getInstance("").getProperty("SUCCESS_CUSTOMER_MSG"));
//
//                //log activity to audit trail
//                audit.customerEntryForm(req.getCreated_by_username(), req.getCreated_by_email(), "/customer/add", req.getPlatform(), req.getIpAddress(), "", req.getTransaction_id(), "SUCCESS");
//
//            } else {
//
//                resp.setResponseCode(Settings.getInstance("").getProperty("FAIL_CUSTOMER_CODE"));
//                resp.setResponseMessage(Settings.getInstance("").getProperty("FAIL_CUSTOMER_MSG"));
//
//                //log activity to audit trail
//                audit.customerEntryForm(req.getCreated_by_username(), req.getCreated_by_email(), "/customer/add", req.getPlatform(), req.getIpAddress(), "", req.getTransaction_id(), "FAILED");
//
//
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            resp.setResponseCode(Settings.getInstance("").getProperty("SERVER_ERROR_CODE"));
//            resp.setResponseMessage(Settings.getInstance("").getProperty("SERVER_ERROR_MSG"));
//
//        }

        return resp;

    }



    @RequestMapping(value = "/account-holder/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public objectResponse updateAccountHolderDetails(HttpServletRequest request, HttpServletResponse response,
                                             @RequestBody customerRequest req) {

        objectResponse resp = new objectResponse();

//
//        try {
//
//            log.info("surname->>>" + req.getSurname());
//            log.info("customerID->>>" + req.getCustomerID());
//
//            int data = dao.updateCustomerDetails(req.getSurname(), req.getMiddleName(), req.getFirstName(), req.getDob(), req.getNationality(), req.getGender(), req.getMaritalStatus(), req.getOccupation(), req.getMobile1(), req.getMobile2(), req.getEmail(), req.getPermanentAddress(), req.getIdType(), req.getIdNumber(), req.getDateOfIssue(), req.getDateOfExpiry(), req.getValidated(), req.getCustomerID());
//
//            if (data > 0) {
//
//                resp.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
//                resp.setResponseMessage(Settings.getInstance("").getProperty("SUCCESS_CUSTOMER_MSG"));
//
//                //log activity to audit trail
//                //audit.customerEntryForm(req.getCreated_by_username(), req.getCreated_by_email(), "/customer/add", req.getPlatform(), req.getIpAddress(), "", req.getTransaction_id(), "SUCCESS");
//
//            } else {
//
//                resp.setResponseCode(Settings.getInstance("").getProperty("FAIL_CUSTOMER_CODE"));
//                resp.setResponseMessage(Settings.getInstance("").getProperty("FAIL_CUSTOMER_MSG"));
//
//                //log activity to audit trail
//                //audit.customerEntryForm(req.getCreated_by_username(), req.getCreated_by_email(), "/customer/add", req.getPlatform(), req.getIpAddress(), "", req.getTransaction_id(), "FAILED");
//
//
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            resp.setResponseCode(Settings.getInstance("").getProperty("SERVER_ERROR_CODE"));
//            resp.setResponseMessage(Settings.getInstance("").getProperty("SERVER_ERROR_MSG"));
//
//        }

        return resp;

    }




    @RequestMapping(value = "/account-holder/fetch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public objectResponse fetchAllAccountHolders(HttpServletRequest request, HttpServletResponse response,
                                        @RequestBody acctHolderRequest req) {

        objectResponse resp = new objectResponse();

        String eventType = "Object Query Events";
        String eventDesc = "Fetching account holder's details";


        try {

            List accountHolderInfo = dao.fetchAccountHolders(req.getCustomerID());
            List coverInfo = dao.fetchCoverInfo(req.getCustomerID());
            List paymentInfo = dao.fetchPaymentInfo(req.getCustomerID());
            List documentInfo = dao.fetchDocumentInfo(req.getCustomerID());
            List productInfo = dao.fetchProductInfo(req.getCustomerID());
            List fetchBeneficiaryInfo = dao.fetchBeneficiaryInfo(req.getCustomerID());

            int count = accountHolderInfo.size();
            if(count > 0){
                resp.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
                resp.setResponseMessage("["+count+"] account holder information was found for scanned record with CUST_ID "+ req.getCustomerID());
                resp.setData("true");

                //log activity to audit trail
                audit.auditLog(req.getRequestBy(), req.getRequestBy(), "/account-holder/fetch", req.getRequestChannel(), req.getRequestIP(), "", "",eventType, eventDesc, "SUCCESS");

            }else{
                resp.setResponseCode(Settings.getInstance("").getProperty("NO_DATA_CODE"));
                resp.setResponseMessage("Sorry, no account holder information was found for scanned record with CUST_ID "+ req.getCustomerID());
                resp.setData("false");

                //log activity to audit trail
                audit.auditLog(req.getRequestBy(), req.getRequestBy(), "/account-holder/fetch", req.getRequestChannel(), req.getRequestIP(), "", "",eventType, eventDesc, "FAILED");

            }

            resp.setCount(count);

            if(accountHolderInfo.size() > 0){
                resp.setAccountHolderInfo(accountHolderInfo.get(0));
            }
            if(coverInfo.size() > 0){
                resp.setCoverInfo(coverInfo.get(0));
            }
            if(paymentInfo.size() > 0){
                resp.setPaymentInfo(paymentInfo.get(0));
            }
            if(documentInfo.size() > 0){
                resp.setDocumentInfo(documentInfo.get(0));
            }
            if(productInfo.size() > 0){
                resp.setProductInfo(productInfo.get(0));
            }
            if(fetchBeneficiaryInfo.size() > 0){
                resp.setBeneficiaryInfo(fetchBeneficiaryInfo);
            }



//
//




        } catch (Exception ex) {
            ex.printStackTrace();

            //log activity to audit trail
            audit.auditLog(req.getRequestBy(), req.getRequestBy(), "/account-holder/fetch", req.getRequestChannel(), req.getRequestIP(), "", "",eventType, eventDesc, "FAILED");

        }

        return resp;

    }

}
