package maximprytyka.com.timetable;

import android.app.Activity;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import maximprytyka.com.timetable.Fragmets.MainScreenFragment;

import static maximprytyka.com.timetable.R.id.visible;
import static maximprytyka.com.timetable.R.id.wrap_content;

/**
 * Created by maksimkuc on 8/24/17.
 */

public class SubjectItem {

    private String time;
    private String subject;
    private String room;
    private String teacher;
    private String type;
    private String building;
    private boolean edit;


    private  Activity ac;


    public SubjectItem(String time, String subject, String room, String teacher, String type, String building, Activity ac,boolean edit) {
        this.time = time;
        this.subject = subject;
        this.room = room;
        this.teacher = teacher;
        this.type = type;
        this.building = building;
        this.ac = ac;
        this.edit = edit;
    }



    public LinearLayout item(){
        LayoutInflater li = (LayoutInflater) ac.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        LinearLayout ll = (LinearLayout) li.inflate(R.layout.list_item,null,false);
        TextView subject = (TextView) ll.findViewById(R.id.sub);
        subject.setText(this.subject);

        TextView teacher = (TextView) ll.findViewById(R.id.teacher);
        teacher.setText(this.teacher);

        TextView type = (TextView) ll.findViewById(R.id.type);
        type.setText(this.type);

        String ttime[] = this.time.split(" ");


        TextView timeStart = (TextView) ll.findViewById(R.id.stime);
        timeStart.setText(ttime[0]);

        TextView timeEnd = (TextView) ll.findViewById(R.id.etime);
        timeEnd.setText(ttime[2]);

        TextView build = (TextView) ll.findViewById(R.id.build);
        build.setText(this.building);


        Button b = (Button) ll.findViewById(R.id.remove_button);
        if(edit == true) {
            b.setVisibility(Button.VISIBLE);
        }else{
            b.setHeight(0);
            b.setWidth(0);
            b.setVisibility(Button.INVISIBLE);
        }
        LinearLayout temp = (LinearLayout) ll.findViewById(R.id.main_sub);

        final LinearLayout.LayoutParams params  = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        if(edit==true){
            params.weight = 100f;
        }else{
            params.weight = 0f;
        }

        temp.setLayoutParams(params);



      return  ll;
    }



}
