package com.ecl.starlife.controllers;

import com.ecl.starlife.models.requests.botExtractionRequest;
import com.ecl.starlife.models.responses.objectResponse;
import com.ecl.starlife.repository.apiDAO;
import com.ecl.starlife.utils.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("stanchart/api/v1")

public class botExtraction {

    @Autowired
    public apiDAO dao;


    @RequestMapping(value = "/bot/extraction/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public objectResponse addBotExtraction(HttpServletRequest request, HttpServletResponse response,
                                          @RequestBody botExtractionRequest req) {

        objectResponse resp = new objectResponse();


        try {

            int data = dao.addBotExtraction(req.getName(), req.getId_number(), req.getDate_of_issue(), req.getDate_of_expiry(), req.getBot_deployment_id(), "incomplete", "web");

            if (data > 0) {

                resp.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
                resp.setResponseMessage(Settings.getInstance("").getProperty("SUCCESS_EXTRACTION_MSG"));

            } else {

                resp.setResponseCode(Settings.getInstance("").getProperty("FAIL_EXTRACTION_CODE"));
                resp.setResponseMessage(Settings.getInstance("").getProperty("FAIL_EXTRACTION_MSG"));

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            resp.setResponseCode(Settings.getInstance("").getProperty("SERVER_ERROR_CODE"));
            resp.setResponseMessage(Settings.getInstance("").getProperty("SERVER_ERROR_MSG"));

        }

        return resp;

    }

}
