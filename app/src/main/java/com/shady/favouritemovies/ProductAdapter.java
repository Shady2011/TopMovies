package com.shady.favouritemovies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shady.favouritemovies.databinding.ProductLayoutBinding;
import com.shady.favouritemovies.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Product> productList = new ArrayList<Product>();
    private OnProductListsner listsner;

    public ProductAdapter(OnProductListsner listsner) {
        this.listsner = listsner;
    }

    void setProductList(List<Product> list) {
        productList.clear();
        productList.addAll(list);
        notifyDataSetChanged();
    }

    public List<Product> getProductList(){
        return productList;
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Product product = productList.get(position);
        ((MyViewHolder) holder).bind(product);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listsner.onProductClick(productList.get(position));
            }
        });
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ProductLayoutBinding layoutBinding = ProductLayoutBinding.inflate(layoutInflater, parent, false);
        return new MyViewHolder(layoutBinding);
    }

    public interface OnProductListsner{
        void onProductClick(Product product);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ProductLayoutBinding binding;

        public MyViewHolder(ProductLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Product obj) {
            binding.setProductModel(obj);
            binding.executePendingBindings();
        }
    }
}


