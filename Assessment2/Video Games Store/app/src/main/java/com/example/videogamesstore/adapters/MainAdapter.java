package com.example.videogamesstore.adapters;

import android.util.Log;
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
import com.example.videogamesstore.models.Games;
import com.example.videogamesstore.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainAdapter extends FirebaseRecyclerAdapter<Games, MainAdapter.myViewHolder> {
    public MainAdapter(@NonNull FirebaseRecyclerOptions<Games> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Games model) {
        holder.name.setText(model.getName());
        holder.platform.setText(model.getPlatform());
        holder.price.setText(String.valueOf(model.getPrice()));
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("AddToCart");

        Glide.with(holder.img.getContext())
                .load(model.getImgurl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(holder.img);

        holder.addToCartBtn.setOnClickListener(v -> {

            Query query = reference.orderByChild("name").equalTo(model.getName());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        // Item already in the cart
                        Toast.makeText(holder.name.getContext(), model.getName() + " is already in the cart", Toast.LENGTH_SHORT).show();
                    } else {
                        // Item not in the cart, add it
                        String cartId = reference.push().getKey();

                        HashMap<String, Object> cartItems = new HashMap<>();
                        cartItems.put("name", model.getName());
                        cartItems.put("platform", model.getPlatform());
                        cartItems.put("price", model.getPrice());
                        cartItems.put("imgurl", String.valueOf(model.getImgurl()));
                        cartItems.put("qty", model.getQty());

                        cartItems.put("currQty", model.getCurrQty()); //TODO

                        reference.child(cartId).setValue(cartItems);

                        Toast.makeText(holder.name.getContext(), model.getName() + " added to cart", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("Firebase", "Error checking cart: " + error.getMessage());
                }
            });

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

        ImageButton addToCartBtn;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imageUrl);
            name = itemView.findViewById(R.id.nameText);
            platform = itemView.findViewById(R.id.platformText);
            price = itemView.findViewById(R.id.priceText);
            qty = itemView.findViewById(R.id.qtyText);

            addToCartBtn = itemView.findViewById(R.id.addToCartBtn);
        }
    }
}
