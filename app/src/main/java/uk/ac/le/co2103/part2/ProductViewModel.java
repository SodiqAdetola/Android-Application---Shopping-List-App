package uk.ac.le.co2103.part2;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    private ProductRepository repo;
    private final LiveData<List<Product>> AllProducts;

    public ProductViewModel (Application application) {
        super(application);
        repo = new ProductRepository(application);
        AllProducts = repo.getAllProducts();
    }

    LiveData<List<Product>> getAllProducts() { return AllProducts; }

    public void insert(Product product) { repo.insert(product); }

    public LiveData<List<Product>> getProductsByShoppingListId(int shoppingListId) {
        return repo.getProductsByShoppingListId(shoppingListId);
    }

}
