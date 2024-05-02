package uk.ac.le.co2103.part2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ProductViewHolder  extends RecyclerView.ViewHolder{
    private final TextView productTextView;
    private ProductViewHolder(View productView) {
        super(productView);
        productTextView = productView.findViewById(R.id.productTextView);
    }
    public void bind(String text) {
        productTextView.setText(text);
    }
    static ProductViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_product, parent, false);
        return new ProductViewHolder(view);
    }
}
