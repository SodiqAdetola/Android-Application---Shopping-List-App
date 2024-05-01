package uk.ac.le.co2103.part2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ShoppingListViewHolder extends RecyclerView.ViewHolder {

    private final TextView shoppingListTextView;

    public interface OnShoppingListItemLongClickListener {
        void onItemLongClick(int position);
    }

    private ShoppingListViewHolder(View shoppingListView) {
        super(shoppingListView);
        shoppingListTextView = shoppingListView.findViewById(R.id.textView);

    }

    public void bind(String text) {
        shoppingListTextView.setText(text);
    }
    static ShoppingListViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ShoppingListViewHolder(view);
    }
    // Method to set the ShoppingListRepository instance


}
