package com.example.appthuoc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

public class trang2 extends AppCompatActivity {

    Button bt4;
    Button bt5;
    ListView lv;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;
    SQLiteDatabase mydatabase;
    EditText edt3;
    Button bt6;
    Button bt7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang2);

        bt4 = findViewById(R.id.bt4);
        bt5 = findViewById(R.id.bt5);
        lv = findViewById(R.id.lv);
        edt3 = findViewById(R.id.edt3);
        bt6 = findViewById(R.id.bt6);
        bt7 = findViewById(R.id.bt7);



        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            opentrang3();
            }
        });

        lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mylist);
        lv.setAdapter(myadapter);

        mydatabase = openOrCreateDatabase("appthuoc.sql",MODE_PRIVATE,null);
        try {
          String sql = "CREATE TABLE tbtime(time Text primary key)";
          mydatabase.execSQL(sql);
        }
        catch (Exception e){
            Log.e("Error","Table đã tồn tại");
        }
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

    }
    private void openDialog(){
        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hours, int minute) {
                edt3.setText(String.valueOf(hours)+":"+String.valueOf(minute));
            }
        },15,00,true);

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = edt3.getText().toString();
                ContentValues myvalue = new ContentValues();
                myvalue.put("time",time);
                String msg = "";
                if ( mydatabase.insert("tbtime",null,myvalue) == -1)
                {
                    msg = "Fail to Insert Record !";
                }
                else {
                    msg = "Insert record Sucessfully";
                }
                Toast.makeText(trang2.this, msg , Toast.LENGTH_SHORT).show();
            }
        });

        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = edt3.getText().toString();
                int n = mydatabase.delete("tbtime","time = ?",new String[]{time});
                String msg = "";
                if(n==0)
                {
                    msg = " No record to delete";
                }
                else{
                    msg = n + "record is delete";
                }
                Toast.makeText(trang2.this,msg,  Toast.LENGTH_SHORT).show();
            }
        });
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mylist.clear();
                Cursor c = mydatabase.query("tbtime",null,null,null,null,null,null);
                c.moveToNext();
                String data = "";
                while (c.isAfterLast() == false)
                {
                    data = c.getString(0);
                    c.moveToNext();
                    mylist.add(data);
                }
                c.close();
                myadapter.notifyDataSetChanged();
            }
        });


        dialog.show();
    }


    public void opentrang3(){
        Intent intent = new Intent(this,trang3.class);
        startActivity(intent);
    }
}