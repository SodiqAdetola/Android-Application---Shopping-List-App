package uk.ac.le.co2103.part2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class CreateListActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.shoppingList-sql.REPLY";
    public static final String IMAGE_EXTRA_REPLY = "com.example.android.image-sql.REPLY";
    public static final int PICK_IMAGE_REQUEST = 1;
    private EditText editShoppingListView;
    private Button imageButton;
    private Uri image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);

        editShoppingListView = findViewById(R.id.edit_shoppingList);

        imageButton = findViewById(R.id.selectImage_button);
        imageButton.setOnClickListener(view -> openGallery());

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(editShoppingListView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);

            } else {
                String shoppingListName = editShoppingListView.getText().toString();
                String imageUri = image != null? image.toString() : null;
                replyIntent.putExtra(EXTRA_REPLY, shoppingListName);
                replyIntent.putExtra(IMAGE_EXTRA_REPLY, imageUri);
                setResult(RESULT_OK, replyIntent);

            }

            finish();
        });
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            image = data.getData();
        }
    }
}