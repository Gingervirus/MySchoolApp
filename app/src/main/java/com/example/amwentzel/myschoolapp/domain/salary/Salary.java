package com.example.amwentzel.myschoolapp.domain.salary;

/**
 * Created by Armin on 2016-04-06.
 */
public abstract class Salary {
    Salary nextSalary;

    public void setNextSalary( Salary nextSalary) {
        this.nextSalary = nextSalary;
    }
    public abstract String handleRequest(String request);
}
