package com.example.sodevs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class CurrentSearch {
    private String location;
    private String checkInDate;
    private String checkOutDate;
    private int people;
    private static CurrentSearch singleInstance;

    private CurrentSearch() { }

    public static synchronized CurrentSearch getInstance() {
        if (singleInstance == null) {
            singleInstance = new CurrentSearch();
        }
        return singleInstance;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public int getDaysBetween() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        long diff = 0;
        try {
            diff = sdf.parse(checkOutDate).getTime() - sdf.parse(checkInDate).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int daysBetween = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        if (daysBetween <= 0) daysBetween = 1;

        return daysBetween;
    }
}
