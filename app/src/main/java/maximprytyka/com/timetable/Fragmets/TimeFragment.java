package maximprytyka.com.timetable.Fragmets;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import maximprytyka.com.timetable.DBHelper;
import maximprytyka.com.timetable.MethodHelper;
import maximprytyka.com.timetable.R;

import static maximprytyka.com.timetable.DBHelper.KEY_VALUE;


public class TimeFragment extends Fragment {

    DBHelper dbHelper;
    SQLiteDatabase db;
    final String table = DBHelper.TABLE_TIME;
    MethodHelper mh;
    ArrayList<String> values = new ArrayList<>();
    ListView lv;
    ArrayAdapter<String> adapter;
    FragmentManager fm;


    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_time, container, false);

        dbHelper = new DBHelper((getActivity()));
        db = dbHelper.getWritableDatabase();

        db.execSQL("create table if not exists " + table + " (" + DBHelper.KEY_ID + " integer primary key," + KEY_VALUE + " text)");

        fm = getFragmentManager();

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        final FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.bringToFront();

        mh = new MethodHelper();

        lv = (ListView) v.findViewById(R.id.listView);

        values = mh.readFromDB(values, db, table);


        for (String counter : values) {
            Log.d("mLog", counter);
        }
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, values);
        lv.setAdapter(adapter);


        fab.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_plus));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fm.beginTransaction().setCustomAnimations(R.animator.slide_in_left2, R.animator.slide_in_right2).replace(R.id.content_frame, new TimeAddFragment(true, values)).commit(); //Заміна фрагмента по нажаттю кнопки
            }
        });


        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                showPopupMenu(view, getActivity());
                return false;
            }
        });




        


        return v;
    }


    public void showPopupMenu(final View v, final Activity ac) {
        PopupMenu popupMenu = new PopupMenu(ac, v);

        popupMenu.inflate(R.menu.popupmenu);
        popupMenu.setGravity(Gravity.RIGHT);

        popupMenu
                .setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // Toast.makeText(PopupMenuDemoActivity.this,
                        // item.toString(), Toast.LENGTH_LONG).show();
                        // return true;
                        final String var = ((TextView) v).getText().toString();
                        String sp1 = ":";
                        String sp2 = " - ";
                        String res = var.replaceAll(sp2, sp1);
                        String times[] = res.split(sp1);

                        switch (item.getItemId()) {

                            case R.id.del:


                                db.delete(table, "value=?", new String[]{var});


                                values.remove(var);
                                adapter.notifyDataSetChanged();


                                return true;
                            case R.id.rename:

                                fm.beginTransaction().setCustomAnimations(R.animator.slide_in_left2, R.animator.slide_in_right2).replace(R.id.content_frame, new TimeAddFragment(false, Integer.parseInt(times[1]), Integer.parseInt(times[0]), Integer.parseInt(times[3]), Integer.parseInt(times[2]), var, values)).commit(); //Заміна фрагмента по нажаттю кнопки

                                return true;

                            default:
                                return false;
                        }
                    }
                });


        popupMenu.show();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear_all:
                mh.cleareTable(db, table, getActivity(), values, adapter);
                return true;
            case R.id.exit:
                //Реалізовано в MainActivity
                return false;
            default:
                break;
        }

        return false;
    }


}
