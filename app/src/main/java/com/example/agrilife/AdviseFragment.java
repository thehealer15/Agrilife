package com.example.agrilife;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/***
 * Advise fragment utility:
 * - request a call from expert ( expert in agri recommendation )
 * - Upload information which ML model will need to predict crops
 * - get recommendation derived from that data
 * - recommendation will be from ML model generated and agri-expert monitored
 * */
public class AdviseFragment extends Fragment {
    View parentHolder;
    Context mContext;

    Button callapi ;
    Button forlocation ;
    Button crop ;
    EditText nitrogen, phosphorus, potassium, temperature, humidity, PH, rainfall ;
    LocationManager locationManager;
    String latitude, longitude;
    String api_humidity , api_temprature ;
    private static final int REQUEST_LOCATION = 1;


    // initializing
    // FusedLocationProviderClient
    // object
    FusedLocationProviderClient mFusedLocationClient;

    // Initializing other items
    // from layout file
    int PERMISSION_ID = 44;



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public AdviseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentHolder  = inflater.inflate(R.layout.fragment_advise, container, false);


        crop = (Button) parentHolder.findViewById(R.id.crop);
        callapi = (Button) parentHolder.findViewById(R.id.callapi );
        nitrogen = (EditText) parentHolder.findViewById(R.id.nitrogen);
        phosphorus = (EditText) parentHolder.findViewById(R.id.phosphorus);
        potassium = (EditText) parentHolder.findViewById(R.id.potassium);
        temperature = (EditText) parentHolder.findViewById(R.id.temperature);
        humidity = (EditText) parentHolder.findViewById(R.id.humidity);
        PH = (EditText) parentHolder.findViewById(R.id.PH);
        rainfall = (EditText) parentHolder.findViewById(R.id.rainfall);


        callapi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wethereAPI();
            }
        });

        forlocation = (Button) parentHolder.findViewById(R.id.forlocation);
        forlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wethereAPI();
            }
        });
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(mContext);

        // method to get the location
        getLastLocation();


        return parentHolder;
    }

    private void APIcall(String apihumidity , String apitemprature){

        String s_nitrogen, s_phosphorus, s_potassium, s_temperature, s_humidity, s_PH, s_rainfall ;
        s_nitrogen = nitrogen.getText().toString();
        s_phosphorus = phosphorus.getText().toString();
        s_potassium = potassium.getText().toString();
        s_temperature =temperature.getText().toString();
        s_humidity = humidity.getText().toString();
        s_PH = PH.getText().toString();
        s_rainfall = rainfall.getText().toString();

        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url ="https://API.saymyname002.repl.co/recommend";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
                        crop.setText(jsonObject.get("crop").toString());
                        crop.setVisibility(View.VISIBLE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mContext,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("nitrogen",s_nitrogen);
                params.put("phosphorus",s_phosphorus);
                params.put("potassium", s_potassium);
                params.put("temperature", apitemprature);
                params.put("humidity", apihumidity);
                params.put("PH", s_PH);
                params.put("rainfall", s_rainfall);

                return params;
            }

        };
        queue.add(stringRequest);

    }

    private void wethereAPI(){

        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url ="https://weatherbit-v1-mashape.p.rapidapi.com/current?lon="+longitude+"&lat="+latitude;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObject = new JSONObject(response);
                            JSONArray json2 = jObject.getJSONArray("data");
                            JSONObject json3 = json2.getJSONObject(0);
                            Double rh = (Double) json3.get("rh");
                            String apihumidity = Double.toString(rh);
                            Double temp = (Double) json3.get("temp");
                            String apitemprature = Double.toString(temp);
                            api_humidity = apihumidity;
                            api_temprature = apitemprature;
                            APIcall(api_humidity,api_temprature);
                            humidity.setText(api_humidity);
                            temperature.setText(api_temprature);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            forlocation.setText(e.getMessage());
                            Toast.makeText(mContext,e.getMessage(),Toast.LENGTH_LONG).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Snackbar.make(getActivity().findViewById(android.R.id.content),
                                error.getMessage(), Snackbar.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("x-rapidapi-host", "weatherbit-v1-mashape.p.rapidapi.com");
                headers.put("x-rapidapi-key", "3d95d716afmsh5af9768c6017ea6p175c19jsn2a3012df750e");
                return headers;
            }


        };
        queue.add(stringRequest);

    }


    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        // check if permissions are given
        if (checkPermissions()) {

            // check if location is enabled
            if (isLocationEnabled()) {

                // getting last
                // location from
                // FusedLocationClient
                // object
                mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if (location == null) {
                            requestNewLocationData();
                        } else {
                            forlocation.setText(location.getLatitude() + "");
                            latitude = Double.toString(location.getLatitude()) ;
                            longitude = Double.toString(location.getLongitude()) ;


                        }
                    }
                });
            } else {
                Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "Please turn on  your location...", Snackbar.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {

            requestPermissions();
        }
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {
        /* Initializing LocationRequest object
        */
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        // setting LocationRequest  on FusedLocationClient
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(mContext);
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }

    private LocationCallback mLocationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            forlocation.setText("Latitude: " + mLastLocation.getLatitude() + "" + "Longitude: " + mLastLocation.getLongitude() + "");
            latitude = Double.toString(mLastLocation.getLatitude()) ;
            longitude = Double.toString(mLastLocation.getLongitude()) ;

        }
    };

    // method to check for permissions
    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
 }

    private void requestPermissions() {
        requestPermissions( new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    // If everything is alright then
    @Override
    public void
    onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
        }
    }

}