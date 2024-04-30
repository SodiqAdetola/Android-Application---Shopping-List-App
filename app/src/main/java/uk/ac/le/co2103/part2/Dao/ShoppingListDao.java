package uk.ac.le.co2103.part2.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import uk.ac.le.co2103.part2.domain.ShoppingList;

@Dao
public interface ShoppingListDao {

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        void insert(ShoppingList shoppinglist);

        @Query("DELETE FROM ShoppingList_table")
        void deleteAll();

        @Query("SELECT * From ShoppingList_table ORDER BY name ASC")
        List<ShoppingList> getAlphabetisedShoppingLists();




}
