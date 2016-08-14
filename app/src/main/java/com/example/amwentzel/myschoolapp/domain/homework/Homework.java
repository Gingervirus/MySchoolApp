package com.example.amwentzel.myschoolapp.domain.homework;

import java.util.Date;

/**
 * Created by amwentzel on 2016/08/14.
 */
public class Homework {
    private Long homework_ID;
    private String subject;
    private String description;
    private Date due_date;

    public Long getHomework_ID() {
        return homework_ID;
    }

    public String getDescription() {
        return description;
    }

    public String getSubject() {
        return subject;
    }

    public Date getDue_date() {
        return due_date;
    }

    public Homework(Builder builder) {
        this.homework_ID = builder.homework_ID;
        this.subject = builder.subject;
        this.description = builder.description;
        this.due_date = builder.due_date;
    }

    public static class Builder
    {
        private Long homework_ID;
        private String subject;
        private String description;
        private Date due_date;

        public Builder homework_ID(Long homework_ID)
        {
            this.homework_ID = homework_ID;
            return this;
        }

        public Builder subject(String subject)
        {
            this.subject = subject;
            return this;
        }

        public Builder due_date(Date due_date)
        {
            this.due_date = due_date;
            return this;
        }

        public Builder description(String description)
        {
            this.description = description;
            return this;
        }

        public Builder copy(Homework value){
            this.homework_ID = value.homework_ID;
            this.subject = value.subject;
            this.due_date = value.due_date;
            this.description = value.description;
            return this;
        }

        public Homework build()
        {
            return new Homework(this);
        }
    }

}
