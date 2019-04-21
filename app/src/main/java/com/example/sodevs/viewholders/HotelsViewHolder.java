package com.example.sodevs.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sodevs.R;
import com.example.sodevs.models.HotelModel;

public class HotelsViewHolder extends RecyclerView.ViewHolder {
    private TextView mHotelName;
    private ImageView mHotelImage;
    private TextView mHotelStayPrice;

    public HotelsViewHolder(@NonNull View container) {
        super(container);
        mHotelName = container.findViewById(R.id.hotel_name);
        mHotelImage = container.findViewById(R.id.hotel_image);
        mHotelStayPrice = container.findViewById(R.id.hotel_stay_price);
    }

    public void bind(HotelModel hotel) {
        mHotelName.setText(hotel.getHotelName());
        mHotelImage.setImageResource(hotel.getHotelImageResId());
        mHotelStayPrice.setText(hotel.getHotelStayPrice());
    }
}
