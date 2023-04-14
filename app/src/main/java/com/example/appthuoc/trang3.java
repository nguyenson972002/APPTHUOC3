package com.example.appthuoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class trang3 extends AppCompatActivity {

    Button thongbao,hienthi,themthuoc,dunglai,bt2;
    SQLiteDatabase mydatabase;
    ListView lv2;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;
    TextView txthienthi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang3);

        thongbao = findViewById(R.id.thongbao);
        hienthi = findViewById(R.id.hienthi);
        themthuoc = findViewById(R.id.themthuoc);
        lv2 = findViewById(R.id.lv2);
        txthienthi = findViewById(R.id.txthienthi);


        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mylist);
        lv2.setAdapter(myadapter);

        mydatabase = openOrCreateDatabase("appthuoc.sql",MODE_PRIVATE,null);

        try {
            String sql = "CREATE TABLE tbtime(tenthuoc TEXT primary key ,NGAY TEXT,gio TEXT )";
            mydatabase.execSQL(sql);
        }
        catch (Exception e){
            Log.e("Error","Table đã tồn tại");
        }

        thongbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            txthienthi.setText("Đặt thông báo thành công !");

            }
        });

        hienthi.setOnClickListener(new View.OnClickListener() {
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




        themthuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });


    }
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}