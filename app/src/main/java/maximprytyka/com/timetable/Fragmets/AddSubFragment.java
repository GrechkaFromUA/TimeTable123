package maximprytyka.com.timetable.Fragmets;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import maximprytyka.com.timetable.R;


public class AddSubFragment extends Fragment {

    @TargetApi(Build.VERSION_CODES.M)
    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_content_add, container, false);
        FragmentManager fm = getFragmentManager(); //Фрагмент менеджер

        fm.beginTransaction().replace(R.id.frame, new AddSubPreferenceFragment()).commit();

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.bringToFront();
        fab.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_accept));
        fab.setClickable(true);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action

            }
        });


        return v;
    }


}
