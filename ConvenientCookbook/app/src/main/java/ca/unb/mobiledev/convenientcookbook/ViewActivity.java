package ca.unb.mobiledev.convenientcookbook;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import ca.unb.mobiledev.convenientcookbook.model.Recipe;
import ca.unb.mobiledev.convenientcookbook.util.JsonUtils;

public class ViewActivity extends AppCompatActivity {
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
                JsonUtils utils = null;
                String selected = parent.getItemAtPosition(position).toString();

                //0 vegetarian, 1 vegan, 2 gluten free, 3 dairy free
                if(selected.equals("Vegetarian")) {
                    utils = new JsonUtils(ViewActivity.this, 0);
                }else if(selected.equals("Vegan")) {
                    utils = new JsonUtils(ViewActivity.this, 1);
                }else if(selected.equals("Gluten-free")) {
                    utils = new JsonUtils(ViewActivity.this, 2);
                }else if(selected.equals("Dairy-free")) {
                    utils = new JsonUtils(ViewActivity.this, 3);
                }else if(selected.equals("All")){
                    utils = new JsonUtils(ViewActivity.this, -1);
                }

                ArrayList<Recipe> list = utils.getRecipes();
                show(list);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                JsonUtils utils = new JsonUtils(ViewActivity.this, -1);
                ArrayList<Recipe> list = utils.getRecipes();
                show(list);
            }
        });
    }

    public void show(ArrayList<Recipe> list){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        MyAdapter adapter = new MyAdapter(list, ViewActivity.this);
        recyclerView.setAdapter(adapter);
    }
}