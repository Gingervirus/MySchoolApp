package com.example.amwentzel.myschoolapp.Services.Student;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.armin.tp5.domain.Student.StudentData;
import com.example.armin.tp5.factories.Student.StudentFactory;
import com.example.armin.tp5.services.Student.InsertStudentService;

import junit.framework.Assert;

/**
 * Created by Armin on 2016-05-12.
 */
public class TestStudentAddService extends AndroidTestCase {
    InsertStudentService insertService;
    boolean isBound = false;

    public void setUp() throws Exception {
        super.setUp();
        Intent serviceIntent = new Intent(this.getContext(), InsertStudentService.class);
        this.getContext().bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE);
    }
    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            InsertStudentService.MyLocalBinder binder = (InsertStudentService.MyLocalBinder) service;
            insertService = binder.getService();
            try {
                testInsert();
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

        public void testInsert() throws Exception
        {
            StudentFactory s = new StudentFactory(null, "3A", "75#77#82#89#67#69#90", true, "Armin", "Wentzel", "21/06/1995", "Male", "0764805005");
            StudentData person = s.buildStudentData();

            Assert.assertEquals("INSERTED", insertService.insertStudent(person));
        }
}

