package com.example.amwentzel.myschoolapp.domain.student;

/**
 * Created by Armin on 2016-04-17.
 */
public class StudentDetails {
    private String name;
    private String lastName;
    private String dob;
    private String gender;
    private String cell;

    public  StudentDetails(Builder builder)
    {
        this.name = builder.name;
        this.lastName = builder.lastName;
        this.dob = builder.dob;
        this.gender = builder.gender;
        this.cell = builder.cell;
    }

    public static class Builder
    {

        private String name;
        private String lastName;
        private String dob;
        private String gender;
        private String cell;



        public Builder name(String name)
        {
            this.name = name;
            return this;
        }

        public Builder lastName(String lastName)
        {
            this.lastName = lastName;
            return this;
        }
        public Builder dob(String dob)
        {
            this.dob = dob;
            return this;
        }

        public Builder gender(String gender)
        {
            this.gender = gender;
            return this;
        }

        public Builder cell(String cell)
        {
            this.cell = cell;
            return this;
        }

        public Builder copy(StudentDetails value){
            this.name = value.name;
            this.lastName = value.lastName;
            this.dob = value.dob;
            this.gender = value.gender;
            this.cell = value.cell;
            return this;
        }

        public StudentDetails build()
        {
            return new StudentDetails(this);
        }

    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }


    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getCell() {
        return cell;
    }


    @Override
    public String toString() {
        return "StudentDetails{" +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", cell='" + cell + '\'' +
                '}';
    }
}

