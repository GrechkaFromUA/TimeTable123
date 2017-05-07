package maximprytyka.com.timetable.Fragmets;


import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import maximprytyka.com.timetable.DBHelper;
import maximprytyka.com.timetable.MethodHelper;
import maximprytyka.com.timetable.R;

import static maximprytyka.com.timetable.DBHelper.KEY_VALUE;


public class BuildsFragment extends Fragment{
    String var;
    private ListView lv;
    ArrayList<String> values = new ArrayList<>();
    //SQlite Components
    DBHelper dbHelper;
    final String table = DBHelper.TABLE_BUILDINGS;
    SQLiteDatabase db;


    ArrayAdapter<String> adapter;
    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_builds,container,false);

        final FragmentManager fm = getFragmentManager();


        final FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab_tea);
        fab.bringToFront();
        fab.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.plus));
        fab.setClickable(true);


        dbHelper = new DBHelper(getActivity());
        db = dbHelper.getWritableDatabase();
        db.execSQL("create table if not exists "+table+" ("+DBHelper.KEY_ID+" integer primary key,"+KEY_VALUE+" text)");

        lv = (ListView) v.findViewById(R.id.listView);


        final MethodHelper mh = new MethodHelper();








        //put From DB



        values =   mh.readFromDB(values,db,table);

        //Start create a items

        adapter =  new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, values);

        lv.setAdapter(adapter);






        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mh.fabClick(getActivity(),table,values,db,adapter);

            }
        });




        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                mh.showPopupMenu(view,getActivity(),db,values,adapter,table);
                return false;
            }
        });

        return v;
    }











}
