package com.example.amwentzel.myschoolapp.repository.parent.Impl;

/**
 * Created by amwentzel on 2016/08/12.
 */

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
import com.example.amwentzel.myschoolapp.domain.parent.Parent;
import com.example.amwentzel.myschoolapp.repository.parent.ParentRepository;

import java.util.HashSet;
import java.util.Set;


public class ParentRepositoryImpl extends SQLiteOpenHelper implements ParentRepository {
        public static final String TABLE_NAME = "parent";
        private SQLiteDatabase db;

    public static final String COLUMN_PARENT_ID = "parent_ID";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_CELLPHONE_NR= "cellphone_nr";
    public static final String COLUMN_TELEPHONE_NR = "telephone_nr";
    public static final String COLUMN_E_MAIL = "e_mail";
        public ParentRepositoryImpl(Context context) {
            super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
        }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public ParentRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public ParentRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
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
    public Parent findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_PARENT_ID ,
                        COLUMN_FIRST_NAME,
                        COLUMN_SURNAME,
                        COLUMN_CELLPHONE_NR,
                        COLUMN_TELEPHONE_NR,
                        COLUMN_E_MAIL},
                COLUMN_PARENT_ID  + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Parent parent = new Parent.Builder()
                    .first_name(cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME)))
                    .surname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                    .cellphone_nr(cursor.getString(cursor.getColumnIndex(COLUMN_CELLPHONE_NR)))
                    .telephone_nr(cursor.getString(cursor.getColumnIndex(COLUMN_TELEPHONE_NR)))
                    .e_mail(cursor.getString(cursor.getColumnIndex(COLUMN_E_MAIL)))
                    .build();

            return parent;
        } else {
            return null;
        }
    }

    @Override
    public Parent save(Parent entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME, entity.getFirst_name());
        values.put(COLUMN_SURNAME, entity.getSurname());
        values.put(COLUMN_CELLPHONE_NR, entity.getCellphone_nr());
        values.put(COLUMN_TELEPHONE_NR, entity.getTelephone_nr());
        values.put(COLUMN_E_MAIL, entity.getE_mail());

        Long id = db.insertOrThrow(TABLE_NAME, null, values);
        Parent insertedEntity = new Parent.Builder()
                .copy(entity)
                .parent_ID(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Parent update(Parent entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PARENT_ID , entity.getParent_ID());
        values.put(COLUMN_FIRST_NAME, entity.getFirst_name());
        values.put(COLUMN_SURNAME, entity.getSurname());
        values.put(COLUMN_CELLPHONE_NR, entity.getCellphone_nr());
        values.put(COLUMN_TELEPHONE_NR, entity.getTelephone_nr());
        values.put(COLUMN_E_MAIL, entity.getE_mail());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_PARENT_ID  + " =? ",
                new String[]{String.valueOf(entity.getParent_ID())}
        );
        return entity;
    }

    @Override
    public Parent delete(Parent entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_PARENT_ID  + " =? ",
                new String[]{String.valueOf(entity.getParent_ID())});
        return entity;
    }

    @Override
    public Set<Parent> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Parent> employeeDatas = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Parent parent = new Parent.Builder()
                        .first_name(cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME)))
                        .surname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                        .cellphone_nr(cursor.getString(cursor.getColumnIndex(COLUMN_CELLPHONE_NR)))
                        .telephone_nr(cursor.getString(cursor.getColumnIndex(COLUMN_TELEPHONE_NR)))
                        .e_mail(cursor.getString(cursor.getColumnIndex(COLUMN_E_MAIL)))
                        .build();

                employeeDatas.add(parent);
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
