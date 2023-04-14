package com.example.appthuoc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button bt1;
    Button bt2;
    TextView Tv1;
    EditText edt1,edt2;
    Button bt3;
    SQLiteDatabase mydatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        Tv1 = findViewById(R.id.Tv1);
        edt1 = findViewById(R.id.edt1);
        bt3 = findViewById(R.id.bt3);
        edt2 = findViewById(R.id.edt2);



        mydatabase = openOrCreateDatabase("appthuoc.sql",MODE_PRIVATE,null);

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opentrang2();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = edt1.getText().toString();
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
                Toast.makeText(MainActivity.this, msg , Toast.LENGTH_SHORT).show();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }
    private void openDialog(){
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                edt2.setText(String.valueOf(year)+"/"+String.valueOf(month)+"/"+String.valueOf(day));

            }
        }, 2023, 1, 15);

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = edt2.getText().toString();
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
                Toast.makeText(MainActivity.this, msg , Toast.LENGTH_SHORT).show();
            }
        });


        dialog.show();

    }


    public void opentrang2() {
        Intent intent = new Intent(this, trang2.class);
        startActivity(intent);
    }
}