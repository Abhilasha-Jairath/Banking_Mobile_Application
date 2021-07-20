package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");

        db.execSQL("insert into user_table values(9845678975,'Abhi',9552.00,'abhi.07@gmail.com','XXXXXXXXXXXX1235','CAA09096543')");
        db.execSQL("insert into user_table values(9733753766,'Burpy',592.77,'burpy.03@gmail.com','XXXXXXXXXXXX2541','BBA98965432')");
        db.execSQL("insert into user_table values(8767867543,'Ruby',1889.56,'rubygin@gmail.com','XXXXXXXXXXXX3492','CBB87677321')");
        db.execSQL("insert into user_table values(8878474783,'Rohan',1990.01,'rohan.55@gmail.com','XXXXXXXXXXXX4923','AUI76553210')");
        db.execSQL("insert into user_table values(9746635366,'Shahrukh',6603.48,'shahrukh.06@gmail.com','XXXXXXXXXXXX9945','BTE65444109')");
        db.execSQL("insert into user_table values(9876876383,'Thakur',985.16,'thankur.07@gmail.com','XXXXXXXXXXXX3488','IOP54321348')");
        db.execSQL("insert into user_table values(9876543356,'Shanaya',5736.00,'shanaya.08@gmail.com','XXXXXXXXXXXX7723','HDF43200987')");
        db.execSQL("insert into user_table values(9783738387,'Jules',807.22,'jules.09@gmail.com','XXXXXXXXXXXX5894','VIJ32106676')");
        db.execSQL("insert into user_table values(9077383837,'sam',4395.46,'sam.10@gmail.com','XXXXXXXXXXXX3996','AXI21098015')");
        db.execSQL("insert into user_table values(9876464666,'Elai',203.90,'elai.111@gmail.com','XXXXXXXXXXXX5563','ICI10987754')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
