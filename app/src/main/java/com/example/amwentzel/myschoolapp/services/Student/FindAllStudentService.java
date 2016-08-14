package com.example.amwentzel.myschoolapp.services.Student;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;


import com.example.amwentzel.myschoolapp.config.util.App;
import com.example.amwentzel.myschoolapp.domain.student.StudentData;
import com.example.amwentzel.myschoolapp.repository.Student.Impl.StudentRepositoryImpl;
import com.example.amwentzel.myschoolapp.repository.Student.StudentRepository;

import java.util.Set;

/**
 * Created by Armin on 2016-05-08.
 */
public class FindAllStudentService extends Service {
    private StudentRepository repo;
    private final IBinder findAllStudent = new MyLocalBinder();

    public FindAllStudentService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return findAllStudent;
    }

    public Set<StudentData>  findAllStudent()
    {
        repo = new StudentRepositoryImpl(App.getAppContext());
        return repo.findAll();
    }

    public class MyLocalBinder extends Binder
    {
        public FindAllStudentService getService()
        {
            return FindAllStudentService.this;
        }
    }
}
