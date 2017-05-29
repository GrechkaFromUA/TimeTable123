package maximprytyka.com.timetable.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import maximprytyka.com.timetable.R;

/**
 * Created by GrechkaFromUA on 14.05.2017.
 */

public class ItemDayAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] values;


public ItemDayAdapter(Context context, String[] values){
    super(context, R.layout.item_day,values);
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

    ArrayList<ItemSub> items = new ArrayList<>();

    for(int i =1;i<3;i++){
        items.add(new ItemSub("Math","10:00","11:00","Lection","A-323","Madam X"));
    }
ItemSubAdapter ad = new ItemSubAdapter(getContext(),items);

    lv.setAdapter(ad);

    return  rowView;
}



}
