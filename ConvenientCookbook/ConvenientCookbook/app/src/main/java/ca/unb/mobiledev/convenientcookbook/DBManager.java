package ca.unb.mobiledev.convenientcookbook;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private final DatabaseHelper dbHelper;

    public DBManager(Context context) {
        dbHelper = new DatabaseHelper(context);
    }


    public void close() {
        dbHelper.close();
    }

    public Cursor listAllRecords() {
        Cursor cursor = openReadOnlyDatabase().query(DatabaseHelper.TABLE_NAME, DatabaseHelper.COLUMNS, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void insertRecord(String id, String recipe, String vegetarian, String vegan, String glutenFree, String dairyFree) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.RECIPE_ID, id);
        contentValue.put(DatabaseHelper.RECIPE, recipe);
        contentValue.put(DatabaseHelper.VEGETARIAN, vegetarian);
        contentValue.put(DatabaseHelper.VEGAN, vegan);
        contentValue.put(DatabaseHelper.GLUTENFREE, glutenFree);
        contentValue.put(DatabaseHelper.DAIRYFREE, dairyFree);
        openWriteDatabase().insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    private SQLiteDatabase openReadOnlyDatabase() {
        return dbHelper.getReadableDatabase();
    }

    private SQLiteDatabase openWriteDatabase() {
        return dbHelper.getWritableDatabase();
    }
}

