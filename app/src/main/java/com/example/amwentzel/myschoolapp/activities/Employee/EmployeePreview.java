package com.example.amwentzel.myschoolapp.activities.Employee;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.amwentzel.myschoolapp.R;
import com.example.amwentzel.myschoolapp.domain.teacher.EmployeeData;
import com.example.amwentzel.myschoolapp.factories.employee.EmployeeFactory;
import com.example.amwentzel.myschoolapp.repository.Employee.EmployeeRepository;
import com.example.amwentzel.myschoolapp.repository.Employee.Impl.EmployeeRepositoryImpl;
import com.example.amwentzel.myschoolapp.services.Employee.InsertEmployeeService;


public class EmployeePreview extends AppCompatActivity{
    InsertEmployeeService insertService;
    EmployeeRepository repo;
    boolean isBound = false;

    private TextView name;
    private TextView surname;
    private TextView dob;
    private TextView gender;
    private TextView cell;
    private TextView sars;
    private TextView job;
    private TextView salary;
    private TextView workHours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_preview);
        Intent c = new Intent(this.getApplicationContext(), InsertEmployeeService.class);
        bindService(c, connection, Context.BIND_AUTO_CREATE);
        repo = new EmployeeRepositoryImpl(this.getApplicationContext());
        name =(TextView)findViewById(R.id.txtName);
        surname =(TextView)findViewById(R.id.txtSurname);
        dob =(TextView)findViewById(R.id.txtDob);
        gender =(TextView)findViewById(R.id.txtGender);
        cell =(TextView)findViewById(R.id.txtCellphone);
        sars =(TextView)findViewById(R.id.txtSars);
        job =(TextView)findViewById(R.id.txtJob);
        salary =(TextView)findViewById(R.id.txtSalary);
        workHours =(TextView)findViewById(R.id.txtHours);

        Intent i = getIntent();

        Bundle b = i.getExtras();

        if(b!=null)
        {
            String fname = (String)b.get("name");
            name.setText(fname);

            String lname =(String)b.get("surname");
            surname.setText(lname);

            String d = (String)b.get("dob");
            dob.setText(d);

            String gen = (String)b.get("gender");
            gender.setText(gen);

            String cellar = (String)b.get("cell");
            cell.setText(cellar);

            String sarsnr =(String)b.get("sars");
            sars.setText(sarsnr);

            String j = (String)b.get("job");
            job.setText(j);

            String sal = (String)b.get("salary");
            salary.setText(sal);

            String work = (String)b.get("workHours");
            workHours.setText(work);

        }

    }

    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            InsertEmployeeService.MyLocalBinder binder = (InsertEmployeeService.MyLocalBinder) service;
            insertService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void btnSubmit(View view) throws InterruptedException {

        Intent intent = new Intent(getApplicationContext(), EmployeeShowAll.class);
        EmployeeFactory emp = new EmployeeFactory();
        EmployeeData e = emp.getEmployee(null, sars.getText().toString(), Double.parseDouble(salary.getText().toString()), workHours.getText().toString(), name.getText().toString(), surname.getText().toString(), dob.getText().toString(), gender.getText().toString(), cell.getText().toString(), job.getText().toString());
        String isActivated = insertService.insertEmployee(e);

       // EmployeeData insertedEntity = repo.save(e);
        startActivity(intent);
    }
}

