package com.ecl.starlife.controllers;

import com.ecl.starlife.models.requests.customerRequest;
import com.ecl.starlife.models.requests.incidentReportRequest;
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

public class incidentReport {

    @Autowired
    public apiDAO dao;

    @Autowired
    public auditService audit;

    static Logger log = Logger.getLogger(incidentReport.class.getName());


    @RequestMapping(value = "/incident-report/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public objectResponse addIncidentReport(HttpServletRequest request, HttpServletResponse response,
                                             @RequestBody incidentReportRequest req) {

        objectResponse resp = new objectResponse();

        String eventType = "Object Manipulation Events";
        String eventDesc = "Adding new incident report for validation issues";


        try {

            int data = dao.addIncidentReport(req.getCustomerID(), req.getWebProdName(), req.getWebProdCode(), req.getClntCode(), req.getWebClntCode(), req.getWebQuoteCode(), req.getSubject(), req.getMessage(), req.getReportedBy());

            if (data > 0) {

                resp.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
                resp.setResponseMessage(Settings.getInstance("").getProperty("SUCCESS_CUSTOMER_MSG"));

                //log activity to audit trail
                audit.auditLog(req.getRequestBy(), req.getRequestBy(), "/incident-report/add", req.getRequestChannel(), req.getRequestIP(), "", "",eventType, eventDesc, "SUCCESS");

            } else {

                resp.setResponseCode(Settings.getInstance("").getProperty("FAIL_CUSTOMER_CODE"));
                resp.setResponseMessage(Settings.getInstance("").getProperty("FAIL_CUSTOMER_MSG"));

                //log activity to audit trail
                audit.auditLog(req.getRequestBy(), req.getRequestBy(), "/incident-report/add", req.getRequestChannel(), req.getRequestIP(), "", "",eventType, eventDesc, "FAILED");


            }

        } catch (Exception ex) {
            resp.setResponseCode(Settings.getInstance("").getProperty("SERVER_ERROR_CODE"));
            resp.setResponseMessage(Settings.getInstance("").getProperty("SERVER_ERROR_MSG"));

            log.info("Exception->>> " + ex);
        }

        return resp;

    }



    @RequestMapping(value = "/incident-report/fetch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
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
