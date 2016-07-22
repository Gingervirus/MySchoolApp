package com.example.amwentzel.myschoolapp.domain.student;

import java.io.Serializable;

/**
 * Created by Armin on 2016-04-07.
 */
public class StudentData implements Serializable {
        private Long studNr;
        private String grade;
        private String marks;
        private boolean detention;

        private StudentDetails studentDetails;

        public StudentData(){}

        public  StudentData(Builder builder)
        {
            this.studNr = builder.studNr;
            this.grade = builder.grade;
            this.marks = builder.marks;
            this.detention = builder.detention;
            this.studentDetails = builder.studentDetails;
        }

        public static class Builder extends StudentData {
            private Long studNr;
            private String grade;
            private String marks;
            private boolean detention;
            private StudentDetails studentDetails;

            public Builder grade(String grade)
            {
                this.grade = grade;
                return this;
            }

            public Builder studentDetails(StudentDetails studentDetails)
            {
                this.studentDetails = studentDetails;
                return this;
            }

            public Builder marks(String marks)
            {
                this.marks = marks;
                return this;
            }

            public Builder studNr(Long studNr)
            {
                this.studNr = studNr;
                return this;
            }

            public Builder detention(Boolean detention)
            {
                this.detention = detention;
                return this;
            }

            public Builder copy(StudentData value){
                this.studNr = value.studNr;
                this.grade = value.grade;
                this.marks = value.marks;
                this.detention = value.detention;
                this.studentDetails = value.studentDetails;
                return this;
            }

            public StudentData build()
            {
                return new StudentData(this);
            }

        }

        public boolean getdetention() {
            return detention;
        }

    public StudentDetails getStudentDetails() {
        return studentDetails;
    }

    public String getGrade() {
        return grade;
    }

    public String getMarks() {
        return marks;
    }

    public boolean isDetention() {
        return detention;
    }


        public Long getStudNr() {
            return studNr;
        }


}

