package uk.ac.le.co2103.part2;


import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductListAdapter  extends ListAdapter<Product, ProductViewHolder> {

    private OnProductClickListener onProductClickListener;
    private Context context;

    public ProductListAdapter(@NonNull DiffUtil.ItemCallback<Product> diffCallback) {
        super(diffCallback);
    }
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ProductViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product current = getItem(position);
        holder.bind(current.getQuantity() + current.getUnit() + " of " + current.getName());

        holder.itemView.setOnClickListener(view -> {
            showOptionsDialog(current);
        });

    }

    public void setOnProductClickListener(OnProductClickListener listener) {
        this.onProductClickListener = listener;
    }

    static class ProductDiff extends DiffUtil.ItemCallback<Product> {
        @Override
        public boolean areItemsTheSame(@NonNull Product oldProduct, @NonNull Product newProduct) {
            return oldProduct == newProduct;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldProduct, @NonNull Product newProduct) {
            return oldProduct.getName().equals(newProduct.getName());
        }


    }


    public interface OnProductClickListener {
        void onProductClick(Product product);
    }

    private void showOptionsDialog(Product product) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Options")
                .setItems(new CharSequence[]{"Edit", "Delete"}, (dialogInterface, i) -> {
                    if (i == 0) {
                        // Handle edit option
                    } else if (i == 1) {
                        deleteProduct(product);
                    }
                });
                builder.create().show();
    }

    private void deleteProduct(Product product) {
        ProductViewModel productViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(ProductViewModel.class);
        productViewModel.deleteProduct(product);
    }
}