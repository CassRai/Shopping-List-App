package uk.ac.le.co2103.part2;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class ItemsRecycler extends ListAdapter<Items, ItemsViewHolder> {

    public ItemsRecycler(@NonNull DiffUtil.ItemCallback<Items> diffCallback) {
        super(diffCallback);
    }

    @Override
    public ItemsViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        return ItemsViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ItemsViewHolder holder, int position) {
        Items current = getItem(position);
        holder.bind(current.getName(), current.getQuantity(), current.getUnit());

    }

    static class ItemsDiff extends DiffUtil.ItemCallback<Items> {
        @Override
        public boolean areItemsTheSame(@NonNull Items oldItem, @NonNull Items newItem) {
            return oldItem.getName() == newItem.getName();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Items oldItem, @NonNull Items newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getQuantity().equals(newItem.getQuantity()) &&
                    oldItem.getUnit().equals(newItem.getUnit());


        }
    }
}
