package com.example.amwentzel.myschoolapp.services.Student;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.amwentzel.myschoolapp.config.util.App;
import com.example.amwentzel.myschoolapp.domain.student.StudentData;
import com.example.amwentzel.myschoolapp.repository.Student.Impl.StudentRepositoryImpl;
import com.example.amwentzel.myschoolapp.repository.Student.StudentRepository;


/**
 * Created by Armin on 2016-05-08.
 */
public class SearchStudentService extends Service {
    private StudentRepository repo;
    private final IBinder searchStudent = new MyLocalBinder();

    public SearchStudentService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return searchStudent;
    }

    public StudentData searchStudent(Long emp)
    {
        repo = new StudentRepositoryImpl(App.getAppContext());
        return repo.findById(emp);
    }

    public class MyLocalBinder extends Binder
    {
        public SearchStudentService getService()
        {
            return SearchStudentService.this;
        }
    }
}
