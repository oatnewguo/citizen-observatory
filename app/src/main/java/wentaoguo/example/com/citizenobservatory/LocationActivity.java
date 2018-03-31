package wentaoguo.example.com.citizenobservatory;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class LocationActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
    }

    public void submit(View view)
    {
        EditText inputLatitude = findViewById(R.id.editLatitude);
        EditText inputLongitude = findViewById(R.id.editLongitude);
        String latitude = inputLatitude.getText().toString();
        String longitude = inputLongitude.getText().toString();

        double lat = Double.parseDouble(latitude);
        double lon = Double.parseDouble(longitude);
        if(latitude == null || longitude == null)
        {
            Toast.makeText(getApplicationContext(), "Location data could not be found.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(lat < -90 || lat > 90 || lon < -180 || lon > 180)
        {
            Toast.makeText(getApplicationContext(), "Latitude should be between -90 and 90, and longitude should be between -180 and 180.", Toast.LENGTH_SHORT).show();
            return;
        }

        ((MyApplication) this.getApplication()).setLatitude(latitude);
        ((MyApplication) this.getApplication()).setLongitude(longitude);

        setResult(RESULT_OK);
        finish();
    }

    /* This code does not work--prompt users to use Google Maps instead
    public void collectData()
    {
        Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_SHORT).show();

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(2000);

        // Create LocationSettingsRequest object using location request
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();

        // Check whether location settings are satisfied
        // https://developers.google.com/android/reference/com/google/android/gms/location/SettingsClient
        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        settingsClient.checkLocationSettings(locationSettingsRequest);

        // new Google API SDK v11 uses getFusedLocationProviderClient(this)
        getFusedLocationProviderClient(this).requestLocationUpdates(mLocationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        // do work here
                        onLocationChanged(locationResult.getLastLocation());
                    }
                },
                Looper.myLooper());

        locationProviderClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>()
                {
                    @Override
                    public void onSuccess(Location loc)
                    {
                        // Got last known location. In some rare situations this can be null.
                        if (location == null)
                        {
                            Toast.makeText(getApplicationContext(), "Something went wrong with the location. Try again?", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        location = loc;
                    }
                })
                .addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Log.d("error", "Error trying to get last GPS location");
                        e.printStackTrace();
                    }
                });
        Toast.makeText(getApplicationContext(), "Location: " + location.toString(), Toast.LENGTH_SHORT).show();
    }*/
}
