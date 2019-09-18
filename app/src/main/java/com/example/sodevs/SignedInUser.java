package com.example.sodevs;

public class SignedInUser {
    private String firstName;
    private String lastName;
    private String email;
    private static SignedInUser singleInstance;

    private SignedInUser() { }

    public static synchronized SignedInUser getInstance() {
        if (singleInstance == null) {
            singleInstance = new SignedInUser();
        }
        return singleInstance;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
