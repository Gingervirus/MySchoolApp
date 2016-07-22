package com.example.amwentzel.myschoolapp.domain.workingHours;

/**
 * Created by Armin on 2016-04-06.
 */
public class WorkingHoursPrincipal extends WorkingHours {
    @Override
    public String handleRequest(String request) {
        if (request.equals("Principal")) {
            return "08H00-15H00";
        } else {
            if (nextWorkingHours != null) {
                return nextWorkingHours.handleRequest(request);
            }
            return "Incorrect Job description";
        }
    }
}