package ca.unb.mobiledev.convenientcookbook;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import ca.unb.mobiledev.convenientcookbook.model.Recipe;
import ca.unb.mobiledev.convenientcookbook.util.JsonUtils;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button viewBtn = (Button) findViewById(R.id.viewBtn);
        Button postBtn = (Button) findViewById(R.id.postBtn);

        viewBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this,ViewActivity.class));
            }
        });

        postBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this,PostActivity.class));
            }
        });
//        JsonUtils utils = new JsonUtils(MainActivity.this);
//        ArrayList<Recipe> list = utils.getRecipes();
//
//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        MyAdapter adapter = new MyAdapter(list, MainActivity.this);
//        recyclerView.setAdapter(adapter);
    }
}