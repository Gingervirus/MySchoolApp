package com.example.amwentzel.myschoolapp.factories.salary;


import com.example.amwentzel.myschoolapp.domain.salary.Salary;
import com.example.amwentzel.myschoolapp.domain.salary.SalaryCleaner;
import com.example.amwentzel.myschoolapp.domain.salary.SalaryPrincipal;
import com.example.amwentzel.myschoolapp.domain.salary.SalarySecretary;
import com.example.amwentzel.myschoolapp.domain.salary.SalaryTeacher;

/**
 * Created by Armin on 2016-04-06.
 */
public class SalaryFactory {
    public static String getSalary(String employeeID)
    {
        Salary chain;
        chain = setUpChain();
        return chain.handleRequest(employeeID);
    }

    public static Salary setUpChain()
    {
        Salary c = new SalaryCleaner();
        Salary s= new SalarySecretary();
        Salary t = new SalaryTeacher();
        Salary p = new SalaryPrincipal();
        c.setNextSalary(s);
        s.setNextSalary(t);
        t.setNextSalary(p);
        return c;
    }
}
