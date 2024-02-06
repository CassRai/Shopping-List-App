package uk.ac.le.co2103.part2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateListActivity extends AppCompatActivity {

    public static final int PICK_IMAGE_REQUEST_CODE = 123;

    public static final String EXTRA_REPLY = "uk.ac.le.co2103.part2.REPLY";
    public static final String EXTRA_IMAGE_URI = "uk.ac.le.co2103.part2.IMAGE_URI";
    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_shopping_lists);

        Button gallery = findViewById(R.id.gallery);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);

            }
        });

        Button button = findViewById(R.id.button);
        EditText EditWordView = findViewById(R.id.editTextTextPassword);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent(this, MainActivity.class);
            if (TextUtils.isEmpty(EditWordView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String shoppingList = EditWordView.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY, shoppingList);
            }
            if (lastGalleryPickIntent != null && lastGalleryPickIntent.getData() != null) {
                Uri imageUri = lastGalleryPickIntent.getData();
                replyIntent.putExtra(EXTRA_IMAGE_URI, imageUri.toString());
            }
            setResult(RESULT_OK, replyIntent);
            finish();
        });


    }

    private Intent lastGalleryPickIntent;

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 3) {
            lastGalleryPickIntent = data;

        }

    }
}