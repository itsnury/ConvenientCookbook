package ca.unb.mobiledev.convenientcookbook;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button viewBtn = findViewById(R.id.viewBtn);
        Button postBtn = findViewById(R.id.postBtn);
        TextView textView = findViewById(R.id.titleTextView);
        TextView textView1 = findViewById(R.id.subtitleTextView);

        textView.setText("Convenient Cookbook");
        textView1.setText("Recipes for everybody");

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
    }
}