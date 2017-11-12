package maximprytyka.com.timetable;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DBTimeTable";
    public static final String TABLE_TIME = "time";
    public static final String TABLE_SUB = "subjects";
    public static final String TABLE_TEACHERS = "teachers";
    public static final String TABLE_BUILDINGS = "buildings";
    public static final String TABLE_TYPE = "lesson_type";
    public static final String TABLE_MON = "monday";
    public static final String TABLE_TUE = "tuesday";
    public static final String TABLE_WEN = "wednesday";
    public static final String TABLE_THU = "thursday";
    public static final String TABLE_FRI = "friday";
    public static final String TABLE_SAT = "saturday";
    public static final String TABLE_SUN = "sunday";



    public static final String KEY_ID = "_id";
    public static final String KEY_VALUE = "value";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table  " + TABLE_TEACHERS + " (" + KEY_ID + " integer primary key," + KEY_VALUE + " text)");
        db.execSQL("create table  " + TABLE_SUB + " (" + KEY_ID + " integer primary key," + KEY_VALUE + " text)");
        db.execSQL("create table  " + TABLE_MON + " (time text,subject text, room text, teacher text,type text, building text)");
        db.execSQL("create table  " + TABLE_TUE + " (time text,subject text, room text, teacher text,type text, building text)");
        db.execSQL("create table  " + TABLE_WEN + " (time text,subject text, room text, teacher text,type text, building text)");
        db.execSQL("create table  " + TABLE_THU + " (time text,subject text, room text, teacher text,type text, building text)");
        db.execSQL("create table  " + TABLE_FRI + " (time text,subject text, room text, teacher text,type text, building text)");
        db.execSQL("create table  " + TABLE_SAT + " (time text,subject text, room text, teacher text,type text, building text)");
        db.execSQL("create table  " + TABLE_SUN + " (time text,subject text, room text, teacher text,type text, building text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS  " + TABLE_TEACHERS);
        db.execSQL("DROP TABLE IF EXISTS  " + TABLE_SUB);
        db.execSQL("DROP TABLE IF EXISTS  " + TABLE_MON);
        db.execSQL("DROP TABLE IF EXISTS  " + TABLE_TUE);
        db.execSQL("DROP TABLE IF EXISTS  " + TABLE_WEN);
        db.execSQL("DROP TABLE IF EXISTS  " + TABLE_THU);
        db.execSQL("DROP TABLE IF EXISTS  " + TABLE_FRI);
        db.execSQL("DROP TABLE IF EXISTS  " + TABLE_SAT);
        db.execSQL("DROP TABLE IF EXISTS  " + TABLE_SUN);

        onCreate(db);

    }



}