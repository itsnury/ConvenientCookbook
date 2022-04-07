package ca.unb.mobiledev.convenientcookbook;
import android.app.Application;
import android.content.Context;
import android.database.Cursor;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;
import java.util.concurrent.ExecutionException;

import ca.unb.mobiledev.convenientcookbook.model.Recipe;

public class RecipeViewModel extends AndroidViewModel {
    private final DBManager dbManager;
    private List<Recipe> recipes;
    private Context parentActivity;

    public List<Recipe> getItems(String filter) throws ExecutionException, InterruptedException {
        if(recipes == null){
            recipes = updateItemsList(filter);
        }
        return recipes;
    }

    public void setParent(Context context){
        parentActivity = context;
    }

    public RecipeViewModel(@NonNull Application application) {
        super(application);
        dbManager = new DBManager(parentActivity);
    }

    // TODO
    //  Add mapping calls between the UI and Database
    public List<Recipe> updateItemsList(String filter) throws ExecutionException, InterruptedException {
        List<Recipe> recipes = null;
        Cursor cursor;
        if(filter.equals("vegetarian")){
            cursor = dbManager.listVegetarianRecords();
        }else if(filter.equals("vegan")){
            cursor = dbManager.listVeganRecords();
        }else if(filter.equals("Gluten-free")){
            cursor = dbManager.listGlutenFreeRecords();
        }else if(filter.equals("Dairy-free")){
            cursor = dbManager.listDairyFreeRecords();
        }else{
            cursor = dbManager.listAllRecords();
        }

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("recipeName"));
            String description = cursor.getString(cursor.getColumnIndexOrThrow("recipeDescription"));
            String ingredients = cursor.getString(cursor.getColumnIndexOrThrow("recipeIngredients"));
            String steps = cursor.getString(cursor.getColumnIndexOrThrow("recipeSteps"));
            String isVegetarian = cursor.getString(cursor.getColumnIndexOrThrow("vegetarian"));
            String isVegan = cursor.getString(cursor.getColumnIndexOrThrow("vegan"));
            String isGlutenFree = cursor.getString(cursor.getColumnIndexOrThrow("glutenFree"));
            String isDairyFree = cursor.getString(cursor.getColumnIndexOrThrow("dairyFree"));

            recipes.add(new Recipe.Builder(id, name, description, ingredients, steps, isVegetarian,
                    isVegan, isGlutenFree, isDairyFree).build());

            cursor.moveToNext();
        }

        return recipes;
    }

    public void insertRecord(Recipe recipe){
        dbManager.insertRecord(recipe.getId(), recipe.getName(), recipe.getDescription(), recipe.getIngredients(),
                recipe.getSteps(), recipe.isVegetarian(), recipe.isVegan(), recipe.isGlutenFree(), recipe.isDairyFree());
    }
}
