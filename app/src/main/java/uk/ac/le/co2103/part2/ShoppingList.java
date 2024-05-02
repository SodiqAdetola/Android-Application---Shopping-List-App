package uk.ac.le.co2103.part2;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "ShoppingList_table")
public class ShoppingList {

    @PrimaryKey(autoGenerate = true)
    private int listId;
    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    private String image;


    public ShoppingList(@NonNull String name) {
        this.name = name;
    }

    public String getShoppingList() {
        return name;
    }
    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
