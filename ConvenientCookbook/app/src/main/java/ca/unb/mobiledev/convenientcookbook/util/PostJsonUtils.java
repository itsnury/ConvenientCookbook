package ca.unb.mobiledev.convenientcookbook.util;

import android.content.Context;
import android.content.res.AssetManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

import ca.unb.mobiledev.convenientcookbook.model.Recipe;

public class PostJsonUtils {
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
    JSONObject mainObj;

    public PostJsonUtils(Context context){
        processJSON(context);
    }

    private void processJSON(Context context){
        recipeArray = new ArrayList<>();

        try{
            mainObj = new JSONObject(Objects.requireNonNull(loadJSONFromAssets(context)));
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

    public void addRecipe(Recipe recipe){
        try {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put(KEY_NAME, recipe.getName());
            jsonObject.put(KEY_DESCRIPTION, recipe.getDescription());
            jsonObject.put(KEY_INGREDIENTS, recipe.getIngredients());
            jsonObject.put(KEY_STEPS, recipe.getSteps());
            jsonObject.put(KEY_VEGETARIAN, recipe.isVegetarian());
            jsonObject.put(KEY_VEGAN, recipe.isVegan());
            jsonObject.put(KEY_GLUTENFREE, recipe.isGlutenFree());
            jsonObject.put(KEY_DAIRYFREE, recipe.isDairyFree());

            addJsonObj(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void addJsonObj(JSONObject newObj){
        try {
            JSONArray array = mainObj.getJSONArray(KEY_RECIPES);
            array.put(newObj);

            FileWriter writer = new FileWriter(CS_JSON_FILE);
            writer.write(mainObj.toString());
            writer.close();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
