package maximprytyka.com.timetable.Fragmets;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import maximprytyka.com.timetable.DBHelper;
import maximprytyka.com.timetable.MethodHelper;
import maximprytyka.com.timetable.R;
import maximprytyka.com.timetable.SubjectItem;

import static android.support.v7.preference.R.id.wrap_content;
import static android.widget.GridLayout.VERTICAL;


public class MainScreenFragment extends Fragment {

     String[] days = {"monday","tuesday","wednesday","thursday","friday","saturday","sunday"};
    LinearLayout ll;
    DBHelper dbHelper;
    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            MethodHelper.swap = 0;



        final View v = inflater.inflate(R.layout.fragment_example,container,false);
        final FragmentManager fm = getFragmentManager();







        //Connect DB
        dbHelper= new DBHelper(getActivity());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //Find view witch contain  day item

        //Start read from DB

        startMain(v);

        //FAB initialization

        final FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab_main);
        fab.bringToFront();
        fab.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_edit));
        fab.setClickable(true);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                switch (MethodHelper.swap){
                    case 0 :

                        fab.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_accept));
                        MethodHelper.swap=1;
                        removeAllView(v);
                        startMainEditable(v);

                        break;

                    case 1:
                        fab.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_edit));
                        MethodHelper.swap = 0;
                        removeAllView(v);
                        startMain(v);
                        break;





                }





            }
        });





        return v;
    }

    public ArrayList<String> getAllData(String day){
        String columns[] = new String[]{"time","subject","room","teacher","type","building"};
        ArrayList<String> data = new ArrayList<>();

        for(int i =0;i<columns.length;i++){

            for(int j=0;j<getData(day,columns[i]).length;j++){

                data.add(getData(day,columns[i])[j]);

            }


        }



        return data;
    }

    public String[] getData(String day,String column){
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery("SELECT * FROM "+ day, null);
        cursor.moveToFirst();
        ArrayList<String> names = new ArrayList<>();


        while(!cursor.isAfterLast()) {
            names.add(cursor.getString(cursor.getColumnIndex(column)));
            cursor.moveToNext();
        }
        cursor.close();
        return names.toArray(new String[names.size()]);
    }


    public int getRowCount(String dayName) {
        int cnt=0;
        Cursor c = dbHelper.getReadableDatabase().rawQuery("select * from "+dayName,null);
           if(c.getCount()>0){
               c.moveToFirst();
               cnt = c.getInt(0);
           }

           c.close();
        return cnt;
    }


    public void startMain(View v){

        LinearLayout lv = (LinearLayout) v.findViewById(R.id.lvMain);
        int countOfRows=0;


        for (int i = 0; i < 7; i++) {
            if(getRowCount(days[i]) == 0 ){



            }else {

                View temp = new DayItem(getAllData(days[i]),days[i],getActivity(),false).itemDay();

                lv.addView(temp);


            }
            countOfRows+=getRowCount(days[i]);

        }

    }

    public void removeAllView(View v){

        LinearLayout lv = (LinearLayout) v.findViewById(R.id.lvMain);

        lv.removeAllViews();


    }

    public void startMainEditable(View v){

        LinearLayout lv = (LinearLayout) v.findViewById(R.id.lvMain);
        int countOfRows=0;


        for (int i = 0; i < 7; i++) {


                View temp = new DayItem(getAllData(days[i]),days[i],getActivity(),true).itemDay();

                lv.addView(temp);



            countOfRows+=getRowCount(days[i]);

        }

    }



}




