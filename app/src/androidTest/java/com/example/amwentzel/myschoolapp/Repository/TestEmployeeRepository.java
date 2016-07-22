package com.example.amwentzel.myschoolapp.Repository;

import android.test.AndroidTestCase;

import com.example.armin.tp5.domain.Employee.EmployeeData;
import com.example.armin.tp5.domain.Employee.EmployeeDetails;
import com.example.armin.tp5.factories.Salary.SalaryFactory;
import com.example.armin.tp5.factories.WorkingHours.WorkingHoursFactory;
import com.example.armin.tp5.repository.Employee.EmployeeRepository;
import com.example.armin.tp5.repository.Employee.Impl.EmployeeRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;


/**
 * Created by Armin on 2016-04-25.
 */
public class TestEmployeeRepository extends AndroidTestCase{

        private static final String TAG="EMPLOYEE TEST";
        private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        EmployeeRepository repo = new EmployeeRepositoryImpl(this.getContext());
        // CREATE

        EmployeeDetails details = new EmployeeDetails.Builder()
                .name("Armin")
                .lastName("Wentzel")
                .gender("Male")
                .cell("0764805005")
                .dob("21/06/1995")
                .build();
        EmployeeData createEntity = new EmployeeData.Builder()
                .salary(Double.parseDouble(SalaryFactory.getSalary("Teacher")))
                .workingHours(WorkingHoursFactory.getWorkingHours("Teacher"))
                .sarsNr("2314565346")
                .job("Teacher")
                .employeeDetails(details)
                .build();

        EmployeeData insertedEntity = repo.save(createEntity);
        id=insertedEntity.getEmpNr();
        Assert.assertNotNull(insertedEntity);

        //READ ALL
        Set<EmployeeData> employeeDatas = repo.findAll();
        Assert.assertTrue(employeeDatas.size()>0);

        //READ ENTITY
        EmployeeData entity = repo.findById(id);
        Assert.assertNotNull(entity);

        //UPDATE ENTITY
        EmployeeData updateEntity = new EmployeeData.Builder()
                .copy(entity)
                .workingHours("12H00-17H00")
                .build();
        repo.update(updateEntity);
        EmployeeData newEntity = repo.findById(id);
        Assert.assertEquals("12H00-17H00",newEntity.getWorkingHours());

        // DELETE ENTITY
        repo.delete(updateEntity);
        EmployeeData deletedEntity = repo.findById(id);
        Assert.assertNull(deletedEntity);

        // DELETE ALL
        repo.deleteAll();
        Set<EmployeeData> deletedUsers = repo.findAll();
        Assert.assertTrue(deletedUsers.size() == 0);
    }


}
