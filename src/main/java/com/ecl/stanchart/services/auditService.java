package com.ecl.stanchart.services;

import com.ecl.stanchart.repository.apiDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@Service
public class auditService {

    private String eventType;
    private String eventDesc;
    private String execDate;
    private String execTime;
    private String eventResult;
    private String userRole = "Default";
    private String usedPriviledge = "Default";


    @Autowired
    public apiDAO dao;



    public boolean customerEntryForm(String username, String email, String requestEndPoint, String requestClient, String ipAddress, String hostname, String transactionID, String eventResult){

        //get current date
        Date currentDate = Calendar.getInstance().getTime();
        DateFormat currentDateFormat = new SimpleDateFormat("MMMMM dd, yyyy");
        execDate = currentDateFormat.format(currentDate);

        //get current time
        Date currentTime = Calendar.getInstance().getTime();
        DateFormat currentTimeFormat = new SimpleDateFormat("hh:mm a");
        execTime = currentTimeFormat.format(currentTime);

        eventType = "Object Manipulation Events";
        eventDesc = "Created new customer entry details for scanned documents";

        int result = dao.customerEntryFormAudit(username, email, eventType, eventDesc, execDate, execTime, eventResult, transactionID, userRole, usedPriviledge, requestEndPoint, requestClient, ipAddress, hostname);
        if (result > 0) {
            return true;
        }else{
            return false;
        }


    }


}
