package maximprytyka.com.timetable;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MethodHelper {




    public ArrayList<String> readFromDB(ArrayList<String> values,SQLiteDatabase db,String table){

        Cursor cursor = db.query(table, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int valueIndex = cursor.getColumnIndex(DBHelper.KEY_VALUE);
            do {

                values.add(cursor.getString(valueIndex));
                Log.d("mLog",cursor.getString(idIndex));
            } while (cursor.moveToNext());
        } else
            Log.d("mLog","0 rows");

        cursor.close();

        return values;
    }





    public void fabClick(final Activity ac, final String table, final ArrayList<String> values, final SQLiteDatabase db, final ArrayAdapter<String> adapter){



        final EditText et = new TextInputEditText(ac);
        et.setSingleLine();
        et.post(new Runnable() {
            public void run() {
                et.requestFocusFromTouch();
                InputMethodManager lManager = (InputMethodManager)ac.getSystemService(Context.INPUT_METHOD_SERVICE);
                lManager.showSoftInput(et, 0);
            }
        });


        AlertDialog.Builder builder = new AlertDialog.Builder(ac);
        builder.setTitle(R.string.alert_add)
                .setView(et)
                .setCancelable(true)
                .setPositiveButton(R.string.alert_add,null)
                .setNegativeButton(R.string.alert_cancel,null);

        final AlertDialog alert = builder.create();

        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    newAdd(et, alert,values,ac,db,table,adapter);
                    return true;
                }
                return false;
            }
        });

        alert.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {
                Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        newAdd(et, alert,values,ac,db,table,adapter);

                    }
                });

            }
        });
        alert.show();



    }








    private void newAdd(EditText et, AlertDialog alert, ArrayList<String> values, Activity ac, SQLiteDatabase db, String table, ArrayAdapter<String> adapter){
        if (!et.getText().toString().isEmpty() && !values.contains(et.getText().toString())) {
            Toast.makeText(ac,
                    R.string.success_add,
                    Toast.LENGTH_SHORT).show();
            ContentValues cv = new ContentValues();
            cv.put("value",et.getText().toString());
            db.insert(table,null,cv);

            values.add(et.getText().toString());
            adapter.notifyDataSetChanged();
            alert.dismiss();
        }else if(values.contains(et.getText().toString())){
            Toast.makeText(ac,
                    R.string.error_same_value,
                    Toast.LENGTH_SHORT).show();
        }

        else  {
            Toast.makeText(ac,
                    R.string.error_add,
                    Toast.LENGTH_SHORT).show();
        }
    }






    public void showPopupMenu(final View v, final Activity ac, final SQLiteDatabase db, final ArrayList<String> values, final ArrayAdapter<String> adapter,final String table) {
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
                        switch (item.getItemId()) {

                            case R.id.del:


                                db.delete(table,"value=?", new String[]{var});




                                values.remove(var);
                                adapter.notifyDataSetChanged();


                                return true;
                            case R.id.rename:

                                final EditText et = new TextInputEditText(ac);
                                et.setText(var);
                                et.setSingleLine();
                                et.selectAll();
                                et.post(new Runnable() {
                                    public void run() {
                                        et.requestFocusFromTouch();
                                        InputMethodManager lManager = (InputMethodManager)ac.getSystemService(Context.INPUT_METHOD_SERVICE);
                                        lManager.showSoftInput(et, 0);
                                    }
                                });




                                final AlertDialog.Builder builder = new AlertDialog.Builder(ac);
                                builder.setTitle(R.string.rename)
                                        .setView(et)
                                        .setCancelable(true)
                                        .setPositiveButton(R.string.rename,null)
                                        .setNegativeButton(R.string.alert_cancel, null);

                                final AlertDialog alert = builder.create();

                                et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                                    @Override
                                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                                        if (actionId == EditorInfo.IME_ACTION_DONE) {
                                            renameVal(et,var,alert,db,values,adapter,ac,table);

                                            return true;
                                        }
                                        return false;
                                    }
                                });

                                alert.setOnShowListener(new DialogInterface.OnShowListener() {

                                    @Override
                                    public void onShow(DialogInterface dialog) {
                                        Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                                        button.setOnClickListener(new View.OnClickListener() {

                                            @Override
                                            public void onClick(View view) {
                                                renameVal(et,var,alert,db,values,adapter,ac,table);

                                            }
                                        });

                                    }
                                });



                                alert.show();

                                return true;

                            default:
                                return false;
                        }
                    }
                });


        popupMenu.show();
    }






    private void renameVal(EditText et,String var,AlertDialog alert,SQLiteDatabase db, ArrayList<String> values, ArrayAdapter<String> adapter,Activity ac,String table){
        if(!et.getText().toString().isEmpty()) {

            ContentValues cv = new ContentValues();
            cv.put("value", et.getText().toString());
            db.update(table, cv, "value = ?",
                    new String[]{var});

            int index = values.indexOf(var);
            values.set(index, et.getText().toString());
            adapter.notifyDataSetChanged();

            alert.dismiss();
        }else Toast.makeText(ac, R.string.error_add,Toast.LENGTH_SHORT).show();
    }




    public void cleareTable(SQLiteDatabase db,String table,Activity ac,ArrayList<String> values,ArrayAdapter<String> adapter){


        db.delete(table,null,null);
        values.clear();
        adapter.notifyDataSetChanged();




        Toast.makeText(ac,R.string.action_clear_all_done, Toast.LENGTH_SHORT).show();



    }



}
