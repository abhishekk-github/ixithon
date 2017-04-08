package com.example.ixithon;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

  TextView name,country,Description;
  Button planBtn;
  Plan myPlan;
  ImageView mImageView;
  Toolbar toolbar;
  CityDescription  cityDescription;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_details);
     toolbar = (Toolbar) findViewById(R.id.toolbar);

    setSupportActionBar(toolbar);
    mImageView = (ImageView) findViewById(R.id.image_id);
    name = (TextView) findViewById(R.id.des_name);
    country = (TextView) findViewById(R.id.des_country);
    Description = (TextView) findViewById(R.id.des_description);
    planBtn = (Button) findViewById(R.id.trip_plan_btn);
    String s = getIntent().getStringExtra("CityID");
    fetchDataFromServer(s);

    myPlan = new Plan();

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_abc);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        Intent intent = new Intent(DetailsActivity.this, UserListActivity.class);
        intent.putExtra("PlanId", myPlan.getPlanID());
        startActivityForResult(intent,101);
      }
    });

    planBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        if(cityDescription.getDescription() == null){
          Toast.makeText(DetailsActivity.this,"Cannot plan for this location",Toast.LENGTH_SHORT).show();
          return;
        }
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
            cityDescription =  CityDescription.getCityDescriptionFromServer(response);
            name.setText(cityDescription.getName());
            toolbar.setTitle(cityDescription.getName());
            country.setText(cityDescription.getCountryName());
            if(cityDescription != null && cityDescription.getDescription() != null) {
              if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Description.setText(Html.fromHtml(cityDescription.getDescription(), Html.FROM_HTML_MODE_COMPACT));
              } else {
                Description.setText(Html.fromHtml(cityDescription.getDescription()));
              }
            }
            else{
              Description.setText("Cannot find description for  this location");
            }
            Picasso.with(DetailsActivity.this).load(cityDescription.getKeyImageUrl()).placeholder(R.drawable.progress_animation ).into(mImageView);

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

    ArrayList<TravellerInvite> userlist = new ArrayList<>();
    if(data != null && data.getExtras()!= null ) {
      Bundle bundle = data.getExtras();
       userlist = (ArrayList<TravellerInvite>) bundle.getSerializable("selectedUser");
      Log.v("msg", userlist.toString());
    }
    myPlan.setUserID("abhi@ixigo.com");
    myPlan.setDestinationPoint(cityDescription.getXid().toString());
    myPlan.setStartPoint("1065223");
    myPlan.setTravellerInvites(userlist);
  }
}
