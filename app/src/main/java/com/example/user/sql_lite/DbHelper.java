package com.example.user.sql_lite;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {
    final String LOG_TAG = "myLogs";

        public static final int DATABASE_VERSION = 1;
        public static  final String DATABASE_NAME = "contactDb";
        public static  final String TABLE_CONTACTS = "contacts";


        public static  final String KEY_ID = "_id";
        public static  final String KEY_NAME = "name";
        public static  final String KEY_AGE = "age";
        public static  final String KEY_GROWTH = "growth";



    public DbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(LOG_TAG," DB1");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(LOG_TAG," DB1222");
        sqLiteDatabase.execSQL("create table " + TABLE_CONTACTS+ "("+ KEY_ID +" integer primary key autoincrement," +
                KEY_NAME+ " text,"+ KEY_AGE+ " integer,"+KEY_GROWTH+" real"+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(LOG_TAG," DB1333");
        sqLiteDatabase.execSQL("drop table if exists"+ TABLE_CONTACTS);


    }
}
