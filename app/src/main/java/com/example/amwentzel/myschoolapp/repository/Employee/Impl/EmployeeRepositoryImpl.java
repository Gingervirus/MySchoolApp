package com.example.amwentzel.myschoolapp.repository.Employee.Impl;

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
import com.example.amwentzel.myschoolapp.domain.teacher.EmployeeData;
import com.example.amwentzel.myschoolapp.domain.teacher.EmployeeDetails;
import com.example.amwentzel.myschoolapp.repository.Employee.EmployeeRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Armin on 2016-04-24.
 */
public class EmployeeRepositoryImpl extends SQLiteOpenHelper implements EmployeeRepository {
    public static final String TABLE_NAME = "employee";
    private SQLiteDatabase db;

    public static final String COLUMN_EMPLOYEE_NR = "empNr";
    public static final String COLUMN_SARS_NR = "sarsNr";
    public static final String COLUMN_SALARY = "salary";
    public static final String COLUMN_WORKHOURS= "workingHours";
    public static final String COLUMN_FIRSTNAME = "firstname";
    public static final String COLUMN_LASTNAME = "lastName";
    public static final String COLUMN_DATE_OF_BIRTH = "dateOfBirth";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_CELL = "cellphoneNr";
    public static final String COLUMN_JOB = "job";

    public EmployeeRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public EmployeeRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public EmployeeRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
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
    public EmployeeData findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_EMPLOYEE_NR ,
                        COLUMN_SARS_NR,
                        COLUMN_SALARY,
                        COLUMN_WORKHOURS,
                        COLUMN_FIRSTNAME,
                        COLUMN_LASTNAME,
                        COLUMN_DATE_OF_BIRTH,
                        COLUMN_GENDER,
                        COLUMN_CELL,
                        COLUMN_JOB},
                COLUMN_EMPLOYEE_NR  + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final EmployeeDetails employeeDetails = new EmployeeDetails.Builder()
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                    .lastName(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)))
                    .dob(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_OF_BIRTH)))
                    .gender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)))
                    .cell(cursor.getString(cursor.getColumnIndex(COLUMN_CELL)))
                    .build();
            final EmployeeData employeeData = new EmployeeData.Builder()
                    .empNr(cursor.getLong(cursor.getColumnIndex(COLUMN_EMPLOYEE_NR )))
                    .sarsNr(cursor.getString(cursor.getColumnIndex(COLUMN_SARS_NR)))
                    .salary(cursor.getDouble(cursor.getColumnIndex(COLUMN_SALARY)))
                    .workingHours(cursor.getString(cursor.getColumnIndex(COLUMN_WORKHOURS)))
                    .job(COLUMN_JOB)
                    .employeeDetails(employeeDetails)
                    .build();

            return employeeData;
        } else {
            return null;
        }
    }

    @Override
    public EmployeeData save(EmployeeData entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SARS_NR, entity.getSarsNr());
        values.put(COLUMN_SALARY, entity.getSalary());
        values.put(COLUMN_WORKHOURS, entity.getWorkingHours());
        values.put(COLUMN_FIRSTNAME, entity.getDetails().getName());
        values.put(COLUMN_LASTNAME, entity.getDetails().getLastName());
        values.put(COLUMN_DATE_OF_BIRTH, entity.getDetails().getDob());
        values.put(COLUMN_GENDER, entity.getDetails().getGender());
        values.put(COLUMN_CELL, entity.getDetails().getCell());
        values.put(COLUMN_JOB, entity.getJob());
        Long id = db.insertOrThrow(TABLE_NAME, null, values);
        EmployeeData insertedEntity = new EmployeeData.Builder()
                .copy(entity)
                .empNr(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public EmployeeData update(EmployeeData entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMPLOYEE_NR , entity.getEmpNr());
        values.put(COLUMN_SARS_NR, entity.getSarsNr());
        values.put(COLUMN_SALARY, entity.getSalary());
        values.put(COLUMN_WORKHOURS, entity.getWorkingHours());
        values.put(COLUMN_FIRSTNAME, entity.getDetails().getName());
        values.put(COLUMN_LASTNAME, entity.getDetails().getLastName());
        values.put(COLUMN_DATE_OF_BIRTH, entity.getDetails().getDob());
        values.put(COLUMN_GENDER, entity.getDetails().getGender());
        values.put(COLUMN_CELL, entity.getDetails().getCell());
        values.put(COLUMN_JOB, entity.getJob());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_EMPLOYEE_NR  + " =? ",
                new String[]{String.valueOf(entity.getEmpNr())}
        );
        return entity;
    }

    @Override
    public EmployeeData delete(EmployeeData entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_EMPLOYEE_NR  + " =? ",
                new String[]{String.valueOf(entity.getEmpNr())});
        return entity;
    }

    @Override
    public Set<EmployeeData> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<EmployeeData> employeeDatas = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final EmployeeDetails employeeDetails = new EmployeeDetails.Builder()
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                        .lastName(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)))
                        .dob(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_OF_BIRTH)))
                        .gender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)))
                        .cell(cursor.getString(cursor.getColumnIndex(COLUMN_CELL)))
                        .build();
                final EmployeeData employeeData = new EmployeeData.Builder()
                        .empNr(cursor.getLong(cursor.getColumnIndex(COLUMN_EMPLOYEE_NR )))
                        .sarsNr(cursor.getString(cursor.getColumnIndex(COLUMN_SARS_NR)))
                        .salary(cursor.getDouble(cursor.getColumnIndex(COLUMN_SALARY)))
                        .workingHours(cursor.getString(cursor.getColumnIndex(COLUMN_WORKHOURS)))
                        .job(COLUMN_JOB)
                        .employeeDetails(employeeDetails)
                        .build();
                employeeDatas.add(employeeData);
            } while (cursor.moveToNext());
        }
        return employeeDatas;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}
