package com.example.user.sql_lite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    final String LOG_TAG = "myLogs";

    Button btnadd, btn2, btn3;
    EditText edName, edAge, edGrowth;

    DbHelper dbHelper;
    SQLiteDatabase database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnadd = (Button) findViewById(R.id.btnadd);
        btnadd.setOnClickListener(this);
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        btn3 = (Button) findViewById(R.id.button3);
        btn3.setOnClickListener(this);

        edName = ( EditText) findViewById(R.id.etName);
        edAge =( EditText) findViewById(R.id.etAge);
        edGrowth =( EditText) findViewById(R.id.etGrowth);

        dbHelper = new DbHelper(this);

        Log.d(LOG_TAG,"onCreate");
    }

    @Override
    public void onClick(View view) {

        database = dbHelper.getWritableDatabase();


        ContentValues contentValues = new ContentValues();

        switch (view.getId()){
            case R.id.btnadd:
                contentValues.put(DbHelper.KEY_NAME, edName.getText().toString());
                contentValues.put(DbHelper.KEY_AGE, edAge.getText().toString());
                contentValues.put(DbHelper.KEY_GROWTH,edGrowth.getText().toString());

                database.insert(DbHelper.TABLE_CONTACTS,null, contentValues);
                Log.d(LOG_TAG,"add");
                break;
            case R.id.btn2:
                Cursor cursor = database.query(DbHelper.TABLE_CONTACTS, null,null, null, null,null,null);

                if (cursor.moveToFirst()){
                    int idIndex = cursor.getColumnIndex(DbHelper.KEY_ID);
                    int nameIndex = cursor.getColumnIndex(DbHelper.KEY_NAME);
                    int ageIndex = cursor.getColumnIndex(DbHelper.KEY_AGE);
                    int growthIndex = cursor.getColumnIndex(DbHelper.KEY_GROWTH);
                        do {
                            Log.d(LOG_TAG , "id = " +cursor.getString(idIndex) +
                                  ", Імя =" + cursor.getString(nameIndex) +
                                   ", вік ="+ cursor.getInt(ageIndex) +
                                   ", ріст ="+ cursor.getDouble(growthIndex) );
                        }while (cursor.moveToNext());
                }else Log.d(LOG_TAG,"0 rows");
                cursor.close();
                break;
            case R.id.button3:
                database.delete(DbHelper.TABLE_CONTACTS, null,null);
                Log.d(LOG_TAG,"Delete");

                break;
        }
        dbHelper.close();
    }
}
