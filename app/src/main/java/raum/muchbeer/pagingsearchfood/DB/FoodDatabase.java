package raum.muchbeer.pagingsearchfood.DB;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import raum.muchbeer.pagingsearchfood.model.Food;

@Database(entities = {Food.class}, version = 2, exportSchema = false)
public abstract class FoodDatabase extends RoomDatabase {

    private static final String LOG_TAG = "FoodDatabase";
    private static volatile FoodDatabase INSTANCE;

    public abstract FoodDao foodDao();

    private static Context activity;

    private final Executor executor = Executors.newFixedThreadPool(2);

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Food "
                    + " ADD COLUMN food_price TEXT"
            );
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Food "
                    + " ADD COLUMN restaurant TEXT"
            );
        }
    };

    public static FoodDatabase getINSTANCE(Context context) {
Log.d(LOG_TAG, "eNTER THE FoodDatabase: ");
        activity = context.getApplicationContext();
        if (INSTANCE == null) {

            synchronized (FoodDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FoodDatabase.class, "Food.db")
                            .addCallback(callback)
                            .fallbackToDestructiveMigration()
                            //.addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InitialFoodData(INSTANCE, activity).execute();
           // addToCallback(activity);
        }
    };



}
