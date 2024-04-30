package uk.ac.le.co2103.part2;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class ShoppingListAdapter extends ListAdapter<ShoppingList, ShoppingListViewHolder> {
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
