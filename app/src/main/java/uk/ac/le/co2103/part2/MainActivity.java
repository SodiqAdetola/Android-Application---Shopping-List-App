package uk.ac.le.co2103.part2;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final int CREATE_LIST_ACTIVITY_REQUEST_CODE = 1;
    private ShoppingListViewModel shoppingListViewModel;
    public static final int SHOPPING_LIST_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate()");

        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ShoppingListAdapter adapter = new ShoppingListAdapter(new ShoppingListAdapter.ShoppingListDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        shoppingListViewModel = new ViewModelProvider(this).get(ShoppingListViewModel.class);
        shoppingListViewModel.getAllShoppingLists().observe(this, shoppingLists -> {
            adapter.submitList(shoppingLists);
        });

        shoppingListViewModel = new ViewModelProvider(this).get(ShoppingListViewModel.class);

        shoppingListViewModel.getAllShoppingLists().observe(this, shoppingLists -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(shoppingLists);
        });

        Log.d(TAG, "Setting up floating action button");
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, CreateListActivity.class);
            startActivityForResult(intent, CREATE_LIST_ACTIVITY_REQUEST_CODE);
        });



        adapter.setOnItemClickListener(new ShoppingListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ShoppingList shoppingList) {
                // Launch ShoppingListActivity when a shopping list item is clicked
                Intent intent = new Intent(MainActivity.this, ShoppingListActivity.class);
                startActivityForResult(intent, SHOPPING_LIST_ACTIVITY_REQUEST_CODE);
            }
        });




    }

    public void onActivityResult(int requestCode, int resultCode, Intent
            data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_LIST_ACTIVITY_REQUEST_CODE && resultCode ==
                RESULT_OK) {
            String shoppingListName = data.getStringExtra(CreateListActivity.EXTRA_REPLY);
            String imageUri = data.getStringExtra(CreateListActivity.IMAGE_EXTRA_REPLY);

            assert shoppingListName != null;

            ShoppingList shoppingList = new ShoppingList(shoppingListName);
            shoppingList.setImage(imageUri);

            shoppingListViewModel.insert(shoppingList);
        }

    }


}