package com.example.narein.kitchen_inventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by narein on 17/9/17.
 */

public class database extends SQLiteOpenHelper {

    SQLiteDatabase db;
    private static final String DATABASE_NAME="inventory.db",TABLE="stash";

    public database(Context context) {
        super(context, DATABASE_NAME, null, 4);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS STASH (product text, quantity integer, productid text not null unique);");
    }

    public boolean isempty(){
        Cursor mcursor = db.rawQuery("select count(*) from stash", null);
        mcursor.moveToFirst();int icount = mcursor.getInt(0);
        if(icount==0){return true;}else{return false;}
    }

    public boolean insertData(String product,String quantity,String productid) {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("product",product);
        contentValues.put("quantity",quantity);
        contentValues.put("productid",productid);
        long result = db.insert("stash",null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE,null);
        return res;
    }

    public Integer deleteData (String product) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE, "product = ?",new String[] {product});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}
