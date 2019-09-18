package com.example.sodevs;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class SearchHotel extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private CurrentSearch currentSearch;
    private DatabaseReference mDatabase;
    private List<String> locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hotel);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        final TextView checkInDate = findViewById(R.id.check_in_date);
        final TextView checkOutDate = findViewById(R.id.check_out_date);
        final TextView peopleMinus = findViewById(R.id.people_minus);
        final TextView peopleCurrent = findViewById(R.id.people_current);
        final TextView peoplePlus = findViewById(R.id.people_plus);
        final Button searchButton = findViewById(R.id.search_btn);
        final Calendar calendar = Calendar.getInstance();
        final String dateFormat = "dd/MM/yyyy";
        final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
        final TextView signOut = findViewById(R.id.sign_out);
        currentSearch = CurrentSearch.getInstance();
        locations = new ArrayList<>();

        Spinner mLocationSpinner;
        ArrayAdapter<String> mLocationSpinnerAdapter;
        List<String> mLocationSpinnerDataSource;

        peopleCurrent.setText("0");
        checkInDate.setText(sdf.format(calendar.getTime()));
        checkOutDate.setText(sdf.format(calendar.getTime()));

        final DatePickerDialog.OnDateSetListener checkInDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                checkInDate.setText(sdf.format(calendar.getTime()));
            }
        };

        final DatePickerDialog.OnDateSetListener checkOutDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                checkOutDate.setText(sdf.format(calendar.getTime()));
            }
        };

        checkInDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SearchHotel.this, checkInDateListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        checkOutDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SearchHotel.this, checkOutDateListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        peopleMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentValue = Integer.parseInt(peopleCurrent.getText().toString());
                if (currentValue > 0) peopleCurrent.setText(String.valueOf(currentValue - 1));
            }
        });

        peoplePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentValue = Integer.parseInt(peopleCurrent.getText().toString());
                if (currentValue < 30) {
                    peopleCurrent.setText(String.valueOf(currentValue + 1));
                }
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isAccepted = true;

                if (peopleCurrent.getText().equals("0")) {
                    isAccepted = false;
                    Toast.makeText(
                        getBaseContext(),
                        "You must enter a valid number of people",
                        Toast.LENGTH_LONG).show();
                }

                if (isAccepted) {
                    currentSearch.setCheckInDate(checkInDate.getText().toString());
                    currentSearch.setCheckOutDate(checkOutDate.getText().toString());
                    currentSearch.setPeople(Integer.parseInt(peopleCurrent.getText().toString()));
                    Intent intent = new Intent(SearchHotel.this, SelectHotel.class);
                    startActivity(intent);
                }
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(
                    getBaseContext(),
                    "Signed out successfully",
                    Toast.LENGTH_LONG).show();
                startActivity(new Intent(SearchHotel.this, MainActivity.class));
            }
        });

        getLocations();
        mLocationSpinnerDataSource = locations;
        mLocationSpinner = findViewById(R.id.location_spinner);
        mLocationSpinnerAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                mLocationSpinnerDataSource);
        mLocationSpinner.setAdapter(mLocationSpinnerAdapter);
        mLocationSpinner.setOnItemSelectedListener(this);
    }

    private void getLocations() {
        locations.add("Select your location");
        DatabaseReference locationsRef = FirebaseDatabase.getInstance().getReference().child("locations");
        locationsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    locations.add(dsp.getValue(String.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position > 0) {
            currentSearch.setLocation(locations.get(position));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
