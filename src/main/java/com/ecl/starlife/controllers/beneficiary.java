package com.ecl.starlife.controllers;

import com.ecl.starlife.models.requests.beneficiaryRequest;
import com.ecl.starlife.models.requests.customerRequest;
import com.ecl.starlife.models.responses.beneficiaryResponse;
import com.ecl.starlife.models.responses.objectResponse;
import com.ecl.starlife.repository.apiDAO;
import com.ecl.starlife.services.auditService;
import com.ecl.starlife.utils.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("starlife/rpa/api/v1")

public class beneficiary {

    @Autowired
    public apiDAO dao;

    @Autowired
    public auditService audit;

    static Logger log = Logger.getLogger(beneficiary.class.getName());


    @RequestMapping(value = "/beneficiary/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public beneficiaryResponse addBeneficiaryDetails(HttpServletRequest request, HttpServletResponse response,
                                             @RequestBody beneficiaryRequest req) {

        beneficiaryResponse resp = new beneficiaryResponse();

        String eventType = "Object Manipulation Events";
        String eventDesc = "Adding new beneficiary after validation";


        try {

            int data = dao.addBeneficiaryDetails(req.getBeneficiarySurname(), req.getBeneficiaryOthernames(), req.getBeneficiaryDOB(), req.getBeneficiaryRelationship(), req.getBeneficiaryPercentage(), req.getBeneficiaryContact(), req.getCustomerID());

            if (data > 0) {

                resp.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
                resp.setResponseMessage("New beneficiary added successfully");

                //log activity to audit trail
                audit.auditLog(req.getRequestBy(), req.getRequestBy(), "/beneficiary/add", req.getRequestChannel(), req.getRequestIP(), "", "",eventType, eventDesc, "SUCCESS");

            } else {

                resp.setResponseCode(Settings.getInstance("").getProperty("FAIL_CUSTOMER_CODE"));
                resp.setResponseMessage("Failed to add new beneficiary");

                //log activity to audit trail
                audit.auditLog(req.getRequestBy(), req.getRequestBy(), "/beneficiary/add", req.getRequestChannel(), req.getRequestIP(), "", "",eventType, eventDesc, "FAILED");


            }

        } catch (Exception ex) {
            ex.printStackTrace();
            resp.setResponseCode(Settings.getInstance("").getProperty("SERVER_ERROR_CODE"));
            resp.setResponseMessage(Settings.getInstance("").getProperty("SERVER_ERROR_MSG"));

            //log activity to audit trail
            audit.auditLog(req.getRequestBy(), req.getRequestBy(), "/beneficiary/add", req.getRequestChannel(), req.getRequestIP(), "", "",eventType, eventDesc, "FAILED");


        }

        return resp;

    }



    @RequestMapping(value = "/beneficiary/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public beneficiaryResponse updateBeneficiaryDetails(HttpServletRequest request, HttpServletResponse response,
                                             @RequestBody beneficiaryRequest req) {

        beneficiaryResponse resp = new beneficiaryResponse();

        String eventType = "Object Manipulation Events";
        String eventDesc = "Updating beneficiary details from scanned document after validation";


        try {

            int data = dao.updateBeneficiaryDetails(req.getBeneficiarySurname(), req.getBeneficiaryOthernames(), req.getBeneficiaryDOB(), req.getBeneficiaryRelationship(), req.getBeneficiaryPercentage(), req.getBeneficiaryContact(), req.getValidated(), req.getValidatedBy(), req.getBeneficiaryID());

            if (data > 0) {

                resp.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
                resp.setResponseMessage("Beneficiary details updated successfully");

                //log activity to audit trail
                audit.auditLog(req.getRequestBy(), req.getRequestBy(), "/beneficiary/update", req.getRequestChannel(), req.getRequestIP(), "", "",eventType, eventDesc, "SUCCESS");

            } else {

                resp.setResponseCode(Settings.getInstance("").getProperty("FAIL_CUSTOMER_CODE"));
                resp.setResponseMessage("Failed to update beneficiary details");

                //log activity to audit trail
                audit.auditLog(req.getRequestBy(), req.getRequestBy(), "/beneficiary/update", req.getRequestChannel(), req.getRequestIP(), "", "",eventType, eventDesc, "FAILED");


            }

        } catch (Exception ex) {
            ex.printStackTrace();
            resp.setResponseCode(Settings.getInstance("").getProperty("SERVER_ERROR_CODE"));
            resp.setResponseMessage(Settings.getInstance("").getProperty("SERVER_ERROR_MSG"));

            //log activity to audit trail
            audit.auditLog(req.getRequestBy(), req.getRequestBy(), "/beneficiary/update", req.getRequestChannel(), req.getRequestIP(), "", "",eventType, eventDesc, "FAILED");


        }

        return resp;

    }





    @RequestMapping(value = "/beneficiary/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public beneficiaryResponse deleteBeneficiaryDetails(HttpServletRequest request, HttpServletResponse response,
                                                        @RequestBody beneficiaryRequest req) {

        beneficiaryResponse resp = new beneficiaryResponse();

        String eventType = "Object Manipulation Events";
        String eventDesc = "Deleting beneficiary details from scanned document after validation";


        try {

            int data = dao.deleteBeneficiaryDetails(req.getBeneficiaryID());

            if (data > 0) {

                resp.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
                resp.setResponseMessage("Beneficiary details deleted successfully");

                //log activity to audit trail
                audit.auditLog(req.getRequestBy(), req.getRequestBy(), "/beneficiary/delete", req.getRequestChannel(), req.getRequestIP(), "", "",eventType, eventDesc, "SUCCESS");

            } else {

                resp.setResponseCode(Settings.getInstance("").getProperty("FAIL_CUSTOMER_CODE"));
                resp.setResponseMessage("Failed to deleted beneficiary details");

                //log activity to audit trail
                audit.auditLog(req.getRequestBy(), req.getRequestBy(), "/beneficiary/delete", req.getRequestChannel(), req.getRequestIP(), "", "",eventType, eventDesc, "FAILED");


            }

        } catch (Exception ex) {
            ex.printStackTrace();
            resp.setResponseCode(Settings.getInstance("").getProperty("SERVER_ERROR_CODE"));
            resp.setResponseMessage(Settings.getInstance("").getProperty("SERVER_ERROR_MSG"));

            //log activity to audit trail
            audit.auditLog(req.getRequestBy(), req.getRequestBy(), "/beneficiary/delete", req.getRequestChannel(), req.getRequestIP(), "", "",eventType, eventDesc, "FAILED");


        }

        return resp;

    }




}
