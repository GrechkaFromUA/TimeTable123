package maximprytyka.com.timetable.Fragmets;


import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import maximprytyka.com.timetable.DBHelper;
import maximprytyka.com.timetable.MethodHelper;
import maximprytyka.com.timetable.R;


public class MainScreenFragment extends Fragment {

    String[] days = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};

    DBHelper dbHelper;

    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        final View v = inflater.inflate(R.layout.fragment_example, container, false);
        final FragmentManager fm = getFragmentManager();


        //Connect DB
        dbHelper = new DBHelper(getActivity());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //Find view witch contain  day item


        //FAB initialization

        final FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab_main);
        fab.bringToFront();

        fab.setClickable(true);


        if (MethodHelper.swap == 0) {
            startMain(v, fab,false);
        } else {
            startMainEditable(v, fab);
        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                switch (MethodHelper.swap) {
                    case 0:
                        startMainEditable(v, fab);
                        break;
                    case 1:
                        startMain(v, fab,true);
                        break;
                }

            }
        });


        return v;
    }


    @Override
    public void onStart() {
        super.onStart();

    }


    public ArrayList<String> getAllData(String day) {
        String columns[] = new String[]{"time", "subject", "room", "teacher", "type", "building"};
        ArrayList<String> data = new ArrayList<>();

        for (int i = 0; i < columns.length; i++) {

            for (int j = 0; j < getData(day, columns[i]).length; j++) {

                data.add(getData(day, columns[i])[j]);

            }


        }


        return data;
    }

    public String[] getData(String day, String column) {
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


    public int getRowCount(String dayName) {
        String countQuery = "SELECT  * FROM " + dayName;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;

    }


    public void startMain(View v, FloatingActionButton fab,boolean animated) {

        MethodHelper.swap = 0;

        removeAllView(v);

        fab.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_edit));

        LinearLayout lv = (LinearLayout) v.findViewById(R.id.lvMain);

        int countOfRows = 0;


        for (int i = 0; i < 7; i++) {
            if (getRowCount(days[i]) == 0) {


            } else {

                View temp = new DayItem(getAllData(days[i]), days[i], getActivity(), false,animated).itemDay();

                lv.addView(temp);


            }
            countOfRows += getRowCount(days[i]);

        }

    }

    public void removeAllView(View v) {

        LinearLayout lv = (LinearLayout) v.findViewById(R.id.lvMain);

        lv.removeAllViews();


    }

    public void startMainEditable(View v, FloatingActionButton fab) {


        MethodHelper.swap = 1;

        removeAllView(v);

        fab.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_accept));

        LinearLayout lv = (LinearLayout) v.findViewById(R.id.lvMain);
        int countOfRows = 0;


        for (int i = 0; i < 7; i++) {


            View temp = new DayItem(getAllData(days[i]), days[i], getActivity(), true,false).itemDay();

            lv.addView(temp);


            countOfRows += getRowCount(days[i]);

        }

    }


}




