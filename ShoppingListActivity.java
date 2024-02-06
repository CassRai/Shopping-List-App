package uk.ac.le.co2103.part2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ShoppingListActivity extends AppCompatActivity {

    public Integer list_id;

    private ShoppingViewModel shoppingViewModel;

    private RecyclerView recyclerView;
    public static final int NEW_PRODUCT_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_main);

        recyclerView = findViewById(R.id.items_recycler_view);
        ItemsRecycler adapter = new ItemsRecycler(new ItemsRecycler.ItemsDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Integer shoppingListId = getIntent().getIntExtra("shopping_list_id",-1);
        shoppingViewModel = new ViewModelProvider(this).get(ShoppingViewModel.class);
        shoppingViewModel.getItemsbyID(shoppingListId).observe(this, items ->
                adapter.submitList(items));

        final FloatingActionButton button = findViewById(R.id.fabAddProduct);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(ShoppingListActivity.this, AddProductActivity.class);
            startActivityForResult(intent, NEW_PRODUCT_ACTIVITY_REQUEST_CODE);
        });

        Intent get = getIntent();
        list_id = get.getIntExtra("shopping_list_id", -1);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_PRODUCT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            String item_name = data.getStringExtra(AddProductActivity.EXTRA_NAME);
            String quantity_text = data.getStringExtra(AddProductActivity.EXTRA_QUANTITY);
            String item_unit =  data.getStringExtra(AddProductActivity.EXTRA_UNIT);
            Integer item_quantity = Integer.parseInt(quantity_text);

            if (item_name != null && !item_name.isEmpty() && list_id != null){
                Items items = new Items(item_name, item_quantity, item_unit,list_id);
                shoppingViewModel.insertItems(items);
                Log.d("flop", "onActivityResult: ");
            } else {
                Toast.makeText(getApplicationContext(), "Please create a product", Toast.LENGTH_SHORT).show();
            }
        }
    }
}