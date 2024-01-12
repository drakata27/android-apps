package com.example.week7shoppingcart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    private final List<Items> myList;
    private final ArrayList<Items> myBasket = new ArrayList<>();

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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_layout, parent, false);
        currentView = parent.getContext();
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Items itemsViewModel = myList.get(position);
        holder.imageView.setImageResource(itemsViewModel.getImage());
        holder.textView.setText(itemsViewModel.getText());
        holder.textPrice.setText(""+itemsViewModel.getPrice());


        // Set up the Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(currentView, R.array.quantity, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinnerQuantity.setAdapter(adapter);

        holder.spinnerQuantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int selectedItemPosition, long id) {
                // Do something with the selected item

                String itemName = holder.textView.getText().toString();
                String itemPrice = holder.textPrice.getText().toString();
                int quantity = holder.spinnerQuantity.getSelectedItemPosition()+1;
                int price = Integer.parseInt(itemPrice);

                Items item = new Items(itemName, price, quantity);

                for (int i=0; i<myBasket.size(); i++){

                    if ((myBasket.get(i).getText().equals(itemName))) //if the item already in the basket, delete that item
                        myBasket.remove(myBasket.get(i));
                }

                if (quantity!=10)//this is the last choice in spinner, and it is none. So item shouldn't be added when its none.
                    myBasket.add(item);

            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }

        });



        //String output = "\n item name: " +itemName + " image code: " +itemImageCode + " item price: " + itemPrice + " quantity : " +selectedItemPosition;
        //Log.e("TEST OUTCOME",output);
    }

    @Override
    public int getItemCount() {
        return myList.size();
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
