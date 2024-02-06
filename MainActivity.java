package uk.ac.le.co2103.part2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;
import java.net.HttpCookie;
import java.net.URI;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    private ShoppingViewModel shoppingViewModel;

    public static final int NEW_SHOPPINGLISTS_ACTIVITY_REQUEST_CODE = 1;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        ShoppingListsRecycler adapter = new ShoppingListsRecycler(new ShoppingListsRecycler.ShoppingListsDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        shoppingViewModel = new ViewModelProvider(this).get(ShoppingViewModel.class);
        shoppingViewModel.getShoppingLists().observe(this, shoppingLists -> {
            adapter.submitList(shoppingLists);
        });


        final FloatingActionButton button = findViewById(R.id.fab);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CreateListActivity.class);
            startActivityForResult(intent, NEW_SHOPPINGLISTS_ACTIVITY_REQUEST_CODE);
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_SHOPPINGLISTS_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String shoppinglistname = data.getStringExtra(CreateListActivity.EXTRA_REPLY);
            String shoppinglistpic_s = data.getStringExtra(CreateListActivity.EXTRA_IMAGE_URI);
            Uri shoppinglistpic = Uri.parse(shoppinglistpic_s);

            if (shoppinglistpic != null && !TextUtils.isEmpty(shoppinglistpic.toString())){
                ShoppingLists shoppingLists = new ShoppingLists(shoppinglistname,shoppinglistpic);
                shoppingViewModel.insertShoppingList(shoppingLists);
            }

            else{
                Uri imageuri = Uri.parse("android.resource://uk.ac.le.co2103.part2/a.png");
                ShoppingLists shoppingLists = new ShoppingLists(shoppinglistname, imageuri);
                shoppingViewModel.insertShoppingList(shoppingLists);
            }
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_SHORT).show();
        }
    }

    }
