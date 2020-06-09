package raum.muchbeer.pagingsearchfood.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
import raum.muchbeer.pagingsearchfood.R;
import raum.muchbeer.pagingsearchfood.model.Food;

public class FoodActivity extends AppCompatActivity {
//make
    TextView foodName, foodPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        foodName = findViewById(R.id.txtFood);
        foodPrice = findViewById(R.id.txtPrice);

        Intent intentFromFoodieMain = getIntent();
        MainActivity activity = new MainActivity();
        if (intentFromFoodieMain.hasExtra(activity.SELECTED_FOOODIE)) {

           Food foodie = getIntent().getParcelableExtra(activity.SELECTED_FOOODIE);

           foodName.setText(foodie.getFood());
           foodPrice.setText(foodie.getFood_price());

        } else {
            Toast.makeText(getApplicationContext(), "No food data found", Toast.LENGTH_SHORT).show();
        }


  /*      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide slide = new Slide(Gravity.BOTTOM);
            slide.addTarget(R.id.description);
            slide.setInterpolator(AnimationUtils.loadInterpolator(this, android.R.interpolator.linear_out_slow_in));
            slide.setDuration(R.integer.detail_desc_slide_duration);
            getWindow().setEnterTransition(slide);
        }*/

    }
}