package ca.unb.mobiledev.convenientcookbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ca.unb.mobiledev.convenientcookbook.model.Recipe;
import ca.unb.mobiledev.convenientcookbook.util.JsonUtils;

public class PostActivity extends AppCompatActivity {

    private EditText nameTextbox;
    private EditText descriptionTextbox;
    private EditText ingredientsTextbox;
    private EditText stepsTextbox;

    private String recipeName;
    private String recipeDescription;
    private String recipeIngredients;
    private String recipeSteps;
    private String isVegetarian = "";
    private String isVegan = "";
    private String isDairyFree = "";
    private String isGlutenFree = "";

    private CheckBox vegetarianCheckbox;
    private CheckBox veganCheckbox;
    private CheckBox dairyFreeCheckbox;
    private CheckBox glutenFreeCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        nameTextbox = findViewById(R.id.nameTextbox);
        descriptionTextbox = findViewById(R.id.descriptionTextbox);
        ingredientsTextbox = findViewById(R.id.ingredientsTextbox);
        stepsTextbox = findViewById(R.id.stepsTextbox);

        vegetarianCheckbox = findViewById(R.id.vegetarianCheckbox);
        veganCheckbox = findViewById(R.id.veganCheckbox);
        dairyFreeCheckbox = findViewById(R.id.dairyFreeCheckbox);
        glutenFreeCheckbox = findViewById(R.id.glutenFreeCheckbox);

        Button submitBtn = findViewById(R.id.submitBtn);

        nameTextbox.setFocusableInTouchMode(true);
        nameTextbox.requestFocus();

        descriptionTextbox.setFocusableInTouchMode(true);
        descriptionTextbox.requestFocus();

        // if submit button is pressed make sure none of the fields are empty
        // and send the data to JSON file
        submitBtn.setOnClickListener(v ->{
            recipeName = nameTextbox.getText().toString();
            recipeDescription = descriptionTextbox.getText().toString();
            recipeIngredients = ingredientsTextbox.getText().toString();
            recipeSteps = stepsTextbox.getText().toString();

            if(isVegetarian.matches("")){
                isVegetarian = "Vegetarian: no";
            }else if(isVegan.matches("")){
                isVegan = "Vegan: no";
            }else if(isDairyFree.matches("")){
                isDairyFree = "Dairy Free: no";
            }else if(isGlutenFree.matches("")){
                isGlutenFree = "Gluten Free: no";
            }

            if(!(recipeName.matches("") || recipeDescription.matches("") ||
                    recipeIngredients.matches("") || recipeSteps.matches(""))){
                Recipe recipe = new Recipe.Builder(recipeName, recipeDescription, recipeIngredients, recipeSteps,
                        isVegetarian, isVegan, isGlutenFree, isDairyFree).build();
                addRecipe(recipe, PostActivity.this);
            }else{
                Context context = getApplicationContext();
                String text = "All fields are required.";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }

    public void onCheckboxClicked(View v){
        boolean checked = ((CheckBox) v).isChecked();

        //check which checkbox is checked
        switch(v.getId()){
            case R.id.vegetarianCheckbox:
                if(checked){
                    isVegetarian = "Vegetarian: yes";
                }else{
                    isVegetarian = "Vegetarian: no";
                }
                break;
            case R.id.veganCheckbox:
                if(checked){
                    isVegan = "Vegan: yes";
                }else{
                    isVegan = "Vegan: no";
                }
                break;
            case R.id.dairyFreeCheckbox:
                if(checked){
                    isDairyFree = "Dairy Free: yes";
                }else{
                    isDairyFree = "Dairy Free: no";
                }
                break;
            case R.id.glutenFreeCheckbox:
                if(checked){
                    isGlutenFree = "Gluten Free: yes";
                }else{
                    isGlutenFree = "Gluten Free: no";
                }
        }
    }

    private void addRecipe(Recipe recipe, Context context){
        JsonUtils utils = new JsonUtils(context);
        utils.addRecipe(recipe, context);

        startActivity(new Intent(PostActivity.this,ViewActivity.class));
    }
}
