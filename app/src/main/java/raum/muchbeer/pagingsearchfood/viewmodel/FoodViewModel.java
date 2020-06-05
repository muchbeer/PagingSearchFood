package raum.muchbeer.pagingsearchfood.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import raum.muchbeer.pagingsearchfood.DB.FoodDao;
import raum.muchbeer.pagingsearchfood.model.Food;

public class FoodViewModel extends ViewModel {

    private static final String LOG_TAG = FoodViewModel.class.getSimpleName();
    private FoodDao foodDao;
    public LiveData<PagedList<Food>> listAllFood;
    private LiveData<PagedList<Food>> listAllFoodsInDb;
    public MutableLiveData<String> filterFoodName = new MutableLiveData<>();

public void initialFood(final FoodDao foodDao) {
    this.foodDao = foodDao;

    PagedList.Config config = (new PagedList.Config.Builder())
            .setPageSize(10)
            .build();

    listAllFood = Transformations.switchMap(new DebouncedLiveData<>(filterFoodName, 400), input -> {

               if (input == null || input.equals("") || input.equals("%%")) {

                   synchronized (this) {
                       //check data is loaded before or not
                       if (listAllFoodsInDb == null)
                           listAllFoodsInDb = new LivePagedListBuilder<>(
                                   foodDao.loadAllFood(), config)
                                   .build();
                   }
                   return listAllFoodsInDb;
            } else {

                   return new LivePagedListBuilder<>(
                           foodDao.loadAllFoodFromSearch("%" + input + "%"), config)
                           .build();
            }
        });
    }
}
