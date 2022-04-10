# ConvenientCookbook
CS2063 Project

# Introduction
The Convenient Cookbook app is a mobile android application built for CS2063.
## Functionaility
Users can either view recipes that have already been posted to the app or post their own recipe. When viewing recipes users can select filters for specific dietary restrictions to find recipes that work best for them, users can also see all the details required to recreate the recipe themselves. If a user chooses to post their own recipe they will be given a template to fill out with fields such as the recipe name, description, ingredients, steps to make it and what dietary restrictions it meets. 

# Contributors
Nury Kim (Project Manager)

Paige Corbyn 

# Getting Started 
* The app is built to run in the Artctic Fox (2020.3.1 Patch 4) version of Android Studio

* If using another version you will be prompted to update the Gradle plugin used

* Pull the most recent version of the application and open in Android Studio

* Build the project before running, either run using an android emulator or with a physical device for testing

# Project files

* MainActivity.java - Main Acitivty for the app, contains the apps homescreen and buttons for the user to either view or post recipes. Has intents to start ViewActivity or PostActivity depending on what the user selects.

* ViewActivity.java - Activity to start if user clicks the View button on the homepage. This class allows the user to view all recipes or narrow down to recipes for specific dietary restrictions using the filter dropdown.

* PostActivity.java - Activity to start if the user clicks post on the homepage. This class gives the user a template to fill out recipe details including the name, descriptions, ingredients, steps and checkboxes for them to select which dietary restrictions the recipe meets. Once the user has filled out all required fields they will click the submit button which will add the recipe to the databaase and allow it to be viewed on the view page. 

* DatabaseHelper.java - Class to set up SQLite databse for the application, creates a table for a recipe object. 

* DBManager.java - Class containing methods to axct as queries on the database where required. Includes a listAllRecords() method as well as list methods for recipes with specific dietary restrictions met. DBmanager class also contains an insertRecord() which is used when a user posts a recipe. 

* Recipe.java - Class representing a recipe object, includes getters and setters for all recipe attributes. 

* MyAdapter.java - Interface class for implementing the RecyclerView for ViewActivity.java

* RecipeViewModel.java - Class used to implement the Android ViewModel to display all recipes and their specific details for users on the view page
