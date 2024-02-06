package uk.ac.le.co2103.part2;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

class ShoppingListsViewHolder extends RecyclerView.ViewHolder {
    private TextView shopLName;
    private ImageView shopLPic;


    private ShoppingListsViewHolder(View textview) {
        super(textview);
        shopLName = textview.findViewById(R.id.ShopLName);
        shopLPic = textview.findViewById(R.id.ShopLPic);
    }

    public void bind(String text, @Nullable Uri image) {
        shopLName.setText(text);
        shopLPic.setImageURI(image);
    }

    static ShoppingListsViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ShoppingListsViewHolder(view);
    }
}
