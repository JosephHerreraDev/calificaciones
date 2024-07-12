package com.example.calificaciones;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {

    private static final String databaseName = "db";
    private static final String tableName = "calificaciones";
    private static final String Col_1 = "ID";
    private static final String Col_2 = "NOMBRE";
    private static final String Col_3 = "CALIFICACION_1";
    private static final String Col_4 = "CALIFICACION_2";
    private static final String Col_5 = "CALIFICACION_3";

    public Database (Context contexto){
        super(contexto, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + tableName +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT, CALIFICACION_1 REAL, CALIFICACION_2 REAL, CALIFICACION_3 REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(db);
    }

    public class ProductDatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "db";
        private static final String TABLE_NAME = "calificaciones";
        private static final String COL_ID = "ID";
        private static final String COL_NOMBRE = "NOMBRE";
        private static final String COL_CALIFICACION_1 = "CALIFICACION_1";
        private static final String COL_CALIFICACION_2 = "CALIFICACION_2";
        private static final String COL_CALIFICACION_3 = "CALIFICACION_3";
        private static final int DATABASE_VERSION = 1; // Start with version 1

        public ProductDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String createTableQuery = "CREATE TABLE " + TABLE_NAME +
                    "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_NOMBRE + " TEXT, " +
                    COL_CALIFICACION_1 + " REAL, " +
                    COL_CALIFICACION_2 + " REAL, " +
                    COL_CALIFICACION_3 + " REAL)";
            db.execSQL(createTableQuery);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Handle database migrations here (e.g., adding columns, altering tables)
            // For now, just drop and recreate (losing data)
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }

        public boolean insertData(String nombre, double calificacion1, double calificacion2, double calificacion3) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_NOMBRE, nombre);
            contentValues.put(COL_CALIFICACION_1, calificacion1);
            contentValues.put(COL_CALIFICACION_2, calificacion2);
            contentValues.put(COL_CALIFICACION_3, calificacion3);

            long result = -1;
            try {
                result = db.insert(TABLE_NAME, null, contentValues);
            } catch (SQLiteException e) {
                Log.e("Database Error", "Error inserting data: " + e.getMessage());
                // Handle the exception (e.g., display an error message)
            } finally {
                db.close();
            }
            return result != -1;
        }

    }

    public boolean insertData(String nombre, double calificacion1, double calificacion2, double calificacion3){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Col_2, nombre);
        contentValues.put(Col_3, calificacion1);
        contentValues.put(Col_4, calificacion2);
        contentValues.put(Col_5, calificacion3);

        long result = db.insert(tableName, null, contentValues);
        return result != -1;
    }

    public boolean updateData(String id,String nombre, double calificacion1, double calificacion2, double calificacion3 ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Col_2, nombre);
        contentValues.put(Col_3, calificacion1);
        contentValues.put(Col_4, calificacion2);
        contentValues.put(Col_5, calificacion3);


        long result = db.update(tableName, contentValues, "ID = ?", new String[] {id});
        return result != -1;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db2 = this.getWritableDatabase();
        return db2.delete(tableName,"ID = ?",new String[]{id});
    }
    public Cursor getData(String id){
        SQLiteDatabase db2 = this.getWritableDatabase();
        return db2.rawQuery("SELECT * FROM " + tableName + " WHERE ID = ?", new String[]{id});
    }
}
