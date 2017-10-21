package maximprytyka.com.timetable.Fragmets;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import maximprytyka.com.timetable.DBHelper;
import maximprytyka.com.timetable.MethodHelper;
import maximprytyka.com.timetable.R;


public class AddSubFragment extends Fragment {

    private String day;
    private boolean edit;

    public AddSubFragment(String day) {
        this.day = day;
        edit = false;
    }

    public AddSubFragment(String day, boolean edit) {
        this.day = day;
        this.edit = edit;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Nullable

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_content_add, container, false);
        FragmentManager fm = getFragmentManager(); //Фрагмент менеджер

        if (edit == false) {
            fm.beginTransaction().replace(R.id.frame, new AddSubPreferenceFragment(day)).commit();
        } else {
            fm.beginTransaction().replace(R.id.frame, new AddSubPreferenceFragment(day, true)).commit();
        }
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.bringToFront();
        fab.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_accept));
        fab.setClickable(true);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edit == false) {


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


                } else {


                }

            }


        });


        return v;
    }


}
