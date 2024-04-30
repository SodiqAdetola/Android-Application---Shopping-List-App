package uk.ac.le.co2103.part2.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import uk.ac.le.co2103.part2.Dao.ProductDao;
import uk.ac.le.co2103.part2.domain.Product;

@Database(entities = {Product.class}, version = 1, exportSchema = false)
abstract class ProductRoomDatabase extends RoomDatabase {

    public abstract ProductDao productDao();

    private static volatile ProductRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ProductRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ProductRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ProductRoomDatabase.class, "word_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
