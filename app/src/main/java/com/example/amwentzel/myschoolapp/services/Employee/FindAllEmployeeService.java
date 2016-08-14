package com.example.amwentzel.myschoolapp.services.Employee;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;


import com.example.amwentzel.myschoolapp.config.util.App;
import com.example.amwentzel.myschoolapp.domain.teacher.EmployeeData;
import com.example.amwentzel.myschoolapp.repository.Employee.EmployeeRepository;
import com.example.amwentzel.myschoolapp.repository.Employee.Impl.EmployeeRepositoryImpl;

import java.util.Set;

/**
 * Created by Armin on 2016-05-08.
 */
public class FindAllEmployeeService extends Service {
    private EmployeeRepository repo;
    private final IBinder findAllEmployee = new MyLocalBinder();

    public FindAllEmployeeService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return findAllEmployee;
    }

    public Set<EmployeeData>  findAllEmployee()
    {
        repo = new EmployeeRepositoryImpl(App.getAppContext());
        return repo.findAll();
    }

    public class MyLocalBinder extends Binder
    {
        public FindAllEmployeeService getService()
        {
            return FindAllEmployeeService.this;
        }
    }
}
