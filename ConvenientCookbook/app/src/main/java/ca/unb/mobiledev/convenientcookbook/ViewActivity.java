package ca.unb.mobiledev.convenientcookbook;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ca.unb.mobiledev.convenientcookbook.model.Recipe;
import ca.unb.mobiledev.convenientcookbook.util.JsonUtils;

public class ViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        JsonUtils utils = new JsonUtils(ViewActivity.this);
        ArrayList<Recipe> list = utils.getRecipes();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        MyAdapter adapter = new MyAdapter(list, ViewActivity.this);
        recyclerView.setAdapter(adapter);
    }
}