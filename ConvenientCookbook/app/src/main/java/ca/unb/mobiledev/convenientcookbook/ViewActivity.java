package ca.unb.mobiledev.convenientcookbook;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ca.unb.mobiledev.convenientcookbook.model.Recipe;

public class ViewActivity extends AppCompatActivity {
    private RecipeViewModel recipeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);


        Spinner spinner = (Spinner) findViewById(R.id.dropdown_menu);
        //String[] dropdown = new String[]{"Vegetarian", "Vegan", "Gluten-free", "Dairy-free"};

        ArrayAdapter<CharSequence> dropdownAdapter = ArrayAdapter.createFromResource(this, R.array.dropdown,android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dropdownAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();

                //0 vegetarian, 1 vegan, 2 gluten free, 3 dairy free
                if(selected.equals("Vegetarian")) {
                    try {
                        searchRecords("vegetarian");
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else if(selected.equals("Vegan")) {
                    try {
                        searchRecords("vegan");
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else if(selected.equals("Gluten-free")) {
                    try {
                        searchRecords("gluten free");
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else if(selected.equals("Dairy-free")) {
                    try {
                        searchRecords("dairy free");
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else if(selected.equals("All")){
                    try {
                        searchRecords("all");
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                try {
                    searchRecords("all");
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Set the ViewModel
        recipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        recipeViewModel.setParent(ViewActivity.this);
    }

    public void show(ArrayList<Recipe> list){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        MyAdapter adapter = new MyAdapter(list, ViewActivity.this);
        recyclerView.setAdapter(adapter);
    }

    private void searchRecords(String item) throws ExecutionException, InterruptedException {
        ArrayList<Recipe> list = (ArrayList<Recipe>) recipeViewModel.getItems(item);

        if(list.isEmpty()){
            Log.w("TAG", "empty list");
        }else{
            Log.w("TAG", "not empty");

            show(list);
        }
    }
}