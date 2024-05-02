package uk.ac.le.co2103.part2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface ShoppingListDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insert(ShoppingList shoppinglist);

        @Query("DELETE FROM ShoppingList_table")
        void deleteAll();

        @Query("SELECT * From ShoppingList_table ORDER BY name ASC")
        LiveData<List<ShoppingList>> getAlphabetisedShoppingLists();

        @Delete
        void delete(ShoppingList shoppingList);

        @Query("DELETE FROM ShoppingList_table WHERE ListId = :shoppingListId")
        void deleteProductsByShoppingListId(int shoppingListId);



}
