package com.example.amwentzel.myschoolapp.Services.Student;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.armin.tp5.domain.Student.StudentData;
import com.example.armin.tp5.factories.Student.StudentFactory;
import com.example.armin.tp5.services.Student.UpdateStudentService;

import junit.framework.Assert;

/**
 * Created by Armin on 2016-05-14.
 */
public class TestStudentUpdateService extends AndroidTestCase{
    UpdateStudentService updateService;
    boolean isBound = false;

    public void setUp() throws Exception {
        super.setUp();
        Intent serviceIntent = new Intent(this.getContext(), UpdateStudentService.class);
        this.getContext().bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE);
    }
    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            UpdateStudentService.MyLocalBinder binder = (UpdateStudentService.MyLocalBinder) service;
            updateService = binder.getService();
            try {
                testUpdate();
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

    public void testUpdate() throws Exception
    {
        StudentFactory s = new StudentFactory(new Long(1), "3A", "75#77#82#89#67#69#90", true, "Armin", "Wentzel", "21/06/1995", "Male", "0764805005");
        StudentData person = s.buildStudentData();

        Assert.assertEquals("UPDATED", updateService.updateStudent(person));
    }
}
