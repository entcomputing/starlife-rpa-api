package com.ecl.starlife.controllers;


import com.ecl.starlife.models.requests.botExtractionRequest;
import com.ecl.starlife.models.responses.objectResponse;
import com.ecl.starlife.repository.apiDAO;
import com.ecl.starlife.utils.Settings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.svix.exceptions.ApiException;
import com.svix.models.MessageIn;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.svix.Svix;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.json.JSONObject;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("stanchart/api/v1")

public class webhook {

    @Autowired
    public apiDAO dao;

    @Autowired
    RestTemplate restTemplate;


    @RequestMapping(value = "/webhook/send", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public objectResponse webhookSend(HttpServletRequest request, HttpServletResponse response,
                                           @RequestBody botExtractionRequest req) throws ApiException {

        objectResponse resp = new objectResponse();

        try {

//            //prepare webhook application
//            Svix svix = new Svix("testsk_vkWBvYkG79oxNU7KNfytI5bqD_qYXFdk");
//            ApplicationOut app = svix.getApplication().create(new ApplicationIn().name("StanChart RPA"));
//            System.out.println("Svix Application ID: " + app.getId());


            //prepare json object for request payload
            JSONObject obj = new JSONObject();
            obj.put("name", req.getName());
            obj.put("id_number", req.getId_number());
            obj.put("date_of_issue", req.getDate_of_issue());
            obj.put("date_of_expiry", req.getDate_of_expiry());
            obj.put("bot_deployment_id", req.getBot_deployment_id());
            obj.put("platform", req.getPlatform());


//            HttpHeaders headers = new HttpHeaders();
//            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//            HttpEntity<Object> entity = new HttpEntity<Object>(obj.toString(),headers);
//
//            restTemplate.exchange(
//                    "https://webhook.site/50e89f2d-fa96-43cd-a23b-6d86f699f3fa", HttpMethod.POST, entity, String.class).getBody();




            //send webhook message
            Svix svix = new Svix("testsk_vkWBvYkG79oxNU7KNfytI5bqD_qYXFdk");
            svix.getMessage().create("app_1zhQp6AS2HtevvLqHyCcfh1UnPj",
                    new MessageIn()
                            .eventType("extraction.complete")
                            .eventId(UUID.randomUUID().toString().replace("-", "").toUpperCase())
                            .payload(obj.toString()));



            resp.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
            resp.setResponseMessage(Settings.getInstance("").getProperty("SUCCESS_EXTRACTION_MSG"));



        } catch (Exception ex) {
            ex.printStackTrace();
            resp.setResponseCode(Settings.getInstance("").getProperty("SERVER_ERROR_CODE"));
            resp.setResponseMessage(Settings.getInstance("").getProperty("SERVER_ERROR_MSG"));

        }

        return resp;

    }


    @RequestMapping(value = "/webhook/receive", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public objectResponse webhookReceive(HttpServletRequest request, HttpServletResponse response,
                                  @RequestBody botExtractionRequest req) throws ApiException {

        objectResponse resp = new objectResponse();

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("MMMMM dd, yyyy hh:mm a");
        String loginDate = dateFormat.format(date);

        Gson gson = new GsonBuilder().serializeNulls().create();


        try {

//            System.out.println("webhook received at " + loginDate);
//            System.out.println("webhook header names: " +  request.getHeaderNames());
//            System.out.println("webhook header-svix-id: " +  request.getHeader("svix-id"));
//            System.out.println("webhook header-svix-timestamp: " +  request.getHeader("svix-timestamp"));
//            System.out.println("webhook header-svix-signature: " +  request.getHeader("svix-signature"));
//            System.out.println("webhook body: " +  request.getInputStream());

            System.out.println("webhook body(name): " +  req.getName());
            System.out.println("webhook body(id_number): " +  req.getId_number());
            System.out.println("webhook body(date_of_issue): " +  req.getDate_of_issue());
            System.out.println("webhook body(date_of_expiry): " +  req.getDate_of_expiry());


            //prepare json object for data response
            JSONObject obj = new JSONObject();
            obj.put("name", req.getName());
            obj.put("id_number", req.getId_number());
            obj.put("date_of_issue", req.getDate_of_issue());
            obj.put("date_of_expiry", req.getDate_of_expiry());
            obj.put("bot_deployment_id", req.getBot_deployment_id());
            obj.put("platform", req.getPlatform());



//            resp.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
//            resp.setResponseMessage(Settings.getInstance("").getProperty("SUCCESS_MSG"));
//            resp.setData(obj.toString());


            int delete_data = dao.deleteWebhookData();

            if(delete_data > 0){


                int add_data = dao.addWebhookData(req.getName(), req.getId_number(), req.getDate_of_issue(), req.getDate_of_expiry(), req.getBot_deployment_id(), "incomplete", "bot");

                if (add_data > 0) {

                    resp.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
                    resp.setResponseMessage(Settings.getInstance("").getProperty("SUCCESS_EXTRACTION_MSG"));
                    resp.setData(obj.toString());

                } else {

                    resp.setResponseCode(Settings.getInstance("").getProperty("FAIL_EXTRACTION_CODE"));
                    resp.setResponseMessage(Settings.getInstance("").getProperty("FAIL_EXTRACTION_MSG"));

                }

            }



        } catch (Exception ex) {
            ex.printStackTrace();
            resp.setResponseCode(Settings.getInstance("").getProperty("SERVER_ERROR_CODE"));
            resp.setResponseMessage(Settings.getInstance("").getProperty("SERVER_ERROR_MSG"));

        }

        return resp;

    }














    public void handleWebhookResponse() throws JSONException {

      //  RestTemplate restTemplate = new RestTemplate();

//
//        String fooResourceUrl
//                = "https://d3d9-41-218-213-197.ngrok.io/stanchart/api/v1/webhook/receive";
//        ResponseEntity<String> response
//                = restTemplate.getForEntity(fooResourceUrl, String.class);
//        //assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));




        String payload = "";
        HttpEntity entity = null;
        ResponseEntity<Object> responses = null;

        HttpHeaders headers = new HttpHeaders();
        Charset utf8 = Charset.forName("UTF-8");
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject obj = new JSONObject();
        obj.put("name", "");

        payload = obj.toString();

        entity = new HttpEntity<String>("", headers);

        responses = restTemplate.exchange(
                "https://d3d9-41-218-213-197.ngrok.io/stanchart/api/v1/webhook/receive", HttpMethod.POST, entity, Object.class);


        System.out.println("webhook response code: " + responses.getStatusCode().value());
        System.out.println("webhook body body body: " + responses.getBody().toString());


//                Gson gsons = new GsonBuilder().serializeNulls().create();
//                String item = gsons.toJson(responses.getBody());
//
//        System.out.println("webhook body item : " + item.toString());






    }



    @RequestMapping(value = "/webhook/response", method = RequestMethod.POST)
    public String webhookResponse(@RequestBody objectResponse response) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<Object>(response,headers);


//        Object[] customerJson = restTemplate.getForObject("https://d3d9-41-218-213-197.ngrok.io/stanchart/api/v1/webhook/receive", Object[].class);
//        System.out.println(customerJson.toString());
//
//        return  customerJson.toString();

        return restTemplate.exchange(
                "https://webhook.site/50e89f2d-fa96-43cd-a23b-6d86f699f3fa", HttpMethod.POST, entity, String.class).getBody();




    }


}