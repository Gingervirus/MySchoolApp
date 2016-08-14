package com.example.amwentzel.myschoolapp.domain.timetable;

import java.io.Serializable;

/**
 * Created by amwentzel on 2016/08/14.
 */
public class Timetable implements Serializable {
        private Long timetable_id;
        private String period;
        private double day;
        private String subject;
        private String time;
        private String room_nr;

    public Long getTimetable_id() {
        return timetable_id;
    }

    public String getPeriod() {
        return period;
    }

    public double getDay() {
        return day;
    }

    public String getSubject() {
        return subject;
    }

    public String getTime() {
        return time;
    }

    public String getRoom_nr() {
        return room_nr;
    }

    public  Timetable(Builder builder)
        {
            this.timetable_id = builder.timetable_id;
            this.period = builder.period;
            this.day = builder.day;
            this.subject = builder.subject;
            this.time = builder.time;
            this.room_nr = builder.room_nr;
        }

        public static class Builder
        {
            private Long timetable_id;
            private String period;
            private double day;
            private String subject;
            private String time;
            private String room_nr;

            public Builder room_nr(String room_nr)
            {
                this.room_nr = room_nr;
                return this;
            }

            public Builder day(double day)
            {
                this.day = day;
                return this;
            }

            public Builder timetable_id(Long timetable_id)
            {
                this.timetable_id = timetable_id;
                return this;
            }

            public Builder subject(String subject)
            {
                this.subject = subject;
                return this;
            }

            public Builder time(String time)
            {
                this.time = time;
                return this;
            }

            public Builder period(String period)
            {
                this.period = period;
                return this;
            }



            public Builder copy(Timetable value){
                this.timetable_id = value.timetable_id;
                this.period = value.period;
                this.day = value.day;
                this.subject = value.subject;
                this.time = value.time;
                this.room_nr = value.room_nr;
                return this;
            }

            public Timetable build()
            {
                return new Timetable(this);
            }
        }
}
