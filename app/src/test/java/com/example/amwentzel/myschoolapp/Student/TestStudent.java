package com.example.amwentzel.myschoolapp.Student;

import com.example.armin.tp5.domain.Student.StudentData;
import com.example.armin.tp5.factories.Student.StudentFactory;

import junit.framework.Assert;

import org.junit.Test;


/**
 * Created by Armin on 2016-04-07.
 */
public class TestStudent {
    @Test
    public void testStudent() throws Exception {

        StudentFactory s = new StudentFactory(null, "3A", "75#77#82#89#67#69#90", true, "Armin", "Wentzel", "21/06/1995", "Male", "0764805005");
        StudentData person = s.buildStudentData();
        Assert.assertEquals(person.getStudentDetails().getDob(),"21/06/1995");
        Assert.assertEquals(person.getStudentDetails().getName(),"Armin");
        Assert.assertEquals(person.getGrade(),"3A");
        Assert.assertEquals(person.getStudentDetails().getLastName(),"Wentzel");
        Assert.assertEquals(person.getStudentDetails().getGender(),"Male");
        Assert.assertEquals(person.getStudNr(),"214296725");
        Assert.assertEquals(person.getMarks(),"75#77#82#89#67#69#90");
        Assert.assertEquals(person.getStudentDetails().getCell(),"0764805005");

        StudentData newPerson = new StudentData.Builder()
                .copy(person)
                .grade("9")
                .build();

        Assert.assertEquals(newPerson.getGrade(),"9");
    }
}
