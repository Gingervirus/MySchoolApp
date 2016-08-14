package com.example.amwentzel.myschoolapp.factories.salary;

/**
 * Created by Armin on 2016-04-06.
 */
public class SalaryPrincipal extends Salary {
    @Override
    public String handleRequest(String request) {
        if (request.equals("Principal")) {
            return "30000.00";
        } else {
            if (nextSalary != null) {
                return nextSalary.handleRequest(request);
            }
            return "Salary does not exist";
        }
    }
}