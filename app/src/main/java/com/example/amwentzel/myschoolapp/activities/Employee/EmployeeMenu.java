package com.example.amwentzel.myschoolapp.activities.Employee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.amwentzel.myschoolapp.R;


/**
 * Created by Armin on 2016-06-05.
 */
public class EmployeeMenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_menu);
    }

    public void buttonRegister(View v)    {
        Intent i = new Intent(getApplicationContext(), EmployeeAddActivity.class);
        startActivity(i);
    }

}
