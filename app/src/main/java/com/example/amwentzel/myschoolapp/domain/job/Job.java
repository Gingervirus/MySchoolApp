package com.example.amwentzel.myschoolapp.domain.job;

/**
 * Created by Armin on 2016-04-06.
 */
public abstract class Job {
    Job nextJob;

    public void setNextJob( Job nextJob) {
        this.nextJob = nextJob;
    }
    public abstract String handleRequest(String request);
}
