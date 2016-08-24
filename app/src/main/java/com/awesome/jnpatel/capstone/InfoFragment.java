package com.awesome.jnpatel.capstone;


import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Locale;

public class InfoFragment extends Fragment {

    private long cityId; // cahange the name

    public String tempString;
    // widgets  are defined
    TextView temperatureTextView;
    TextView conditionTextView;
    TextView cityName;
    ImageView cityImage;
    TextView cityInfo;
    RatingBar cityRatingBar;
    Handler handler;
    //Set to 2 decimal places
    DecimalFormat df = new DecimalFormat("#.##");

    // weather service fail flag
    private boolean weatherServicesHasFailed = false;

    // shared preference variable is defined
    SharedPreferences preferences = null;
    SharedPreferences.Editor editor;


    public InfoFragment() {
        handler = new Handler();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        // shared preference getting reference and will save values forever
        preferences = getActivity().getSharedPreferences("preferences", Activity.MODE_PRIVATE);

        if (view != null) {
            cityName = (TextView) view.findViewById(R.id.txtCityName);
            cityImage = (ImageView) view.findViewById(R.id.imgcitypic);
            cityInfo = (TextView) view.findViewById(R.id.txtTempInfo);
            cityRatingBar = (RatingBar) view.findViewById(R.id.rtBarCityRatingBar);
            temperatureTextView = (TextView) view.findViewById(R.id.temperatureTextView);
            conditionTextView = (TextView) view.findViewById(R.id.conditionTextView);
            // weather widgets are updated in the renderWeather method
            // save rating bar when user change the rating
            ((RatingBar) view.findViewById(R.id.rtBarCityRatingBar))
                    .setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                        @Override
                        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                            editor = preferences.edit();
                            editor.putFloat(Long.toString(cityId), v);
                            editor.apply();
                        }
                    });

            // getting the index from the list item user clicked on
            final Data data = Data.data[(int) cityId];
            // changing city name to current city that is being displayed
            changeCity(data.getName()); // getting city name

            // setting each field on the City information fragment
            // display name, image,
            cityName.setText(data.getName());
            cityInfo.setText(data.getInfo());
            cityImage.setImageResource(data.GetPic());
            cityRatingBar.setRating(preferences.getFloat(Long.toString(cityId), 5));
            view.setBackgroundColor(data.getColor());

        }
    }

    public void setCity(long id) {
        this.cityId = id;
    }

    // creating a new thread, to run runable in background to renderWeather on screen
    private void updateWeatherData ( final String city){
        final String noSpaceCity = city.replace(" ", "");
        new Thread() {
            @Override
            public void run() {
                // Mackinac Island
                // getting weather info for the city
                final JSONObject json = RemoteFetch.getJSON(getActivity(), noSpaceCity);
                if (json == null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() { // show toast if city entered is not found
                            Toast.makeText(getActivity(),
                                    getActivity().getString(R.string.place_not_found),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() { // if weather info is found then rander the weather
                            renderWeather(json); // calling the method to display weather
                        }
                    });
                }
            }
        }.start();
    }


    private void renderWeather(JSONObject json) {
        try {
            String cityname = json.getString("name").toUpperCase(Locale.US);
            cityName.setText(cityname);

            JSONObject details = json.getJSONArray("weather").getJSONObject(0);
            JSONObject main = json.getJSONObject("main"); //

            conditionTextView.setText(details.getString("description").toUpperCase(Locale.US) + "\n"
                    + "Humidity: " + main.getString("humidity") + "%" + "\n" + "Pressure: "
                    + main.getString("pressure") + "hPa"); // converting JSON in to string and diplay it in the view

            boolean isCelcius = MainActivity.convertTemp(); // checking if user sepected celcius to disply units in C rather then F
            //temperatureTextView.setText(String.format("%.2f", main.getDouble("temp")) + " °C");
            if(isCelcius == false)
            {
                Double f = (((main.getDouble("temp")) * 9 / 5.0) + 32);
                tempString = String.valueOf(df.format(f)) + " °F";
                temperatureTextView.setText(tempString);
            }
            else
            {
                Double f = (main.getDouble("temp"));
                tempString = String.valueOf(df.format(f)) + " °C";
                temperatureTextView.setText(tempString);
            }
//            temperatureTextView.setText(Double.toString(f) + " °F");
//            DateFormat df = DateFormat.getDateTimeInstance();
//            String updateOn = df.format(new Date(json.getLong("dt") * 1000));
//            updateField.setText("Last update: " + updateOn);
//            setWeatherIcon(details.getInt("id"), json.getJSONObject("sys").getLong("sunrise") * 1000,
//                    json.getJSONObject("sys").getLong("sunset") * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putLong("cityId", cityId);
    }

    // methos to update watherIcon but decided on to use this on our
    private void setWeatherIcon(int actualId, long sunrise, long sunset) {
        int id = actualId / 100;
        String icon = "";
        if(actualId == 800) {
            long currentTime = new Date().getTime();
            if(currentTime >= sunrise && currentTime < sunset) {
                icon = getActivity().getString(R.string.weather_sunny);
            } else {
                icon = getActivity().getString(R.string.weather_clear_night);
            }
        } else {
            switch (id) {
                case 2 : icon = getActivity().getString(R.string.weather_thunder);
                    break;
                case 3 : icon = getActivity().getString(R.string.weather_drizzle);
                    break;
                case 7 : icon = getActivity().getString(R.string.weather_foggy);
                    break;
                case 8 : icon = getActivity().getString(R.string.weather_cloudy);
                    break;
                case 6 : icon = getActivity().getString(R.string.weather_snowy);
                    break;
                case 5 : icon = getActivity().getString(R.string.weather_rainy);
                    break;
            }
        }
        //weatherIcon.setText(icon);
    }


    // each time user select a new city update weather data method will be called to update the weather info on the screen
    public void changeCity(String city) {
        updateWeatherData(city);
    }

}
