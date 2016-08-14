package com.example.amwentzel.myschoolapp.services.Employee;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.amwentzel.myschoolapp.config.util.App;
import com.example.amwentzel.myschoolapp.domain.teacher.EmployeeData;
import com.example.amwentzel.myschoolapp.repository.Employee.EmployeeRepository;
import com.example.amwentzel.myschoolapp.repository.Employee.Impl.EmployeeRepositoryImpl;


/**
 * Created by Armin on 2016-05-08.
 */
public class SearchEmployeeService extends Service {
    private EmployeeRepository repo;
    private final IBinder searchEmployee = new MyLocalBinder();

    public SearchEmployeeService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return searchEmployee;
    }

    public EmployeeData searchEmployee(Long emp)
    {
        repo = new EmployeeRepositoryImpl(App.getAppContext());
        return repo.findById(emp);
    }

    public class MyLocalBinder extends Binder
    {
        public SearchEmployeeService getService()
        {
            return SearchEmployeeService.this;
        }
    }
}
