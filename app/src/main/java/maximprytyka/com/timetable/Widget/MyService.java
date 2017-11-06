package maximprytyka.com.timetable.Widget;

/**
 * Created by maksimkuc on 11/6/17.
 */

import android.content.Intent;
import android.widget.RemoteViewsService;

public class MyService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MyFactory(getApplicationContext(), intent);
    }

}