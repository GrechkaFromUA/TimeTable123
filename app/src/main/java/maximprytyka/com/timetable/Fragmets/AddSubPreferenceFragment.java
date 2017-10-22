package maximprytyka.com.timetable.Fragmets;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import android.preference.EditTextPreference;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import maximprytyka.com.timetable.DBHelper;
import maximprytyka.com.timetable.MethodHelper;
import maximprytyka.com.timetable.R;


public class AddSubPreferenceFragment extends PreferenceFragment {

    public static String subject;
    public static String time;
    public static String building;
    public static String room;
    public static String teacher;
    public static String type;



    public static final String[] tables = {"subjects", "time", "buildings", "room", "teachers", "lesson_type"};


    private String day;
    private boolean edit;
    private FloatingActionButton fab;
    public AddSubPreferenceFragment(String day, FloatingActionButton fab) {
        this.day = day;
        this.edit = false;
        this.fab = fab;
    }

    public AddSubPreferenceFragment(String day,FloatingActionButton fab, boolean edit) {
        this.day = day;
        this.edit = edit;
        this.fab = fab;
    }

    @TargetApi(Build.VERSION_CODES.M)

    @Override
    public void onCreate(final Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.addsub); //файл в папці res/xml
        fab.bringToFront();

        for (int i = 0; i < tables.length; i++) {

            pref(tables[i]);
        }


        final EditTextPreference et = (EditTextPreference) findPreference("room");


        et.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                AddSubPreferenceFragment.room = o.toString();
                et.setSummary(o.toString());

                return false;
            }
        });


    }



    void pref(final String table) {

        Preference pref = findPreference(table); //найти пункт по параметру "key"
        switch (table) {

            case "subjects":
                if (AddSubPreferenceFragment.subject == null) {
                    AddSubPreferenceFragment.subject = "temp";

                }
                pref.setSummary(AddSubPreferenceFragment.subject);
                prefClick(pref, table);
                break;

            case "time":
                if (AddSubPreferenceFragment.time == null) {
                    AddSubPreferenceFragment.time = "temp";

                }
                pref.setSummary(AddSubPreferenceFragment.time);
                prefClick(pref, table);
                break;
            case "buildings":
                if (AddSubPreferenceFragment.building == null) {
                    AddSubPreferenceFragment.building = "temp";
                }
                pref.setSummary(AddSubPreferenceFragment.building);
                prefClick(pref, table);
                break;
            case "room":
                if (AddSubPreferenceFragment.room == null) {
                    AddSubPreferenceFragment.room = "temp";
                }
                pref.setSummary(AddSubPreferenceFragment.room);
                break;
            case "teachers":
                if (AddSubPreferenceFragment.teacher == null) {
                    AddSubPreferenceFragment.teacher = "temp";
                }
                pref.setSummary(AddSubPreferenceFragment.teacher);
                prefClick(pref, table);
                break;
            case "lesson_type":
                if (AddSubPreferenceFragment.type == null) {
                    AddSubPreferenceFragment.type = "temp";
                }
                pref.setSummary(AddSubPreferenceFragment.type);
                prefClick(pref, table);
                break;
        }












    }


    public void prefClick(Preference preference, final String table) {

        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                //Дія при нажатті
                getFragmentManager().beginTransaction().replace(R.id.frame, new ChooseFragment(table, day)).commit();


                return false;
            }
        });

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK) {

                    if(edit == true){
                        DBHelper dbHelper = new DBHelper(getActivity());
                        SQLiteDatabase db = dbHelper.getWritableDatabase();


                        db.execSQL("INSERT INTO " + day + "(subject,teacher,room,building,time,type) VALUES ( '"
                                + AddSubPreferenceFragment.subject + "','"
                                + AddSubPreferenceFragment.teacher + "','"
                                + AddSubPreferenceFragment.room + "','"
                                + AddSubPreferenceFragment.building + "','"
                                + AddSubPreferenceFragment.time + "','" +
                                AddSubPreferenceFragment.type + "')");

                        db.close();

                        MethodHelper.swap = 1;

                        getFragmentManager().beginTransaction().replace(R.id.content_frame, new MainScreenFragment()).commit();
                    }
                    else{

                        MethodHelper.swap = 1;

                        getFragmentManager().beginTransaction().replace(R.id.content_frame, new MainScreenFragment()).commit();
                    }


                    return true;
                } else {
                    return false;
                }
            }
        });

    }



}
