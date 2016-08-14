package com.example.amwentzel.myschoolapp.repository.homework.Impl;

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
import com.example.amwentzel.myschoolapp.domain.homework.Homework;
import com.example.amwentzel.myschoolapp.repository.homework.HomeworkRepository;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by amwentzel on 2016/08/14.
 */
public class HomeworkRepositoryImpl extends SQLiteOpenHelper implements HomeworkRepository {
    public static final String TABLE_NAME= "homework";
    private SQLiteDatabase db;

    public static final String COLUMN_HOMEWORK_ID = "homework_ID";
    public static final String COLUMN_H_SUBJECT_NAME = "subject";
    public static final String COLUMN_HOMEWORK_DESCRIPTION = "description";
    public static final String COLUMN_DUE_DATE = "date_due";

    public HomeworkRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public HomeworkRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public HomeworkRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
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
    public Homework findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_HOMEWORK_ID ,
                        COLUMN_H_SUBJECT_NAME,
                        COLUMN_HOMEWORK_DESCRIPTION,
                        COLUMN_DUE_DATE},
                COLUMN_HOMEWORK_ID  + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Homework homework = new Homework.Builder()
                    .homework_ID(cursor.getLong(cursor.getColumnIndex(COLUMN_HOMEWORK_ID)))
                    .subject(cursor.getString(cursor.getColumnIndex(COLUMN_H_SUBJECT_NAME)))
                    .description(cursor.getString(cursor.getColumnIndex(COLUMN_HOMEWORK_DESCRIPTION)))
                    .due_date(new Date(cursor.getString(cursor.getColumnIndex(COLUMN_DUE_DATE))))
                    .build();

            return homework;
        } else {
            return null;
        }
    }

    @Override
    public Homework save(Homework entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_HOMEWORK_ID , entity.getHomework_ID());
        values.put(COLUMN_H_SUBJECT_NAME, entity.getSubject());
        values.put(COLUMN_HOMEWORK_DESCRIPTION, entity.getDescription());
        values.put(COLUMN_DUE_DATE, entity.getDue_date().toString());
        Long id = db.insertOrThrow(TABLE_NAME, null, values);
        Homework insertedEntity = new Homework.Builder()
                .copy(entity)
                .homework_ID(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Homework update(Homework entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_HOMEWORK_ID , entity.getHomework_ID());
        values.put(COLUMN_H_SUBJECT_NAME, entity.getSubject());
        values.put(COLUMN_HOMEWORK_DESCRIPTION, entity.getDescription());
        values.put(COLUMN_DUE_DATE, entity.getDue_date().toString());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_HOMEWORK_ID  + " =? ",
                new String[]{String.valueOf(entity.getHomework_ID())}
        );
        return entity;
    }

    @Override
    public Homework delete(Homework entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_HOMEWORK_ID  + " =? ",
                new String[]{String.valueOf(entity.getHomework_ID())});
        return entity;
    }

    @Override
    public Set<Homework> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Homework> homeworks = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Homework homework = new Homework.Builder()
                        .homework_ID(cursor.getLong(cursor.getColumnIndex(COLUMN_HOMEWORK_ID)))
                        .subject(cursor.getString(cursor.getColumnIndex(COLUMN_H_SUBJECT_NAME)))
                        .description(cursor.getString(cursor.getColumnIndex(COLUMN_HOMEWORK_DESCRIPTION)))
                        .due_date(new Date(cursor.getString(cursor.getColumnIndex(COLUMN_DUE_DATE))))
                        .build();

                homeworks.add(homework);
            } while (cursor.moveToNext());
        }
        return homeworks;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}
