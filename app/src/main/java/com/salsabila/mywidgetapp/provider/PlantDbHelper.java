package com.salsabila.mywidgetapp.provider;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PlantDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "shushme.db";
    private static final int DATABASE_VERSION = 1;

    public PlantDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_PLANTS_TABLE = "CREATE TABLE " + PlantContract.PlantEntry.TABLE_NAME + " (" +
                PlantContract.PlantEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                PlantContract.PlantEntry.COLUMN_PLANT_TYPE + " INTEGER NOT NULL, " +
                PlantContract.PlantEntry.COLUMN_CREATION_TIME + " TIMESTAMP NOT NULL, " +
                PlantContract.PlantEntry.COLUMN_LAST_WATERED_TIME + " TIMESTAMP NOT NULL)";

        sqLiteDatabase.execSQL(SQL_CREATE_PLANTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PlantContract.PlantEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
