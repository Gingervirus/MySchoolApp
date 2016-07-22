package com.example.amwentzel.myschoolapp.factories.job;


import com.example.amwentzel.myschoolapp.domain.job.Job;
import com.example.amwentzel.myschoolapp.domain.job.JobCleaner;
import com.example.amwentzel.myschoolapp.domain.job.JobPrincipal;
import com.example.amwentzel.myschoolapp.domain.job.JobSecretary;
import com.example.amwentzel.myschoolapp.domain.job.JobTeacher;

/**
 * Created by Armin on 2016-04-06.
 */
public class JobFactory {
    public static String getJob(String employeeID)
    {
        String jobID = employeeID.substring(0,3);
        System.out.println(jobID);
        Job chain = setUpChain();
        return chain.handleRequest(jobID);
    }

    public static Job setUpChain()
    {
        Job c = new JobCleaner();
        Job s= new JobSecretary();
        Job t = new JobTeacher();
        Job p = new JobPrincipal();
        c.setNextJob(s);
        s.setNextJob(t);
        t.setNextJob(p);
        return c;
    }
}
