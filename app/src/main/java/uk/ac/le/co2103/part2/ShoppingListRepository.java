package uk.ac.le.co2103.part2;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ShoppingListRepository {
    private ShoppingListDao shoppingListDao;
    private LiveData<List<ShoppingList>> allShoppingLists;
    ShoppingListRepository(Application application) {
        ShoppingListDB db = ShoppingListDB.getDatabase(application);
        shoppingListDao = db.shoppinglistDao();
        allShoppingLists = shoppingListDao.getAlphabetisedShoppingLists();
    }
    LiveData<List<ShoppingList>> getAllShoppingLists() {
        return allShoppingLists;
    }
    void insert(ShoppingList shoppingList) {
        ShoppingListDB.databaseWriteExecutor.execute(() -> {
            shoppingListDao.insert(shoppingList);
        });
    }


    void delete(ShoppingList shoppingList) {
        ShoppingListDB.databaseWriteExecutor.execute(() -> {
            shoppingListDao.delete(shoppingList);
            shoppingListDao.deleteProductsByShoppingListId(shoppingList.getListId());
        });
    }


}
