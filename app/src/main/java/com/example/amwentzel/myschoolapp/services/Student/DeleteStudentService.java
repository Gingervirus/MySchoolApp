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
public class DeleteStudentService extends Service {
    private StudentRepository repo;
    private final IBinder deleteStudent = new MyLocalBinder();

    public DeleteStudentService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return deleteStudent;
    }

    public String deleteStudent(StudentData emp)
    {
        repo = new StudentRepositoryImpl(App.getAppContext());
        repo.delete(emp);
        return "DELETED";
    }

    public class MyLocalBinder extends Binder
    {
        public DeleteStudentService getService()
        {
            return DeleteStudentService.this;
        }
    }
}
