package uk.ac.le.co2103.part2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class ItemsViewHolder extends RecyclerView.ViewHolder {
    private TextView item_name;
    private TextView item_quantity;
    private TextView item_unit;

    private ItemsViewHolder(View textView){
        super(textView);
        item_name = textView.findViewById(R.id.textview_name);
        item_quantity = textView.findViewById(R.id.textview_quantity);
        item_unit = textView.findViewById(R.id.textview_unit);

    }

    public void bind(String name, Integer quantity, String unit){
        item_name.setText(name);
        item_quantity.setText(String.valueOf(quantity));
        item_unit.setText(unit);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String description = "Name: " + name + "\nQuantity: " + quantity + "\nUnit: " + unit;
                Toast.makeText(view.getContext(), description, Toast.LENGTH_SHORT).show();
            }
        });
    }

    static ItemsViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shoppinglist_items, parent, false);
        return new ItemsViewHolder(view);
    }

}
