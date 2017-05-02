package maximprytyka.com.timetable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TimeTableDB";
    public static final String TABLE_TIME = "time";
    public static final String TABLE_SUB = "subjects";
    public static final String TABLE_TEACHERS = "teachers";
    public static final String TABLE_BUILDINGS = "buikdings";
    public static final String TABLE_TYPE = "lesson type";

    public static final String KEY_ID = "_id";
    public static final String KEY_VALUE = "value";
    public static final String KEY_VALUE_END = "value end";




    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}