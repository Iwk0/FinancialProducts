package bg.financialproducts.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import bg.financialproducts.model.Settings;

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String createTableLoans = String.format(Constants.TABLE_LOAN,
                Constants.TABLE_NAME_LOAN,
                Constants.ID,
                Constants.CONTENT,
                Constants.TYPE,
                Constants.CREATED_AT);

        String createTableSettings = String.format(Constants.TABLE_SETTINGS,
                Constants.TABLE_NAME_SETTINGS,
                Constants.ID,
                Constants.URL,
                Constants.USER_ID,
                Constants.USERNAME);

        database.execSQL(createTableLoans);
        database.execSQL(createTableSettings);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_LOAN);
        database.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_SETTINGS);
        onCreate(database);
    }

    public void insertLoan(String loan, int type) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constants.CONTENT, loan);
        values.put(Constants.TYPE, type);
        values.put(Constants.CREATED_AT, new SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault()).format(new Date()));

        db.delete(Constants.TABLE_NAME_LOAN, Constants.TYPE + "=" + type, null);
        db.insert(Constants.TABLE_NAME_LOAN, null, values);
        db.close();
    }

    public void insertSettings(String url, String userId, String username) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constants.URL, url);
        values.put(Constants.USER_ID, userId);
        values.put(Constants.USERNAME, username);

        try {
            db.delete(Constants.TABLE_NAME_SETTINGS, Constants.USERNAME + "='" + username + "'", null);
        } catch (SQLiteException e) {
            Log.e("SQLiteException", "No item to delete");
        } finally {
            db.insert(Constants.TABLE_NAME_SETTINGS, null, values);
        }

        db.close();
    }

    public Settings findLastSettingsRecord() {
        Cursor cursor = getWritableDatabase().rawQuery(String.format("SELECT * FROM %s ORDER BY %s DESC LIMIT 1;",
                Constants.TABLE_NAME_SETTINGS, Constants.ID), new String[] {});

        Settings settings = null;
        if (cursor.moveToFirst()) {
            settings = new Settings();
            do {
                settings.url = cursor.getString(1);
                settings.id = cursor.getString(2);
                settings.username = cursor.getString(3);
            } while (cursor.moveToNext());
        }

        return settings;
    }

    public String getCreatedAtDate(int type) {
        Cursor cursor = getWritableDatabase().rawQuery(String.format("SELECT * FROM %s WHERE type = ? ORDER BY %s DESC LIMIT 1;",
                Constants.TABLE_NAME_LOAN, Constants.ID), new String[] { String.valueOf(type) });

        String date = null;
        if (cursor.moveToFirst()) {
            do {
                date = cursor.getString(3);
            } while (cursor.moveToNext());
        }

        return date;
    }

    public String findLoanByType(String table, int type) {
        Cursor cursor = getWritableDatabase().rawQuery(String.format("SELECT * FROM %s where type = ?", table), new String[] { String.valueOf(type) });

        String loan = null;
        if (cursor.moveToFirst()) {
            do {
                loan = cursor.getString(1);
            } while (cursor.moveToNext());
        }

        return loan;
    }
}