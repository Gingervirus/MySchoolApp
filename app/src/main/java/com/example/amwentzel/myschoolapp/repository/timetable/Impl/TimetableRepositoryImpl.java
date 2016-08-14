package com.example.amwentzel.myschoolapp.repository.timetable.Impl;

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
import com.example.amwentzel.myschoolapp.domain.timetable.Timetable;
import com.example.amwentzel.myschoolapp.repository.timetable.TimetableRepository;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by amwentzel on 2016/08/14.
 */
public class TimetableRepositoryImpl extends SQLiteOpenHelper implements TimetableRepository {
    public static final String TABLE_NAME = "employee";
    private SQLiteDatabase db;

    public static final String COLUMN_TIMETABLE_ID = "timetable_id";
    public static final String COLUMN_TIMETABLE_PERIOD = "period";
    public static final String COLUMN_TIMETABLE_DAY = "day";
    public static final String COLUMN_SUBJECT_NAME= "subject";
    public static final String COLUMN_PERIOD_TIME = "time";
    public static final String COLUMN_ROOM_NR = "room_nr";

    public TimetableRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public TimetableRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public TimetableRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
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
    public Timetable findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_TIMETABLE_ID ,
                        COLUMN_TIMETABLE_PERIOD,
                        COLUMN_TIMETABLE_DAY,
                        COLUMN_SUBJECT_NAME,
                        COLUMN_PERIOD_TIME,
                        COLUMN_ROOM_NR},
                COLUMN_TIMETABLE_ID  + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Timetable timetable = new Timetable.Builder()
                    .timetable_id(cursor.getLong(cursor.getColumnIndex(COLUMN_TIMETABLE_ID)))
                    .period(cursor.getString(cursor.getColumnIndex(COLUMN_TIMETABLE_PERIOD)))
                    .day(cursor.getLong(cursor.getColumnIndex(COLUMN_TIMETABLE_DAY )))
                    .subject(cursor.getString(cursor.getColumnIndex(COLUMN_SUBJECT_NAME)))
                    .time(cursor.getString(cursor.getColumnIndex(COLUMN_PERIOD_TIME)))
                    .room_nr(cursor.getString(cursor.getColumnIndex(COLUMN_ROOM_NR)))
                    .build();
            return timetable;
        } else {
            return null;
        }
    }

    @Override
    public Timetable save(Timetable entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TIMETABLE_ID , entity.getTimetable_id());
        values.put(COLUMN_TIMETABLE_PERIOD, entity.getPeriod());
        values.put(COLUMN_TIMETABLE_DAY, entity.getDay());
        values.put(COLUMN_SUBJECT_NAME, entity.getSubject());
        values.put(COLUMN_PERIOD_TIME, entity.getTime());
        values.put(COLUMN_ROOM_NR, entity.getRoom_nr());
        Long id = db.insertOrThrow(TABLE_NAME, null, values);
        Timetable insertedEntity = new Timetable.Builder()
                .copy(entity)
                .timetable_id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Timetable update(Timetable entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TIMETABLE_ID , entity.getTimetable_id());
        values.put(COLUMN_TIMETABLE_PERIOD, entity.getPeriod());
        values.put(COLUMN_TIMETABLE_DAY, entity.getDay());
        values.put(COLUMN_SUBJECT_NAME, entity.getSubject());
        values.put(COLUMN_PERIOD_TIME, entity.getTime());
        values.put(COLUMN_ROOM_NR, entity.getRoom_nr());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_TIMETABLE_ID  + " =? ",
                new String[]{String.valueOf(entity.getTimetable_id())}
        );
        return entity;
    }

    @Override
    public Timetable delete(Timetable entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_TIMETABLE_ID  + " =? ",
                new String[]{String.valueOf(entity.getTimetable_id())});
        return entity;
    }

    @Override
    public Set<Timetable> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Timetable> timetables = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Timetable timetable = new Timetable.Builder()
                        .timetable_id(cursor.getLong(cursor.getColumnIndex(COLUMN_TIMETABLE_ID)))
                        .period(cursor.getString(cursor.getColumnIndex(COLUMN_TIMETABLE_PERIOD)))
                        .day(cursor.getLong(cursor.getColumnIndex(COLUMN_TIMETABLE_DAY )))
                        .subject(cursor.getString(cursor.getColumnIndex(COLUMN_SUBJECT_NAME)))
                        .time(cursor.getString(cursor.getColumnIndex(COLUMN_PERIOD_TIME)))
                        .room_nr(cursor.getString(cursor.getColumnIndex(COLUMN_ROOM_NR)))
                        .build();
                timetables.add(timetable);
            } while (cursor.moveToNext());
        }
        return timetables;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}
