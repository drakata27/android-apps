package com.example.recyclerviewproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataModelRecyclerViewAdapter extends RecyclerView.Adapter<DataModelRecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<DataModel> dataModels;
    public DataModelRecyclerViewAdapter(Context context, ArrayList<DataModel> dataModels) {
        this.context = context;
        this.dataModels = dataModels;
    }

    @NonNull
    @Override
    public DataModelRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the layout (Give it a look)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);

        return new DataModelRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataModelRecyclerViewAdapter.MyViewHolder holder, int position) {
        // assign values to the views created in the layout.xml file for the row
        // based on the position of the recycler view
        holder.tvHeading.setText(dataModels.get(position).getHeading());
        holder.tvText1.setText(dataModels.get(position).getText1());
        holder.tvText2.setText(dataModels.get(position).getText2());
        holder.imageView.setImageResource(dataModels.get(position).getImage());


    }

    @Override
    public int getItemCount() {
        // number of items the you want to display
        return dataModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // grabbing views from the layout.xml file for the row
        // similar to onCreate method

        ImageView imageView;
        TextView tvHeading, tvText1, tvText2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            tvHeading = itemView.findViewById(R.id.heading);
            tvText1 = itemView.findViewById(R.id.textView1);
            tvText2 = itemView.findViewById(R.id.textView2);

        }
    }
}
