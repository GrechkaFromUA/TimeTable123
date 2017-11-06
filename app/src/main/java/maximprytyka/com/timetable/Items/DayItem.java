package maximprytyka.com.timetable.Items;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import maximprytyka.com.timetable.Animations.ZoomOut;
import maximprytyka.com.timetable.Animations.ZoomOutBack;
import maximprytyka.com.timetable.Fragmets.AddSubFragment;
import maximprytyka.com.timetable.MethodHelper;
import maximprytyka.com.timetable.R;


public class DayItem {
    ImageButton bt;
    TextView tw;


    private int animetedBack;
    private ArrayList<String> subjectArr;
    private boolean edit;
    private boolean backAnim;
    private String dayName;
    private Activity activity;

    public DayItem(int animetedBack, ArrayList<String> subjectarr, String dayName, Activity activity, boolean edit, boolean backAnim) {
        this.animetedBack = animetedBack;
        this.subjectArr = subjectarr;
        this.activity = activity;
        this.dayName = dayName;
        this.edit = edit;
        this.backAnim = backAnim;
    }

    public View itemDay() {

        //Inflate from other xml
        final LayoutInflater li = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        LinearLayout ll = (LinearLayout) li.inflate(R.layout.item_day, null, false);


        //Find and change text on TextView
        bt = (ImageButton) ll.findViewById(R.id.add_butt);
        tw = (TextView) ll.findViewById(R.id.dayName);

        LinearLayout.LayoutParams buttParams = (LinearLayout.LayoutParams) bt.getLayoutParams();
        LinearLayout.LayoutParams textParams = (LinearLayout.LayoutParams) tw.getLayoutParams();


        String day = dayName.substring(0, 1).toUpperCase() + dayName.substring(1).toLowerCase();
        tw.setText(day);


        if (animetedBack == 0) {
            bt.getLayoutParams().height = 0;

            if(backAnim){
                tw.getLayoutParams().height=0;
            }
        }


        if (animetedBack == 1) {


            ZoomOut anim = new ZoomOut(bt, buttParams.width, buttParams.height, buttParams.width, 0);
            bt.setAnimation(anim);

            if (backAnim) {

                ZoomOut textAnim = new ZoomOut(tw, textParams.width, textParams.height, textParams.width, 0);

                tw.setAnimation(textAnim);


            }

        }


        if (animetedBack == 2) {
            int temp = bt.getLayoutParams().height;
            bt.getLayoutParams().height = 0;
            ZoomOutBack zob = new ZoomOutBack(bt, buttParams.width, 0, buttParams.width, temp);
            bt.setAnimation(zob);

            if (backAnim) {
                int tmp = tw.getLayoutParams().height;
                tw.getLayoutParams().height = 0;
                ZoomOutBack textAnim = new ZoomOutBack(tw, textParams.width, 0, textParams.width, tmp);
                tw.setAnimation(textAnim);

            }


        }


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MethodHelper mh = new MethodHelper();
                mh.clearStaticVars();
                activity.getFragmentManager().beginTransaction().replace(R.id.content_frame, new AddSubFragment(dayName)).commit();

            }
        });


        //Find view witch contain SubjectItems
        LinearLayout view = (LinearLayout) ll.findViewById(R.id.content);


        int repeat = subjectArr.size() / 6;

        if (subjectArr.size() > 0) {
            for (int i = 0; i < repeat; i++) {


                view.addView(new SubjectItem(this.subjectArr.get(0 + i), this.subjectArr.get(0 + repeat + i), this.subjectArr.get(0 + 2 * repeat + i), this.subjectArr.get(0 + 3 * repeat + i), this.subjectArr.get(0 + 4 * repeat + i), this.subjectArr.get(0 + 5 * repeat + i), activity, this.edit, dayName).item());


            }
        }




        return ll;
    }

}

