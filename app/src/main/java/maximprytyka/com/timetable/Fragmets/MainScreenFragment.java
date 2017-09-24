package maximprytyka.com.timetable.Fragmets;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import maximprytyka.com.timetable.DBHelper;
import maximprytyka.com.timetable.R;
import maximprytyka.com.timetable.SubjectItem;

import static android.widget.GridLayout.VERTICAL;


public class MainScreenFragment extends Fragment {

     String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    LinearLayout ll;
    DBHelper dbHelper;
    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_example,container,false);

        final FragmentManager fm = getFragmentManager();
        final FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);

        dbHelper= new DBHelper(getActivity());

        LayoutInflater dopInf = getActivity().getLayoutInflater();



//        ll = (LinearLayout) v.findViewById(R.id.lvMain);
//
//        LinearLayout rl = (LinearLayout) dopInf.inflate(R.layout.list_item,null, false);
//
//        SubjectItem sb = new SubjectItem("Math",getActivity());
//
//
//        ll.addView(sb.item());
//
//        ll.addView(rl);


        LinearLayout parentLayout = (LinearLayout) v.findViewById(R.id.lvMain);
        LinearLayout linearLayout1 = (LinearLayout) dopInf.inflate(R.layout.list_item,null, false);
        LinearLayout linearLayout2 = (LinearLayout) dopInf.inflate(R.layout.list_item,null, false);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                AppBarLayout.LayoutParams.WRAP_CONTENT, AppBarLayout.LayoutParams.WRAP_CONTENT);

        params.gravity = Gravity.CENTER_VERTICAL;

        parentLayout.setOrientation(VERTICAL);
        parentLayout.addView(linearLayout1, params);
        parentLayout.addView(linearLayout2, params);





//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                fm.beginTransaction().setCustomAnimations(R.animator.slide_in_left2,R.animator.slide_in_right2).replace(R.id.content_frame, new ExampleFragment2()).commit(); //Заміна фрагмента по нажаттю кнопки
//            }
//        });

        return v;
    }




}
