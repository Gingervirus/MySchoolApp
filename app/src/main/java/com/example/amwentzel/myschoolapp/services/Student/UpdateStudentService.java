package com.example.amwentzel.myschoolapp.services.Student;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.amwentzel.myschoolapp.config.util.App;
import com.example.amwentzel.myschoolapp.domain.student.StudentData;
import com.example.amwentzel.myschoolapp.repository.Student.Impl.StudentRepositoryImpl;
import com.example.amwentzel.myschoolapp.repository.Student.StudentRepository;


public class UpdateStudentService extends Service {
    private StudentRepository repo;
    private final IBinder updateStudent = new MyLocalBinder();

    public UpdateStudentService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return updateStudent;
    }

    public String updateStudent(StudentData emp)
    {
        repo = new StudentRepositoryImpl(App.getAppContext());
        repo.update(emp);
        return "UPDATED";
    }

    public class MyLocalBinder extends Binder
    {
       public UpdateStudentService getService()
        {
            return UpdateStudentService.this;
        }
    }
}
