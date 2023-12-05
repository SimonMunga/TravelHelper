package com.example.travelhelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity {
    private GoogleMap mMap;
    private LinearLayout bottomSheet;
    private TextView textViewPlaceName;
    private TextView textViewPlaceAddress;
    private Button btnViewDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Initialize Google Map fragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync((OnMapReadyCallback) this);

        // Initialize views in the bottom sheet layout
        bottomSheet = findViewById(R.id.bottomSheet);
        textViewPlaceName = findViewById(R.id.textViewPlaceName);
        textViewPlaceAddress = findViewById(R.id.textViewPlaceAddress);
        btnViewDetails = findViewById(R.id.btnViewDetails);

        // Set click listener for the "View Details" button in the bottom sheet
        btnViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click to view more details or navigate to the selected place
                // You can implement the desired behavior here.
            }
        });
    }


    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add sample markers to the map (replace with actual data and logic)
        LatLng place1 = new LatLng(40.7128, -74.0060);
        LatLng place2 = new LatLng(34.0522, -118.2437);
        mMap.addMarker(new MarkerOptions().position(place1).title("Place 1").snippet("Address 1"));
        mMap.addMarker(new MarkerOptions().position(place2).title("Place 2").snippet("Address 2"));

        // Move the camera to the first marker
        mMap.moveCamera(CameraUpdateFactory.newLatLng(place1));

        // Set marker click listener to show bottom sheet with place details
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                // Show the bottom sheet when a marker is clicked
                bottomSheet.setVisibility(View.VISIBLE);

                // Update the bottom sheet content with place details
                textViewPlaceName.setText(marker.getTitle());
                textViewPlaceAddress.setText(marker.getSnippet());

                // Return true to indicate that the click event is handled
                return true;
            }
        });

        // Set map click listener to hide the bottom sheet when the map is clicked
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                bottomSheet.setVisibility(View.GONE);
            }
        });
    }
}