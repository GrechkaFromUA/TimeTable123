package maximprytyka.com.timetable.Fragmets;


import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import maximprytyka.com.timetable.DBHelper;
import maximprytyka.com.timetable.R;


public class TeachersFragment extends Fragment {

    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_example,container,false);
        final FragmentManager fm = getFragmentManager();
        final FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.edit));


        //SQlite Components
        DBHelper dbHelper = new DBHelper(getActivity());

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.execSQL("create table if not exists "+dbHelper.TABLE_TEACHERS+" ("+dbHelper.KEY_ID+" integer primary key,"+dbHelper.KEY_VALUE+"text)");




        //put From DB
        Cursor cursor = db.query(DBHelper.TABLE_TEACHERS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_VALUE);
            do {
                Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                        ", value = " + cursor.getString(nameIndex));
            } while (cursor.moveToNext());
        } else
            Log.d("mLog","0 rows");

        cursor.close();






        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //fm.beginTransaction().replace(R.id.content_frame, new EditFragment()).commit(); //Заміна фрагмента по нажаттю кнопки
            }
        });

        return v;
    }
}
