package ca.unb.mobiledev.convenientcookbook.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import ca.unb.mobiledev.convenientcookbook.model.Recipe;

public class JsonUtils {

    private static final String CS_JSON_FILE = "Recipes.json";

    private static final String KEY_RECIPES = "recipes";
    private static final String KEY_NAME = "recipeName";
    private static final String KEY_DESCRIPTION = "recipeDescription";
    private static final String KEY_INGREDIENTS = "recipeIngredients";
    private static final String KEY_STEPS = "recipeSteps";

    private static final String KEY_VEGETARIAN = "vegetarian";
    private static final String KEY_VEGAN = "vegan";
    private static final String KEY_GLUTENFREE = "glutenFree";
    private static final String KEY_DAIRYFREE = "dairyFree";

    private ArrayList<Recipe> recipeArray;
    private JSONObject jsonObject;
    private JSONArray jsonArray;

    public JsonUtils(Context context){
        processJSON(context);
    }

    private void processJSON(Context context){
        recipeArray = new ArrayList<>();

        try{
            jsonObject = new JSONObject(Objects.requireNonNull(loadJSONFromAssets(context)));
            jsonArray = jsonObject.getJSONArray(KEY_RECIPES);

            for(int i=0; i<jsonArray.length(); i++){
                JSONObject myObj = (JSONObject) jsonArray.get(i);

                if(!myObj.isNull("recipeName")) {
                    Recipe recipe = new Recipe.Builder(myObj.getString(KEY_NAME), myObj.getString(KEY_DESCRIPTION), myObj.getString(KEY_INGREDIENTS),
                            myObj.getString(KEY_STEPS), myObj.getString(KEY_VEGETARIAN), myObj.getString(KEY_VEGAN), myObj.getString(KEY_GLUTENFREE), myObj.getString(KEY_DAIRYFREE)).build();
                    recipeArray.add(recipe);
                }
            }
        }
        catch(JSONException e){
            e.printStackTrace();
        }
    }

    private String loadJSONFromAssets(Context context){
        AssetManager manager = context.getAssets();

        try{
            InputStream ioStream = manager.open(CS_JSON_FILE);

            byte[] arr = new byte[ioStream.available()];

            ioStream.read(arr);
            String str = new String(arr, "UTF-8");
            return str;
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return "";
    }

    public ArrayList<Recipe> getRecipes(){
        return recipeArray;
    }

    public void addRecipe(Recipe recipe, Context context){
        try {
            JSONObject newObj = new JSONObject();

            newObj.put(KEY_NAME, recipe.getName());
            newObj.put(KEY_DESCRIPTION, recipe.getDescription());
            newObj.put(KEY_INGREDIENTS, recipe.getIngredients());
            newObj.put(KEY_STEPS, recipe.getSteps());
            newObj.put(KEY_VEGETARIAN, recipe.isVegetarian());
            newObj.put(KEY_VEGAN, recipe.isVegan());
            newObj.put(KEY_GLUTENFREE, recipe.isGlutenFree());
            newObj.put(KEY_DAIRYFREE, recipe.isDairyFree());

            jsonArray.put(newObj);
            try{
                File file = new File(context.getFilesDir(), "Recipes.json");
                Writer output = new BufferedWriter(new FileWriter(file));
                output.write(jsonArray.toString());
                output.close();
                Toast.makeText(context, "Recipe Added!", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
