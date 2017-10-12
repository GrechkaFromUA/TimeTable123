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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import maximprytyka.com.timetable.DBHelper;
import maximprytyka.com.timetable.MethodHelper;
import maximprytyka.com.timetable.R;

import static maximprytyka.com.timetable.DBHelper.KEY_VALUE;


public class ChooseFragment extends Fragment{

    private String column;
    private String table;
    ListView lv;
    ArrayList<String> values = new ArrayList<>();
    ArrayAdapter<String> adapter;

    public ChooseFragment(String table,String column) {
        this.column = column;
        this.table = table;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_choose,container,false);

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

       lv = (ListView) v.findViewById(R.id.choose_list);

        MethodHelper mh = new MethodHelper();
        DBHelper dbHelper = new DBHelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        values = mh.getAllStringValues(db,table,column);

        adapter =  new ArrayAdapter<>(getActivity(), android.R.layout.simple_expandable_list_item_1, values);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getActivity(),"test",Toast.LENGTH_LONG).show();
            }
        });




        return v;
    }











}
