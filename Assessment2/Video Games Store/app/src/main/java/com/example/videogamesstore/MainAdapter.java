package com.example.videogamesstore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MainAdapter extends FirebaseRecyclerAdapter<Games, MainAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<Games> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Games model) {
        holder.name.setText(model.getName());
        holder.platform.setText(model.getPlatform());
        holder.price.setText(String.valueOf(model.getPrice()));


        Glide.with(holder.img.getContext())
                .load(model.getImgurl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(holder.img);

        holder.addToCartBtn.setOnClickListener(v -> {
            Toast.makeText(holder.name.getContext(), holder.name.getText().toString() + " added to cart", Toast.LENGTH_SHORT).show();
        });

        holder.incrementQty.setOnClickListener(v -> {
            int newQty = Integer.parseInt(holder.qty.getText().toString());
            if (model.getQty() > newQty) {
                newQty++;
                holder.qty.setText(String.valueOf(newQty));
            }
        });

        holder.decrementQty.setOnClickListener(v -> {
            int newQty = Integer.parseInt(holder.qty.getText().toString());
            if (newQty > 0) {
                newQty--;
                holder.qty.setText(String.valueOf(newQty));
            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_layout, parent, false);
        return new myViewHolder(view);
    }

    static class myViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name, platform, price, qty;

        ImageButton addToCartBtn, decrementQty, incrementQty;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imageUrl);
            name = itemView.findViewById(R.id.nameText);
            platform = itemView.findViewById(R.id.platformText);
            price = itemView.findViewById(R.id.priceText);
            qty = itemView.findViewById(R.id.qtyText);

            addToCartBtn = itemView.findViewById(R.id.addToCartBtn);
            incrementQty = itemView.findViewById(R.id.qtyIncrementBtn);
            decrementQty = itemView.findViewById(R.id.qtyDecrementBtn);
        }
    }
}
