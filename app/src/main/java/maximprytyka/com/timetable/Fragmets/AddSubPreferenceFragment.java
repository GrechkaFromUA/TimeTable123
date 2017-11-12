package maximprytyka.com.timetable.Fragmets;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import android.preference.EditTextPreference;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import maximprytyka.com.timetable.DBHelper;
import maximprytyka.com.timetable.MainActivity;
import maximprytyka.com.timetable.MethodHelper;
import maximprytyka.com.timetable.R;


public class AddSubPreferenceFragment extends PreferenceFragment {

    public static String subject;
    public static String time;
    public static String building;
    public static String room;
    public static String teacher;
    public static String type;



    public  final String[] tables = {"subjects", "time", "buildings", "room", "teachers", "lesson_type"};


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

        Preference pref = findPreference(table); //найти пункт по параметру "key"\
        Fragment fragment = null;
        String nav_name = null;
        switch (table) {

            case "subjects":
                if (AddSubPreferenceFragment.subject == null) {
                    AddSubPreferenceFragment.subject = "temp";

                }
                nav_name = getString(R.string.title_subjects);
                fragment = new SubjectsFragment();
                pref.setSummary(AddSubPreferenceFragment.subject);




                break;

            case "time":
                if (AddSubPreferenceFragment.time == null) {
                    AddSubPreferenceFragment.time = "temp";

                }
                nav_name = getString(R.string.title_time);
                fragment = new TimeFragment();
                pref.setSummary(AddSubPreferenceFragment.time);

                break;
            case "buildings":
                if (AddSubPreferenceFragment.building == null) {
                    AddSubPreferenceFragment.building = "temp";
                }
                nav_name = getString(R.string.title_buildings);
                fragment = new BuildsFragment();
                pref.setSummary(AddSubPreferenceFragment.building);

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
                nav_name = getString(R.string.title_teachers);
                fragment = new TeachersFragment();
                pref.setSummary(AddSubPreferenceFragment.teacher);

                break;
            case "lesson_type":
                if (AddSubPreferenceFragment.type == null) {
                    AddSubPreferenceFragment.type = "temp";
                }
                nav_name = getString(R.string.title_lessons_type);
                fragment = new TypeFragment();
                pref.setSummary(AddSubPreferenceFragment.type);

                break;
        }



        prefClick(pref, table,nav_name,fragment);










    }


    public void prefClick(Preference preference, final String table, final String nav_name, final Fragment f) {

        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                //Дія при нажатті
                if(getRowCount(table)<=0 && !table.equals("room")){//Якщо список Того чи Іншого пустий

                    TextView tw = new TextView(getActivity());
                    tw.setText(R.string.description_of_empty_list);
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle(R.string.empty_list_title)
                            .setView(tw)
                            .setCancelable(true)
                            .setPositiveButton(R.string.go_to, null)
                            .setNegativeButton(R.string.alert_cancel, null);

                    final AlertDialog alert = builder.create();

                   

                    alert.setOnShowListener(new DialogInterface.OnShowListener() {

                        @Override
                        public void onShow(DialogInterface dialog) {
                            Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                            button.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View view) {
                                    getFragmentManager().beginTransaction().replace(R.id.content_frame, f).commit();




                                    getActivity().setTitle(nav_name);
                                    DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
                                    drawer.closeDrawer(GravityCompat.START);
                                    alert.cancel();
                                }
                            });

                        }
                    });
                    alert.show();


                    // Toast.makeText(getActivity(),"nothing ",Toast.LENGTH_SHORT).show();
                }else {

                    getFragmentManager().beginTransaction().replace(R.id.frame, new ChooseFragment(table, day)).commit();

                }
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

    public int getRowCount(String column) {
        DBHelper dbHelper = new DBHelper(getActivity());
        String countQuery = "SELECT * FROM " + column;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;

    }


}
