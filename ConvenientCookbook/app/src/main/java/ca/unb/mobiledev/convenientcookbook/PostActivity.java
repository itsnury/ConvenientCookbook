package ca.unb.mobiledev.convenientcookbook;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PostActivity extends AppCompatActivity {
    private TextView postTitle;
    private TextView recipeName;
    private TextView recipeDescription;
    private TextView recipeIngredients;
    private TextView recipeSteps;

    private EditText nameTextbox;
    private EditText descriptionTextbox;
    private EditText ingredientsTextbox;
    private EditText stepsTextbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        postTitle = findViewById(R.id.postPage);
        recipeName = findViewById(R.id.recipeName);
        recipeDescription = findViewById(R.id.recipeDescription);
        recipeIngredients = findViewById(R.id.recipeIngredients);
        recipeSteps = findViewById(R.id.recipeSteps);

        postTitle.setText("Post Recipe");
        recipeName.setText("Recipe name: ");
        recipeDescription.setText("Recipe description: ");
        recipeIngredients.setText("Ingredients: ");
        recipeSteps.setText("Steps: ");

        nameTextbox = findViewById(R.id.nameTextbox);
        descriptionTextbox = findViewById(R.id.descriptionTextbox);
        ingredientsTextbox = findViewById(R.id.ingredientsTextbox);
        stepsTextbox = findViewById(R.id.stepsTextbox);
    }
}
