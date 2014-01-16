package com.example.MapsDemo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyActivity extends FragmentActivity {
    /**
     * Called when the activity is first created.
     */
    private GoogleMap googleMap;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

      //  map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

        googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        final LatLng CIU = new LatLng(1.926, 73.400);
        googleMap.addMarker(new MarkerOptions().position(CIU).title("My dreamed island"));

    }
}
