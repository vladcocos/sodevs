package com.example.sodevs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ReservationConfirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_confirmation);

        TextView reservationConfirmation = findViewById(R.id.reservation_confirmation_text_view);
        TextView goHomeBtn = findViewById(R.id.go_home_text_view);
        CurrentHotel currentHotel = CurrentHotel.getInstance();
        CurrentSearch currentSearch = CurrentSearch.getInstance();

        Bundle extras = getIntent().getExtras();
        String roomType = "";
        int stayTotalPrice = 0;
        if (extras != null) {
            roomType = extras.getString("selectedRoomType");
            stayTotalPrice = extras.getInt("selectedRoomTotalPrice");
        }

        reservationConfirmation.setText("Congratulations! You reserved a "
            + roomType.toLowerCase()
            + (roomType.equals("Apartment") ? "" : " room ")
            + " for "
            + stayTotalPrice
            + " lei at "
            + currentHotel.getCurrentHotel().getName()
            + " in "
            + currentHotel.getCurrentHotel().getLocation()
            + " between "
            + currentSearch.getCheckInDate()
            + " and "
            + currentSearch.getCheckOutDate()
            + ". Hope you enjoy your stay.");

        goHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReservationConfirmation.this, SearchHotel.class);
                startActivity(intent);
            }
        });
    }
}
