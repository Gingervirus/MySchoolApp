package com.example.amwentzel.myschoolapp.factories.job;

/**
 * Created by Armin on 2016-04-06.
 */
public class JobSecretary extends Job {
    @Override
    public String handleRequest(String request) {
        if (request.equals("Secretary")) {
            return "Secretary";
        } else {
            if (nextJob != null) {
                return nextJob.handleRequest(request);
            }
            return "That employee Nr does not exist! That person does not have a job";
        }
    }
}