package maximprytyka.com.timetable;

import android.app.FragmentManager;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.SwitchCompat;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import maximprytyka.com.timetable.Fragmets.BuildsFragment;
import maximprytyka.com.timetable.Fragmets.MainScreenFragment;
import maximprytyka.com.timetable.Fragmets.SettingsFragment;
import maximprytyka.com.timetable.Fragmets.SubjectsFragment;
import maximprytyka.com.timetable.Fragmets.TeachersFragment;
import maximprytyka.com.timetable.Fragmets.TimeAddFragment;
import maximprytyka.com.timetable.Fragmets.TimeFragment;
import maximprytyka.com.timetable.Fragmets.TypeFragment;
import maximprytyka.com.timetable.Widget.Widget;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private AdView adView;
    private SwitchCompat switcher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        updateWidget();

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        //admob ad start
        MobileAds.initialize(this, "ca-app-pub-3940256099942544/6300978111");

        adView = (AdView) this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        //admob add end

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getFragmentManager().beginTransaction().replace(R.id.content_frame, new MainScreenFragment()).commit(); //Заміна фрагмента по нажаттю кнопки


        Menu menu = navigationView.getMenu();
        MenuItem menuItem = menu.findItem(R.id.nav_two_weeks);
        View actionView = MenuItemCompat.getActionView(menuItem);

        switcher = (SwitchCompat) actionView.findViewById(R.id.drawer_switch);
        switcher.setChecked(true);
        switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, (switcher.isChecked()) ? "is checked!!!" : "not checked!!!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first

        updateWidget();

    }
    @Override
    public void onDestroy() {
        super.onDestroy();  // Always call the superclass method first

        updateWidget();

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //////////////////////////////
    ///////Вспливаюче меню////////
    //////////////////////////////

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear_all:

                return false;
            case R.id.exit:
                android.os.Process.killProcess(android.os.Process.myPid());
                return true;
            default:
                break;
        }
        return false;
    }

    ///////////////////////////////
    ///Кінець вспливаючого меню///
    //////////////////////////////

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentManager fm = getFragmentManager(); //Фрагмент менеджер
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        SwitchCompat switchCompat = (SwitchCompat) findViewById(R.id.drawer_switch);


        if (id == R.id.nav_main) {
            MethodHelper.swap =0;
            fm.beginTransaction().replace(R.id.content_frame, new MainScreenFragment()).commit();
            setTitle(getString(R.string.timetable));
            drawer.closeDrawer(GravityCompat.START);

        } else if (id == R.id.nav_settings) {
            fm.beginTransaction().replace(R.id.content_frame, new SettingsFragment()).commit();
            setTitle(getString(R.string.title_settings));
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_time) {
            fm.beginTransaction().replace(R.id.content_frame, new TimeFragment()).commit();
            setTitle(getString(R.string.title_time));
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_subjects) {
            fm.beginTransaction().replace(R.id.content_frame, new SubjectsFragment()).commit();
            setTitle(getString(R.string.title_subjects));
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_teachers) {
            fm.beginTransaction().replace(R.id.content_frame, new TeachersFragment()).commit();
            setTitle(getString(R.string.title_teachers));
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_buildings) {
            fm.beginTransaction().replace(R.id.content_frame, new BuildsFragment()).commit();
            setTitle(getString(R.string.title_buildings));
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_lessons_types) {
            fm.beginTransaction().replace(R.id.content_frame, new TypeFragment()).commit();
            setTitle(getString(R.string.title_lessons_type));
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_two_weeks) {
            switcher.setChecked(!switcher.isChecked());
            Snackbar.make(item.getActionView(), (switcher.isChecked()) ? "is checked" : "not checked", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
        } else if (id == R.id.nav_first_day) {

        }

        return true;
    }





    public void updateWidget(){
        Intent intent = new Intent(this, Widget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
// Use an array and EXTRA_APPWIDGET_IDS instead of AppWidgetManager.EXTRA_APPWIDGET_ID,
// since it seems the onUpdate() is only fired on that:
        int[] ids = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(), Widget.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        sendBroadcast(intent);
    }



}
