package bg.financialproducts.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LoansDAO extends SQLiteOpenHelper {

    public LoansDAO(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String createTableLoans = String.format(Constants.TABLE_LOAN,
                Constants.TABLE_NAME_LOAN,
                Constants.ID,
                Constants.CONTENT,
                Constants.TYPE);

        //database.execSQL(createTableLoans);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_LOAN);
        onCreate(database);
    }

    public void insertLoan(String loan, String type) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constants.CONTENT, loan);
        values.put(Constants.TYPE, type);

        db.delete(Constants.TABLE_NAME_LOAN, Constants.TYPE + "=" + type, null);
        db.insert(Constants.TABLE_NAME_LOAN, null, values);
        db.close();
    }

    public String findLoanByType(String table, String type) {
        Cursor cursor = getWritableDatabase().
                rawQuery(String.format("SELECT * FROM %s where type = ?", table),
                        new String[] { type });

        String loan = null;
        if (cursor.moveToFirst()) {
            do {
                loan = cursor.getString(1);
            } while (cursor.moveToNext());
        }

        return loan;
    }

/*    public boolean deleteLoan(String type) {
        return getWritableDatabase().
                delete(Constants.TABLE_NAME_LOAN, Constants.TYPE + "=" + type, null) > 0;
    }*/
}