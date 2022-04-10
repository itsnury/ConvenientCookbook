package ca.unb.mobiledev.convenientcookbook;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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

    public Cursor listVegetarianRecords() {
        Cursor cursor = openReadOnlyDatabase().query(DatabaseHelper.TABLE_NAME,
                DatabaseHelper.COLUMNS, "vegetarian=?", new String[]{"Vegetarian: yes\n"}, null, null,
        dbHelper.RECIPE_ID + "ASC");
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor listVeganRecords() {
        Cursor cursor = openReadOnlyDatabase().query(DatabaseHelper.TABLE_NAME,
                DatabaseHelper.COLUMNS, "vegan=?", new String[]{"Vegan: yes\n"}, null, null,
                dbHelper.RECIPE_ID + "ASC");
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor listDairyFreeRecords() {
        Cursor cursor = openReadOnlyDatabase().query(DatabaseHelper.TABLE_NAME,
                DatabaseHelper.COLUMNS, "dairyFree=?", new String[]{"Dairy Free: yes\n"}, null, null,
                dbHelper.RECIPE_ID + "ASC");
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor listGlutenFreeRecords() {
        Cursor cursor = openReadOnlyDatabase().query(DatabaseHelper.TABLE_NAME,
                DatabaseHelper.COLUMNS, "glutenFree=?", new String[]{"Gluten Free: yes\n"}, null, null,
                dbHelper.RECIPE_ID + "ASC");
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void insertRecord(String id, String recipeName, String recipeDescription, String recipeIngredients,
                             String recipeSteps, String vegetarian, String vegan, String glutenFree, String dairyFree) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.RECIPE_ID, id);
        contentValue.put(DatabaseHelper.RECIPENAME, recipeName);
        contentValue.put(DatabaseHelper.RECIPEDESCRIPTION, recipeDescription);
        contentValue.put(DatabaseHelper.RECIPEINGREDIENTS, recipeIngredients);
        contentValue.put(DatabaseHelper.RECIPESTEPS, recipeSteps);
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

