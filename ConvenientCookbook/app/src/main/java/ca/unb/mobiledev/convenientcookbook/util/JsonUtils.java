package ca.unb.mobiledev.convenientcookbook.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

import ca.unb.mobiledev.convenientcookbook.model.Recipe;

public class JsonUtils {


    private static final String CS_JSON_FILE = "Recipes.json";

    private static final String KEY_RECIPES = "recipes";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_INGREDIENTS = "ingredients";
    private static final String KEY_STEPS = "steps";

    private static final String KEY_VEGETARIAN = "vegetarian";
    private static final String KEY_VEGAN = "vegan";
    private static final String KEY_GLUTENFREE = "glutenFree";
    private static final String KEY_DAIRYFREE = "dairyFree";

    private ArrayList<Recipe> recipeArray;

    public JsonUtils(Context context){
        processJSON(context);
    }

    private void processJSON(Context context){
        recipeArray = new ArrayList();

        try{
            JSONObject jsonObject = new JSONObject(Objects.requireNonNull(loadJSONFromAssets(context)));

            JSONArray jsonArray = jsonObject.getJSONArray(KEY_RECIPES);

            for(int i=0; i<jsonArray.length(); i++){
                JSONObject myObj = (JSONObject) jsonArray.get(i);

                Recipe recipe = new Recipe.Builder(myObj.getString(KEY_NAME), myObj.getString(KEY_DESCRIPTION), myObj.getString(KEY_INGREDIENTS),
                        myObj.getString(KEY_STEPS), myObj.getString(KEY_VEGETARIAN), myObj.getString(KEY_VEGAN), myObj.getString(KEY_GLUTENFREE), myObj.getString(KEY_DAIRYFREE)).build();

                recipeArray.add(recipe);
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
            Log.w("here", "utils");

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

    public ArrayList<Recipe> getRecipes(){ return recipeArray;}
}
