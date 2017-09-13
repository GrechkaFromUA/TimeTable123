package maximprytyka.com.timetable.Fragmets;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import maximprytyka.com.timetable.R;
import maximprytyka.com.timetable.SubjectItem;

/**
 * Created by maksimkuc on 9/2/17.
 */

public class DayItem {

    private ArrayList<SubjectItem> subjectItems;
    private int count;
    private  String day;
    private Activity activity;

    public DayItem(ArrayList<SubjectItem> subjectItems,String day,Activity activity){

        this.subjectItems = subjectItems;
        this.activity = activity;

    }

   public View itemDay(){
       LayoutInflater li = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

       LinearLayout ll = (LinearLayout) li.inflate(R.layout.list_item,null,false);






    return  ll;
   }







}
