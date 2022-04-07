package ca.unb.mobiledev.convenientcookbook.model;

import androidx.annotation.NonNull;

public class Recipe {
    private final String id;
    private final String name;
    private final String description;
    private final String ingredients;
    private final String steps;

    private final String vegetarian;
    private final String vegan;
    private final String glutenFree;
    private final String dairyFree;

    public Recipe(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.ingredients = builder.ingredients;
        this.steps = builder.steps;

        this.vegetarian = builder.vegetarian;
        this.vegan = builder.vegan;
        this.glutenFree = builder.glutenFree;
        this.dairyFree = builder.dairyFree;
    }

    public String getId() {return id; }
    public String getName(){ return name; }

    public String getDescription(){
        return description;
    }

    public String getIngredients(){
        return ingredients;
    }

    public String getSteps(){
        return steps;
    }

    public String isVegetarian(){
        return vegetarian;
    }

    public String isVegan(){
        return vegan;
    }

    public String isGlutenFree(){
        return glutenFree;
    }

    public String isDairyFree(){
        return dairyFree;
    }

    public static class Builder {
        private final String id;
        private final String name;
        private final String description;
        private final String ingredients;
        private final String steps;

        private final String vegetarian;
        private final String vegan;
        private final String glutenFree;
        private final String dairyFree;

        public Builder(@NonNull String id, @NonNull String name, @NonNull String description, @NonNull String ingredients, @NonNull String steps,
                       @NonNull String vegetarian, @NonNull String vegan, @NonNull String glutenFree, @NonNull String dairyFree){
            this.id = id;
            this.name = name;
            this.description = description;
            this.ingredients = ingredients;
            this.steps = steps;

            this.vegetarian = vegetarian;
            this.vegan = vegan;
            this.glutenFree = glutenFree;
            this.dairyFree = dairyFree;
        }

        public Recipe build(){
            return new Recipe(this);
        }
    }
}
