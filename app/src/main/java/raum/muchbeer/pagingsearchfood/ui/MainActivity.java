package raum.muchbeer.pagingsearchfood.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import raum.muchbeer.pagingsearchfood.DB.FoodDatabase;
import raum.muchbeer.pagingsearchfood.R;
import raum.muchbeer.pagingsearchfood.adapter.FoodAdapter;
import raum.muchbeer.pagingsearchfood.model.Food;
import raum.muchbeer.pagingsearchfood.viewmodel.FoodViewModel;

public class MainActivity extends AppCompatActivity {
    private PagedList<Food> foodsactivity;

    private RecyclerView recyclerView;
    EditText searchFood;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    FoodViewModel viewModel;
    private FoodAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        searchFood = findViewById(R.id.search_food);

        adapter = new FoodAdapter(this);
        viewModel = ViewModelProviders.of(this).get(FoodViewModel.class);

        viewModel.initialFood(FoodDatabase.getINSTANCE(this).foodDao());

getFoodList();


        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        // adding inbuilt divider line
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // adding custom divider line with padding 16dp
        // recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setItemAnimator(new DefaultItemAnimator());



        recyclerView.setAdapter(adapter);

//        adapter.notifyDataSetChanged();
//first time set an empty value to get all data
        viewModel.filterFoodName.setValue("");

        searchFood.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //just set the current value to search.
                viewModel.filterFoodName.
                        setValue("%" + editable.toString() + "%");
            }
        });
    }


    public void getFoodList() {

        viewModel.listAllFood.observe(this, foodlistPaging -> {
            try {
                Log.d(LOG_TAG, "list of all page number " + foodlistPaging.size());
                Log.d(LOG_TAG, "list of food are " + foodlistPaging);
                //refresh current list
                foodsactivity = foodlistPaging;
             //   showOnRecyclerView();
                adapter.submitList(foodlistPaging);

            } catch (Exception e) {
            }
        });


    }

    private void showOnRecyclerView() {

   //    movieAdapter = new MovieAdapter(this, moviesMain);
//paging here below

        adapter.submitList(foodsactivity);

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
           recyclerView.setLayoutManager(mLayoutManager);

        // adding inbuilt divider line
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // adding custom divider line with padding 16dp
        // recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
     //   adapter.notifyDataSetChanged();
    }

    }
