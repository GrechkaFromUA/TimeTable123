package maximprytyka.com.timetable.Fragmets;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import maximprytyka.com.timetable.MethodHelper;
import maximprytyka.com.timetable.R;
import maximprytyka.com.timetable.SubjectItem;



public class DayItem {
    Button bt;


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
        bt = (Button) ll.findViewById(R.id.add_butt);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) bt.getLayoutParams();

        Animation animation;

        animation = AnimationUtils.loadAnimation(activity,
                    R.anim.zoom_in);








            if(animetedBack == 0){

                bt.getLayoutParams().height=0;
            }


        if(animetedBack == 1){



            Animation back;
            back = AnimationUtils.loadAnimation(activity,
                    R.anim.zoom_in_back);

            bt.startAnimation(back);


            bt.getAnimation().setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    Toast.makeText(activity,"tetst",Toast.LENGTH_SHORT).show();
                    buttontozero();


                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

        }


        if(animetedBack == 2){

            bt.setAnimation(animation);

        }




        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MethodHelper mh = new MethodHelper();
                mh.clearStaticVars();
                activity.getFragmentManager().beginTransaction().replace(R.id.content_frame, new AddSubFragment(dayName)).commit();

            }
        });





        TextView tw = (TextView) ll.findViewById(R.id.dayName);
        String day = dayName.substring(0, 1).toUpperCase() + dayName.substring(1).toLowerCase();
        tw.setText(day);


        if(backAnim){
            tw.setAnimation(animation);
        }

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



public void buttontozero(){
    bt.getLayoutParams().height=0;
}


}
