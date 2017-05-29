package maximprytyka.com.timetable.Fragmets;


import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import maximprytyka.com.timetable.DBHelper;
import maximprytyka.com.timetable.Adapters.ItemDayAdapter;
import maximprytyka.com.timetable.R;


public class ExampleFragment extends Fragment {

     String[] days = {"Monday","Vivtorok","Sereda","Chetver","Pyatnytsa","Subota","Nedilya"};
    ArrayList<String> temp = new ArrayList<>();
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


        ItemDayAdapter id = new ItemDayAdapter(getActivity(),days);
        lv.setAdapter(id);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fm.beginTransaction().setCustomAnimations(R.animator.slide_in_left2,R.animator.slide_in_right2).replace(R.id.content_frame, new ExampleFragment2()).commit(); //Заміна фрагмента по нажаттю кнопки
            }
        });

        return v;
    }
}
