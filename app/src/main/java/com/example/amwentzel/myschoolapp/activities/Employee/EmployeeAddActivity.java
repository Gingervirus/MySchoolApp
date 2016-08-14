package com.example.amwentzel.myschoolapp.activities.Employee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.amwentzel.myschoolapp.R;
import com.example.amwentzel.myschoolapp.factories.salary.SalaryFactory;
import com.example.amwentzel.myschoolapp.factories.workingHours.WorkingHoursFactory;


public class EmployeeAddActivity extends AppCompatActivity {

    private RadioGroup jobGroup;
    private Button previewButton;

    private EditText name;
    private  EditText surname;
    private EditText dob;
    private EditText gender;
    private EditText cell;
    private EditText sars;
    private RadioButton job;
    private String salary;
    private String workHours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_add);

        name = (EditText) findViewById(R.id.name);
        surname = (EditText) findViewById(R.id.surname);
        dob = (EditText) findViewById(R.id.txtDob);
        gender = (EditText) findViewById(R.id.txtGender);
        cell = (EditText) findViewById(R.id.txtCell);
        sars = (EditText) findViewById(R.id.txtSarsNr);
        jobGroup = (RadioGroup)findViewById(R.id.jobGroup);

        previewButton = (Button)findViewById(R.id.btnPreview);
        previewButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), EmployeePreview.class);
                int selectedId = jobGroup.getCheckedRadioButtonId();
                job = (RadioButton) findViewById(selectedId);
                salary = SalaryFactory.getSalary(job.getText().toString());
                workHours = WorkingHoursFactory.getWorkingHours(job.getText().toString());

                i.putExtra("name",name.getText().toString());
                i.putExtra("surname",surname.getText().toString());
                i.putExtra("dob",dob.getText().toString());
                i.putExtra("gender",gender.getText().toString());
                i.putExtra("cell",cell.getText().toString());
                i.putExtra("sars",sars.getText().toString());
                i.putExtra("job",job.getText().toString());
                i.putExtra("salary",salary);
                i.putExtra("workHours",workHours);
                startActivity(i);
            }
        });
    }





}
