package com.example.el_terminali;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.List;

public class Database extends SQLiteOpenHelper {

    private String ordersTable = "ORDERS";

    public Database(Context context) {
        super(context, "MainDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + ordersTable + "(" +
                "ItemCode VARCHAR," +
                "ItemName VARCHAR," +
                "Quantity FLOAT," +
                "InputQuantity FLOAT," +
                "ObjectType INT," +
                "DocEntry INT," +
                "BPCode VARCHAR," +
                "BPName VARCHAR," +
                "DocDate VARCHAR," +
                "LineNum FLOAT," +
                "STATUS INT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void PassActiveOrders(List<ActiveOrderDetailObject> _activeOrders, ActiveOrder.ActiveOrderObject _activeOrder) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM " + ordersTable);

        ContentValues values = new ContentValues();

        for (ActiveOrderDetailObject aodo : _activeOrders) {
            values.put("ItemCode", aodo.getItemCode());
            values.put("ItemName", aodo.getItemDescription());
            values.put("Quantity", aodo.getQuantity());
            //values.put("InputQuantity", aodo.getInputQuantity());
            //values.put("ObjectType", _stock.getQuantity());
            values.put("DocEntry", _activeOrder.getDocEntry());
            values.put("BPCode", _activeOrder.getCardCode());
            values.put("BPName", _activeOrder.getCardName());
            values.put("DocDate", _activeOrder.getDocDate());
            values.put("LineNum", aodo.getLineNum());
            values.put("STATUS", 0);
        }

        db.insert(ordersTable, null, values);

        Cursor cursor = db.rawQuery("SELECT * FROM " + ordersTable, null);

        while (cursor.moveToNext()) {
            Log.i("BERAT", cursor.getString(1));
        }
        db.close();
    }
}
