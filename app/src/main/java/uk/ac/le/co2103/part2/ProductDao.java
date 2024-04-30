package uk.ac.le.co2103.part2;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import uk.ac.le.co2103.part2.Product;

public interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Product product);
    @Query("DELETE FROM product_table")
    void deleteAll();
    @Query("SELECT * FROM product_table ORDER BY Name ASC")
    LiveData<List<Product>> getAlphabetisedProducts();
}
