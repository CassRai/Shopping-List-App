package uk.ac.le.co2103.part2;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class ShoppingListsRecycler extends ListAdapter<ShoppingLists, ShoppingListsViewHolder> {

    private AdapterView.OnItemClickListener onItemClickListener;
    public ShoppingListsRecycler(@NonNull DiffUtil.ItemCallback<ShoppingLists> diffCallback) {
        super(diffCallback);
    }

    @Override
    public ShoppingListsViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        return ShoppingListsViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ShoppingListsViewHolder holder, int position) {
        ShoppingLists current = getItem(position);
        Context context = holder.itemView.getContext();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShoppingListActivity.class);
                Integer f  = current.getListId();
                intent.putExtra("shopping_list_id", f);
                context.startActivity(intent);
            }
        });
        holder.bind(current.getName(), current.getImage());
    }

    static class ShoppingListsDiff extends DiffUtil.ItemCallback<ShoppingLists> {
        @Override
        public boolean areItemsTheSame(@NonNull ShoppingLists oldItem, @NonNull ShoppingLists newItem) {
            return oldItem.getListId() == newItem.getListId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull ShoppingLists oldItem, @NonNull ShoppingLists newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }
}