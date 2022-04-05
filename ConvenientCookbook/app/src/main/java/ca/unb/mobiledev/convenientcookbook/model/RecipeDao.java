package ca.unb.mobiledev.convenientcookbook.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.OnConflictStrategy;

import java.util.List;

import ca.unb.mobiledev.convenientcookbook.model.Recipe;

/**
 * This DAO object validates the SQL at compile-time and associates it with a method
 */
@Dao
public interface RecipeDao {
    // TODO
    //  Add app specific queries in here
    //  Additional details can be found at https://developer.android.com/reference/android/arch/persistence/room/Dao
    @Query("SELECT * FROM recipe_table WHERE dairyFree=:"Dairy Free: yes\n"")
    List<Recipe> listDairyFreeRecords();

    @Query("SELECT * FROM recipe_table WHERE glutenFree=:"Gluten Free: yes\n"")
    List<Recipe> listGlutenFreeRecords();

    @Query("SELECT * FROM recipe_table WHERE vegan=:"Vegan: yes\n"")
    List<Recipe> listVeganRecords();

    @Query("SELECT * FROM recipe_table WHERE vegetarian=:"Vegetarian: yes\n"")
    List<Recipe> listVegetarianRecords();

    @Query("SELECT * FROM recipe_table")
    List<Recipe> listAllRecords();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Recipe recipe);
}
