package com.example.amwentzel.myschoolapp.domain.marks;

import java.util.Date;
import java.util.Locale.Builder;

/**
 * Created by amwentzel on 2016/08/01.
 */
public class Mark {
    private Long mark_ID;
    private String title;
    private Date date;
    private int mark;

    public Mark(Builder builder) {
        this.mark_ID = builder.mark_ID;
        this.title = builder.title;
        this.date = builder.date;
        this.mark = builder.mark;
    }

    public Long getMark_ID() {
        return mark_ID;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public int getMark() {
        return mark;
    }

    public static class Builder
    {
        private Long mark_ID;
        private String title;
        private Date date;
        private int mark;

        public Builder mark_ID(Long mark_ID)
        {
            this.mark_ID = mark_ID;
            return this;
        }

        public Builder title(String title)
        {
            this.title = title;
            return this;
        }

        public Builder date(Date date)
        {
            this.date = date;
            return this;
        }

        public Builder mark(int mark)
        {
            this.mark = mark;
            return this;
        }

        public Builder copy(Mark value){
            this.mark_ID = value.mark_ID;
            this.title = value.title;
            this.date = value.date;
            this.mark = value.mark;
            return this;
        }

        public Mark build()
        {
            return new Mark(this);
        }
    }

}
