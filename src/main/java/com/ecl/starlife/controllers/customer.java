package com.ecl.starlife.controllers;

import com.ecl.starlife.models.requests.customerRequest;
import com.ecl.starlife.models.responses.objectResponse;
import com.ecl.starlife.repository.apiDAO;
import com.ecl.starlife.utils.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;

import com.ecl.starlife.services.auditService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("starlife/rpa/api/v1")

public class customer {

    @Autowired
    public apiDAO dao;

    @Autowired
    public auditService audit;

    static Logger log = Logger.getLogger(customer.class.getName());


    @RequestMapping(value = "/customer/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public objectResponse addCustomerDetails(HttpServletRequest request, HttpServletResponse response,
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



    @RequestMapping(value = "/customer/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public objectResponse updateCustomerDetails(HttpServletRequest request, HttpServletResponse response,
                                             @RequestBody customerRequest req) {

        objectResponse resp = new objectResponse();

        String eventType = "Object Manipulation Events";
        String eventDesc = "Updating customer onboarding details from scanned document after validation";


        try {

            log.info("surname->>>" + req.getSurname());
            log.info("customerID->>>" + req.getCustomerID());

            int data = dao.updateCustomerDetails(req.getSurname(), req.getMiddleName(), req.getFirstName(), req.getDob(), req.getNationality(), req.getGender(), req.getMaritalStatus(), req.getOccupation(), req.getMobile1(), req.getMobile2(), req.getEmail(), req.getPermanentAddress(), req.getIdType(), req.getIdNumber(), req.getDateOfIssue(), req.getDateOfExpiry(), req.getProposalNumber(), req.getValidated(), req.getValidatedBy(), req.getCustomerID());

            if (data > 0) {

                resp.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
                resp.setResponseMessage(Settings.getInstance("").getProperty("SUCCESS_CUSTOMER_MSG"));

                //log activity to audit trail
                audit.auditLog(req.getRequestBy(), req.getRequestBy(), "/customer/update", req.getRequestChannel(), req.getRequestIP(), "", "",eventType, eventDesc, "SUCCESS");

            } else {

                resp.setResponseCode(Settings.getInstance("").getProperty("FAIL_CUSTOMER_CODE"));
                resp.setResponseMessage(Settings.getInstance("").getProperty("FAIL_CUSTOMER_MSG"));

                //log activity to audit trail
                audit.auditLog(req.getRequestBy(), req.getRequestBy(), "/customer/update", req.getRequestChannel(), req.getRequestIP(), "", "",eventType, eventDesc, "FAILED");


            }

        } catch (Exception ex) {
            ex.printStackTrace();
            resp.setResponseCode(Settings.getInstance("").getProperty("SERVER_ERROR_CODE"));
            resp.setResponseMessage(Settings.getInstance("").getProperty("SERVER_ERROR_MSG"));

            //log activity to audit trail
            audit.auditLog(req.getRequestBy(), req.getRequestBy(), "/customer/update", req.getRequestChannel(), req.getRequestIP(), "", "",eventType, eventDesc, "FAILED");


        }

        return resp;

    }




    @RequestMapping(value = "/customer/fetch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public objectResponse fetchAllCustomers(HttpServletRequest request, HttpServletResponse response,
                                        @RequestBody customerRequest req) {

        objectResponse resp = new objectResponse();

        String eventType = "Object Query Events";
        String eventDesc = "Fetching customer onboarding details";


        try {

            List data = dao.fetchAllCustomers(req.getRequestBy());
            int count = data.size();

            resp.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
            resp.setResponseMessage(Settings.getInstance("").getProperty("SUCCESS_MSG"));
            resp.setCount(count);
            resp.setData(data);

            //log activity to audit trail
            audit.auditLog(req.getRequestBy(), req.getRequestBy(), "/customer/fetch", req.getRequestChannel(), req.getRequestIP(), "", "",eventType, eventDesc, "SUCCESS");



        } catch (Exception ex) {
            ex.printStackTrace();

            //log activity to audit trail
            audit.auditLog(req.getRequestBy(), req.getRequestBy(), "/customer/fetch", req.getRequestChannel(), req.getRequestIP(), "", "",eventType, eventDesc, "FAILED");

        }

        return resp;

    }

}
