package ca.unb.mobiledev.convenientcookbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.lang.String;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ca.unb.mobiledev.convenientcookbook.model.Recipe;

public class PostActivity extends AppCompatActivity {
    private ExecutorService executor;
    private DBManager dbManager;

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

    //private RecipeViewModel recipeViewModel;

    private int currentId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        executor = Executors.newSingleThreadExecutor();
        dbManager = new DBManager(PostActivity.this);

        nameTextbox = findViewById(R.id.nameTextbox);
        descriptionTextbox = findViewById(R.id.descriptionTextbox);
        ingredientsTextbox = findViewById(R.id.ingredientsTextbox);
        stepsTextbox = findViewById(R.id.stepsTextbox);

        vegetarianCheckbox = findViewById(R.id.vegetarianCheckbox);
        veganCheckbox = findViewById(R.id.veganCheckbox);
        dairyFreeCheckbox = findViewById(R.id.dairyFreeCheckbox);
        glutenFreeCheckbox = findViewById(R.id.glutenFreeCheckbox);

        //recipeViewModel.setParent(PostActivity.this);

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

            if(!(recipeName.matches("") || recipeDescription.matches("") ||
                    recipeIngredients.matches("") || recipeSteps.matches(""))){

                Recipe recipe = new Recipe.Builder(recipeId, recipeName + "\n\n", "Description\n" +
                        recipeDescription + "\n\n", "Ingredients:\n" + recipeIngredients + "\n\n",
                        "Steps:\n" + recipeSteps + "\n\n", isVegetarian, isVegan, isGlutenFree, isDairyFree).build();
                addRecipe(recipe);

                startActivity(new Intent(PostActivity.this,ViewActivity.class));
            }else{
                Context context = getApplicationContext();
                String text = "All fields are required.";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        //recipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
    }

    public void onCheckboxClicked(View v){
        boolean checked = ((CheckBox) v).isChecked();

        //check which checkbox is checked
        switch(v.getId()){
            case R.id.vegetarianCheckbox:
                if(checked){
                    isVegetarian = "Vegetarian: yes\n";
                }
                break;
            case R.id.veganCheckbox:
                if(checked){
                    isVegan = "Vegan: yes\n";
                }
                break;
            case R.id.dairyFreeCheckbox:
                if(checked){
                    isDairyFree = "Dairy Free: yes\n";
                }
                break;
            case R.id.glutenFreeCheckbox:
                if(checked){
                    isGlutenFree = "Gluten Free: yes\n";
                }
        }
    }

    private void addRecipe(Recipe recipe) {
        // TODO
        //  Make a call to the view model to create a record in the database table
        executor.execute(()->{
            dbManager.insertRecord(recipe.getId(), recipe.getName(), recipe.getDescription(),
                    recipe.getIngredients(), recipe.getSteps(), recipe.isVegetarian(),recipe.isVegan(),
                    recipe.isGlutenFree(), recipe.isDairyFree());
        });
        //recipeViewModel.addRecord(recipe);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        dbManager.close();
    }
}
