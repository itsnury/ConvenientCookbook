package ca.unb.mobiledev.convenientcookbook;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import ca.unb.mobiledev.convenientcookbook.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        TextView textView = findViewById(R.id.description_textview);

        textView.setText(bundle.getString("description"));
        textView.append(bundle.getString("ingredients"));
        textView.append(bundle.getString("steps"));
        textView.append(bundle.getString("vegetarian"));
        textView.append(bundle.getString("vegan"));
        textView.append(bundle.getString("glutenFree"));
        textView.append(bundle.getString("dairyFree"));

        textView.setMovementMethod(new ScrollingMovementMethod());

        getSupportActionBar().setTitle(bundle.getString("name"));

    }
}
