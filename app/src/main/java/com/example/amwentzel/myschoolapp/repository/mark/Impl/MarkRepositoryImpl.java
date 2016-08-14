package com.example.amwentzel.myschoolapp.repository.mark.Impl;

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
import com.example.amwentzel.myschoolapp.domain.marks.Mark;
import com.example.amwentzel.myschoolapp.repository.mark.MarkRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by amwentzel on 2016/08/11.
 */
public class MarkRepositoryImpl extends SQLiteOpenHelper implements MarkRepository {
    public static final String TABLE_NAME = "mark";
    private SQLiteDatabase db;

    public static final String COLUMN_MARK_ID = "mark_ID";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_WRITING_DATE = "write_date";
    public static final String COLUMN_MARK= "mark";

    public MarkRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public MarkRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MarkRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
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
    public Mark findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_MARK_ID ,
                        COLUMN_TITLE,
                        COLUMN_WRITING_DATE,
                        COLUMN_MARK},
                COLUMN_MARK_ID  + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Mark mark = new Mark.Builder()
                    .title(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)))
                    //.date(cursor.getString(cursor.getColumnIndex(COLUMN_WRITING_DATE)))
                    .mark(cursor.getInt(cursor.getColumnIndex(COLUMN_MARK)))
                    .build();
            return mark;
        } else {
            return null;
        }
    }

    @Override
    public Mark save(Mark entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, entity.getTitle());
        //values.put(COLUMN_WRITING_DATE, entity.getDate());
        values.put(COLUMN_MARK, entity.getMark());
        Long id = db.insertOrThrow(TABLE_NAME, null, values);
        Mark insertedEntity = new Mark.Builder()
                .copy(entity)
                .mark_ID(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Mark update(Mark entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MARK_ID , entity.getMark_ID());
        values.put(COLUMN_TITLE, entity.getTitle());
        //values.put(COLUMN_WRITING_DATE, entity.getDate());
        values.put(COLUMN_MARK, entity.getMark());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_MARK_ID  + " =? ",
                new String[]{String.valueOf(entity.getMark_ID())}
        );
        return entity;
    }

    @Override
    public Mark delete(Mark entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_MARK_ID  + " =? ",
                new String[]{String.valueOf(entity.getMark_ID())});
        return entity;
    }

    @Override
    public Set<Mark> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Mark> marks = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Mark mark = new Mark.Builder()
                        .title(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)))
                        //.date(cursor.getString(cursor.getColumnIndex(COLUMN_WRITING_DATE)))
                        .mark(cursor.getInt(cursor.getColumnIndex(COLUMN_MARK)))
                        .build();
                marks.add(mark);
            } while (cursor.moveToNext());
        }
        return marks;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}
