package com.example.sodevs.models;

import android.content.res.Resources;
import android.widget.ImageView;

import com.example.sodevs.R;

import java.util.ArrayList;
import java.util.List;

public class HotelModel {
    private String name;
    private String location;
    private String description;
    private int stayPrice;
    private int imageResId;
    private int roomsCount;
    private List<HotelRoom> rooms;
    private List<ImageView> images;

    public HotelModel(String name, String location, String description) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.roomsCount = 0;
        this.rooms = new ArrayList<>();
        this.images = new ArrayList<>();
    }

    public void addRoom(String type, int people, int price) {
        this.rooms.add(new HotelRoom(this.roomsCount, type, people, price));
        roomsCount++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<HotelRoom> getRooms() {
        return rooms;
    }

    public void setRooms(List<HotelRoom> rooms) {
        this.rooms = rooms;
    }

    public int getStayPrice() {
        return stayPrice;
    }

    public void setStayPrice(int stayPrice) {
        this.stayPrice = stayPrice;
    }

    public String getStayPriceText() { return "Lowest price for your stay: " + String.valueOf(stayPrice); }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
