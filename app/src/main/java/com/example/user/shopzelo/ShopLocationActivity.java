package com.example.user.shopzelo;

import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Random;

public class ShopLocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    GPSTracker gps;
    public double latitude,longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_location);

        ActivityCompat.requestPermissions(ShopLocationActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);


        TextView nearlocation = (TextView)findViewById(R.id.txt_nearlocation);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        Random rand = new Random();

        int  n = rand.nextInt(5) + 1;

        if(n == 1){
            nearlocation.setText("Apple Store is nearest location.");
        }
        else if(n==2){
            nearlocation.setText("BestBuyStore is nearest location.");
        }
        else if(n==3){
            nearlocation.setText("Samsung is nearest location.");
        }
        else if(n==4){
            nearlocation.setText("Canadian Tire is nearest location.");
        }
        else if(n==5){
            nearlocation.setText("Wireless Wave is nearest location.");
        }
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng apple = new LatLng(43.7891292, -79.3627652);
        LatLng bestbuy = new LatLng(43.7787632,-79.2599262);
        LatLng canadian_tire = new LatLng(43.7900601,-79.2594274);
        LatLng samsung = new LatLng(43.7752932,-79.257825);
        LatLng wireless = new LatLng(43.7754836,-79.2587701);

        mMap.addMarker(new MarkerOptions().position(apple).title("Apple Store"));
        mMap.addMarker(new MarkerOptions().position(bestbuy).title("Best Buy Store"));
        mMap.addMarker(new MarkerOptions().position(canadian_tire).title("Canadian tire"));
        mMap.addMarker(new MarkerOptions().position(samsung).title("Samsung"));
        mMap.addMarker(new MarkerOptions().position(wireless).title("WirelessWave"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(apple, 11));

    }
}
