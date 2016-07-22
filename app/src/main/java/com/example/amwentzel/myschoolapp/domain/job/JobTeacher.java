package com.example.amwentzel.myschoolapp.domain.job;

/**
 * Created by Armin on 2016-04-06.
 */
public class JobTeacher extends Job {
    @Override
    public String handleRequest(String request) {
        if (request.equals("Teacher")) {
            return "teacher";
        } else {
            if (nextJob != null) {
                return nextJob.handleRequest(request);
            }
            return "That employee Nr does not exist! That person does not have a job";
        }
    }
}