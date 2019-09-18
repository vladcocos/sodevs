package com.example.sodevs;

import com.example.sodevs.models.HotelModel;

public class CurrentHotel {
    private HotelModel hotel;
    private static CurrentHotel singleInstance;

    private CurrentHotel() { }

    public static synchronized CurrentHotel getInstance() {
        if (singleInstance == null) {
            singleInstance = new CurrentHotel();
        }
        return singleInstance;
    }

    public HotelModel getCurrentHotel() {
        return hotel;
    }

    public void setCurrentHotel(HotelModel hotel) {
        this.hotel = hotel;
    }
}
