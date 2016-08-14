package com.example.amwentzel.myschoolapp.factories.employee;

import com.example.amwentzel.myschoolapp.domain.teacher.EmployeeData;
import com.example.amwentzel.myschoolapp.domain.teacher.EmployeeDetails;
import com.example.amwentzel.myschoolapp.factories.salary.SalaryFactory;
import com.example.amwentzel.myschoolapp.factories.workingHours.WorkingHoursFactory;

/**
 * Created by Armin on 2016-05-08.
 */
public class EmployeeFactory {

    public EmployeeData getEmployee(Long empNr, String sarsNr, double salary, String workingHours, String name, String lastName, String dob, String gender, String cell, String job)
    {
        EmployeeDetails details = new EmployeeDetails.Builder()
                .name(name)
                .lastName(lastName)
                .gender(gender)
                .cell(cell)
                .dob(dob)
                .build();
        EmployeeData emp = new EmployeeData.Builder()
                .empNr(empNr)
                .salary(Double.parseDouble(SalaryFactory.getSalary(job)))
                .workingHours(WorkingHoursFactory.getWorkingHours(job))
                .sarsNr(sarsNr)
                .employeeDetails(details)
                .job(job)
                .build();
        return emp;
    }
}
