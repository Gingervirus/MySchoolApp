package com.example.amwentzel.myschoolapp.Repository;

import android.test.AndroidTestCase;

import com.example.amwentzel.myschoolapp.domain.login.Login;
import com.example.amwentzel.myschoolapp.repository.login.Impl.LoginRepositoryImpl;
import com.example.amwentzel.myschoolapp.repository.login.LoginRepository;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by amwentzel on 2016/08/08.
 */
public class TestLoginRepository extends AndroidTestCase {
    private static final String TAG = "LOGIN TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        LoginRepositoryImpl repo = new LoginRepositoryImpl(this.getContext());
        // CREATE

        Login login = new Login.Builder()
                .Username("amwentzel")
                .Password("Password123")
                .build();

        Login insertedEntity = repo.save(login);
        id = insertedEntity.getUser_id();
        Assert.assertNotNull(insertedEntity);

        //READ ALL
        Set<Login> loginData = repo.findAll();
        Assert.assertTrue(loginData.size() > 0);

        //READ ENTITY
        Login entity = repo.findById(id);
        Assert.assertNotNull(entity);

        //UPDATE ENTITY
        Login updateEntity = new Login.Builder()
                .copy(entity)
                .Password("password")
                .build();
        repo.update(updateEntity);
        Login newEntity = repo.findById(id);
        Assert.assertEquals("password", newEntity.getPassword());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Login deletedEntity = repo.findById(id);
        Assert.assertNull(deletedEntity);

        // DELETE ALL
        repo.deleteAll();
        Set<Login> deletedUsers = repo.findAll();
        Assert.assertTrue(deletedUsers.size() == 0);
    }
}
