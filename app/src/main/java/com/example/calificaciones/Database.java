package com.example.calificaciones;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final String databaseName = "db";
    private static final String tableName = "calificaciones";
    private static final String Col_1 = "ID";
    private static final String Col_2 = "NOMBRE";
    private static final String Col_3 = "CALIFICACION_1";
    private static final String Col_4 = "CALIFICACION_2";
    private static final String Col_5 = "CALIFICACION_3";
    private static final String Col_6 = "PROMEDIO";

    public Database(Context contexto) {
        super(contexto, databaseName, null, 1);
    }

    public void dropDatabase(Context context) {
        context.deleteDatabase(databaseName);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + tableName +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT, CALIFICACION_1 REAL, CALIFICACION_2 REAL, CALIFICACION_3 REAL, PROMEDIO REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(db);
    }

    public boolean insertData(String nombre, double calificacion1, double calificacion2, double calificacion3, double promedio) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Col_2, nombre);
        contentValues.put(Col_3, calificacion1);
        contentValues.put(Col_4, calificacion2);
        contentValues.put(Col_5, calificacion3);
        contentValues.put(Col_6, promedio);

        long result = db.insert(tableName, null, contentValues);
        return result != -1;
    }

    public boolean updateData(String id, String nombre, double calificacion1, double calificacion2, double calificacion3, double promedio) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Col_2, nombre);
        contentValues.put(Col_3, calificacion1);
        contentValues.put(Col_4, calificacion2);
        contentValues.put(Col_5, calificacion3);
        contentValues.put(Col_6, promedio);

        long result = db.update(tableName, contentValues, "ID = ?", new String[]{id});
        return result != -1;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(tableName, "ID = ?", new String[]{id});
    }

    public Cursor getDataById(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + tableName + " WHERE ID = ?", new String[]{id});
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + tableName, null);
    }
}
