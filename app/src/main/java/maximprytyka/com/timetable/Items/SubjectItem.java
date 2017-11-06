package maximprytyka.com.timetable.Items;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import maximprytyka.com.timetable.DBHelper;
import maximprytyka.com.timetable.Fragmets.AddSubFragment;
import maximprytyka.com.timetable.Fragmets.AddSubPreferenceFragment;
import maximprytyka.com.timetable.Fragmets.MainScreenFragment;
import maximprytyka.com.timetable.Fragmets.TypeFragment;
import maximprytyka.com.timetable.MethodHelper;
import maximprytyka.com.timetable.R;

import static maximprytyka.com.timetable.R.id.dayName;
import static maximprytyka.com.timetable.R.id.icon;
import static maximprytyka.com.timetable.R.id.visible;
import static maximprytyka.com.timetable.R.id.wrap_content;

public class SubjectItem {

    private String time;
    private String subject;
    private String room;
    private String teacher;
    private String type;
    private String building;
    private boolean edit;

    private String dayName;

    private Activity ac;


    public SubjectItem(String time, String subject, String room, String teacher, String type, String building, Activity ac, boolean edit, String dayName) {
        this.time = time;
        this.subject = subject;
        this.room = room;
        this.teacher = teacher;
        this.type = type;
        this.building = building;
        this.ac = ac;
        this.edit = edit;
        this.dayName = dayName;
    }


    public LinearLayout item() {
        LayoutInflater li = (LayoutInflater) ac.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        LinearLayout ll = (LinearLayout) li.inflate(R.layout.list_item, null, false);
        TextView subject = (TextView) ll.findViewById(R.id.sub);
        subject.setText(this.subject);

        TextView teacher = (TextView) ll.findViewById(R.id.teacher);
        teacher.setText(this.teacher);

        TextView type = (TextView) ll.findViewById(R.id.type);
        type.setText(this.type);


        if (!this.time.equals("temp")) {
            String ttime[] = this.time.split(" ");


            TextView timeStart = (TextView) ll.findViewById(R.id.stime);
            timeStart.setText(ttime[0]);

            TextView timeEnd = (TextView) ll.findViewById(R.id.etime);
            timeEnd.setText(ttime[2]);
        } else {

            TextView timeStart = (TextView) ll.findViewById(R.id.stime);
            timeStart.setText("-");

            TextView timeEnd = (TextView) ll.findViewById(R.id.etime);
            timeEnd.setText("-");

        }

        TextView build = (TextView) ll.findViewById(R.id.corpus);
        build.setText(this.building);

        TextView room = (TextView) ll.findViewById(R.id.room);
        room.setText(this.room);

        LinearLayout butLoy = (LinearLayout) ll.findViewById(R.id.but_loy);

        LinearLayout.LayoutParams parametrs = (LinearLayout.LayoutParams) butLoy.getLayoutParams();


        Animation animation;
        animation = AnimationUtils.loadAnimation(ac,
                R.anim.move_left);

        if (edit == true) {

            butLoy.setVisibility(LinearLayout.VISIBLE);
            // b.setVisibility(Button.VISIBLE);
        } else {
            parametrs.height = 0;
            parametrs.weight = 0;
            // b.setHeight(0);
            // b.setWidth(0);
            // b.setVisibility(Button.INVISIBLE);
        }
        butLoy.setLayoutParams(parametrs);
        butLoy.startAnimation(animation);
        ImageButton del_but = (ImageButton) ll.findViewById(R.id.remove_button);

        final String query = "DELETE FROM " + dayName + " WHERE " +
                "subject LIKE '%" + this.subject + "%' AND " +
                "time LIKE '%" + this.time + "%' AND " +
                "building LIKE '%" + this.building + "%' AND " +
                "room LIKE '%" + this.room + "%' AND " +
                "teacher LIKE '%" + this.teacher + "%' AND " +
                "type LIKE '%" + this.type + "%' ";

        del_but.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(ac);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL(query);
                db.close();

                MethodHelper.swap = 1;
                ac.getFragmentManager().beginTransaction().replace(R.id.content_frame, new MainScreenFragment()).commit();
            }
        });

        ImageButton edit_but = (ImageButton) ll.findViewById(R.id.edit);

        edit_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                DBHelper dbHelper = new DBHelper(ac);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL(query);
                db.close();

                writeStaticVars();

                ac.getFragmentManager().beginTransaction().replace(R.id.content_frame, new AddSubFragment(dayName, true)).commit();


            }
        });







        if (edit == true) {
            butLoy.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
        } else {
            butLoy.getLayoutParams().width =0;
        }




        return ll;
    }

    public void writeStaticVars() {

        AddSubPreferenceFragment.subject = this.subject;
        AddSubPreferenceFragment.time = this.time;
        AddSubPreferenceFragment.building = this.building;
        AddSubPreferenceFragment.room = this.room;
        AddSubPreferenceFragment.teacher = this.teacher;
        AddSubPreferenceFragment.type = this.type;


    }


}
