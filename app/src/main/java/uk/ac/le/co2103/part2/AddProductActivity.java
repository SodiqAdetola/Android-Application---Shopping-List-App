package uk.ac.le.co2103.part2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddProductActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextQuantity;
    private Spinner spinner;
    private Button productButton;
    private int shoppingListId;
    private static final String TAG = AddProductActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        shoppingListId = getIntent().getIntExtra("LIST_ID", -1);
        Log.d(TAG, "Shopping list ID on product creating: " + shoppingListId);

        editTextName = findViewById(R.id.editTextName);
        editTextQuantity = findViewById(R.id.editTextQuantity);
        spinner = findViewById(R.id.spinner);
        productButton = findViewById(R.id.add_product);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.labels_unit, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        productButton.setOnClickListener(v -> addProduct());

    }

    private void addProduct() {
        // Get input values
        String name = editTextName.getText().toString().trim();
        String quantityStr = editTextQuantity.getText().toString().trim();
        String unit = spinner.getSelectedItem().toString();

        // Check field is empty
        if (name.isEmpty() || quantityStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int quantity = Integer.parseInt(quantityStr);

        ProductViewModel productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        LiveData<Product> productLiveData = productViewModel.getProductByNameAndListId(name, shoppingListId);

        Observer<Product> observer = new Observer<Product>() {
            @Override
            public void onChanged(Product existingProduct) {

                productLiveData.removeObserver(this);

                // Check if product already exists
                if (existingProduct != null) {

                    Toast.makeText(AddProductActivity.this, "Product already exists", Toast.LENGTH_SHORT).show();
                } else {
                    // Create product object
                    Product product = new Product(name);
                    product.setQuantity(quantity);
                    product.setUnit(unit);
                    product.setShoppingListId(shoppingListId);

                    // Insert product
                    productViewModel.insert(product);

                    // Finish activity
                    finish();
                }
            }
        };

        productLiveData.observe(this, observer);
    }
}