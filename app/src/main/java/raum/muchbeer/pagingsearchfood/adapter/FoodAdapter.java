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
import raum.muchbeer.pagingsearchfood.model.Food;

public class FoodAdapter  extends PagedListAdapter<Food, FoodAdapter.FoodViewHolder> {

    private static final String LOG_TAG = FoodAdapter.class.getSimpleName();
    private Activity activity;

    public FoodAdapter( Activity activity) {
        super(Food.DIFF_CALLBACK);
        this.activity = activity;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.list_food,
                            parent,
                            false   );
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {

        if (position <= -1) {  return; }

        Food foodObject = getItem(position);
        try {
            holder.txtFoodName.setText(foodObject.getFood());
            Log.d(LOG_TAG, "The value of food is: " + foodObject);
        } catch (Exception e) {
            e.printStackTrace();  }
    }


    public class FoodViewHolder extends RecyclerView.ViewHolder {

        private TextView txtFoodName;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            txtFoodName = itemView.findViewById(R.id.txtFood);
        }
    }
}
