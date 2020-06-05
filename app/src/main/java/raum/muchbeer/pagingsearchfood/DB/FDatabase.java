package raum.muchbeer.pagingsearchfood.DB;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import raum.muchbeer.pagingsearchfood.R;
import raum.muchbeer.pagingsearchfood.model.Food;

@Database(entities = {Food.class}, version = 1, exportSchema = false)
public abstract class FDatabase extends RoomDatabase {
    private static final String LOG_TAG = "FDatabase";
    private static volatile FDatabase INSTANCE;

    public abstract FoodDao foodDao();

    private static Context activity;

    public static FDatabase getINSTANCE(Context context) {
        Log.d(LOG_TAG, "eNTER THE FoodDatabase: ");
        activity = context.getApplicationContext();
        if (INSTANCE == null) {

            synchronized (FDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FDatabase.class, "FDataBaseName.db")
                            .fallbackToDestructiveMigration()
                            .addCallback(callback)
                            //.addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
           new InitialFoodData(INSTANCE).execute();

        }
    };

    private static class InitialFoodData extends AsyncTask<Void, Void, Void> {

        private FoodDao foodDao;

        public InitialFoodData(FDatabase instance) {
            foodDao = instance.foodDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            foodDao.insert(new Food("Kisamvu Makande", "1500", "Mpabwa"));
            foodDao.insert(new Food("Karanga Mdafu", "1800", "Mpekaje"));

            addFood(activity);
            Food loadFoodData=   foodDao.loadFoodObject();
            Log.d(LOG_TAG, "tHE populated data are: " + loadFoodData);
            return null;
        }
    }

    private static JSONArray loadFoodFromArray(Context context) {

        StringBuilder builder = new StringBuilder();

        InputStream in = context.getResources().openRawResource(R.raw.food);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;
        try {
            while((line= reader.readLine()) != null)    {
                builder.append(line);       }

            JSONObject json = new JSONObject(builder.toString());
            return json.getJSONArray("foodjson");

        }catch(IOException | JSONException exceptn) {
            exceptn.printStackTrace();
        }

        return null;
    }

    private static void addFood(Context context) {
        FoodDao dao = getINSTANCE(context).foodDao();

        JSONArray foodlist = loadFoodFromArray(context);
        try{
            for (int foodie=0; foodie<foodlist.length(); foodie++) {
                JSONObject contact = foodlist.getJSONObject(foodie);

                String foodName = contact.getString("food");
                String foodPrice = contact.getString("food_price");
                String foodRestaurant = contact.getString("restauranct");

                Log.d(LOG_TAG, "tHE value fetched is : " + foodName);
                dao.insert(new Food(foodName, foodPrice, foodRestaurant));
            }
        }catch(JSONException e) {
            Log.d(LOG_TAG, "inital load data error: " + e.getMessage());
        }
    }
}
