package com.example.radiospinnerinteractions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ClubAdapter extends ArrayAdapter<ClubItem> {

    public ClubAdapter(Context context, ArrayList<ClubItem> clubList) {
        super(context, 0, clubList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.clubs_row, parent, false
            );
        }

        TextView clubTextView = convertView.findViewById(R.id.club_row);

        ClubItem currItem = getItem(position);

        if(currItem != null) {
            clubTextView.setText(currItem.getClubName());
        }

        return convertView;
    }
}
