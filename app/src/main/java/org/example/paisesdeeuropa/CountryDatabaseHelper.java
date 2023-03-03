package org.example.paisesdeeuropa;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class CountryDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "countries.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "countries";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_FLAG_RESOURCE_ID = "flag_resource_id";
    private static final String COLUMN_CAPITAL = "capital";
    private static final String COLUMN_POPULATION = "population";
    private static final String COLUMN_SURFACE = "surface";
    private static final String COLUMN_CONTINENT = "continent";

    private SQLiteDatabase db;

    public CountryDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_NAME + " TEXT PRIMARY KEY, " +
                COLUMN_FLAG_RESOURCE_ID + " INTEGER, " +
                COLUMN_CAPITAL + " TEXT, " +
                COLUMN_POPULATION + " INTEGER, " +
                COLUMN_SURFACE + " INTEGER, " +
                COLUMN_CONTINENT + " TEXT)";
        db.execSQL(createTableQuery);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTableQuery);
        onCreate(db);
    }

    public List<Pais> getCountries() {
        List<Pais> countries = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            @SuppressLint("Range") int flagResourceId = cursor.getInt(cursor.getColumnIndex(COLUMN_FLAG_RESOURCE_ID));


            @SuppressLint("Range") String capital = cursor.getString(cursor.getColumnIndex(COLUMN_CAPITAL));
            @SuppressLint("Range") int population = cursor.getInt(cursor.getColumnIndex(COLUMN_POPULATION));
            @SuppressLint("Range") int surface = cursor.getInt(cursor.getColumnIndex(COLUMN_SURFACE));
            @SuppressLint("Range") String continent = cursor.getString(cursor.getColumnIndex(COLUMN_CONTINENT));
            Pais country = new Pais(name, flagResourceId, capital, population, surface, continent);
            countries.add(country);
        }
        cursor.close();
        db.close();
        return countries;
    }

    public Pais getCountryByName(String name) {
        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_NAME + "=?", new String[]{name}, null, null, null);
        Pais country = null;
        if (cursor.moveToNext()) {
            @SuppressLint("Range") int flagResourceId = cursor.getInt(cursor.getColumnIndex(COLUMN_FLAG_RESOURCE_ID));
            @SuppressLint("Range") String capital = cursor.getString(cursor.getColumnIndex(COLUMN_CAPITAL));
            @SuppressLint("Range") int population = cursor.getInt(cursor.getColumnIndex(COLUMN_POPULATION));
            @SuppressLint("Range") int surface = cursor.getInt(cursor.getColumnIndex(COLUMN_SURFACE));
            @SuppressLint("Range") String continent = cursor.getString(cursor.getColumnIndex(COLUMN_CONTINENT));
            country = new Pais(name, flagResourceId, capital, population, surface, continent);
        }
        cursor.close();
        return country;
    }

    public void deleteCountry(String name) {
            SQLiteDatabase db = getWritableDatabase();
            String whereClause = "name" + "=?";
            String[] whereArgs = new String[]{String.valueOf(name)};
            db.delete("countries", whereClause, whereArgs);

    }
    public void insertCountry(String name, int flagUrl, String capital, int population, double surfaceArea, String continent) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("flag_resource_id", flagUrl);
        values.put("capital", capital);
        values.put("population", population);
        values.put("surface", surfaceArea);
        values.put("continent", continent);

        db.insert("countries", null, values);

        db.close();
    }

    public void updateCountry(String last_name, String name, String flagUrl, String capital, int population, double surfaceArea, String continent) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("flag_resource_id", flagUrl);
        values.put("capital", capital);
        values.put("population", population);
        values.put("surface", surfaceArea);
        values.put("continent", continent);

        db.update("countries", values, "name" + "=?", new String[]{String.valueOf(last_name)});

        db.close();
    }
}
