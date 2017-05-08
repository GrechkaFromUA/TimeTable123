package maximprytyka.com.timetable.Fragmets;


import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import maximprytyka.com.timetable.R;


public class TimeAddFragment extends Fragment {

    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_time_add,container,false);

        TimePicker tp1,tp2;

        tp1 = (TimePicker)v.findViewById(R.id.timePicker); //Найти по ID TimePicker
        tp2 = (TimePicker)v.findViewById(R.id.timePicker2);

        tp1.setIs24HourView(true); //Якщо true - пропадає AM i PM, день стає 24-х годинний.
        tp2.setIs24HourView(true);

        final FragmentManager fm = getFragmentManager();
        final FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);

        fab.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.accept));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fm.beginTransaction().replace(R.id.content_frame, new TimeFragment()).commit(); //Заміна фрагмента по нажаттю кнопки
                Toast.makeText(getActivity(),R.string.success_add, Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}
