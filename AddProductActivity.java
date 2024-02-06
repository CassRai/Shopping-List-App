package uk.ac.le.co2103.part2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddProductActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "uk.ac.le.co2103.part2.NAME";
    public static final String EXTRA_QUANTITY = "uk.ac.le.co2103.part2.QUANTITY";
    public static final String EXTRA_UNIT = "uk.ac.le.co2103.part2.UNIT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        Button button = findViewById(R.id.add_button);
        EditText EditNameView = findViewById(R.id.editTextName);
        EditText EditQuantityView = findViewById(R.id.editTextQuantity);
        Spinner UnitSpinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.labels_unit, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        UnitSpinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener spinnerItemSelectedListener = new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Please select an item", Toast.LENGTH_SHORT).show();
            }
        };

        UnitSpinner.setOnItemSelectedListener(spinnerItemSelectedListener);

        button.setOnClickListener(view -> {
            Intent reply_intent = new Intent(this, ShoppingListActivity.class);
            if (TextUtils.isEmpty(EditNameView.getText()) || TextUtils.isEmpty(EditQuantityView.getText())){
                setResult(RESULT_CANCELED, reply_intent);
            } else {
                String item_name = EditNameView.getText().toString();
                String quantity_text = EditQuantityView.getText().toString();
                String selectedItem = (String) UnitSpinner.getSelectedItem();
                reply_intent.putExtra(EXTRA_NAME,item_name);
                reply_intent.putExtra(EXTRA_QUANTITY,quantity_text);
                reply_intent.putExtra(EXTRA_UNIT,selectedItem);
                setResult(RESULT_OK,reply_intent);
            }
            finish();
        });
    }
}