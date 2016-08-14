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

    public static final String TABLE_NAME_LOGIN = "login";

    public static final String COLUMN_LOGIN_ID = "user_id";
    public static final String COLUMN_USERNAME= "username";
    public static final String COLUMN_PASSWORD = "password";

    private static final String DATABASE_LOGIN_CREATE = " CREATE TABLE "
            + TABLE_NAME_LOGIN + "("
            + COLUMN_LOGIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USERNAME + " TEXT NOT NULL , "
            + COLUMN_PASSWORD + " TEXT NOT NULL );";

    public static final String TABLE_NAME_ABSENT = "absent";

    public static final String COLUMN_ABSENT_ID = "absent_ID";
    public static final String COLUMN_SUBJECT= "subject";
    public static final String COLUMN_DATE = "date";

    private static final String DATABASE_ABSENT_CREATE = " CREATE TABLE "
            + TABLE_NAME_ABSENT + "("
            + COLUMN_ABSENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_SUBJECT + " TEXT NOT NULL , "
            + COLUMN_DATE + " DATE NOT NULL );";

    public static final String TABLE_NAME_DETENTION = "detention";

    public static final String COLUMN_DETENTION_ID = "detention_ID";
    public static final String COLUMN_DSTUDENT_NR = "studentNr";
    public static final String COLUMN_DETENTION_DATE = "date";
    public static final String COLUMN_REASON= "reason";
    public static final String COLUMN_AMOUNT_HOURS = "amount_hours";

    private static final String DATABASE_DETENTION_CREATE = " CREATE TABLE "
            + TABLE_NAME_DETENTION + "("
            + COLUMN_DETENTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_DSTUDENT_NR + " TEXT NOT NULL , "
            + COLUMN_DETENTION_DATE + " DATE NOT NULL , "
            + COLUMN_REASON + " TEXT NOT NULL , "
            + COLUMN_AMOUNT_HOURS + " TEXT NOT NULL );";

    public static final String TABLE_NAME_MARK = "mark";

    public static final String COLUMN_MARK_ID = "mark_ID";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_WRITING_DATE = "write_date";
    public static final String COLUMN_MARK= "mark";

    private static final String DATABASE_MARK_CREATE = " CREATE TABLE "
            + TABLE_NAME_MARK+ "("
            + COLUMN_MARK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TITLE + " TEXT NOT NULL , "
            + COLUMN_WRITING_DATE + " DATE NOT NULL , "
            + COLUMN_MARK + " TEXT NOT NULL );";

    public static final String TABLE_NAME_SUBJECT = "subject";

    public static final String COLUMN_SUBJECT_CODE = "subject_code";
    public static final String COLUMN_S_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PRESCRIBED_BOOK = "prescribed_book";

    private static final String DATABASE_SUBJECT_CREATE = " CREATE TABLE "
            + TABLE_NAME_SUBJECT+ "("
            + COLUMN_SUBJECT_CODE + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_S_TITLE + " TEXT NOT NULL , "
            + COLUMN_DESCRIPTION + " TEXT NOT NULL , "
            + COLUMN_PRESCRIBED_BOOK + " TEXT NOT NULL );";

    public static final String TABLE_NAME_PARENT = "parent";

    public static final String COLUMN_PARENT_ID = "parent_ID";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_CELLPHONE_NR= "cellphone_nr";
    public static final String COLUMN_TELEPHONE_NR = "telephone_nr";
    public static final String COLUMN_E_MAIL = "e_mail";

    private static final String DATABASE_PARENT_CREATE = " CREATE TABLE "
            + TABLE_NAME_PARENT + "("
            + COLUMN_PARENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_FIRST_NAME + " TEXT NOT NULL , "
            + COLUMN_SURNAME + " TEXT NOT NULL , "
            + COLUMN_CELLPHONE_NR + " TEXT NOT NULL , "
            + COLUMN_TELEPHONE_NR + " TEXT NOT NULL , "
            + COLUMN_E_MAIL + " TEXT NOT NULL );";

    public static final String TABLE_NAME_TIMETABLE = "timetable";

    public static final String COLUMN_TIMETABLE_ID = "timetable_id";
    public static final String COLUMN_TIMETABLE_PERIOD = "period";
    public static final String COLUMN_TIMETABLE_DAY = "day";
    public static final String COLUMN_SUBJECT_NAME= "subject";
    public static final String COLUMN_PERIOD_TIME = "time";
    public static final String COLUMN_ROOM_NR = "room_nr";

    private static final String DATABASE_TIMETABLE_CREATE = " CREATE TABLE "
            + TABLE_NAME_TIMETABLE + "("
            + COLUMN_TIMETABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TIMETABLE_PERIOD + " TEXT NOT NULL , "
            + COLUMN_TIMETABLE_DAY + " TEXT NOT NULL , "
            + COLUMN_SUBJECT_NAME + " TEXT NOT NULL , "
            + COLUMN_PERIOD_TIME + " TEXT NOT NULL , "
            + COLUMN_ROOM_NR + " TEXT NOT NULL );";

    public static final String TABLE_NAME_HOMEWORK= "homework";

    public static final String COLUMN_HOMEWORK_ID = "homework_ID";
    public static final String COLUMN_H_SUBJECT_NAME = "subject";
    public static final String COLUMN_HOMEWORK_DESCRIPTION = "description";
    public static final String COLUMN_DUE_DATE = "date_due";

    private static final String DATABASE_HOMEWORK_CREATE = " CREATE TABLE "
            + TABLE_NAME_HOMEWORK+ "("
            + COLUMN_HOMEWORK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_H_SUBJECT_NAME + " TEXT NOT NULL , "
            + COLUMN_HOMEWORK_DESCRIPTION + " TEXT NOT NULL , "
            + COLUMN_DUE_DATE + " DATE NOT NULL );";

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
        db.execSQL(DATABASE_LOGIN_CREATE);
        db.execSQL(DATABASE_EMPLOYEE_CREATE);
        db.execSQL(DATABASE_STUDENT_CREATE);
        db.execSQL(DATABASE_ABSENT_CREATE);
        db.execSQL(DATABASE_DETENTION_CREATE);
        db.execSQL(DATABASE_MARK_CREATE);
        db.execSQL(DATABASE_SUBJECT_CREATE);
        db.execSQL(DATABASE_PARENT_CREATE);
        db.execSQL(DATABASE_TIMETABLE_CREATE);
        db.execSQL(DATABASE_HOMEWORK_CREATE);
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
