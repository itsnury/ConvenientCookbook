package ca.unb.mobiledev.convenientcookbook;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

import ca.unb.mobiledev.convenientcookbook.model.Recipe;
import ca.unb.mobiledev.convenientcookbook.DetailActivity;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private final static String TAG = "My Adapter";
    private final ArrayList<Recipe> mDataset;
    private final Activity parentActivity;

    public MyAdapter(ArrayList<Recipe> myDataset, Activity parentActivity){
        mDataset = myDataset;
        this.parentActivity = parentActivity;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Recipe currentRecipe = mDataset.get(position);

        holder.mTextView.setText(currentRecipe.getName());

        holder.mTextView.setOnClickListener(v-> {
            Intent intent = new Intent(parentActivity, DetailActivity.class);
            intent.putExtra("name", currentRecipe.getName());
            intent.putExtra("description", currentRecipe.getDescription());
            intent.putExtra("ingredients", currentRecipe.getIngredients());
            intent.putExtra("steps", currentRecipe.getSteps());
            intent.putExtra("vegetarian", currentRecipe.isVegetarian());
            intent.putExtra("vegan", currentRecipe.isVegan());
            intent.putExtra("glutenFree", currentRecipe.isGlutenFree());
            intent.putExtra("dairyFree", currentRecipe.isDairyFree());

            parentActivity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
