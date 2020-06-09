package raum.muchbeer.pagingsearchfood.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import raum.muchbeer.pagingsearchfood.DB.FoodDatabase;
import raum.muchbeer.pagingsearchfood.R;
import raum.muchbeer.pagingsearchfood.adapter.FoodAdapter;
import raum.muchbeer.pagingsearchfood.clicklistener.FoodClickListener;
import raum.muchbeer.pagingsearchfood.databinding.ActivityMainBinding;
import raum.muchbeer.pagingsearchfood.model.Food;
import raum.muchbeer.pagingsearchfood.viewmodel.FoodViewModel;

public class MainActivity extends AppCompatActivity implements FoodClickListener {
    public static final String SELECTED_FOOODIE = "foodie";
    private PagedList<Food> foodsactivity;
    SearchView searchView;
    private RecyclerView recyclerView;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    FoodViewModel viewModel;
    private FoodAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        adapter = new FoodAdapter(this, this::onFoodClicked);
        viewModel = new ViewModelProvider(this).get(FoodViewModel.class);

        viewModel.initialFood(FoodDatabase.getINSTANCE(this).foodDao());

        mainBinding.setViewModel(viewModel);
        mainBinding.setLifecycleOwner(this);
        getFoodList();

        RecyclerView recyclerView = mainBinding.recycler;
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
            Log.d(LOG_TAG, "lIST is : " + foodlistPaging);
            try {
                Log.d(LOG_TAG, "list of all page number " + foodlistPaging.size());
                Log.d(LOG_TAG, "list of food are " + foodlistPaging);

                foodsactivity = foodlistPaging;

                adapter.submitList(foodlistPaging);

            } catch (Exception e) {
            }
        });


    }

    @Override
    public void onFoodClicked(Food foodie, View sharedView) {
        Toast.makeText(getApplicationContext(), "The food is: " + foodie.getFood(),
                Toast.LENGTH_LONG).show();

        //CONTENT TRANSITION
    /*    Bundle bundle = ActivityOptions
                .makeSceneTransitionAnimation(this)
                .toBundle();
           */

    //sHARED transition
        Bundle bundle = ActivityOptions
                .makeSceneTransitionAnimation(
                        this,
                        sharedView,
                        sharedView.getTransitionName())
                .toBundle();

        Intent intent = new Intent(this, FoodActivity.class);
        intent.putExtra(SELECTED_FOOODIE, foodie);
        //  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent, bundle);
    }

/*    @Override
    public void onFoodClicked(Food foodie) {
        Toast.makeText(getApplicationContext(), "The food is: " + foodie.getFood(),
                Toast.LENGTH_LONG).show();

        Bundle bundle = ActivityOptions
                .makeSceneTransitionAnimation(
                        this,
                        sharedView,
                        sharedView.getTransitionName())
                .toBundle();

        Intent intent = new Intent(this, FoodActivity.class);
        intent.putExtra(SELECTED_FOOODIE, foodie);
      //  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.search_items);
        searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //just set the current value to search.
                viewModel.filterFoodName.
                        setValue("%" + newText + "%");
                return false;
            }
        });
        return true;
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.onActionViewCollapsed();
        } else {
            super.onBackPressed();
        }
       // super.onBackPressed();
    }
}
