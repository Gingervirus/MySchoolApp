package com.example.amwentzel.myschoolapp.Employee;

import com.example.armin.tp5.domain.Employee.EmployeeData;
import com.example.armin.tp5.factories.Employee.EmployeeFactory;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Armin on 2016-04-06.
 */
public class TestEmployee{
    @Test
    public void teste() throws Exception {

        EmployeeFactory emp = new EmployeeFactory();
        EmployeeData e = emp.getEmployee(null, "2314565346", 15000.00, "08H00-13H00", "Armin", "Wentzel", "21/06/1995", "Male", "0764805005", "Teacher");

        Assert.assertEquals(e.getDetails().getDob(), "21/06/1995");
        Assert.assertEquals(e.getDetails().getName(), "Armin");
        Assert.assertEquals(e.getEmpNr(), null);
        Assert.assertEquals(e.getDetails().getLastName(), "Wentzel");
        Assert.assertEquals(e.getDetails().getGender(), "Male");
        Assert.assertEquals(e.getSalary(), 15000.00);
        Assert.assertEquals(e.getWorkingHours(), "08H00-13H00");
        Assert.assertEquals(e.getSarsNr(), "2314565346");
        Assert.assertEquals(e.getDetails().getCell(), "0764805005");

        EmployeeData person = new EmployeeData.Builder()
                .copy(e)
                .salary(16000.00)
                .build();

        Assert.assertEquals(person.getSalary(), 16000.00);
    }
}
