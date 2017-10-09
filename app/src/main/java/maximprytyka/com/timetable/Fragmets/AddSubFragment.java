package maximprytyka.com.timetable.Fragmets;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import maximprytyka.com.timetable.R;


public class AddSubFragment extends PreferenceFragment {

    @TargetApi(Build.VERSION_CODES.M)
    @Nullable

    @Override
    public void onCreate(final Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.addsub); //файл в папці res/xml

        FloatingActionButton fab = getFab(getActivity());
        fab.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_accept));

        Preference preference = findPreference("time_start"); //найти пункт по параметру "key"
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                //Дія при нажатті
                Toast.makeText(getActivity(), "clicked", Toast.LENGTH_SHORT).show();

                return false;
            }
        });
    }




    public FloatingActionButton getFab(Context context) {
        FloatingActionButton fab = new FloatingActionButton(context);



        return fab;
    }

}
