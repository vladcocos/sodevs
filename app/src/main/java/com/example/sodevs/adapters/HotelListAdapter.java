package com.example.sodevs.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sodevs.ListItemClickListener;
import com.example.sodevs.R;
import com.example.sodevs.models.HotelModel;

import java.util.List;

public class HotelListAdapter extends RecyclerView.Adapter {
    private List<HotelModel> hotelList;
    final private ListItemClickListener mOnClickListener;

    public HotelListAdapter(List<HotelModel> hotelList, ListItemClickListener listener) {
        this.hotelList = hotelList;
        this.mOnClickListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_view_holder, parent, false);
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

    public class HotelsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mHotelName;
        private ImageView mHotelImage;
        private TextView mHotelStayPrice;

        public HotelsViewHolder(@NonNull View container) {
            super(container);
            mHotelName = container.findViewById(R.id.hotel_name);
            mHotelImage = container.findViewById(R.id.hotel_image);
            mHotelStayPrice = container.findViewById(R.id.hotel_stay_price);
            container.setOnClickListener(this);
        }

        public void bind(HotelModel hotel) {
            mHotelName.setText(hotel.getName());
            mHotelImage.setImageResource(hotel.getImageResId());
            mHotelStayPrice.setText(hotel.getStayPriceText());
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
