package com.example.ixithon;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.example.ixithon.model.CityDescription;
import com.example.ixithon.model.Plan;
import com.example.ixithon.model.TravellerInvite;
import com.example.ixithon.network.SingltonRequestQueue;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

  TextView name,country,Description;
  Button plan;
  Plan myPlan;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_details);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    name = (TextView) findViewById(R.id.des_name);
    country = (TextView) findViewById(R.id.des_country);
    Description = (TextView) findViewById(R.id.des_description);
    plan = (Button) findViewById(R.id.trip_plan_btn);
    String s = getIntent().getStringExtra("CityID");
    fetchDataFromServer(s);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_abc);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        Intent intent = new Intent(DetailsActivity.this, UserListActivity.class);
        // intent.putExtra("CityID", items.getID());
        startActivityForResult(intent,101);
      }
    });

    plan.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(DetailsActivity.this, TripDetailsActivity.class);
       // intent.putExtra("CityID", items.getID());
        startActivity(intent);
      }
    });
  }

  /**
   * this will fetch the responce from the list of items to be displayed.
   */
  private void fetchDataFromServer(String cityid) {
    String JSON_URL = "http://build2.ixigo.com/api/v3/namedentities/id/"+cityid+"?apiKey=ixicode!2$";
    StringRequest stringRequest = new StringRequest(JSON_URL.replace(" ",""),
        new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
            Log.v("MSG",response);
            CityDescription  cityDescription =  CityDescription.getCityDescriptionFromServer(response);
            name.setText(cityDescription.getName());
            country.setText(cityDescription.getCountryName());
            Description.setText(cityDescription.getDescription());
          }
        },
        new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            NetworkResponse response = error.networkResponse;
            if (error instanceof ServerError && response != null) {
              try {
                String res = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                JSONObject obj = new JSONObject(res);
              } catch (UnsupportedEncodingException | JSONException e1) {
                // Couldn't properly decode data to string
                e1.printStackTrace();
              }
            }
          }
        });
    RequestQueue requestQueue = SingltonRequestQueue.getInstance(this).getRequestQueue();
    requestQueue.add(stringRequest);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    Bundle bundle = data.getExtras();
    ArrayList<TravellerInvite> userlist = (ArrayList<TravellerInvite>) bundle.getSerializable("selectedUser");
  }
}
