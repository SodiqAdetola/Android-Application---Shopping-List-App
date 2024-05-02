package uk.ac.le.co2103.part2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ShoppingListActivity extends AppCompatActivity {
    private static final String TAG = ShoppingListActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private ProductListAdapter adapter;
    private int shoppingListId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        // Get the shopping list ID from the intent
        shoppingListId = getIntent().getIntExtra("LIST_ID", -1);
        Log.d(TAG, "Shopping list ID: " + shoppingListId);

        recyclerView = findViewById(R.id.recyclerviewProduct);
        adapter = new ProductListAdapter(new ProductListAdapter.ProductDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        ProductViewModel productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        productViewModel.getProductsByShoppingListId(shoppingListId).observe(this, productList -> {
            adapter.submitList(productList);
        });


        FloatingActionButton fabAddProduct = findViewById(R.id.fabAddProduct);
        fabAddProduct.setOnClickListener(v -> {

            Intent intent = new Intent(ShoppingListActivity.this, AddProductActivity.class);
            intent.putExtra("LIST_ID", shoppingListId);
            startActivity(intent);
        });
    }
}