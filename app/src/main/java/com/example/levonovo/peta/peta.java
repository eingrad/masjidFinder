package com.example.levonovo.peta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class peta extends AppCompatActivity {

    private static final String TAG = "PetaActivity";
    private FusedLocationProviderClient fusedLocationClient;
    private WebView mWebView;
    private LocationCallback locationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peta);

        mWebView = findViewById(R.id.web_view);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();
                if (url.startsWith("http://") || url.startsWith("https://")) {
                    return false;
                } else if (url.startsWith("intent://")) {
                    try {
                        Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                        if (intent != null) {
                            startActivity(intent);
                            return true;
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "Error parsing intent URL: " + e.getMessage());
                    }
                }
                return true;
            }
        });

        mWebView.loadUrl("http://google.com/maps/search/masjid/");

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(peta.this);

        // Check for permissions and request if not granted
        if (ActivityCompat.checkSelfPermission(peta.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(peta.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        } else {
            if (isLocationEnabled()) {
                fetchLocation();
            } else {
                showLocationEnableDialog();
            }
        }

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    Log.e(TAG, "Location result is null");
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    String latitude = String.valueOf(location.getLatitude());
                    String longitude = String.valueOf(location.getLongitude());
                    String urlWithLocation = "http://google.com/maps/search/masjid/@" + latitude + "," + longitude + ",15z";
                    mWebView.loadUrl(urlWithLocation);
                    Log.d(TAG, "Location fetched: " + latitude + ", " + longitude);
                }
            }
        };
    }

    private void fetchLocation() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);

        if (ActivityCompat.checkSelfPermission(peta.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(peta.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        fusedLocationClient.getLastLocation()
                .addOnCompleteListener(peta.this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            Location location = task.getResult();
                            String latitude = String.valueOf(location.getLatitude());
                            String longitude = String.valueOf(location.getLongitude());
                            String urlWithLocation = "http://google.com/maps/search/masjid/@" + latitude + "," + longitude + ",15z";
                            mWebView.loadUrl(urlWithLocation);
                            Log.d(TAG, "Location fetched: " + latitude + ", " + longitude);
                        } else {
                            Log.e(TAG, "Failed to get location. Result: " + task.getResult());
                            if (ActivityCompat.checkSelfPermission(peta.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(peta.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                return;
                            }
                            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
                        }
                    }
                });
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        Log.d(TAG, "Location enabled: " + isEnabled);
        return isEnabled;
    }

    private void showLocationEnableDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(peta.this);
        builder.setTitle("Enable Location")
                .setMessage("Location services are required for this app. Please enable location services.")
                .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Toast.makeText(peta.this, "Location services are required for this app.", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (isLocationEnabled()) {
                    fetchLocation();
                } else {
                    showLocationEnableDialog();
                }
            } else {
                Toast.makeText(peta.this, "Location permission denied. Cannot fetch location.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
