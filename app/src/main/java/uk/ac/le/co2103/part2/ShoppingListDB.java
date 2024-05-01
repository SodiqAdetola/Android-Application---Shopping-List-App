package uk.ac.le.co2103.part2;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//import uk.ac.le.co2103.part2.Dao.ShoppingListDao;
//import uk.ac.le.co2103.part2.domain.Product;
//import uk.ac.le.co2103.part2.domain.ShoppingList;

@Database(entities = {ShoppingList.class, Product.class}, version = 1, exportSchema = false)
public abstract class ShoppingListDB extends RoomDatabase {

    public abstract ShoppingListDao shoppinglistDao();

    private static volatile ShoppingListDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    static ShoppingListDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ShoppingList.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ShoppingListDB.class, "shoppinglist_db").build();
                }
            }
        }
        return INSTANCE;
    }


}


