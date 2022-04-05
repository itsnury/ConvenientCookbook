package ca.unb.mobiledev.convenientcookbook;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import ca.unb.mobiledev.convenientcookbook.model.RecipeDao;
import ca.unb.mobiledev.convenientcookbook.AppDatabase;
import ca.unb.mobiledev.convenientcookbook.model.Recipe;

public class RecipeRepository {
    private final RecipeDao recipeDao;

    public RecipeRepository(Application application, Context context) {
        AppDatabase db = AppDatabase.getDatabase(application);
        AppDatabase.setParent(context);
        recipeDao = db.recipeDao();
    }

    //  See the example project file at
    //  https://github.com/hpowell20/cs2063-winter-2022-examples/blob/master/Lecture7/RoomPersistenceLibraryDemo/app/src/main/java/mobiledev/unb/ca/roompersistencetest/repository/ItemRepository.java
    //  to see examples of how to work with the Executor Service

    // TODO Add query specific methods
    //  HINTS
    //   The insert operation can make use of the Runnable interface
    //   As the search operation needs to return results have a look at using Callable interface with Future
    //  Some more details on runnables and callables can be found <a href="https://www.baeldung.com/java-runnable-callable">here</a>
    public List<Recipe> listAllRecords(String filter) throws ExecutionException, InterruptedException {
        Future <List<Recipe>> future = AppDatabase.databaseWriterExecutor.submit(new Callable<List<Recipe>>(){
            public List<Recipe> call() throws Exception {
                if(filter.equals("vegetarian")){
                    return recipeDao.listVegetarianRecords();
                }else if(filter.equals("vegan")){
                    return recipeDao.listVeganRecords();
                }else if(filter.equals("dairy free")){
                    return recipeDao.listDairyFreeRecords();
                }else if(filter.equals("gluten free")){
                    return recipeDao.listGlutenFreeRecords();
                }

                return recipeDao.listAllRecords();
            }
        });
        return future.get();
    }

    public void insertRecord(final Recipe recipe){
        AppDatabase.databaseWriterExecutor.execute(() -> recipeDao.insert(recipe));
    }
}
