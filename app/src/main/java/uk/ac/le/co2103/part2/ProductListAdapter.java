package uk.ac.le.co2103.part2;


import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class ProductListAdapter  extends ListAdapter<Product, ProductViewHolder>{
    public ProductListAdapter(@NonNull DiffUtil.ItemCallback<Product> diffCallback)
    {
        super(diffCallback);
    }
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return ProductViewHolder.create(parent);
    }
    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product current = getItem(position);
        holder.bind(current.getProduct());
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
}
