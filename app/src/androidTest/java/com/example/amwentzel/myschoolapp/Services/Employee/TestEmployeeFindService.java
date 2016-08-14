package com.example.amwentzel.myschoolapp.Services.Employee;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;


import com.example.amwentzel.myschoolapp.services.Employee.SearchEmployeeService;

import junit.framework.Assert;

/**
 * Created by Armin on 2016-05-12.
 */
public class TestEmployeeFindService extends AndroidTestCase {
    SearchEmployeeService searchService;
    boolean isBound = false;

    public void setUp() throws Exception {
        super.setUp();
        Intent serviceIntent = new Intent(this.getContext(), SearchEmployeeService.class);
        this.getContext().bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE);
    }
    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            SearchEmployeeService.MyLocalBinder binder = (SearchEmployeeService.MyLocalBinder) service;
            searchService = binder.getService();
            try {
                testSearch();
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

    public void testSearch() throws Exception
    {
        Assert.assertEquals("EEE1111", searchService.searchEmployee(new Long(1)));
    }
}
