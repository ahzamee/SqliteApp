package com.example.zamee.sqliteapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper MyDB;
    EditText name, surname, marks, id;
    Button btnsubmit, btnshow, btnupdate, btndelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyDB = new DatabaseHelper(this);

        id = (EditText) findViewById(R.id.stid);
        name = (EditText) findViewById(R.id.stName);
        surname = (EditText) findViewById(R.id.stSurname);
        marks = (EditText) findViewById(R.id.stMarks);
        btnsubmit = (Button) findViewById(R.id.btn_submit);
        btnshow = (Button) findViewById(R.id.btn_show);
        btnupdate = (Button) findViewById(R.id.btn_update);
        btndelete = (Button) findViewById(R.id.btn_delete);
        addData();
        showData();
        updateData();
        deleteData();
    }

    public void addData (){
        btnsubmit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = MyDB.insertData(name.getText().toString(), surname.getText().toString(),marks.getText().toString());
                    if (isInserted == true)
                        Toast.makeText(MainActivity.this,"Data is Inserted",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(MainActivity.this,"ERROR",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void showData(){
        btnshow.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = MyDB.getAllData();
                        if (res.getCount()==0){
                            showMessage("Error ", "No data Found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("ID: " + res.getString(0) + "\n");
                            buffer.append("Name: " + res.getString(1) + "\n");
                            buffer.append("SurName: " + res.getString(2) + "\n");
                            buffer.append("Marks: " + res.getString(3) + "\n\n");
                        }
                        showMessage("Data" , buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void updateData(){
        btnupdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdate = MyDB.updateData(id.getText().toString(), name.getText().toString(), surname.getText().toString(),marks.getText().toString());
                        if (isUpdate == true)
                            Toast.makeText(MainActivity.this,"Data is Updated",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Unable to Update",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void deleteData(){
        btndelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deletedRow = MyDB.deleteData(id.getText().toString());
                        if (deletedRow > 0)
                            Toast.makeText(MainActivity.this,"Data is deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Unable to deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
