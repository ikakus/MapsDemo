package com.example.MapsDemo;


import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyActivity extends FragmentActivity {
    /**
     * Called when the activity is first created.
     */
    private GoogleMap googleMap;
    private static final LatLng GOLDEN_GATE_BRIDGE =
            new LatLng(37.828891, -122.485884);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

        if (googleMap == null) {
            Toast.makeText(this, "Google Maps not available",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Hi I am Toast )", Toast.LENGTH_LONG).show();
        }

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is
        // present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private LocationClient mLocationClient;

//    private void setUpLocationClientIfNeeded() {
//        if (mLocationClient == null) {
//            mLocationClient = new LocationClient(
//                    getApplicationContext(),
//                    this,  // ConnectionCallbacks
//                    this); // OnConnectionFailedListener
//        }
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_sethybrid:
                googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;

            case R.id.menu_showtraffic:
                googleMap.setTrafficEnabled(true);
                break;

            case R.id.menu_zoomin:
                googleMap.animateCamera(CameraUpdateFactory.zoomIn());
                break;

            case R.id.menu_zoomout:
                googleMap.animateCamera(CameraUpdateFactory.zoomOut());
                break;

            case R.id.menu_gotolocation:
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(GOLDEN_GATE_BRIDGE) // Sets the center of the map to
                                // Golden Gate Bridge
                        .zoom(17)                   // Sets the zoom
                        .bearing(90) // Sets the orientation of the camera to east
                        .tilt(30)    // Sets the tilt of the camera to 30 degrees
                        .build();    // Creates a CameraPosition from the builder
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(
                        cameraPosition));
                break;

            case R.id.menu_addmarker:

                // ---using the default marker---
            /*
            map.addMarker(new MarkerOptions()
                .position(GOLDEN_GATE_BRIDGE)
                .title("Golden Gate Bridge") .snippet("San Francisco")
                .icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            */

                googleMap.addMarker(new MarkerOptions()
                        .position(GOLDEN_GATE_BRIDGE)
                        .title("Golden Gate Bridge")
                        .snippet("San Francisco")
                        .icon(BitmapDescriptorFactory
                                .fromResource(R.drawable.ic_launcher)));
                break;


            case R.id.menu_getcurrentlocation:
                // ---get your current location and display a blue dot---
                googleMap.setMyLocationEnabled(true);

                break;

            case R.id.menu_showcurrentlocation:

                //Location loc = LocationManager.getLastKnownLocation(LocationManager.getBestProvider(new Criteria(), false));

                Location myLocation = googleMap.getMyLocation();
                if (myLocation != null) {
                    LatLng myLatLng = new LatLng(myLocation.getLatitude(),
                            myLocation.getLongitude());

                    CameraPosition myPosition = new CameraPosition.Builder()
                            .target(myLatLng).zoom(17).bearing(90).tilt(30).build();
                    googleMap.animateCamera(
                            CameraUpdateFactory.newCameraPosition(myPosition));
                } else {
                    Toast.makeText(this, " Location Error ", Toast.LENGTH_LONG).show();
                }
                break;
        }

        return true;
    }

}
