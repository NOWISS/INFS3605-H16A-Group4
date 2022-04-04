package com.example.covidscreeningapp.visitor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class VisitorDBHelper extends SQLiteOpenHelper {

    public static final String DBname = "VisitorDetail.db";
    private Context context;
    private static final String tablename = "visitor_detail";
    private static final String ID = "_id";
    private static final String firstname = "first_name";
    private static final String lastname = "last_name";
    private static final String mobile = "mobile_number";
    private static final String destination = "destination";


    public VisitorDBHelper(Context context) {
        super(context, "VisitorDetail.db", null, 1 );
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        String query =
                "CREATE TABLE  "+ tablename +
                        " ("+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        firstname + " TEXT, " +
                        lastname + " TEXT, " +
                        mobile + " INTEGER, " +
                        destination + " TEXT);";
        DB.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE IF EXISTS " + tablename);
        onCreate(DB);
    }
    void addPerson(String fname, String lname, int mo, String des){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(firstname,fname);
        cv.put(lastname,lname);
        cv.put(mobile,mo);
        cv.put(destination,des);
        long result = db.insert(tablename,null,cv);
        if (result == -1){
            Toast.makeText(context, "Failed to submit", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"Submitted successfully", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readData(){
        String query = "SELECT * FROM " + tablename;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(tablename, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
}
