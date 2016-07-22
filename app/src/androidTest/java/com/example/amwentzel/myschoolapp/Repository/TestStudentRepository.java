package com.example.amwentzel.myschoolapp.Repository;
import android.test.AndroidTestCase;

import com.example.armin.tp5.domain.Student.StudentData;
import com.example.armin.tp5.domain.Student.StudentDetails;
import com.example.armin.tp5.repository.Student.Impl.StudentRepositoryImpl;
import com.example.armin.tp5.repository.Student.StudentRepository;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Armin on 2016-04-25.
 */
public class TestStudentRepository extends AndroidTestCase{
    private static final String TAG="SETTINGS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        StudentRepository repo = new StudentRepositoryImpl(this.getContext());
        // CREATE

        StudentDetails stud = new StudentDetails.Builder()
                .name("Armin")
                .lastName("Wentzel")
                .gender("Male")
                .dob("21/06/1995")
                .cell("0764805005")
                .build();
        StudentData createEntity = new StudentData.Builder()
                .studentDetails(stud)
                .marks("75#77#82#89#67#69#90")
                .grade("3A")
                .build();
        StudentData insertedEntity = repo.save(createEntity);
        id=insertedEntity.getStudNr();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<StudentData> studentDatas = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",studentDatas.size()>0);

        //READ ENTITY
        StudentData entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        StudentData updateEntity = new StudentData.Builder()
                .copy(entity)
                .grade("7")
                .build();
        repo.update(updateEntity);
        StudentData newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","7",newEntity.getGrade());

        // DELETE ENTITY
        repo.delete(updateEntity);
        StudentData deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

        // DELETE ALL
        repo.deleteAll();
        Set<StudentData> deletedUsers = repo.findAll();
        Assert.assertTrue(deletedUsers.size() == 0);
    }
}
