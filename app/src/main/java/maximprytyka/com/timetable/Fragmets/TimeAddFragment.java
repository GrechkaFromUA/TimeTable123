package maximprytyka.com.timetable.Fragmets;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

import maximprytyka.com.timetable.DBHelper;
import maximprytyka.com.timetable.R;


public class TimeAddFragment extends Fragment {


    public Boolean timetype = true;
    String time;
    TimePicker tp1,tp2;
    SQLiteDatabase db;
    final String table = DBHelper.TABLE_TIME;
    private boolean add;
    private int tp1Min,tp1Hour,tp2Min,tp2Hour;
    private String oldValue;
    ArrayList<String> values;
    FragmentManager fm;


    public TimeAddFragment(){

    }

    public TimeAddFragment(boolean add, ArrayList<String> values){

        this.add = add;
        this.values = values;
    }

    public TimeAddFragment(boolean add,int tp1Min, int tp1Hour,int tp2Min,int tp2Hour,String oldValue,ArrayList<String> values){

        this.add = add;
        this.tp1Min = tp1Min;
        this.tp2Min = tp2Min;
        this.tp1Hour = tp1Hour;
        this.tp2Hour = tp2Hour;
        this.oldValue = oldValue;
        this.values = values;

    }

    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_time_add,container,false);



        DBHelper dbHelper = new DBHelper(getActivity());
        db = dbHelper.getWritableDatabase();



        tp1 = (TimePicker)v.findViewById(R.id.timePicker); //Найти по ID TimePicker
        tp2 = (TimePicker)v.findViewById(R.id.timePicker2);

        tp1.setIs24HourView(timetype); //Якщо true - пропадає AM i PM, день стає 24-х годинний.
        tp2.setIs24HourView(timetype);


        tp1.setCurrentHour(tp1Hour);
        tp2.setCurrentHour(tp2Hour);
        tp1.setCurrentMinute(tp1Min);
        tp2.setCurrentMinute(tp2Min);

        fm = getFragmentManager();
        final FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);

        fab.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_accept));



        v.setFocusableInTouchMode(true);
        v.requestFocus();
        v.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if( keyCode == KeyEvent.KEYCODE_BACK ) {

                    fm.beginTransaction().setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right).replace(R.id.content_frame, new TimeFragment()).commit();
                    return true;
                } else {
                    return false;
                }
            }
        });



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(add == true) {
                    String tt1 = null, tt2 = null,th1=null,th2=null;

                    for(int i=0;i<10;i++){
                        String temp = "0"+i;
                        if (String.valueOf(tp1.getCurrentMinute()).equals(String.valueOf(i))){ tt1 = temp; break;}
                        else tt1 = String.valueOf(tp1.getCurrentMinute());

                    }
                    for(int i=0;i<10;i++){
                        String temp = "0"+i;
                        if (String.valueOf(tp2.getCurrentMinute()).equals(String.valueOf(i))){ tt2 = temp; break;}
                        else tt2 = String.valueOf(tp2.getCurrentMinute());

                    }

                    for(int i=0;i<10;i++){
                        String temp = "0"+i;

                        if (String.valueOf(tp1.getCurrentHour()).equals(String.valueOf(i))){ th1 = temp; break;}
                        else th1 = String.valueOf(tp1.getCurrentHour());

                    }
                    for(int i=0;i<10;i++){
                        String temp = "0"+i;
                        if (String.valueOf(tp2.getCurrentHour()).equals(String.valueOf(i))){ th2 = temp; break;}
                        else th2 = String.valueOf(tp2.getCurrentHour());

                    }





                    String t1 = th1 + ":" + tt1 ;

                    String t2 = th2 + ":" + tt2;
                    time = t1 + " - " + t2;
                    if(!values.contains(time)) {

                        ContentValues cv = new ContentValues();
                        cv.put("value", time);
                        db.insert(table, null, cv);


                    fm.beginTransaction().setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right).replace(R.id.content_frame, new TimeFragment()).commit(); //Заміна фрагмента по нажаттю кнопки
                    Toast.makeText(getActivity(), R.string.success_add, Toast.LENGTH_SHORT).show();}
                    else{Toast.makeText(getActivity(),R.string.error_same_value,Toast.LENGTH_SHORT).show();}

                }else{




                    String tt1 = null, tt2 = null,th1=null,th2=null;

                   for(int i=0;i<10;i++){
                       String temp = "0"+i;
                       if (String.valueOf(tp1.getCurrentMinute()).equals(String.valueOf(i))){ tt1 = temp; break;}
                       else tt1 = String.valueOf(tp1.getCurrentMinute());

                   }
                    for(int i=0;i<10;i++){
                        String temp = "0"+i;
                        if (String.valueOf(tp2.getCurrentMinute()).equals(String.valueOf(i))){ tt2 = temp; break;}
                        else tt2 = String.valueOf(tp2.getCurrentMinute());

                    }
                    for(int i=0;i<10;i++){
                        String temp = "0"+i;

                        if (String.valueOf(tp1.getCurrentHour()).equals(String.valueOf(i))){ th1 = temp; break;}
                        else th1 = String.valueOf(tp1.getCurrentHour());

                    }
                    for(int i=0;i<10;i++){
                        String temp = "0"+i;
                        if (String.valueOf(tp2.getCurrentHour()).equals(String.valueOf(i))){ th2 = temp; break;}
                        else th2 = String.valueOf(tp2.getCurrentHour());

                    }



                    String t1 = th1 + ":" + tt1 ;
                    
                    String t2 = th2 + ":" + tt2;


                    time = t1 + " - " + t2;

                    ContentValues cv = new ContentValues();
                    cv.put("value", time);
                    db.update(table, cv, "value = ?",
                            new String[]{oldValue});

                    fm.beginTransaction().setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right).replace(R.id.content_frame, new TimeFragment()).commit(); //Заміна фрагмента по нажаттю кнопки
                    Toast.makeText(getActivity(), R.string.success_rename, Toast.LENGTH_SHORT).show();

                }
            }
        });








        return v;
    }







}
