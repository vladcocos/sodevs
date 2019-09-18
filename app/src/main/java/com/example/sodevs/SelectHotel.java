package com.example.sodevs;

import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sodevs.adapters.HotelListAdapter;
import com.example.sodevs.models.Booking;
import com.example.sodevs.models.HotelModel;
import com.example.sodevs.models.HotelRoom;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class SelectHotel extends AppCompatActivity implements ListItemClickListener {
    private RecyclerView mHotelList;
    private List<HotelModel> hotelList;
    private CurrentHotel currentHotel;
    private CurrentSearch currentSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_hotel);

        currentHotel = CurrentHotel.getInstance();
        currentSearch = CurrentSearch.getInstance();

        TextView userSearch = findViewById(R.id.select_hotel_user_search);
        hotelList = new ArrayList<>();
        getAvailableHotels();
        userSearch.setText("No hotels found");
        if (hotelList.size() == 0) {
            userSearch.setText("No hotels found");
        } else {
            userSearch.setText("You searched booking for " + currentSearch.getPeople() + (currentSearch.getPeople() == 1 ? " person " : " people ") + "between " + currentSearch.getCheckInDate() + " and " + currentSearch.getCheckOutDate() + ".");
        }

        mHotelList = findViewById(R.id.hotels_recycler_view);
        RecyclerView.LayoutManager userListLayoutManager = new LinearLayoutManager(getApplicationContext());
        mHotelList.setLayoutManager(userListLayoutManager);

        final TextView selectedLocation = findViewById(R.id.selected_location_text_view);
        selectedLocation.setText("Selected location: " + currentSearch.getLocation());
        HotelListAdapter listAdapter = new HotelListAdapter(hotelList, this);
        mHotelList.setAdapter(listAdapter);
    }

    private void getAvailableHotels() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        for (HotelModel hotel : Hotels.getInstance().getHotels()) {
            if (hotel.getLocation().equals(currentSearch.getLocation())) {
                int minPrice = Integer.MAX_VALUE;
                int roomsAvailable = 0;

                for (HotelRoom hotelRoom : hotel.getRooms()) {
                    if (currentSearch.getPeople() <= hotelRoom.getPeople()) {
                        for (Booking booking : hotelRoom.getBookings()) {
                            try {
                                Date searchIn = sdf.parse(currentSearch.getCheckInDate());
                                Date searchOut = sdf.parse(currentSearch.getCheckOutDate());
                                Date bookingIn = sdf.parse(booking.getCheckInDate());
                                Date bookingOut = sdf.parse(booking.getCheckOutDate());

                                if ((searchIn.after(bookingIn) && searchIn.before(bookingOut)) ||
                                        (searchOut.after(bookingIn) && searchOut.before(bookingOut))) {
                                    break;
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }

                        int daysBetween = currentSearch.getDaysBetween();
                        if (daysBetween * hotelRoom.getPrice() < minPrice) {
                            minPrice = daysBetween * hotelRoom.getPrice();
                        }

                        roomsAvailable++;
                    }
                }

                if (roomsAvailable > 0) {
                    hotel.setStayPrice(minPrice);
                    String hotelImageName = hotel.getName().toLowerCase().replaceAll(" ", "_")
                            + "_" + hotel.getLocation().toLowerCase()
                            + "_1";
                    hotel.setImageResId(getResources().getIdentifier(hotelImageName, "drawable", getPackageName()));
                    hotelList.add(hotel);
                }
            }
        }
    }

    public void onListItemClick(int position) {
        Intent intent = new Intent(this, SelectedHotel.class);
        currentHotel.setCurrentHotel(hotelList.get(position));
        startActivity(intent);
    }
}
