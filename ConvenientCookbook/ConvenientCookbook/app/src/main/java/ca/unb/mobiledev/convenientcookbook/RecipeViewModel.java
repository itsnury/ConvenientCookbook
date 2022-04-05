package ca.unb.mobiledev.convenientcookbook;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;
import java.util.concurrent.ExecutionException;

import ca.unb.mobiledev.convenientcookbook.model.Recipe;
import ca.unb.mobiledev.convenientcookbook.RecipeRepository;

public class RecipeViewModel extends AndroidViewModel {
    private final RecipeRepository recipeRepository;
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
        recipeRepository = new RecipeRepository(application, parentActivity);
    }

    // TODO
    //  Add mapping calls between the UI and Database
    public List<Recipe> updateItemsList(String filter) throws ExecutionException, InterruptedException {
        return recipeRepository.listAllRecords(filter);
    }

    public void insert(Recipe recipe){
        recipeRepository.insertRecord(recipe);
    }
}
