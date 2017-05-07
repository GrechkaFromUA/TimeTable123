package maximprytyka.com.timetable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DBTimeTable";
    public static final String TABLE_TIME = "time";
    public static final String TABLE_SUB = "subjects";
    public static final String TABLE_TEACHERS = "teachers";
    public static final String TABLE_BUILDINGS = "buildings";
    public static final String TABLE_TYPE = "lesson_type";

    public static final String KEY_ID = "_id";
    public static final String KEY_VALUE = "value";
    public static final String KEY_VALUE_END = "value end";




    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table  "+TABLE_TEACHERS+" ("+KEY_ID+" integer primary key,"+KEY_VALUE+" text)");
        db.execSQL("create table  "+TABLE_SUB+" ("+KEY_ID+" integer primary key,"+KEY_VALUE+" text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS  "+ TABLE_TEACHERS);
        db.execSQL("DROP TABLE IF EXISTS  "+ TABLE_SUB);

        onCreate(db);

    }
}