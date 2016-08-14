package com.example.amwentzel.myschoolapp.factories.workingHours;

/**
 * Created by Armin on 2016-04-06.
 */
public class WorkingHoursCleaner extends WorkingHours {
    @Override
    public String handleRequest(String request) {
        if (request.equals("Cleaner")) {
            return "08H00-17H00";
        } else {
            if (nextWorkingHours != null) {
                return nextWorkingHours.handleRequest(request);
            }
            return "Incorrect Job description";
        }
    }
}