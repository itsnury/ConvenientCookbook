package ca.unb.mobiledev.convenientcookbook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    // Database information
    private static final String DATABASE_NAME = "RECIPES.DB";
    private static final int DATABASE_VERSION = 1;

    // Table columns
    public static final String RECIPE_ID = "id";
    public static final String RECIPENAME = "recipeName";
    public static final String RECIPEDESCRIPTION = "recipeDescription";
    public static final String RECIPEINGREDIENTS = "recipeIngredients";
    public static final String RECIPESTEPS = "recipeSteps";
    public static final String VEGETARIAN = "vegetarian";
    public static final String VEGAN = "vegan";
    public static final String GLUTENFREE = "glutenFree";
    public static final String DAIRYFREE = "dairyFree";
    public static String[] COLUMNS = {RECIPE_ID, RECIPENAME, RECIPEDESCRIPTION, RECIPEINGREDIENTS,
            RECIPESTEPS, VEGETARIAN, VEGAN, GLUTENFREE,DAIRYFREE };

    // Table creation statement
    public static final String TABLE_NAME = "recipe_table";
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" + RECIPE_ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + RECIPENAME + RECIPEDESCRIPTION + " TEXT, " + RECIPEINGREDIENTS + " TEXT, "
                    + RECIPESTEPS + " TEXT, " + VEGETARIAN + " TEXT, " + VEGETARIAN + " TEXT,"
                    + GLUTENFREE + "TEXT," + DAIRYFREE + "TEXT);";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }
}
