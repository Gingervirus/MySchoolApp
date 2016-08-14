package com.example.amwentzel.myschoolapp.factories.workingHours;


/**
 * Created by Armin on 2016-04-06.
 */
public class WorkingHoursFactory {
    public static String getWorkingHours(String employeeType)
    {
        WorkingHours chain = setUpChain();
        return chain.handleRequest(employeeType);
    }

    public static WorkingHours setUpChain()
    {
        WorkingHours c = new WorkingHoursCleaner();
        WorkingHours s= new WorkingHoursSecratary();
        WorkingHours t = new WorkingHoursTeacher();
        WorkingHours p = new WorkingHoursPrincipal();
        c.setNextWorkingHours(s);
        s.setNextWorkingHours(t);
        t.setNextWorkingHours(p);
        return c;
    }
}
