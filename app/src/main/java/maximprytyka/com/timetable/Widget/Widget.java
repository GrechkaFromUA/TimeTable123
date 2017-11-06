package maximprytyka.com.timetable.Widget;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;
import android.widget.Toast;

import maximprytyka.com.timetable.DBHelper;
import maximprytyka.com.timetable.R;

public class Widget extends AppWidgetProvider {

    final String ACTION_ON_CLICK = "ru.startandroid.develop.p1211listwidget.itemonclick";
    final static String ITEM_POSITION = "item_position";
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (int i : appWidgetIds) {
            updateWidget(context, appWidgetManager, i);
        }
    }

    void updateWidget(Context context, AppWidgetManager appWidgetManager,
                      int appWidgetId) {
        RemoteViews rv = new RemoteViews(context.getPackageName(),
                R.layout.widget);

        rv.setTextViewText(R.id.dayName,MyFactory.getCurrentDay());

        setList(rv, context, appWidgetId);

        setListClick(rv, context, appWidgetId);


        appWidgetManager.updateAppWidget(appWidgetId, rv);
    }


    void setList(RemoteViews rv, Context context, int appWidgetId) {
        Intent adapter = new Intent(context, MyService.class);
        adapter.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        rv.setRemoteAdapter(R.id.lvList, adapter);
    }

    void setListClick(RemoteViews rv, Context context, int appWidgetId) {

        Intent listClickIntent = new Intent(context, Widget.class);
        listClickIntent.setAction(ACTION_ON_CLICK);
        PendingIntent listClickPIntent = PendingIntent.getBroadcast(context, 0,
                listClickIntent, 0);
        rv.setPendingIntentTemplate(R.id.lvList, listClickPIntent);

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction().equalsIgnoreCase(ACTION_ON_CLICK)) {
            int itemPos = intent.getIntExtra(ITEM_POSITION, -1);
            if (itemPos != -1) {
                Toast.makeText(context, "Clicked on item " + itemPos,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public ArrayList<String> getAllData(String day,Context context) {
        String columns[] = new String[]{"time", "subject", "room", "teacher", "type", "building"};
        ArrayList<String> data = new ArrayList<>();

        for (int i = 0; i < columns.length; i++) {

            for (int j = 0; j < getData(day, columns[i],context).length; j++) {

                data.add(getData(day, columns[i],context)[j]);

            }


        }


        return data;
    }

    public  String[] getData(String day, String column,Context context) {

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

}