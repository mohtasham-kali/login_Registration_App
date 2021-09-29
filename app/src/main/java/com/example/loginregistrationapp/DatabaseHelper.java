package com.example.loginregistrationapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.accessibility.AccessibilityManager;

/**
 * Created by Mr.Anonymous.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db_crud";
    private static final String TABLE_NAME = "tbl_records";
    private static final String TABlE_NAME_FEES = "tbl_fees";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME+" (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, password TEXT)");
        sqLiteDatabase.execSQL("create table "+TABlE_NAME_FEES+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, Fees INT, Due_Fess INT, name TEXT)" );

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys= ON");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table IF EXISTS "+TABLE_NAME+"");
        sqLiteDatabase.execSQL("drop table IF EXISTS "+TABlE_NAME_FEES+"");
        onCreate(sqLiteDatabase);

    }

    //function for insert data
    public boolean insertData(String name, String email, String password)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("email",email);
        contentValues.put("password",password);

        long result = sqLiteDatabase.insert(TABLE_NAME,null, contentValues);

        sqLiteDatabase.close();

        if(result == -1)
            return false;
        else
            return true;
    }


    public boolean insertData_fees(int fees, int due, String name)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("Fees", fees);
        contentValues.put("Due_Fees", due);
        contentValues.put("name", name);

        long result = sqLiteDatabase.insert(TABlE_NAME_FEES,null, contentValues);

        sqLiteDatabase.close();

        if(result == -1)
            return false;
        else
            return true;
    }

    //function for search data
    public Cursor searchData(String name)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String query = "select * from "+TABLE_NAME+" where name = '"+name+"'";
        Cursor data = sqLiteDatabase.rawQuery(query,null);

        return data;
    }

    //function for update data
    public boolean updateData( String email, String password)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);

        long result = sqLiteDatabase.update(TABLE_NAME,contentValues,"email = '"+email+"'",null);
        sqLiteDatabase.close();

        if (result == -1)
            return false;
        else
            return true;
    }

    //show all record
    public Cursor allData()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String query = "select * from " +TABLE_NAME + "";
        Cursor data = sqLiteDatabase.rawQuery(query,null);

        return data;
    }

    // funcation for login

    public Cursor login(String email,String password)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String query = "select * from "+TABLE_NAME+" where email = '"+email+"' and password='"+password+"'";
        Cursor data = sqLiteDatabase.rawQuery(query,null);

        return data;
    }

    // function for reset password

    public Cursor resetPassword(String name, String email)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String query = "select * from "+TABLE_NAME+" where name = '"+name+"' and email='"+email+"'";
        Cursor data = sqLiteDatabase.rawQuery(query,null);

        return data;
    }

    //function for update data
    public boolean updatePassword(String email, String name, String newPassword)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("password", newPassword);

        long result = sqLiteDatabase.update(TABLE_NAME,contentValues,"email = '"+email+"' and name = '"+name+"'",null);
        sqLiteDatabase.close();

        if (result == -1)
            return false;
        else
            return true;
    }
}
