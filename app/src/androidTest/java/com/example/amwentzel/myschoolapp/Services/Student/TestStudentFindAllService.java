package com.example.amwentzel.myschoolapp.Services.Student;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.armin.tp5.domain.Student.StudentData;
import com.example.armin.tp5.services.Student.FindAllStudentService;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Armin on 2016-05-12.
 */
public class TestStudentFindAllService extends AndroidTestCase {
    FindAllStudentService findAllService;
    boolean isBound = false;

    public void setUp() throws Exception {
        super.setUp();
        Intent serviceIntent = new Intent(this.getContext(), FindAllStudentService.class);
        this.getContext().bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE);
    }
    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            FindAllStudentService.MyLocalBinder binder = (FindAllStudentService.MyLocalBinder) service;
            findAllService = binder.getService();
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
        Set<StudentData> stud;
        stud = findAllService.findAllStudent();
        Assert.assertNotNull(stud);
    }
}
