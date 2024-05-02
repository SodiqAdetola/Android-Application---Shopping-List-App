package uk.ac.le.co2103.part2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import uk.ac.le.co2103.part2.Product;
@Dao
public interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Product product);
    @Query("DELETE FROM product_table")
    void deleteAll();
    @Query("SELECT * FROM product_table ORDER BY Name ASC")
    LiveData<List<Product>> getAlphabetisedProducts();

    @Query("SELECT * FROM product_table WHERE shoppingListId = :shoppingListId")
    LiveData<List<Product>> getProductsByShoppingListId(int shoppingListId);


}
