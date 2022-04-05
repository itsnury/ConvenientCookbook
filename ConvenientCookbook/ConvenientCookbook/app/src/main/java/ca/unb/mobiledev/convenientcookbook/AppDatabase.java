package ca.unb.mobiledev.convenientcookbook;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ca.unb.mobiledev.convenientcookbook.model.Recipe;
import ca.unb.mobiledev.convenientcookbook.model.RecipeDao;

/**
 * Database layer in top of the SQLite database
 */
@Database(entities = {Recipe.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    private static Context parentActivity;

    public abstract RecipeDao recipeDao();


    // Define an ExecutorService with a fixed thread pool which is used to run database operations asynchronously on a background thread
    public static final ExecutorService databaseWriterExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static DatabaseHelper dbHelper = new DatabaseHelper(parentActivity);

    // Singleton access to the database
    public static AppDatabase getDatabase(final Context context) {
        if (null == INSTANCE) {
            synchronized (AppDatabase.class) {
                if (null == INSTANCE) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    public static void setParent(Context context){
        parentActivity = context;
    }
}
