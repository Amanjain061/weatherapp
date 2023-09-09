package com.example.weather_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    EditText cityname;
    Button button;
    private final String url = "https://api.openweathermap.org/data/2.5/weather";
    private final String appid = "95ec89e7b29853a63926c4ae9b05f1a2";
    DecimalFormat df = new DecimalFormat("#.##");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityname=findViewById(R.id.cityname);
        button=findViewById(R.id.button);
    }

    public void getweatherdetails(View view) {

        String tempurl = "";
        String  city = cityname.getText().toString();
        if(city.equals("")){
            Toast t = Toast. makeText(getApplicationContext(), "city feild cannot be empty", Toast. LENGTH_LONG);
        }else{
            tempurl = url + "?q=" + city  + "&appid="+ appid;
            StringRequest stringRequest = new StringRequest(Request.Method.POST, tempurl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("response",response);
                    Intent intent=new Intent(getApplicationContext(),Activity2.class);
                    intent.putExtra("your_data",response);
                    startActivity(intent);


                    Log.d("response",response);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                     Toast.makeText(getApplicationContext(),error.toString().trim(),Toast.LENGTH_SHORT).show();
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }

    }
}