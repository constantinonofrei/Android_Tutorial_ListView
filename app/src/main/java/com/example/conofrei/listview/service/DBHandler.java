package com.example.conofrei.listview.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.conofrei.listview.dto.SqlItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by conofrei on 11/1/2016.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "items.db";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "create table " + SqlItem.class.getSimpleName() + " (" +
                "itemid integer primary key autoincrement, " +
                " name text, " +
                "score integer);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public void addProduct(SqlItem item) {
        ContentValues values = new ContentValues();
        values.put("name", item.getName());
        values.put("score", item.getScore());
        SQLiteDatabase db=getWritableDatabase();
        db.insert(SqlItem.class.getSimpleName(), null, values);
        db.close();
    }

    public void deleteProduct(SqlItem item) {
        SQLiteDatabase db= getWritableDatabase();
        db.execSQL("delete from " + SqlItem.class.getSimpleName() + " where name=\"" + item.getName() + "\";" );
    }

    public List<SqlItem> getSqlItems() {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from " + SqlItem.class.getSimpleName() + ";";
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        List<SqlItem> list = new ArrayList<SqlItem>();
        while(!c.isAfterLast()) {
            SqlItem item = new SqlItem(c.getString(c.getColumnIndex("name")), c.getInt(c.getColumnIndex("score")), c.getInt(c.getColumnIndex("itemid")));
            list.add(item);
            c.moveToNext();
        }

        return list;
    }
}
