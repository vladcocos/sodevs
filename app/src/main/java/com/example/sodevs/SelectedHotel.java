package com.example.sodevs;

import android.content.Intent;
import android.media.Image;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sodevs.models.Booking;
import com.example.sodevs.models.HotelModel;
import com.example.sodevs.models.HotelRoom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class SelectedHotel extends AppCompatActivity {
    private CurrentHotel currentHotel;
    private CurrentSearch currentSearch;
    private RadioGroup roomsRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_hotel);

        currentHotel = CurrentHotel.getInstance();
        currentSearch = CurrentSearch.getInstance();

        TextView hotelNameView = findViewById(R.id.current_hotel_name);
        TextView hotelDescriptionView = findViewById(R.id.current_hotel_description);
        ImageView hotelImageView = findViewById(R.id.current_hotel_image);
        TextView userSearch = findViewById(R.id.selected_hotel_user_search);
        userSearch.setText("You searched booking for " + currentSearch.getPeople() + (currentSearch.getPeople() == 1 ? " person " : " people ") + "between " + currentSearch.getCheckInDate() + " and " + currentSearch.getCheckOutDate() + ".");
        final Button reserveBtn = findViewById(R.id.reserve_btn);
        roomsRadioGroup = findViewById(R.id.selected_hotel_rooms_layout);

        hotelNameView.setText(currentHotel.getCurrentHotel().getName());
        hotelDescriptionView.setText(currentHotel.getCurrentHotel().getDescription());
        hotelImageView.setImageResource(currentHotel.getCurrentHotel().getImageResId());

        getAvailableRooms();

        roomsRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
            RadioButton selectedRoom = findViewById(roomsRadioGroup.getCheckedRadioButtonId());

            int daysBetween = currentSearch.getDaysBetween();

            int roomPrice = Integer.parseInt(selectedRoom.getText().toString().split(" ")[2]);
            reserveBtn.setText("Reserve (" + String.valueOf(daysBetween * roomPrice) +  " lei)");
            }
        });

        reserveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton selectedRoom = findViewById(roomsRadioGroup.getCheckedRadioButtonId());

                boolean booked = false;
                for (HotelRoom hotelRoom : currentHotel.getCurrentHotel().getRooms()) {
                    if (hotelRoom.getType().equals(selectedRoom.getText().toString().split(" ")[0])) {
                        hotelRoom.getBookings().add(new Booking(currentSearch.getCheckInDate(),
                                currentSearch.getCheckOutDate()));
                        booked = true;
                    }

                    if (booked) {
                        int daysBetween = currentSearch.getDaysBetween();

                        Toast.makeText(getBaseContext(),
                                "Reservation successful: "
                                        + hotelRoom.getType()
                                        + (hotelRoom.getType().equals("Apartment") ? "" : " room")
                                        + " for " + daysBetween * hotelRoom.getPrice() + " lei",
                                Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(SelectedHotel.this, ReservationConfirmation.class);
                        intent.putExtra("selectedRoomType", hotelRoom.getType());
                        intent.putExtra("selectedRoomTotalPrice", daysBetween * hotelRoom.getPrice());
                        startActivity(intent);
                        break;
                    }
                }
            }
        });
    }

    private void getAvailableRooms() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        for (HotelRoom hotelRoom : currentHotel.getCurrentHotel().getRooms()) {
            boolean roomAvailable = true;

            if (currentSearch.getPeople() <= hotelRoom.getPeople()) {
                for (Booking booking : hotelRoom.getBookings()) {
                    try {
                        Date searchIn = sdf.parse(currentSearch.getCheckInDate());
                        Date searchOut = sdf.parse(currentSearch.getCheckOutDate());
                        Date bookingIn = sdf.parse(booking.getCheckInDate());
                        Date bookingOut = sdf.parse(booking.getCheckOutDate());

                        if ((searchIn.after(bookingIn) && searchIn.before(bookingOut)) ||
                                (searchOut.after(bookingIn) && searchOut.before(bookingOut))) {
                            roomAvailable = false;
                            break;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                if (roomAvailable) {
                    RadioButton newRadioButton = new RadioButton(getBaseContext());
                    newRadioButton.setId(View.generateViewId());
                    newRadioButton.setText(hotelRoom.getType() + " - " + String.valueOf(hotelRoom.getPrice()) + " lei");
                    newRadioButton.setTextSize(16);
                    roomsRadioGroup.addView(newRadioButton);
                }
            }
        }
    }
}
