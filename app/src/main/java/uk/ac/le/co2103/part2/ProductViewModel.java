package uk.ac.le.co2103.part2;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    private ProductRepository mRepository;
    private final LiveData<List<Product>> AllProducts;

    public ProductViewModel (Application application) {
        super(application);
        mRepository = new ProductRepository(application);
        AllProducts = mRepository.getAllProducts();
    }

    LiveData<List<Product>> getAllProducts() { return AllProducts; }

    public void insert(Product product) { mRepository.insert(product); }

}
