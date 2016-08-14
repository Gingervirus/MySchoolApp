package com.example.amwentzel.myschoolapp.factories.salary;

/**
 * Created by Armin on 2016-04-06.
 */
public class SalaryTeacher extends Salary {
    @Override
    public String handleRequest(String request) {
        if (request.equals("Teacher")) {
            System.out.println(15000);
            return "15000.00";
        } else {
            if (nextSalary != null) {
                return nextSalary.handleRequest(request);
            }
            return "Salary does not exist";
        }
    }
}