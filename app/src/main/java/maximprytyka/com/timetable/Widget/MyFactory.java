package maximprytyka.com.timetable.Widget;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

import maximprytyka.com.timetable.DBHelper;
import maximprytyka.com.timetable.R;


public class MyFactory implements RemoteViewsFactory {

    ArrayList<String> data;

    Context context;
    int widgetID;

    MyFactory(Context ctx, Intent intent) {
        context = ctx;
        widgetID = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

    }

    @Override
    public void onCreate() {
        data = new ArrayList<String>();
    }

    @Override
    public int getCount() {
        return data.size()/6;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rView = new RemoteViews(context.getPackageName(),
                R.layout.item);

        int reapet = getCount();

        String time = data.get(0+position);

        rView.setTextViewText(R.id.sub_widget, data.get(0+reapet+position));//Subject on i
        rView.setTextViewText(R.id.room_widget, data.get(0+2*reapet+position));//Subject on i
        rView.setTextViewText(R.id.teacher_widget, data.get(0+3*reapet+position));//Subject on i
        rView.setTextViewText(R.id.type_widget, data.get(0+4*reapet+position));//Subject on i
        rView.setTextViewText(R.id.corpus_widget, data.get(0+5*reapet+position));//Subject on i


        if (!time.equals("temp")) {
            String ttime[] = time.split(" ");


            rView.setTextViewText(R.id.stime_widget, ttime[0]);//Subject on i
            rView.setTextViewText(R.id.etime_widget, ttime[2]);//Subject on i
        } else {

            rView.setTextViewText(R.id.stime_widget, "-");//Subject on i
            rView.setTextViewText(R.id.etime_widget, "-");//Subject on i

        }





        Intent clickIntent = new Intent();
        clickIntent.putExtra(Widget.ITEM_POSITION, position);
        rView.setOnClickFillInIntent(R.id.sub_widget, clickIntent);

        return rView;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onDataSetChanged() {
        data.clear();
        data = getAllData(getCurrentDay());


    }

    @Override
    public void onDestroy() {

    }



    public  ArrayList<String> getAllData(String day) {
        String columns[] = new String[]{"time", "subject", "room", "teacher", "type", "building"};
        ArrayList<String> data = new ArrayList<>();

        for (int i = 0; i < columns.length; i++) {

            for (int j = 0; j < getData(day, columns[i]).length; j++) {

                data.add(getData(day, columns[i])[j]);

            }


        }


        return data;
    }

    public  String[] getData(String day, String column) {

        DBHelper dbHelper = new DBHelper(context);

        Cursor cursor = dbHelper.getReadableDatabase().rawQuery("SELECT * FROM " + day, null);
        cursor.moveToFirst();
        ArrayList<String> names = new ArrayList<>();


        while (!cursor.isAfterLast()) {
            names.add(cursor.getString(cursor.getColumnIndex(column)));
            cursor.moveToNext();
        }
        cursor.close();
        return names.toArray(new String[names.size()]);
    }

    public static String getCurrentDay(){

        Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();

// full name form of the day
       return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());



    }
}