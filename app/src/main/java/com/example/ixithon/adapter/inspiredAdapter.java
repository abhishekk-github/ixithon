package com.example.ixithon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ixithon.R;
import com.example.ixithon.model.InspiredCity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by abhishek on 8/4/17.
 */

public class inspiredAdapter  extends RecyclerView.Adapter<inspiredAdapter.MyViewHolder> {

  private List<InspiredCity> cityList;
  private Context mContext;

  public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView city, country;
    TextView price;
    ImageView mImageView;

    public MyViewHolder(View view) {
      super(view);
      city = (TextView) view.findViewById(R.id.hr_cityName);
      country = (TextView) view.findViewById(R.id.hr_countryName);
      price = (TextView) view.findViewById(R.id.hr_price);
      mImageView = (ImageView) view.findViewById(R.id.hr_imageview);
    }
  }

  public inspiredAdapter(Context context,List<InspiredCity> itemList) {
    this.cityList = itemList;
    this.mContext = context;
  }

  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.inspiredcityitem, parent, false);
    return new MyViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(MyViewHolder holder, int position) {
    InspiredCity items = cityList.get(position);
    holder.city.setText(items.getCityName());
    holder.country.setText(items.getCountryName());
    //holder.price.setText(items.getPrice());
    Picasso.with(mContext).load(items.getImageUrl()).placeholder(R.drawable.progress_animation ).into(holder.mImageView);

  }

  @Override
  public int getItemCount() {
    return cityList.size();
  }

  public InspiredCity getItem(int position) {
    return cityList.get(position);
  }
}
