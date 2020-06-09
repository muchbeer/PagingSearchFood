package raum.muchbeer.pagingsearchfood.DB;

import android.content.Context;
import android.os.AsyncTask;

import raum.muchbeer.pagingsearchfood.model.Food;

public class InitialFoodData extends AsyncTask<Void, Void, Void> {

    private static final String LOG_TAG = InitialFoodData.class.getSimpleName();
    private static FoodDao foodDao;
    private static Context activity;

    public InitialFoodData(FoodDatabase instance, Context context) {
        foodDao = instance.foodDao();
        activity = context.getApplicationContext();
    }

    @Override
    protected Void doInBackground(Void... voids) {

        foodDao.insert(new Food("Kisamvu Makande", "1500", "Mpabwa"));
        foodDao.insert(new Food("Karanga Mdafu", "1800", "Mpekaje"));
        foodDao.insert(new Food("Mtori Chapati", "1700", "Muchbeer"));
        foodDao.insert(new Food("Ugali Maharage", "1000", "Dotto"));
        foodDao.insert(new Food("Wale Maharage", "1200", "KPC"));
        foodDao.insert(new Food("Chicken Wing", "5000", "KFC"));
        foodDao.insert(new Food("mAKANDE karanga", "2000", "Container"));
        foodDao.insert(new Food("Kitimoto Ndizi", "9000", "Babu"));
        foodDao.insert(new Food("Mtori Chapati", "2000", "Container"));
        foodDao.insert(new Food("Supu Mandazi ", "2500", "Victoria"));
        foodDao.insert(new Food("Chips Kuku", "3500", "Bima"));
        foodDao.insert(new Food("Ugali Kisamvu", "2300", "Kinyerezo"));
        foodDao.insert(new Food("Mtori Andazi", "2100", "Container"));
        foodDao.insert(new Food("Kongoro Chapati", "2300", "Jomoki"));
        foodDao.insert(new Food("Shuwarma", "7000", "Wallet"));
        foodDao.insert(new Food("Karanga Choroko", "4000", "Mtaijeka"));
        foodDao.insert(new Food("Viazi Vitamu Nyama", "1800", "Yusna"));

        return null;
    }


}
