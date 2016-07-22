package com.example.amwentzel.myschoolapp.domain.salary;

/**
 * Created by Armin on 2016-04-06.
 */
public class SalaryCleaner extends Salary {
    @Override
    public String handleRequest(String request) {
        if(request.equals("Cleaner")){
            return "4000";
        }else{
            if (nextSalary!=null) {
                return nextSalary.handleRequest(request);
            }
            return "";
        }
    }
}
