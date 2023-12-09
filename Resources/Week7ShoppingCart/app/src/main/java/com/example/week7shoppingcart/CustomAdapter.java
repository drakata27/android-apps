package com.example.week7shoppingcart;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    private List<Items> myList;
    private ArrayList<Items> myBasket = new ArrayList<Items>();

    private int pos;

    private onItemClickListener mListener;
    private Context currentView;

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    public ArrayList<Items> getMyBasket() {
        return myBasket;
    }

    public void setOnItemClickListener(onItemClickListener mListener) {
        this.mListener = mListener;
    }

    public CustomAdapter(List<Items> list) {
        myList = list;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView textPrice;
        Spinner spinnerQuantity;

        public ViewHolder(View itemView, final onItemClickListener listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            textView = itemView.findViewById(R.id.textView);
            textPrice = itemView.findViewById(R.id.textPrice);
            spinnerQuantity = itemView.findViewById(R.id.spinnerQuantity);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
