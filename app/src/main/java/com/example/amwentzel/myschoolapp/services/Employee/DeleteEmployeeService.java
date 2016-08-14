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
public class DeleteEmployeeService extends Service {
    private EmployeeRepository repo;
    private final IBinder deleteEmployee = new MyLocalBinder();

    public DeleteEmployeeService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return deleteEmployee;
    }

    public EmployeeData deleteEmployee(EmployeeData emp)
    {
        repo = new EmployeeRepositoryImpl(App.getAppContext());
        return repo.delete(emp);
    }

    public class MyLocalBinder extends Binder
    {
        public DeleteEmployeeService getService()
        {
            return DeleteEmployeeService.this;
        }
    }
}
