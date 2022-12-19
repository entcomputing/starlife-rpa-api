package com.ecl.starlife.controllers;

import com.ecl.starlife.models.requests.authRequest;
import com.ecl.starlife.models.requests.beneficiaryRequest;
import com.ecl.starlife.models.responses.authResponse;
import com.ecl.starlife.models.responses.beneficiaryResponse;
import com.ecl.starlife.models.responses.objectResponse;
import com.ecl.starlife.repository.apiDAO;
import com.ecl.starlife.services.auditService;
import com.ecl.starlife.utils.Settings;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import jdk.jpackage.internal.Log;
import org.json.JSONArray;
import org.json.JSONObject;
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

public class auth {

    @Autowired
    public apiDAO dao;

    @Autowired
    public auditService audit;

    @Autowired
    private ObjectMapper objectMapper;

    static Logger log = Logger.getLogger(auth.class.getName());


    @RequestMapping(value = "/auth/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public authResponse login(HttpServletRequest request, HttpServletResponse response,
                              @RequestBody authRequest req) {

        authResponse resp = new authResponse();

        String currentPassword = "";
        String userID = "";
        String email = "";
        String phone = "";
        String firstName = "";
        String lastName = "";
        String branch = "";
        String role = "";

        try {

            List user = dao.fetchUserByEmail(req.getEmail());
            int count = user.size();

            if (count > 0) {
                Gson gson = new Gson();
                String userProfile = gson.toJson(user);
                //log.info("userProfile ->>> " + userProfile);

                try {
                    JSONArray ja = new JSONArray(userProfile);
                    JSONObject jo = null;

                    for (int i = 0; i < ja.length(); i++) {
                        jo = ja.getJSONObject(i);

                        if(jo.has("PASSWORD")) {currentPassword = jo.getString("PASSWORD");}
                        if(jo.has("USER_ID")) {userID = jo.getString("USER_ID");}
                        if(jo.has("EMAIL")) {email = jo.getString("EMAIL");}
                        if(jo.has("PHONE")) {phone = jo.getString("PHONE");}
                        if(jo.has("FIRST_NAME")) {firstName = jo.getString("FIRST_NAME");}
                        if(jo.has("LAST_NAME")) {lastName = jo.getString("LAST_NAME");}
                        if(jo.has("BRANCH")){ branch = jo.getString("BRANCH");}
                        if(jo.has("ROLE")){  role = jo.getString("ROLE");}

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                //log.info("currentPassword ->>> " + currentPassword);

                //JSONObject profile = new JSONObject();
                ObjectNode profile = objectMapper.createObjectNode();
                profile.put("userID", userID);
                profile.put("email", email);
                profile.put("phone", phone);
                profile.put("firstName", firstName);
                profile.put("lastName", lastName);
                profile.put("branch", branch);
                profile.put("role", role);

                if(req.getPassword().equals(currentPassword)){
                    resp.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
                    resp.setResponseMessage("Login successful");
                    resp.setProfile(profile);

                }else{
                    resp.setResponseCode(Settings.getInstance("").getProperty("FAIL_CUSTOMER_CODE"));
                    resp.setResponseMessage("Wrong user email or password");

                }


            }else {
                resp.setResponseCode(Settings.getInstance("").getProperty("FAIL_CUSTOMER_CODE"));
                resp.setResponseMessage("Wrong user email or password");

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            resp.setResponseCode(Settings.getInstance("").getProperty("SERVER_ERROR_CODE"));
            resp.setResponseMessage(Settings.getInstance("").getProperty("SERVER_ERROR_MSG"));

        }

        return resp;
    }


    @RequestMapping(value = "/auth/change-password", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public authResponse changePassword(HttpServletRequest request, HttpServletResponse response,
                                                        @RequestBody authRequest req) {

        authResponse resp = new authResponse();

        String eventType = "Object Manipulation Events";
        String eventDesc = "Changing user password";

        String currentPassword = "";


        //validate current password
        try {
            List user = dao.fetchUserByEmail(req.getEmail());
            int count = user.size();

            if (count > 0) {
                Gson gson = new Gson();
                String userProfile = gson.toJson(user);

                try {
                    JSONArray ja = new JSONArray(userProfile);
                    JSONObject jo = null;

                    for (int i = 0; i < ja.length(); i++) {
                        jo = ja.getJSONObject(i);

                        currentPassword = jo.getString("PASSWORD");

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


                if(req.getCurrentPassword().equals(currentPassword)){

                    //change password
                    try {
                        int data = dao.changePassword(req.getNewPassword(), req.getEmail());
                        if (data > 0) {

                            resp.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
                            resp.setResponseMessage("Password changed successfully");

                            //log activity to audit trail
                            audit.auditLog(req.getRequestBy(), req.getRequestBy(), "/auth/change-password", req.getRequestChannel(), req.getRequestIP(), "", "",eventType, eventDesc, "SUCCESS");

                        } else {

                            resp.setResponseCode(Settings.getInstance("").getProperty("FAIL_CUSTOMER_CODE"));
                            resp.setResponseMessage("Failed to change password");

                            //log activity to audit trail
                            audit.auditLog(req.getRequestBy(), req.getRequestBy(), "/auth/change-password", req.getRequestChannel(), req.getRequestIP(), "", "",eventType, eventDesc, "FAILED");


                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        resp.setResponseCode(Settings.getInstance("").getProperty("SERVER_ERROR_CODE"));
                        resp.setResponseMessage(Settings.getInstance("").getProperty("SERVER_ERROR_MSG"));

                        //log activity to audit trail
                        audit.auditLog(req.getRequestBy(), req.getRequestBy(), "/auth/change-password", req.getRequestChannel(), req.getRequestIP(), "", "",eventType, eventDesc, "FAILED");


                    }

                }else{
                    resp.setResponseCode(Settings.getInstance("").getProperty("FAIL_CUSTOMER_CODE"));
                    resp.setResponseMessage("Wrong user email or current password");

                }


            }else {
                resp.setResponseCode(Settings.getInstance("").getProperty("FAIL_CUSTOMER_CODE"));
                resp.setResponseMessage("Wrong user email or current password");

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            resp.setResponseCode(Settings.getInstance("").getProperty("SERVER_ERROR_CODE"));
            resp.setResponseMessage(Settings.getInstance("").getProperty("SERVER_ERROR_MSG"));

        }



        return resp;

    }


    @RequestMapping(value = "/auth/request-token", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public authResponse requestToken(HttpServletRequest request, HttpServletResponse response,
                                       @RequestBody authRequest req) {

        authResponse resp = new authResponse();

        String eventType = "Object Query Events";
        String eventDesc = "Requesting for password reset token";


        return resp;

    }




}
