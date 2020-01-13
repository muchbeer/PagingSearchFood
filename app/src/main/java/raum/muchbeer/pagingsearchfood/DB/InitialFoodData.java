package raum.muchbeer.pagingsearchfood.DB;

import android.os.AsyncTask;

import raum.muchbeer.pagingsearchfood.model.Food;

public class InitialFoodData extends AsyncTask<Void, Void, Void> {

    private FoodDao foodDao;

    public InitialFoodData(FoodDatabase instance) {
       foodDao = instance.foodDao();
    }

    @Override
    protected Void doInBackground(Void... voids) {

        Food tzFood1=new Food();
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
        foodDao.insert(tzFood10);

        return null;
    }
}
