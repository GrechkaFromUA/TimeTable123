package maximprytyka.com.timetable;

import android.app.Activity;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import maximprytyka.com.timetable.Fragmets.MainScreenFragment;

/**
 * Created by maksimkuc on 8/24/17.
 */

public class SubjectItem {

    private String timeStart;
    private String timeEnd;
    private String teacher;
    private String name;
    private String type;
    private  Activity ac;
    private View v;
    private  LayoutInflater inflater;

    public SubjectItem(String name,Activity ac) {

        this.name = name;
        this.ac = ac;

    }




    public ViewGroup item(){
        LayoutInflater li = (LayoutInflater) ac.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        LinearLayout ll = (LinearLayout) li.inflate(R.layout.list_item,null,false);
        TextView name = (TextView) ll.findViewById(R.id.sub);
        name.setText(this.name);




      return  ll;
    }



}
