package uk.ac.le.co2103.part2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Product.class}, version = 1, exportSchema = false)
public abstract class ProductDB extends RoomDatabase {

    public abstract ProductDao productDao();

    private static volatile ProductDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ProductDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ProductDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ProductDB.class, "word_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
