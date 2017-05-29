package maximprytyka.com.timetable.Fragmets;


import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import maximprytyka.com.timetable.DBHelper;
import maximprytyka.com.timetable.ItemAdapter;
import maximprytyka.com.timetable.R;


public class ExampleFragment extends Fragment {

     String[] days;
    String temp;
    DBHelper dbHelper;
    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_example,container,false);

        final FragmentManager fm = getFragmentManager();
        final FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);

        dbHelper= new DBHelper(getActivity());
        ListView lv = (ListView) v.findViewById(R.id.mainLv);
        for(int i=1;i<=7;i++)
            if (dbHelper.getProfilesCount(dbHelper.getNameTableByNumb(i)) == 0) {

            } else {
                temp += " " + dbHelper.getNameTableByNumb(i);
            }
        days =temp.split(" ");
        ItemAdapter id = new ItemAdapter(getActivity(),days);
        lv.setAdapter(id);

        fab.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_edit));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fm.beginTransaction().setCustomAnimations(R.animator.slide_in_left2,R.animator.slide_in_right2).replace(R.id.content_frame, new ExampleFragment2()).commit(); //Заміна фрагмента по нажаттю кнопки
            }
        });

        return v;
    }
}
