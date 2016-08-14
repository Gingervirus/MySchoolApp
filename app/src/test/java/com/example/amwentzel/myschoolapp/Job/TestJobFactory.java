package com.example.amwentzel.myschoolapp.Job;


import com.example.armin.tp5.factories.Job.JobFactory;

import org.junit.Test;

/**
 * Created by Armin on 2016-04-06.
 */
public class TestJobFactory
{
        @Test
        public void testJobFactory()
        {
            String job = JobFactory.getJob("TEA123123");
            System.out.println("Your job is :" + job);
        }

}
