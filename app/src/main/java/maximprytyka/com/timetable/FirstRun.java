package maximprytyka.com.timetable;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class FirstRun extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main); //we don't need this line
        SharedPreferences settings=getSharedPreferences("prefs",0);
        boolean firstRun=settings.getBoolean("firstRun",false);
        if(!firstRun)//if running for first time
        {
            SharedPreferences.Editor editor=settings.edit();
            editor.putBoolean("firstRun",true);
            editor.commit();
            Intent i=new Intent(FirstRun.this,IntroActivity.class);//Activity to be     launched For the First time
            startActivity(i);
            finish();
        }
        else
        {
            Intent a=new Intent(FirstRun.this,MainActivity.class);//Default Activity
            startActivity(a);
            finish();
        }
    }

}

