package com.example.amwentzel.myschoolapp.Services.Employee;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.amwentzel.myschoolapp.domain.employee.EmployeeData;
import com.example.amwentzel.myschoolapp.factories.employee.EmployeeFactory;
import com.example.amwentzel.myschoolapp.services.Employee.InsertEmployeeService;

import junit.framework.Assert;

/**
 * Created by Armin on 2016-05-12.
 */
public class TestEmployeeAddService extends AndroidTestCase {
    private InsertEmployeeService insertService;
    boolean isBound = false;

    public void setUp() throws Exception {
        super.setUp();
        Intent serviceIntent = new Intent(this.getContext(), InsertEmployeeService.class);
        this.getContext().bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE);
    }
    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            InsertEmployeeService.MyLocalBinder binder = (InsertEmployeeService.MyLocalBinder) service;
            insertService = binder.getService();

            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

        public void testInsert() throws Exception
        {
            try {
                Thread.sleep(1000);
                EmployeeFactory emp = new EmployeeFactory();
                EmployeeData ee = emp.getEmployee(null, "2314565346", 15000.00, "08H00-13H00", "Armin", "Wentzel", "21/06/1995", "Male", "0764805005", "Teacher");

                String isActivated = insertService.insertEmployee(ee);
                Assert.assertEquals("ACTIVATED", isActivated);

               // Assert.assertNotNull();
            }catch (Exception e){
                e.getMessage();
            }
        }
}

