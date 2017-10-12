package maximprytyka.com.timetable.Fragmets;

import android.annotation.TargetApi;
import android.app.FragmentManager;
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


public class AddSubPreferenceFragment extends PreferenceFragment {
    private String day;

    public AddSubPreferenceFragment(String day) {
        this.day = day;
    }

    @TargetApi(Build.VERSION_CODES.M)

    @Override
    public void onCreate(final Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.addsub); //файл в папці res/xml

        final FragmentManager fm = getFragmentManager();

        Preference preference = findPreference("time"); //найти пункт по параметру "key"
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                //Дія при нажатті
               fm.beginTransaction().replace(R.id.frame, new ChooseFragment(day,"time")).commit();




                return false;
            }
        });
    }


}
