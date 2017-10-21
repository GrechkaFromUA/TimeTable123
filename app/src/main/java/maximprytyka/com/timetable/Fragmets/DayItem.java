package maximprytyka.com.timetable.Fragmets;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import maximprytyka.com.timetable.MethodHelper;
import maximprytyka.com.timetable.R;
import maximprytyka.com.timetable.SubjectItem;




/**
 * Created by maksimkuc on 9/2/17.
 */

public class DayItem {

    private ArrayList<String> subjectArr;
    private boolean edit;
    private  String dayName;
    private Activity activity;

    public DayItem(ArrayList<String> subjectarr,String dayName,Activity activity,boolean edit){

        this.subjectArr = subjectarr;
        this.activity = activity;
        this.dayName = dayName;
        this.edit=edit;

    }

   public View itemDay(){

       //Inflate from other xml
       LayoutInflater li = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

       LinearLayout ll = (LinearLayout) li.inflate(R.layout.item_day,null,false);
        //Find and change text on TextView

       LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
               LinearLayout.LayoutParams.WRAP_CONTENT);

       Button bt = (Button) ll.findViewById(R.id.add_butt);
       if(this.edit == true){


       }else{

           params.height=0;
       }
        bt.setLayoutParams(params);

       bt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               MethodHelper mh = new MethodHelper();
               mh.clearStaticVars();
               activity.getFragmentManager().beginTransaction().replace(R.id.content_frame, new AddSubFragment(dayName)).commit();

           }
       });
       TextView tw = (TextView) ll.findViewById(R.id.dayName);

       String day = dayName.substring(0,1).toUpperCase() + dayName.substring(1).toLowerCase();


       tw.setText(day);

        //Find view witch contain SubjectItems
        LinearLayout view = (LinearLayout) ll.findViewById(R.id.content);




       int repeat = subjectArr.size()/6;

    if(subjectArr.size()>0) {
        for (int i = 0; i < repeat; i++) {


            view.addView(new SubjectItem(this.subjectArr.get(0 + i), this.subjectArr.get(0 + repeat + i), this.subjectArr.get(0 + 2 * repeat + i), this.subjectArr.get(0 + 3 * repeat + i), this.subjectArr.get(0 + 4 * repeat + i), this.subjectArr.get(0 + 5 * repeat + i), activity,this.edit,dayName).item());


        }
    }


    return  ll;
   }





}
