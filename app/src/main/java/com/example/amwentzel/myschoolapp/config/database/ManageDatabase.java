package com.example.amwentzel.myschoolapp.config.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Armin on 2016-06-05.
 */
public class ManageDatabase extends  SQLiteOpenHelper {
    public static final String TABLE_NAME_EMPLOYEE = "employee";
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
    public static final String COLUMN_JOB = "Job";

    private static final String DATABASE_EMPLOYEE_CREATE = " CREATE TABLE "
            + TABLE_NAME_EMPLOYEE + "("
            + COLUMN_EMPLOYEE_NR + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_SARS_NR  + " TEXT NOT NULL , "
            + COLUMN_SALARY + " TEXT NOT NULL , "
            + COLUMN_WORKHOURS + " TEXT NOT NULL , "
            + COLUMN_FIRSTNAME + " TEXT NOT NULL , "
            + COLUMN_LASTNAME + " TEXT NOT NULL , "
            + COLUMN_DATE_OF_BIRTH + " TEXT NOT NULL , "
            + COLUMN_GENDER + " TEXT NOT NULL , "
            + COLUMN_CELL + " TEXT NOT NULL ,"
            + COLUMN_JOB + " TEXT  NOT NULL );";

    public static final String TABLE_NAME_STUDENT = "student";

    public static final String COLUMN_STUDENT_NR = "studentNr";
    public static final String COLUMN_GRADE= "grader";
    public static final String COLUMN_MARKS = "marks";
    public static final String COLUMN_STUD_FIRSTNAME = "firstname";
    public static final String COLUMN_STUD_LASTNAME = "lastName";
    public static final String COLUMN_STUD_DATE_OF_BIRTH = "dateOfBirth";
    public static final String COLUMN_STUD_GENDER = "gender";
    public static final String COLUMN_STUD_CELL = "cellphoneNr";

    private static final String DATABASE_STUDENT_CREATE = " CREATE TABLE "
            + TABLE_NAME_STUDENT + "("
            + COLUMN_STUDENT_NR + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_GRADE + " TEXT NOT NULL , "
            + COLUMN_MARKS + " TEXT NOT NULL , "
            + COLUMN_STUD_FIRSTNAME + " TEXT NOT NULL , "
            + COLUMN_STUD_LASTNAME + " TEXT NOT NULL , "
            + COLUMN_STUD_DATE_OF_BIRTH + " TEXT NOT NULL , "
            + COLUMN_STUD_GENDER + " TEXT NOT NULL , "
            + COLUMN_STUD_CELL + " TEXT NOT NULL );";

    public ManageDatabase(Context context)
    {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);

    }

    public ManageDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, SQLiteDatabase db) {
        super(context, name, factory, version);
        this.db = db;
    }

    public ManageDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler, SQLiteDatabase db) {
        super(context, name, factory, version, errorHandler);
        this.db = db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABASE_EMPLOYEE_CREATE);
        db.execSQL(DATABASE_STUDENT_CREATE);
    }

    public void deleteTable(String tablename){
        db.execSQL("DROP TABLE IF EXISTS "+tablename+";");
    }

    public boolean deleteDatabase(Context context) {
        return context.deleteDatabase(DBConstants.DATABASE_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
