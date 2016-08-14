package com.example.amwentzel.myschoolapp.repository.absent.Impl;

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
import com.example.amwentzel.myschoolapp.domain.absent.Absent;
import com.example.amwentzel.myschoolapp.repository.absent.AbsentRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by amwentzel on 2016/08/10.
 */
public class AbsentRepositoryImpl extends SQLiteOpenHelper implements AbsentRepository {
    public static final String TABLE_NAME= "absent";
    private SQLiteDatabase db;

    public static final String COLUMN_ABSENT_ID = "absent_ID";
    public static final String COLUMN_SUBJECT= "subject";
    public static final String COLUMN_DATE = "date";

    public AbsentRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public AbsentRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public AbsentRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
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
    public Absent findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ABSENT_ID ,
                        COLUMN_SUBJECT,
                        COLUMN_DATE},
                COLUMN_ABSENT_ID  + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Absent employeeData = new Absent.Builder()
                    .absent_ID(cursor.getLong(cursor.getColumnIndex(COLUMN_ABSENT_ID )))
                    .subject(cursor.getString(cursor.getColumnIndex(COLUMN_SUBJECT)))
                   // .date(cursor.getType(cursor.getColumnIndex(COLUMN_DATE)))
                    .build();

            return employeeData;
        } else {
            return null;
        }
    }

    @Override
    public Absent save(Absent entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SUBJECT, entity.getSubject());
        values.put(COLUMN_DATE, entity.getDate().toString());
        Long id = db.insertOrThrow(TABLE_NAME, null, values);
        Absent insertedEntity = new Absent.Builder()
                .copy(entity)
                .absent_ID(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Absent update(Absent entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ABSENT_ID , entity.getAbsent_ID());
        values.put(COLUMN_SUBJECT, entity.getSubject());
        values.put(COLUMN_DATE, entity.getDate().toString());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ABSENT_ID  + " =? ",
                new String[]{String.valueOf(entity.getAbsent_ID())}
        );
        return entity;
    }

    @Override
    public Absent delete(Absent entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ABSENT_ID  + " =? ",
                new String[]{String.valueOf(entity.getAbsent_ID())});
        return entity;
    }

    @Override
    public Set<Absent> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Absent> AbsentDatas = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Absent Absent = new Absent.Builder()
                        .absent_ID(cursor.getLong(cursor.getColumnIndex(COLUMN_ABSENT_ID )))
                        .subject(cursor.getString(cursor.getColumnIndex(COLUMN_SUBJECT)))
                      //  .date(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)))
                        .build();

                AbsentDatas.add(Absent);
            } while (cursor.moveToNext());
        }
        return AbsentDatas;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}
