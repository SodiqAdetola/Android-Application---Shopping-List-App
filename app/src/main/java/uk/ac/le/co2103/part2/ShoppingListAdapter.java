package uk.ac.le.co2103.part2;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class ShoppingListAdapter extends ListAdapter<ShoppingList, ShoppingListViewHolder> {

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ShoppingList shoppingList);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public ShoppingListAdapter(@NonNull DiffUtil.ItemCallback<ShoppingList> diffCallback)
    {
        super(diffCallback);
    }
    @Override
    public ShoppingListViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return ShoppingListViewHolder.create(parent);
    }
    @Override
    public void onBindViewHolder(ShoppingListViewHolder holder, int position) {
        ShoppingList current = getItem(position);
        holder.bind(current.getShoppingList());
        // Set click listener on the itemView
        // Set OnClickListener for the item view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Pass the clicked item to the listener
                if (listener != null) {
                    listener.onItemClick(current);
                }
            }
        });

    }
    static class ShoppingListDiff extends DiffUtil.ItemCallback<ShoppingList> {
        @Override
        public boolean areItemsTheSame(@NonNull ShoppingList oldShoppingList, @NonNull ShoppingList newShoppingList) {
            return oldShoppingList == newShoppingList;
        }
        @Override
        public boolean areContentsTheSame(@NonNull ShoppingList oldShoppingList, @NonNull ShoppingList newShoppingList) {
            return oldShoppingList.getShoppingList().equals(newShoppingList.getShoppingList());
        }
    }

}