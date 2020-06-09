package raum.muchbeer.pagingsearchfood.DB;


import android.util.Log;

import org.reactivestreams.Subscription;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import raum.muchbeer.pagingsearchfood.model.Food;

public class InitialFoodRxJava {

    private CompositeDisposable roomDisposables = new CompositeDisposable();
    private static final String LOG_TAG = InitialFoodRxJava.class.getSimpleName();
    private static FoodDao foodDao;

    public InitialFoodRxJava(FoodDatabase instance) {
        foodDao = instance.foodDao();
    }

    public void insertLocalRoom() {
        roomDisposables.add(Flowable.fromCallable(() -> {

            foodDao.insert(new Food("RxJava Food", "1500", "Mpabwa"));
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
            foodDao.insert(new Food("Ugali Nyama choma", "3500", "Mtoa"));
            foodDao.insert(new Food("Dafuzi", "5400", "CHoma"));

            Food loadFoodData=   foodDao.loadFoodObject();

            return loadFoodData;
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe((result) -> {
                    Log.d(LOG_TAG, "The value out from the observer" + result);
                }));
    };

    public void insertRoom() {
        Flowable.fromCallable(() -> {
            foodDao.insert(new Food("Mbuzi choma", "4000", "Mzee babu"));
            foodDao.insert(new Food("RxJava Food", "1500", "Mpabwa"));
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
            foodDao.insert(new Food("Ugali Nyama choma", "3500", "Mtoa"));
            foodDao.insert(new Food("Dafuzi", "5400", "CHoma"));

            Food loadFoodData=   foodDao.loadFoodObject();
            Log.d(LOG_TAG, "rXjAVA input composite: " + loadFoodData);
            return "Done";
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(new FlowableSubscriber<String>() {
                    @Override
                    public void onSubscribe(@NonNull Subscription s) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable t) {
                    Log.d(LOG_TAG, "Error observer: " + t);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(LOG_TAG, "Task completed: " );
                    }
                });
    }

    public void clear() {
        roomDisposables.clear();
    }
}


