package raum.muchbeer.pagingsearchfood.DB;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import raum.muchbeer.pagingsearchfood.R;
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
        /*        Food tzFood1=new Food();
        tzFood1.setFood("Wali");


        Food tzFood2=new Food();
        tzFood2.setFood("Maharage");


        Food tzFood3=new Food();
        tzFood3.setFood("Samaki");

        Food tzFood4=new Food();
        tzFood4.setFood("Viazi vitamu");

        Food tzFood5=new Food();
        tzFood4.setFood("Viazi mviringo");

        Food tzFood6=new Food();
        tzFood6.setFood("Nyama");

        Food tzFood7=new Food();
        tzFood7.setFood("Miogo");

        Food tzFood8=new Food();
        tzFood8.setFood("Mtori");

        Food tzFood9=new Food();
        tzFood9.setFood("Ndizi");

        Food tzFood10=new Food();
        tzFood10.setFood("Kisamvu");

        Food tzFood11=new Food();
        tzFood11.setFood("Maboga");

        Food tzFood12=new Food();
        tzFood12.setFood("Mrenda");

        tzFood12.setFood("Mrenda");

        tzFood12.setFood("Mrenda");

        Food tzFood13=new Food();
        tzFood13.setFood("Sukuma Week");

        foodDao.insert(tzFood1);
        foodDao.insert(tzFood2);
        foodDao.insert(tzFood3);
        foodDao.insert(tzFood4);
        foodDao.insert(tzFood5);
        foodDao.insert(tzFood6);
        foodDao.insert(tzFood7);
        foodDao.insert(tzFood8);
        foodDao.insert(tzFood9);
        foodDao.insert(tzFood10);*/

        return null;
    }


}
