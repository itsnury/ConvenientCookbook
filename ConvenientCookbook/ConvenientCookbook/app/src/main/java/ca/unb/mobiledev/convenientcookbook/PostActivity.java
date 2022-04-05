package ca.unb.mobiledev.convenientcookbook;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import ca.unb.mobiledev.convenientcookbook.model.Recipe;

public class PostActivity extends AppCompatActivity {


    private EditText nameTextbox;
    private EditText descriptionTextbox;
    private EditText ingredientsTextbox;
    private EditText stepsTextbox;

    private String recipeId;
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

    private RecipeViewModel recipeViewModel;

    private DBManager dbManager;

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

        recipeViewModel.setParent(PostActivity.this);

        Button submitBtn = findViewById(R.id.submitBtn);

        dbManager = new DBManager(PostActivity.this);

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
                isVegetarian = "Vegetarian: no\n";
            }else if(isVegan.matches("")){
                isVegan = "Vegan: no\n";
            }else if(isDairyFree.matches("")){
                isDairyFree = "Dairy Free: no\n";
            }else if(isGlutenFree.matches("")){
                isGlutenFree = "Gluten Free: no\n";
            }

            if(!(recipeName.matches("") || recipeDescription.matches("") ||
                    recipeIngredients.matches("") || recipeSteps.matches(""))){
                Recipe recipe = new Recipe.Builder(recipeId, recipeName, recipeDescription, recipeIngredients, recipeSteps,
                        isVegetarian, isVegan, isGlutenFree, isDairyFree).build();
                addRecipe(recipe);
            }else{
                Context context = getApplicationContext();
                String text = "All fields are required.";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        recipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
    }

    public void onCheckboxClicked(View v){
        boolean checked = ((CheckBox) v).isChecked();

        //check which checkbox is checked
        switch(v.getId()){
            case R.id.vegetarianCheckbox:
                if(checked){
                    isVegetarian = "Vegetarian: yes\n";
                }else{
                    isVegetarian = "Vegetarian: no\n";
                }
                break;
            case R.id.veganCheckbox:
                if(checked){
                    isVegan = "Vegan: yes\n";
                }else{
                    isVegan = "Vegan: no\n";
                }
                break;
            case R.id.dairyFreeCheckbox:
                if(checked){
                    isDairyFree = "Dairy Free: yes\n";
                }else{
                    isDairyFree = "Dairy Free: no\n";
                }
                break;
            case R.id.glutenFreeCheckbox:
                if(checked){
                    isGlutenFree = "Gluten Free: yes\n";
                }else{
                    isGlutenFree = "Gluten Free: no\n";
                }
        }
    }

    private void addRecipe(Recipe recipe) {
        // TODO
        //  Make a call to the view model to create a record in the database table
        dbManager.insertRecord(recipe.getId(), recipe.getName(),recipe.isVegetarian(), recipe.isVegan()
        ,recipe.isGlutenFree(), recipe.isDairyFree());
    }
}
