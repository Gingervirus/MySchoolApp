package com.example.amwentzel.myschoolapp.repository.login.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.amwentzel.myschoolapp.config.database.DBConstants;
import com.example.amwentzel.myschoolapp.config.database.ManageDatabase;
import com.example.amwentzel.myschoolapp.config.util.App;
import com.example.amwentzel.myschoolapp.domain.login.Login;
import com.example.amwentzel.myschoolapp.domain.teacher.EmployeeData;
import com.example.amwentzel.myschoolapp.domain.teacher.EmployeeDetails;
import com.example.amwentzel.myschoolapp.repository.login.LoginRepository;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by amwentzel on 2016/08/08.
 */
public class LoginRepositoryImpl extends SQLiteOpenHelper implements LoginRepository {
    public static final String TABLE_NAME= "login";
    private SQLiteDatabase db;

    public static final String COLUMN_LOGIN_ID = "user_id";
    public static final String COLUMN_USERNAME= "username";
    public static final String COLUMN_PASSWORD = "password";

    public LoginRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public LoginRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public LoginRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ManageDatabase manageDatabase = new ManageDatabase(App.getInstance());
        manageDatabase.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    @Override
    public Login findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_LOGIN_ID ,
                        COLUMN_USERNAME,
                        COLUMN_PASSWORD},
                COLUMN_LOGIN_ID  + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Login employeeData = new Login.Builder()
                    .user_id(cursor.getLong(cursor.getColumnIndex(COLUMN_LOGIN_ID )))
                    .Username(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)))
                    .Password(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))
                    .build();

            return employeeData;
        } else {
            return null;
        }
    }

    @Override
    public Login save(Login entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, entity.getUsername());
        values.put(COLUMN_PASSWORD, entity.getPassword());
        Long id = db.insertOrThrow(TABLE_NAME, null, values);
        Login insertedEntity = new Login.Builder()
                .copy(entity)
                .user_id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Login update(Login entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LOGIN_ID , entity.getUser_id());
        values.put(COLUMN_USERNAME, entity.getUsername());
        values.put(COLUMN_PASSWORD, entity.getPassword());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_LOGIN_ID  + " =? ",
                new String[]{String.valueOf(entity.getUser_id())}
        );
        return entity;
    }

    @Override
    public Login delete(Login entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_LOGIN_ID  + " =? ",
                new String[]{String.valueOf(entity.getUser_id())});
        return entity;
    }

    @Override
    public Set<Login> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Login> loginDatas = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Login login = new Login.Builder()
                        .user_id(cursor.getLong(cursor.getColumnIndex(COLUMN_LOGIN_ID )))
                        .Username(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)))
                        .Password(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))
                        .build();

                loginDatas.add(login);
            } while (cursor.moveToNext());
        }
        return loginDatas;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}

