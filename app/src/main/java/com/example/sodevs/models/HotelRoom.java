package com.example.sodevs.models;

import java.util.ArrayList;
import java.util.List;

public class HotelRoom {
    private int id;
    private String type;
    private int people;
    private int price;
    private List<Booking> bookings;

    HotelRoom(int id, String type, int people, int price) {
        this.id = id;
        this.type = type;
        this.people = people;
        this.price = price;
        this.bookings = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
}
