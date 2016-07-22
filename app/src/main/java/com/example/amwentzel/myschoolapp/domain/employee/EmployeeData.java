package com.example.amwentzel.myschoolapp.domain.employee;

import java.io.Serializable;

/**
 * Created by Armin on 2016-04-06.
 */
public class EmployeeData implements Serializable {
    private Long empNr;
    private String sarsNr;
    private double salary;
    private String workingHours;
    private String job;
    private EmployeeDetails details;
    public EmployeeData(){}

    public  EmployeeData(Builder builder)
    {
        this.empNr = builder.empNr;
        this.sarsNr = builder.sarsNr;
        this.salary = builder.salary;
        this.workingHours = builder.workingHours;
        this.details = builder.details;
        this.job = builder.job;
    }

    public static class Builder
    {
        private Long empNr;
        private String sarsNr;
        private double salary;
        private String workingHours;
        private EmployeeDetails details;
        private String job;

        public Builder sarsNr(String sarsNr)
        {
            this.sarsNr = sarsNr;
            return this;
        }

        public Builder salary(double salary)
        {
            this.salary = salary;
            return this;
        }

        public Builder empNr(Long empNr)
        {
            this.empNr = empNr;
            return this;
        }

        public Builder workingHours(String workingHours)
        {
            this.workingHours = workingHours;
            return this;
        }

        public Builder employeeDetails(EmployeeDetails details)
        {
            this.details = details;
            return this;
        }

        public Builder job(String job)
        {
            this.job = job;
            return this;
        }


        public Builder copy(EmployeeData value){
            this.empNr = value.empNr;
            this.sarsNr = value.sarsNr;
            this.salary = value.salary;
            this.workingHours = value.workingHours;
            this.details = value.details;
            this.job = value.job;
            return this;
        }

        public EmployeeData build()
        {
            return new EmployeeData(this);
        }
    }



    public String getWorkingHours() {
        return workingHours;
    }

    public String getSarsNr() {
        return sarsNr;
    }

    public EmployeeDetails getDetails() {
        return details;
    }

    public double getSalary() {
        return salary;
    }

    public Long getEmpNr() {
        return empNr;
    }

    public String getJob() {
        return job;
    }

    @Override
    public String toString() {
        return "EmployeeData{" +
                "empNr='" + empNr + '\'' +
                ", sarsNr='" + sarsNr + '\'' +
                ", salary=" + salary +
                ", workingHours='" + workingHours + '\'' +
                ", job='" + job + '\'' +
                ", details=" + details +
                '}';
    }
}
