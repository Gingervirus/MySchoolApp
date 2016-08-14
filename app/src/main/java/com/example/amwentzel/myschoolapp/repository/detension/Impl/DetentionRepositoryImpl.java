package com.example.amwentzel.myschoolapp.repository.detension.Impl;

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
import com.example.amwentzel.myschoolapp.domain.detention.Detention;
import com.example.amwentzel.myschoolapp.repository.detension.DetentionRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by amwentzel on 2016/08/10.
 */
public class DetentionRepositoryImpl extends SQLiteOpenHelper implements DetentionRepository{
    public static final String TABLE_NAME = "detention";
    private SQLiteDatabase db;

    public static final String COLUMN_DETENTION_ID = "detention_ID";
    public static final String COLUMN_STUDENT_NR = "studentNr";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_REASON= "reason";
    public static final String COLUMN_AMOUNT_HOURS = "amount_hours";

    public DetentionRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public DetentionRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DetentionRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
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
    public Detention findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_DETENTION_ID ,
                        COLUMN_STUDENT_NR,
                        COLUMN_DATE,
                        COLUMN_REASON,
                        COLUMN_AMOUNT_HOURS},
                COLUMN_DETENTION_ID  + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Detention detention = new Detention.Builder()
                    .detention_ID(cursor.getLong(cursor.getColumnIndex(COLUMN_DETENTION_ID)))
                    .studentNr(cursor.getLong(cursor.getColumnIndex(COLUMN_STUDENT_NR)))
                   // .date(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)))
                    .reason(cursor.getString(cursor.getColumnIndex(COLUMN_REASON)))
                    .amount_hours(cursor.getInt(cursor.getColumnIndex(COLUMN_AMOUNT_HOURS)))
                    .build();

            return detention;
        } else {
            return null;
        }
    }

    @Override
    public Detention save(Detention entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENT_NR, entity.getStudentNr());
       // values.put(COLUMN_DATE, entity.getDate());
        values.put(COLUMN_REASON, entity.getReason());
        values.put(COLUMN_AMOUNT_HOURS, entity.getAmount_hours());
        Long id = db.insertOrThrow(TABLE_NAME, null, values);
        Detention insertedEntity = new Detention.Builder()
                .copy(entity)
                .detention_ID(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Detention update(Detention entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DETENTION_ID , entity.getDetention_ID());
        values.put(COLUMN_STUDENT_NR, entity.getStudentNr());
        // values.put(COLUMN_DATE, entity.getDate());
        values.put(COLUMN_REASON, entity.getReason());
        values.put(COLUMN_AMOUNT_HOURS, entity.getAmount_hours());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_DETENTION_ID  + " =? ",
                new String[]{String.valueOf(entity.getDetention_ID())}
        );
        return entity;
    }

    @Override
    public Detention delete(Detention entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_DETENTION_ID  + " =? ",
                new String[]{String.valueOf(entity.getDetention_ID())});
        return entity;
    }

    @Override
    public Set<Detention> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Detention> employeeDatas = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Detention detention = new Detention.Builder()
                        .detention_ID(cursor.getLong(cursor.getColumnIndex(COLUMN_DETENTION_ID)))
                        .studentNr(cursor.getLong(cursor.getColumnIndex(COLUMN_STUDENT_NR)))
                        // .date(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)))
                        .reason(cursor.getString(cursor.getColumnIndex(COLUMN_REASON)))
                        .amount_hours(cursor.getInt(cursor.getColumnIndex(COLUMN_AMOUNT_HOURS)))
                        .build();

                employeeDatas.add(detention);
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
