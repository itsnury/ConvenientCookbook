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

        Cursor cursor = openReadOnlyDatabase().rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE id = ?", new String[] {DatabaseHelper.RECIPE_ID});

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor listVegetarianRecords() {
        Cursor cursor = openReadOnlyDatabase().rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE id = ? AND vegetarian LIKE 'Vegetarian: yes\n'", new String[] {DatabaseHelper.RECIPE_ID});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor listVeganRecords() {
        Cursor cursor = openReadOnlyDatabase().rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE id = ? AND vegan LIKE 'Vegan: yes\n'", new String[] {DatabaseHelper.RECIPE_ID});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor listDairyFreeRecords() {
        Cursor cursor = openReadOnlyDatabase().rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE id = ? AND dairyFree LIKE 'Dairy Free: yes\n'", new String[] {DatabaseHelper.RECIPE_ID});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor listGlutenFreeRecords() {
        Cursor cursor = openReadOnlyDatabase().rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE id = ? AND glutenFree LIKE 'Gluten Free: yes\n'", new String[] {DatabaseHelper.RECIPE_ID});
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

