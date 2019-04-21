package com.example.sodevs.models;

public class HotelModel {
    private String hotelName;
    private int hotelImageResId;
    private String hotelStayPrice;

    public HotelModel(String hotelName, int hotelImageResId, String hotelStayPrice) {
        this.hotelName = hotelName;
        this.hotelImageResId = hotelImageResId;
        this.hotelStayPrice = "Price for your stay: " + hotelStayPrice;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getHotelImageResId() {
        return hotelImageResId;
    }

    public void setHotelImageResId(int hotelImageResId) {
        this.hotelImageResId = hotelImageResId;
    }

    public String getHotelStayPrice() {
        return hotelStayPrice;
    }

    public void setHotelStayPrice(String hotelStayPrice) {
        this.hotelStayPrice = "Price for your stay: " + hotelStayPrice;
    }
}
