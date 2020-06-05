package raum.muchbeer.pagingsearchfood.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import raum.muchbeer.pagingsearchfood.R;
import raum.muchbeer.pagingsearchfood.clicklistener.FoodClickListener;
import raum.muchbeer.pagingsearchfood.model.Food;

public class FoodAdapter  extends PagedListAdapter<Food, FoodAdapter.FoodViewHolder> {

    private static final String LOG_TAG = FoodAdapter.class.getSimpleName();
    private Activity activity;
   private FoodClickListener mfoodClickListener;

    public FoodAdapter( Activity activity, FoodClickListener foodClickListener) {
        super(Food.DIFF_CALLBACK);
        this.activity = activity;
        this.mfoodClickListener = foodClickListener;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.list_food,
                            parent,
                            false   );
        return new FoodViewHolder(view, mfoodClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {

        if (position <= -1) {  return; }

        Food foodObject = getItem(position);
        try {
            holder.txtFoodName.setText(foodObject.getFood());
            Log.d(LOG_TAG, "The value of food is: " + foodObject);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mfoodClickListener.onFoodClicked(foodObject, view);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();  }
    }


    public class FoodViewHolder extends RecyclerView.ViewHolder {

        private TextView txtFoodName;
       FoodClickListener mfoodClicker;

        public FoodViewHolder(@NonNull View itemView, FoodClickListener mfoodClickListener) {
            super(itemView);
        mfoodClicker = mfoodClickListener;
            txtFoodName = itemView.findViewById(R.id.txtFood);


        }



    }
}
