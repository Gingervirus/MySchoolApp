package com.example.amwentzel.myschoolapp.Services.Employee;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.amwentzel.myschoolapp.domain.teacher.EmployeeData;
import com.example.amwentzel.myschoolapp.domain.teacher.EmployeeDetails;
import com.example.amwentzel.myschoolapp.factories.salary.SalaryFactory;
import com.example.amwentzel.myschoolapp.factories.workingHours.WorkingHoursFactory;
import com.example.amwentzel.myschoolapp.services.Employee.DeleteEmployeeService;

import junit.framework.Assert;

/**
 * Created by Armin on 2016-05-12.
 */
public class TestEmployeeDeleteService extends AndroidTestCase {
    DeleteEmployeeService deleteService;
    boolean isBound = false;

    public void setUp() throws Exception {
        super.setUp();
        Intent serviceIntent = new Intent(this.getContext(), DeleteEmployeeService.class);
        this.getContext().bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE);
    }
    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DeleteEmployeeService.MyLocalBinder binder = (DeleteEmployeeService.MyLocalBinder) service;
            deleteService = binder.getService();
            try {
                testDelete();
            } catch (Exception e) {
                e.printStackTrace();
            }
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void testDelete() throws Exception
    {
        EmployeeDetails details = new EmployeeDetails.Builder()
                .name("Armin")
                .lastName("Wentzel")
                .gender("Male")
                .cell("0764805005")
                .dob("21/06/1995")
                .build();
        EmployeeData createEntity = new EmployeeData.Builder()
                .salary(Double.parseDouble(SalaryFactory.getSalary("TEA11111")))
                .workingHours(WorkingHoursFactory.getWorkingHours("Teacher"))
                .sarsNr("2314565346")
                .employeeDetails(details)
                .build();
        EmployeeData deleteEntity = new EmployeeData.Builder()
                .copy(createEntity)
                .workingHours("12H00-17H00")
                .build();

        Assert.assertNull(deleteService.deleteEmployee(deleteEntity));
    }
}
