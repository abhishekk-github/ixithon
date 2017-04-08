package com.example.ixithon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ixithon.R;
import com.example.ixithon.model.City;

import java.util.List;

/**
 * Created by abhishek on 8/4/17.
 */



  public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    private List<City> cityList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
      TextView city, state, country;
      ImageView mImageView;

      public MyViewHolder(View view) {
        super(view);
        city = (TextView) view.findViewById(R.id.search_city_tv);
        state = (TextView) view.findViewById(R.id.search_state_tv);
        country = (TextView) view.findViewById(R.id.search_country_tv);
       // mImageView = (ImageView) view.findViewById(R.id.product_image_view);
      }
    }

    public SearchAdapter(Context context,List<City> itemList) {
      this.cityList = itemList;
      this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View itemView = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.city_item_layout, parent, false);
      return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
      City items = cityList.get(position);
      holder.city.setText(items.getCityName());
      holder.state.setText(items.getState());
      holder.country.setText(items.getCountry());
    }

    @Override
    public int getItemCount() {
      return cityList.size();
    }

}
