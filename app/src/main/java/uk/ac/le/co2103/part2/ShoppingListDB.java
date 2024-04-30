package uk.ac.le.co2103.part2;

import android.content.ClipData;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ShoppingListDB.class, "shoppinglist_db").addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                ShoppingListDao dao = INSTANCE.shoppinglistDao();
                dao.deleteAll();
                ShoppingList shoppingList = new ShoppingList("ClothingList");
                dao.insert(shoppingList);
                shoppingList = new ShoppingList("FoodList");
                dao.insert(shoppingList);
            });
        }
    };


}


