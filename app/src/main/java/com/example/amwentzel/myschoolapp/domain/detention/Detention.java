package com.example.amwentzel.myschoolapp.domain.detention;

import java.util.Date;

/**
 * Created by Armin on 2016-04-07.
 */
public class Detention {
    private Long detention_ID;
    private Long studentNr;
    private Date date;
    private String reason;
    private int amount_hours;

    public Detention(Builder builder) {
        this.detention_ID = builder.detention_ID;
        this.studentNr = builder.studentNr;
        this.date = builder.date;
        this.reason = builder.reason;
        this.amount_hours = builder.amount_hours;

    }
    public Long getDetention_ID() {
        return detention_ID;
    }

    public Long getStudentNr() {
        return studentNr;
    }

    public Date getDate() {
        return date;
    }

    public String getReason() {
        return reason;
    }

    public int getAmount_hours() {
        return amount_hours;
    }
    public static class Builder
    {
        private Long detention_ID;
        private Long studentNr;
        private Date date;
        private String reason;
        private int amount_hours;

        public Builder detention_ID(Long detention_ID)
        {
            this.detention_ID = detention_ID;
            return this;
        }

        public Builder studentNr(Long studentNr)
        {
            this.studentNr = studentNr;
            return this;
        }

        public Builder date(Date date)
        {
            this.date = date;
            return this;
        }

        public Builder reason(String reason)
        {
            this.reason = reason;
            return this;
        }

        public Builder amount_hours(int amount_hours)
        {
            this.amount_hours = amount_hours;
            return this;
        }

        public Builder copy(Detention value){
            this.detention_ID = value.detention_ID;
            this.studentNr = value.studentNr;
            this.date = value.date;
            this.reason = value.reason;
            this.amount_hours = value.amount_hours;
            return this;
        }

        public Detention build()
        {
            return new Detention(this);
        }
    }

}
