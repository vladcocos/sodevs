package com.example.sodevs.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.sodevs.R;
import com.example.sodevs.models.HotelModel;
import com.example.sodevs.viewholders.HotelsViewHolder;

import java.util.List;

public class HotelListAdapter extends RecyclerView.Adapter {
    private List<HotelModel> hotelList;

    public HotelListAdapter(List<HotelModel> hotelList) {
        this.hotelList = hotelList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_view_holder, parent, false);
        return new HotelsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ((HotelsViewHolder) viewHolder).bind(hotelList.get(position));
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }
}
