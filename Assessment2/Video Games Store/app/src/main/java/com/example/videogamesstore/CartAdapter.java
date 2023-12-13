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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;
import java.util.Objects;

public class CartAdapter extends FirebaseRecyclerAdapter <Games, CartAdapter.myViewHolder>{
    private double total;
    private double itemTotal;
    private final CartTotalListener cartTotalListener;

    public CartAdapter(@NonNull FirebaseRecyclerOptions<Games> options, CartTotalListener cartTotalListener) {
        super(options);
        total = 0;
        this.cartTotalListener = cartTotalListener;
    }

    @Override
    protected void onBindViewHolder(@NonNull CartAdapter.myViewHolder holder, int position, @NonNull Games model) {
        holder.name.setText(model.getName());
        holder.platform.setText(model.getPlatform());
        holder.price.setText(String.valueOf(model.getPrice()));
        itemTotal = Double.parseDouble(holder.price.getText().toString());

        total += Double.parseDouble(holder.price.getText().toString());

        DatabaseReference cartItems = FirebaseDatabase.getInstance().getReference().child("AddToCart")
                .child(Objects.requireNonNull(getRef(position).getKey()));


        Glide.with(holder.img.getContext())
                .load(model.getImgurl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(holder.img);

        holder.removeItemBtn.setOnClickListener(v -> removeFromCart(cartItems, holder));

        holder.incrementQty.setOnClickListener(v -> {
            int newQty = Integer.parseInt(holder.qty.getText().toString());
            double itemTotalPrice = model.getPrice();

            if (model.getQty() > newQty) {
                newQty++;
                holder.qty.setText(String.valueOf(newQty));
                itemTotalPrice = itemTotalPrice * newQty;
                total += model.getPrice();
                holder.price.setText(String.format(Locale.UK,"%.2f", itemTotalPrice));

                updateTotal(total);
            }
        });

        holder.decrementQty.setOnClickListener(v -> {
            int newQty = Integer.parseInt(holder.qty.getText().toString());
            itemTotal = Double.parseDouble(holder.price.getText().toString());

            if (newQty > 1) {
                newQty--;
                holder.qty.setText(String.valueOf(newQty));

                itemTotal -= model.getPrice();
                total -= model.getPrice();

                holder.price.setText(String.format(Locale.UK,"%.2f", itemTotal));
                updateTotal(total);
            } else
                removeFromCart(cartItems, holder);
        });
    }

    @NonNull
    @Override
    public CartAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_recycler_layout, parent, false);
        return new CartAdapter.myViewHolder(view);
    }

    static class myViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name, platform, price, qty;

        ImageButton removeItemBtn, decrementQty, incrementQty;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imageUrl);
            name = itemView.findViewById(R.id.nameText);
            platform = itemView.findViewById(R.id.platformText);
            price = itemView.findViewById(R.id.priceText);
            qty = itemView.findViewById(R.id.qtyText);

            removeItemBtn = itemView.findViewById(R.id.removeItemBtn);
            incrementQty = itemView.findViewById(R.id.qtyIncrementBtn);
            decrementQty = itemView.findViewById(R.id.qtyDecrementBtn);
        }
    }

    private void removeFromCart(DatabaseReference cartItems, @NonNull myViewHolder holder) {
        cartItems.removeValue();
        Toast.makeText(holder.name.getContext(), holder.name.getText().toString() + " was removed from cart ", Toast.LENGTH_SHORT).show();
        total -= Double.parseDouble(holder.price.getText().toString());
        updateTotal(total);
    }

    private void updateTotal(double total) {
        if (cartTotalListener != null) {
            cartTotalListener.onCartTotalUpdated(total);
        }
    }

    public void updateTotalInAdapter() {
        if (cartTotalListener != null) {
            cartTotalListener.onCartTotalUpdated(total);
        }
    }

    public double getTotal() {
        return total;
    }
}


