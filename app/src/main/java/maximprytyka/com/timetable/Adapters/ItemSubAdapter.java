package maximprytyka.com.timetable.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import maximprytyka.com.timetable.Adapters.ItemSub;
import maximprytyka.com.timetable.R;

public class ItemSubAdapter extends ArrayAdapter<ItemSub>{


    public ItemSubAdapter( Context context,  List<ItemSub> objects) {
        super(context, 0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ItemSub item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView sub = (TextView) convertView.findViewById(R.id.sub);
        sub.setText(item.getSub());
        TextView stime = (TextView) convertView.findViewById(R.id.stime);
        stime.setText(item.getsTime());
        TextView etime = (TextView) convertView.findViewById(R.id.etime);
        etime.setText(item.geteTime());
        TextView type = (TextView) convertView.findViewById(R.id.type);
        type.setText(item.getType());
        TextView build = (TextView) convertView.findViewById(R.id.build);
        build.setText(item.getBuild());
        TextView teacher = (TextView) convertView.findViewById(R.id.teacher);
        teacher.setText(item.getTeacher());

        return convertView;
    }


}