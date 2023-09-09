package com.example.weather_app;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class Activity2 extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    TextView textview3;
    private final String url = "https://api.openweathermap.org/data/2.5/weather";
    private final String appid = "95ec89e7b29853a63926c4ae9b05f1a2";
    DecimalFormat df = new DecimalFormat("#.##");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        textView=findViewById(R.id.textView);
        imageView=findViewById(R.id.imageView);
        textview3=findViewById(R.id.textViewnew);

        String response = getIntent().getStringExtra("your_data");
        String output="";
//        textView.setText("hello");
        try {
            JSONObject jsonresponse = new JSONObject(response);
            JSONArray jsonarray = jsonresponse.getJSONArray("weather");
            JSONObject jsonObjectweather=jsonarray.getJSONObject(0);
            String description = jsonObjectweather.getString("description");
            JSONObject jsonObjectmain = jsonresponse.getJSONObject("main");
            double temp = jsonObjectmain.getDouble("temp")-273.15;
            double feelslike = jsonObjectmain.getDouble("feels_like")-273.15;
            float pressure = jsonObjectmain.getInt("pressure");
            int humidity = jsonObjectmain.getInt("humidity");
            JSONObject jsonobjectwind = jsonresponse.getJSONObject("wind");
            String wind = jsonobjectwind.getString("speed");
            JSONObject jsonObjectclouds = jsonresponse.getJSONObject("clouds");
            String clouds = jsonObjectclouds.getString("all");
            JSONObject jsonObjectsys = jsonresponse.getJSONObject("sys");
            String cityyname=jsonresponse.getString("name");
            output+="Current waether of "+ cityyname +
                    "\n Temp: "+df.format(temp) + " degree celsius"
                    +"\n Feels Like: " + df.format(feelslike) + " degree celsius"
                    +"\n humidity: "+humidity+"%"
                    +"\n Description: "+description
                    +"\n Wind Speed: "+wind+"m/s (meters per second)"
                    +"\n Cloudiness: "+clouds+"%"
                    +"\n Pressure: "+pressure+"hPa";
            textView.setText(output);
            String newstr="";
            if (temp>35){
               newstr+="Avoid going out its too hot today!!. \n";
            }
            else if(temp>30){
                newstr+="wait till evening to go out. \n";
            }
            if(Integer.parseInt(clouds)>75){
                newstr+="Do not forget to carry an umbrella with you!!. \n";
            }
            else{
                newstr+="Very low chances of raining.\n";
            }
            if(humidity>50 && temp>35){

                newstr+="too much humidity and hot, wear Over Sized clothes!!. \n";
            } else if (humidity>50) {
                newstr+="too much humidity, Avoid layering of  clothes!!. \n";
            } else{
                newstr+="best weather to travel.\n";
            }
            textview3.setText(newstr);






        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}