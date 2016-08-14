package com.example.amwentzel.myschoolapp.Grading;

import com.example.armin.tp5.domain.Student.StudentData;
import com.example.armin.tp5.factories.Grading.MarkGradingFactory;

import org.junit.Test;


/**
 * Created by Armin on 2016-04-07.
 */
public class TestGradingFactory{
    @Test
    public void testGrading()
    {
        StudentData person = new StudentData.Builder()
                .marks("75#77#82#89#67#69#90")
                .build();

        String marks[] = person.getMarks().split("#");
        String mark;
        for (int i = 0;i < 7; i++)
        {
            mark = MarkGradingFactory.getGrade(Integer.parseInt(marks[i]));
            System.out.println("Absent" + (i+1) + ": " + marks[i] + "You got an " + mark);
        }

    }

}
