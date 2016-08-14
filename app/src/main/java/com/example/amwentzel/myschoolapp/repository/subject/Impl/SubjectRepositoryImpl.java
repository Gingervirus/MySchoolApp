package com.example.amwentzel.myschoolapp.repository.subject.Impl;

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
import com.example.amwentzel.myschoolapp.domain.subject.Subject;
import com.example.amwentzel.myschoolapp.repository.subject.SubjectRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by amwentzel on 2016/08/12.
 */
public class SubjectRepositoryImpl extends SQLiteOpenHelper implements SubjectRepository {
    public static final String TABLE_NAME = "subject";
    private SQLiteDatabase db;

    public static final String COLUMN_SUBJECT_CODE = "subject_code";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PRESCRIBED_BOOK = "prescribed_book";

    public SubjectRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public SubjectRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public SubjectRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
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
    public Subject findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_SUBJECT_CODE,
                        COLUMN_TITLE,
                        COLUMN_DESCRIPTION,
                        COLUMN_PRESCRIBED_BOOK},
                COLUMN_SUBJECT_CODE + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Subject subject = new Subject.Builder()
                    .subject_code(cursor.getLong(cursor.getColumnIndex(COLUMN_SUBJECT_CODE)))
                    .title(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)))
                    .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                    .prescribed_book(cursor.getString(cursor.getColumnIndex(COLUMN_PRESCRIBED_BOOK)))
                    .build();
            return subject;
        } else {
            return null;
        }
    }

    @Override
    public Subject save(Subject entity) {
        open();

        ContentValues values = new ContentValues();

        values.put(COLUMN_TITLE, entity.getTitle());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        values.put(COLUMN_PRESCRIBED_BOOK, entity.getPrescribed_book());
        Long id = db.insertOrThrow(TABLE_NAME, null, values);

        Subject insertedEntity = new Subject.Builder()
                .copy(entity)
                .subject_code(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Subject update(Subject entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SUBJECT_CODE, entity.getSubject_code());
        values.put(COLUMN_TITLE, entity.getTitle());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        values.put(COLUMN_PRESCRIBED_BOOK, entity.getPrescribed_book());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_SUBJECT_CODE + " =? ",
                new String[]{String.valueOf(entity.getSubject_code())}
        );
        return entity;
    }

    @Override
    public Subject delete(Subject entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_SUBJECT_CODE + " =? ",
                new String[]{String.valueOf(entity.getSubject_code())});
        return entity;
    }

    @Override
    public Set<Subject> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Subject> subjects = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                final Subject subject = new Subject.Builder()
                        .subject_code(cursor.getLong(cursor.getColumnIndex(COLUMN_SUBJECT_CODE)))
                        .title(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)))
                        .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                        .prescribed_book(cursor.getString(cursor.getColumnIndex(COLUMN_PRESCRIBED_BOOK)))
                        .build();
                subjects.add(subject);
            } while (cursor.moveToNext());
        }
        return subjects;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME, null, null);
        close();
        return rowsDeleted;
    }
}