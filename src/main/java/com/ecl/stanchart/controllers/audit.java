package com.ecl.stanchart.controllers;


import com.ecl.stanchart.models.requests.auditRequest;
import com.ecl.stanchart.models.requests.customerRequest;
import com.ecl.stanchart.models.responses.objectResponse;
import com.ecl.stanchart.repository.apiDAO;
import com.ecl.stanchart.utils.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("stanchart/api/v1")

public class audit {

    @Autowired
    public apiDAO dao;



    @RequestMapping(value = "/audit/fetch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public objectResponse fetchAllAudit(HttpServletRequest request, HttpServletResponse response,
                                            @RequestBody auditRequest req) {

        objectResponse resp = new objectResponse();

        try {

            List data = dao.fetchAllAudit();
            int count = data.size();

            resp.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
            resp.setResponseMessage(Settings.getInstance("").getProperty("SUCCESS_MSG"));
            resp.setCount(count);
            resp.setData(data);


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return resp;

    }


}
