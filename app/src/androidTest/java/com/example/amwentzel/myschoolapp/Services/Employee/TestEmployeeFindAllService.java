package com.example.amwentzel.myschoolapp.Services.Employee;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;


import com.example.amwentzel.myschoolapp.domain.teacher.EmployeeData;
import com.example.amwentzel.myschoolapp.services.Employee.FindAllEmployeeService;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Armin on 2016-05-12.
 */
public class TestEmployeeFindAllService extends AndroidTestCase {
    FindAllEmployeeService FindAllService;
    boolean isBound = false;

    public void setUp() throws Exception {
        super.setUp();
        Intent serviceIntent = new Intent(this.getContext(), FindAllEmployeeService.class);
        this.getContext().bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE);
    }
    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            FindAllEmployeeService.MyLocalBinder binder = (FindAllEmployeeService.MyLocalBinder) service;
            FindAllService = binder.getService();
            try {
                testFindAll();
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

    public void testFindAll() throws Exception
    {
        Set<EmployeeData> emp;
        emp = FindAllService.findAllEmployee();
        Assert.assertNotNull(emp);
    }
}
