package com.example.amwentzel.myschoolapp.domain.workingHours;

/**
 * Created by Armin on 2016-04-06.
 */
public class WorkingHoursTeacher extends WorkingHours {
    @Override
    public String handleRequest(String request) {
        if (request.equals("Teacher")) {
            return "08H00-13H00";
        } else {
            if (nextWorkingHours != null) {
                return nextWorkingHours.handleRequest(request);
            }
            return "Incorrect Job description";
        }
    }
}
