package mobiledev.unb.ca.recyclerviewlab.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

import mobiledev.unb.ca.recyclerviewlab.model.Course;

public class JsonUtils {
    private static final String CS_JSON_FILE = "CS.json";

    private static final String KEY_COURSES = "courses";
    private static final String KEY_COURSE_ID = "courseID";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";

    private ArrayList<Course> coursesArray;

    // Initializer to read our data source (JSON file) into an array of course objects
    public JsonUtils(Context context) {
        processJSON(context);
    }

    private void processJSON(Context context) {
        coursesArray = new ArrayList();

        try {
            // Create a JSON Object from file contents String
            JSONObject jsonObject = new JSONObject(Objects.requireNonNull(loadJSONFromAssets(context)));

            // Create a JSON Array from the JSON Object
            // This array is the "courses" array mentioned in the lab write-up
            JSONArray jsonArray = jsonObject.getJSONArray(KEY_COURSES);

            for (int i = 0; i < jsonArray.length(); i++) {
                // TODO 1:
                //  Using the JSON array update coursesArray

                // Create a JSON Object from individual JSON Array element
                JSONObject myObj = (JSONObject) jsonArray.get(i);

                // set data from individual JSON Object

                // Add new Course to courses ArrayList;
                Course course = new Course.Builder(myObj.getString(KEY_COURSE_ID), myObj.getString(KEY_NAME),
                        myObj.getString(KEY_DESCRIPTION)).build();

                coursesArray.add(course);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String loadJSONFromAssets(Context context) {
        // TODO 2:
        //  1. Obtain an instance of the AssetManager class from the referenced context
        //    (https://developer.android.com/reference/android/content/Context#getAssets())
        AssetManager manager = context.getAssets();

        //  2. Open the CS_JSON_FILE from the assets folder
        //     (https://developer.android.com/reference/android/content/res/AssetManager)
        try {
            InputStream ioStream = manager.open(CS_JSON_FILE);
            Log.w("here", "utils");

            //  3. Process the file using an InputStream
            byte[] arr = new byte[ioStream.available()];

            ioStream.read(arr);
            String str = new String(arr, "UTF-8");
            return str;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    // Getter method for courses ArrayList
    public ArrayList<Course> getCourses() {
        return coursesArray;
    }
}
