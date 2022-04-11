package ca.unb.mobiledev.convenientcookbook;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ca.unb.mobiledev.convenientcookbook.model.Recipe;

public class ViewActivity extends AppCompatActivity {
    //private RecipeViewModel recipeViewModel;
    private DBManager dbManager;
    private ExecutorService executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        dbManager = new DBManager(ViewActivity.this);
        executor = Executors.newSingleThreadExecutor();

        Spinner spinner = (Spinner) findViewById(R.id.dropdown_menu);

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
    }

    public void show(ArrayList<Recipe> list){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        MyAdapter adapter = new MyAdapter(list, ViewActivity.this);
        recyclerView.setAdapter(adapter);
    }

    private void searchRecords(String filter) throws ExecutionException, InterruptedException {
        show(updateItemsList(filter));
    }

    public ArrayList<Recipe> updateItemsList(String filter) throws ExecutionException, InterruptedException {
        ArrayList<Recipe> list = new ArrayList<Recipe>();
        executor.execute(()-> {
            Cursor cursor;
            if (filter.equals("vegetarian")) {
                cursor = dbManager.listVegetarianRecords();
            } else if (filter.equals("vegan")) {
                cursor = dbManager.listVeganRecords();
            } else if (filter.equals("Gluten-free")) {
                cursor = dbManager.listGlutenFreeRecords();
            } else if (filter.equals("Dairy-free")) {
                cursor = dbManager.listDairyFreeRecords();
            } else {
                cursor = dbManager.listAllRecords();
            }

            Log.d("TAG", Integer.toString(cursor.getCount()));

            if(cursor != null) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow("recipeName"));
                    String description = cursor.getString(cursor.getColumnIndexOrThrow("recipeDescription"));
                    String ingredients = cursor.getString(cursor.getColumnIndexOrThrow("recipeIngredients"));
                    String steps = cursor.getString(cursor.getColumnIndexOrThrow("recipeSteps"));
                    String isVegetarian = cursor.getString(cursor.getColumnIndexOrThrow("vegetarian"));
                    String isVegan = cursor.getString(cursor.getColumnIndexOrThrow("vegan"));
                    String isGlutenFree = cursor.getString(cursor.getColumnIndexOrThrow("glutenFree"));
                    String isDairyFree = cursor.getString(cursor.getColumnIndexOrThrow("dairyFree"));

                    list.add(new Recipe.Builder(id, name, description, ingredients, steps, isVegetarian,
                            isVegan, isGlutenFree, isDairyFree).build());

                    cursor.moveToNext();
                }
                cursor.close();
            }
        });
        return list;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        dbManager.close();
    }
}