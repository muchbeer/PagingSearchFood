package raum.muchbeer.pagingsearchfood.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "food")
public class Food implements Parcelable {


    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "food_name")
    public String food;

    @ColumnInfo(name = "food_price")
    public String food_price;

    @ColumnInfo(name = "restaurant")
    public String restaurant;

    public Food(String food, String price, String restaurant) {
        this.food = food;
        this.food_price = price;
        this.restaurant = restaurant;
    }

    public Food() {
    }

    protected Food(Parcel in) {
        id = in.readInt();
        food = in.readString();
        food_price = in.readString();
        restaurant = in.readString();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getFood() {
        return food;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(String price) {
        this.food_price = price;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }



    public String getRestaurant() {
        return restaurant;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public static DiffUtil.ItemCallback<Food> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Food>() {

                // FoodObject details may have changed if reloaded
                // from the database, but ID is fixed.
                @Override
                public boolean areItemsTheSame(@NonNull Food oldItem, @NonNull Food newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull Food oldItem, @NonNull Food newItem) {
                    return oldItem.equals(newItem);
                }
            };


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Food)) {
            return false;
        }
        Food p = (Food)o;
        return food.equals(p.food);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(food);
        parcel.writeString(food_price);
        parcel.writeString(restaurant);
    }

    public String getFood_price() {
        return food_price;
    }
}
