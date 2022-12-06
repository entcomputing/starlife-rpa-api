package com.ecl.starlife.controllers;


import com.ecl.starlife.models.requests.auditRequest;
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

public class audit {

    @Autowired
    public apiDAO dao;

    @Autowired
    public auditService audit;

    static Logger log = Logger.getLogger(customer.class.getName());




    @RequestMapping(value = "/audit/fetch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public objectResponse fetchAllAudit(HttpServletRequest request, HttpServletResponse response,
                                            @RequestBody auditRequest req) {

        objectResponse resp = new objectResponse();

        String eventType = "Object Query Events";
        String eventDesc = "Fetching audit logs";


        try {

            List data = dao.fetchAllAudit();
            int count = data.size();

            resp.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
            resp.setResponseMessage(Settings.getInstance("").getProperty("SUCCESS_MSG"));
            resp.setCount(count);
            resp.setData(data);

            //log activity to audit trail
            audit.auditLog(req.getRequestBy(), req.getRequestBy(), "/audit/fetch", req.getRequestChannel(), req.getRequestIP(), "", "",eventType, eventDesc, "SUCCESS");


        } catch (Exception ex) {
            ex.printStackTrace();

            //log activity to audit trail
            audit.auditLog(req.getRequestBy(), req.getRequestBy(), "/audit/fetch", req.getRequestChannel(), req.getRequestIP(), "", "",eventType, eventDesc, "FAILED");

        }

        return resp;

    }


}
