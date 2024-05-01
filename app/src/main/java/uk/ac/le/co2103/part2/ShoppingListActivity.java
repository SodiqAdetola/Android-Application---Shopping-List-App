package uk.ac.le.co2103.part2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ShoppingListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        // Get the item name from the intent
        String itemName = getIntent().getStringExtra("ITEM_NAME");

        // Display the item name in a TextView or perform any other relevant operation

    }

    protected void onClick(){

    }
}