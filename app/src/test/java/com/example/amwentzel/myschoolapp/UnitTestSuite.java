package com.example.amwentzel.myschoolapp;

import com.example.amwentzel.myschoolapp.Detention.TestDetention;
import com.example.amwentzel.myschoolapp.Employee.TestEmployee;
import com.example.amwentzel.myschoolapp.Grading.TestGradingFactory;
import com.example.amwentzel.myschoolapp.Job.TestJobFactory;
import com.example.amwentzel.myschoolapp.Salary.TestSalaryFactory;
import com.example.amwentzel.myschoolapp.Student.TestStudent;
import com.example.amwentzel.myschoolapp.WorkingHours.TestWorkingHours;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// Runs all unit tests.
@RunWith(Suite.class)
@Suite.SuiteClasses({TestEmployee.class, TestDetention.class, TestGradingFactory.class, TestJobFactory.class, TestSalaryFactory.class, TestStudent.class, TestWorkingHours.class})
public class UnitTestSuite {}