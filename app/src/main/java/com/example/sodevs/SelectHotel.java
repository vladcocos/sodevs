package com.example.sodevs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sodevs.adapters.HotelListAdapter;
import com.example.sodevs.models.HotelModel;

import java.util.ArrayList;
import java.util.List;

public class SelectHotel extends AppCompatActivity {
    RecyclerView mHotelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_hotel);

        mHotelList = findViewById(R.id.hotels_recycler_view);
        RecyclerView.LayoutManager userListLayoutManager = new LinearLayoutManager(getApplicationContext());
        mHotelList.setLayoutManager(userListLayoutManager);

        List<HotelModel> hotelList = new ArrayList<>();
        hotelList.add(new HotelModel("Hotel Carmen", R.drawable.enter_screen_bg, "200 lei"));
        hotelList.add(new HotelModel("Hotel Ibis", R.drawable.enter_screen_bg, "450 lei"));
        hotelList.add(new HotelModel("Hotel Carmen", R.drawable.enter_screen_bg, "200 lei"));
        hotelList.add(new HotelModel("Hotel Ibis", R.drawable.enter_screen_bg, "450 lei"));
        hotelList.add(new HotelModel("Hotel Carmen", R.drawable.enter_screen_bg, "200 lei"));

        HotelListAdapter listAdapter = new HotelListAdapter(hotelList);
        mHotelList.setAdapter(listAdapter);
    }
}
