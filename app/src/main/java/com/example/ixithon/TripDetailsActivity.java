package com.example.ixithon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.ixithon.db.TravelPlanDBSource;
import com.example.ixithon.model.Plan;

import java.util.List;

public class TripDetailsActivity extends AppCompatActivity {

  CardView hotels,mode,placetovisit;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_trip_details);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    init();
  }

  private void init() {
    hotels = (CardView) findViewById(R.id.card_view_hotel);
    mode = (CardView) findViewById(R.id.card_view_mode);
    placetovisit = (CardView) findViewById(R.id.card_view_places);

    hotels.setCardBackgroundColor(getResources().getColor(R.color.educational_card_view_color));
    mode.setCardBackgroundColor(getResources().getColor(R.color.technical_card_view_color));
    placetovisit.setCardBackgroundColor(getResources().getColor(R.color.job_preference_card_view_color));

    mode.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(TripDetailsActivity.this, ModeActivity.class);
        startActivity(intent);
      }
    });



  }

}
