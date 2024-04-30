package uk.ac.le.co2103.part2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ShoppingListDao {

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        void insert(ShoppingList shoppinglist);

        @Query("DELETE FROM ShoppingList_table")
        void deleteAll();

        @Query("SELECT * From ShoppingList_table ORDER BY name ASC")
        LiveData<List<ShoppingList>> getAlphabetisedShoppingLists();




}
