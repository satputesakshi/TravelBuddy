package com.example.travelbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Description extends AppCompatActivity {
    //Declare Variables
    private RequestQueue requestQueue;
    private List<Visa> visaList;
    TextView overviewTxt, languageTxt, currencyTxt, detailsTxt, countryTxt;
    ImageView bgImg, flagImg, backArrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        overviewTxt = findViewById(R.id.overviewTxt);
        languageTxt = findViewById(R.id.languageText);
        currencyTxt = findViewById(R.id.currencyTxt);
        detailsTxt = findViewById(R.id.visaDetails);
        countryTxt = findViewById(R.id.countryTxtView);
        bgImg = findViewById(R.id.bgImgview);
        flagImg = findViewById(R.id.flagView);
        backArrow = findViewById(R.id.backArrowView);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();
        fetchVisa();
    }
    private void fetchVisa() {
        //Code for fetching data from api
        Intent intent = getIntent();
        String fromCountry = intent.getStringExtra("from");
        String toCountry = intent.getStringExtra("to");
        String country = intent.getStringExtra("country");
        countryTxt.setText(country);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int resId = bundle.getInt("image");
            int flagId = bundle.getInt("flag");
            flagImg.setImageResource(flagId);
            bgImg.setImageResource(resId);
        }
        //URL for visa API
        String url = "https://visa-list.p.rapidapi.com/api/public/visa/country/"+fromCountry+"/"+toCountry;
        //Get JSON object response
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Populate JSON response in the textview
                        try {
                            String language = response.getJSONObject("country").getString("languageNames");
                            String overview = response.getString("details");
                            String travelRestriction = response.getJSONObject("travelRestriction").getString("details");
                            String currency = response.getJSONObject("country").getString("currencyCode");

                            overviewTxt.setText(overview);
                            languageTxt.setText(language);
                            detailsTxt.setText(travelRestriction);
                            currencyTxt.setText(currency);

                        } catch (JSONException e) {
                            //Catch error
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        //Check JSON Error
                        overviewTxt.setText(error.toString());
                    }
                }){@Override
                //Set headers for the API call
                public Map getHeaders() throws AuthFailureError
                {
                    HashMap headers = new HashMap();
                    headers.put("X-RapidAPI-Host", "visa-list.p.rapidapi.com");
                    headers.put("Content-type", "application/json");
                    headers.put("X-RapidAPI-Key", "12e7056570msha6229d0150f89e3p167d21jsn56b8de3b9997");
                    return headers;
            }};

        requestQueue.add(jsonObjectRequest);
    }
    }
