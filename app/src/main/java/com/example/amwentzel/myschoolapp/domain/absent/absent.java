package com.example.amwentzel.myschoolapp.domain.absent;

import java.util.Date;

/**
 * Created by amwentzel on 2016/08/01.
 */
public class Absent {
    private Long absent_ID;
    private String subject;
    private Date date;

    public Absent(Builder builder) {
        this.absent_ID = builder.absent_ID;
        this.subject = builder.subject;
        this.date = builder.date;
    }

    public Long getAbsent_ID() {
        return absent_ID;
    }

    public String getSubject() {
        return subject;
    }

    public Date getDate() {
        return date;
    }

    public static class Builder
    {
        private Long absent_ID;
        private String subject;
        private Date date;

        public Builder absent_ID(Long absent_ID)
        {
            this.absent_ID = absent_ID;
            return this;
        }

        public Builder subject(String subject)
        {
            this.subject = subject;
            return this;
        }

        public Builder date(Date date)
        {
            this.date = date;
            return this;
        }

        public Builder copy(Absent value){
            this.absent_ID = value.absent_ID;
            this.subject = value.subject;
            this.date = value.date;
            return this;
        }

        public Absent build()
        {
            return new Absent(this);
        }
    }
}
