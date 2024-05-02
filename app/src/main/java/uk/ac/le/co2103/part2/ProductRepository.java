package uk.ac.le.co2103.part2;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductRepository {
    private ProductDao productDao;
    private LiveData<List<Product>> allProducts;
    ProductRepository(Application application) {
        ProductDB db = ProductDB.getDatabase(application);
        productDao = db.productDao();
        allProducts = productDao.getAlphabetisedProducts();
    }
    LiveData<List<Product>> getAllProducts() {
        return allProducts;
    }
    void insert(Product product) {
        ProductDB.databaseWriteExecutor.execute(() -> {
            productDao.insert(product);
        });
    }
    public LiveData<List<Product>> getProductsByShoppingListId(int shoppingListId) {
        return productDao.getProductsByShoppingListId(shoppingListId);
    }

    LiveData<Product> getProductByNameAndListId(String name, int shoppingListId) {
        return productDao.getProductByNameAndListId(name, shoppingListId);
    }

    public void deleteProduct(Product product) {
        ProductDB.databaseWriteExecutor.execute(() -> {
            productDao.delete(product);
        });
    }

}
