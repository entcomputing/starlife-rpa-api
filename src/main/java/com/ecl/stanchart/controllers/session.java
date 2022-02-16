package com.ecl.stanchart.controllers;


import com.ecl.stanchart.models.clients_Request;
import com.ecl.stanchart.models.requests.loginSessionRequest;
import com.ecl.stanchart.models.responses.objectResponse;
import com.ecl.stanchart.repository.apiDAO;
import com.ecl.stanchart.utils.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("stanchart/api/v1")


public class session {

    @Autowired
    public apiDAO dao;


    @RequestMapping(value = "/user/session/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public objectResponse addLoginSession(HttpServletRequest request, HttpServletResponse response,
                                          @RequestBody loginSessionRequest req) {

        objectResponse resp = new objectResponse();

        //get today's date
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("MMMMM dd, yyyy hh:mm a");
        String loginDate = dateFormat.format(date);


        try {

            int data = dao.addLoginSession(req.getUsername(), req.getEmail(), req.getPlatform(), req.getSession_id(), loginDate, "N/A");

            if (data > 0) {

                resp.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
                resp.setResponseMessage(Settings.getInstance("").getProperty("SUCCESS_SESSION_LOG_MSG"));

            } else {

                resp.setResponseCode(Settings.getInstance("").getProperty("FAIL_SESSION_LOG_CODE"));
                resp.setResponseMessage(Settings.getInstance("").getProperty("FAIL_SESSION_LOG_MSG"));


            }

        } catch (Exception ex) {
            ex.printStackTrace();
            resp.setResponseCode(Settings.getInstance("").getProperty("SERVER_ERROR_CODE"));
            resp.setResponseMessage(Settings.getInstance("").getProperty("SERVER_ERROR_MSG"));

        }

        return resp;

    }


    @RequestMapping(value = "/user/session/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public objectResponse updateLoginSession(HttpServletRequest request, HttpServletResponse response,
                                             @RequestBody loginSessionRequest req) {

        objectResponse resp = new objectResponse();

        //get today's date
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("MMMMM dd, yyyy hh:mm a");
        String logout_date = dateFormat.format(date);


        try {

            int data = dao.updateLoginSession(logout_date, req.getSession_id());

            if (data > 0) {

                resp.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
                resp.setResponseMessage(Settings.getInstance("").getProperty("SUCCESS_SESSION_LOG_MSG"));

            } else {

                resp.setResponseCode(Settings.getInstance("").getProperty("FAIL_SESSION_LOG_CODE"));
                resp.setResponseMessage(Settings.getInstance("").getProperty("FAIL_SESSION_LOG_MSG"));

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            resp.setResponseCode(Settings.getInstance("").getProperty("SERVER_ERROR_CODE"));
            resp.setResponseMessage(Settings.getInstance("").getProperty("SERVER_ERROR_MSG"));

        }

        return resp;

    }


    @RequestMapping(value = "/clients", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public objectResponse getClientsWeb(HttpServletRequest request, HttpServletResponse response,
                                        @RequestBody clients_Request req) {


        objectResponse resp = new objectResponse();

        try {

            List data = dao.getAllClients(req.getCountry());

            resp.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
            resp.setResponseMessage(Settings.getInstance("").getProperty("SUCCESS_MSG"));
            resp.setData(data);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return resp;

    }

}



