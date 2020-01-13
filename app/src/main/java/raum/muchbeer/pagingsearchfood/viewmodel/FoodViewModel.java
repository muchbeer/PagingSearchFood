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
    public MutableLiveData<String> filterFoodName = new MutableLiveData<>();

public void initialFood(final FoodDao foodDao) {
    this.foodDao = foodDao;

    PagedList.Config config = (new PagedList.Config.Builder())
            .setPageSize(10)
            .build();

    listAllFood = Transformations.switchMap(filterFoodName, input -> {

               if (input == null || input.equals("") || input.equals("%%")) {
                //check if the current value is empty load all data else search
                return new LivePagedListBuilder<>(
                        foodDao.loadAllFood(), config)
                        .build();
            } else {
                   return new LivePagedListBuilder<>(
                        foodDao.loadAllFoodFromSearch(input),config)
                        .build();
            }
        });
    }
}
