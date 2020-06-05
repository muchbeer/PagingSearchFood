package raum.muchbeer.pagingsearchfood.DB;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import raum.muchbeer.pagingsearchfood.model.Food;

@Dao
public interface FoodDao {

    @Insert
    void insert(Food tzFood);

    @Query("SELECT * FROM food")
    Food loadFoodObject();

    @Query("SELECT * FROM food order by food_name")
    DataSource.Factory<Integer, Food> loadAllFood();

    @Query("SELECT * FROM food where food_name LIKE  :name order by food_name")
    DataSource.Factory<Integer, Food> loadAllFoodFromSearch(String name);
}
