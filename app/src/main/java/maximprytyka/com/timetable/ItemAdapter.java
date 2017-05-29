package maximprytyka.com.timetable;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import maximprytyka.com.timetable.Fragmets.CostumListView;

/**
 * Created by GrechkaFromUA on 14.05.2017.
 */

public class ItemAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] values;


public ItemAdapter(Context context, String[] values){
    super(context,R.layout.item_day,values);
    this.context = context;
    this.values = values;




}



public View getView(int position, View convertView, ViewGroup parent){

    LayoutInflater inflater = (LayoutInflater) context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    View rowView = inflater.inflate(R.layout.item_day,null);

    TextView tw = (TextView) rowView.findViewById(R.id.dayName);

    tw.setText(values[position]);


    CostumListView lv;
    lv = (CostumListView) rowView.findViewById(R.id.lv);

    String[] text = {"1","2","3"};
    ArrayList<String> ar= new ArrayList<String>();
    for(int i=1;i<=3;i++){

        ar.add(text[i-1]);
    }
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(rowView.getContext(),android.R.layout.simple_expandable_list_item_1,ar);

    lv.setAdapter(adapter);
    return  rowView;
}



}
