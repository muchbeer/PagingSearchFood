package raum.muchbeer.pagingsearchfood.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import raum.muchbeer.pagingsearchfood.DB.FoodDatabase;
import raum.muchbeer.pagingsearchfood.R;
import raum.muchbeer.pagingsearchfood.adapter.FoodAdapter;
import raum.muchbeer.pagingsearchfood.databinding.ActivityMainBinding;
import raum.muchbeer.pagingsearchfood.model.Food;
import raum.muchbeer.pagingsearchfood.viewmodel.FoodViewModel;

public class MainActivity extends AppCompatActivity {
    private PagedList<Food> foodsactivity;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    FoodViewModel viewModel;
    private FoodAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        adapter = new FoodAdapter(this);
        viewModel = ViewModelProviders.of(this).get(FoodViewModel.class);

        viewModel.initialFood(FoodDatabase.getINSTANCE(this).foodDao());

        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        getFoodList();

        RecyclerView recyclerView = binding.recycler;
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(adapter);


        //first time set an empty value to get all data
        viewModel.filterFoodName.setValue("");
    }


    public void getFoodList() {

        viewModel.listAllFood.observe(this, foodlistPaging -> {
            try {
                Log.d(LOG_TAG, "list of all page number " + foodlistPaging.size());
                Log.d(LOG_TAG, "list of food are " + foodlistPaging);

                foodsactivity = foodlistPaging;

                adapter.submitList(foodlistPaging);

            } catch (Exception e) {
            }
        });


    }


    }
