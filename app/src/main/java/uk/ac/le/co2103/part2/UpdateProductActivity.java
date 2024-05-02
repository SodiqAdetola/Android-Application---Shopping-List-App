package uk.ac.le.co2103.part2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class UpdateProductActivity extends AppCompatActivity {
    private TextView editTextQuantity;
    private TextView textViewName;
    private Button incrementButton;
    private Button decrementButton;
    private Button saveButton;
    private String productName;
    private int shoppingListId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        productName = getIntent().getStringExtra("PRODUCT_NAME");
        shoppingListId = getIntent().getIntExtra("SHOPPING_LIST_ID", -1);


        textViewName = findViewById(R.id.textViewName);
        editTextQuantity = findViewById(R.id.editTextQuantity);
        incrementButton = findViewById(R.id.increment_button);
        decrementButton = findViewById(R.id.decrement_button);
        saveButton = findViewById(R.id.save_button);


        // Set the initial quantity value
        ProductViewModel productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productViewModel.getProductByNameAndListId(productName, shoppingListId).observe(this, product -> {
            if (product != null) {
                editTextQuantity.setText(String.valueOf(product.getQuantity()));
                textViewName.setText(productName + " (" + product.getUnit() + ")");

            }
        });

        incrementButton.setOnClickListener(v -> incrementQuantity());
        decrementButton.setOnClickListener(v -> decrementQuantity());
        saveButton.setOnClickListener(v -> saveProduct());
    }

    private void incrementQuantity() {
        int quantity = Integer.parseInt(editTextQuantity.getText().toString());
        quantity++;
        editTextQuantity.setText(String.valueOf(quantity));
    }

    private void decrementQuantity() {
        int quantity = Integer.parseInt(editTextQuantity.getText().toString());
        if (quantity > 1) {
            quantity--;
            editTextQuantity.setText(String.valueOf(quantity));
        }
    }

    private void saveProduct() {
        int quantity = Integer.parseInt(editTextQuantity.getText().toString());

        // Create a new Product object with updated quantity
        ProductViewModel productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productViewModel.getProductByNameAndListId(productName, shoppingListId).observe(this, product -> {
            if (product != null) {
                String unit = product.getUnit();

                Product updatedProduct = new Product(productName);
                updatedProduct.setQuantity(quantity);
                updatedProduct.setShoppingListId(shoppingListId);
                updatedProduct.setUnit(unit);

                // Update the product in the database
                productViewModel.updateProduct(updatedProduct);

                // Finish the activity and return to ShoppingListActivity
                finish();
            }
        });
    }
}