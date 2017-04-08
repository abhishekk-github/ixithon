package com.example.ixithon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ixithon.R;
import com.example.ixithon.model.TravelModeModel;

import java.util.List;

public class ModeSelectionAdapter  extends RecyclerView.Adapter<ModeSelectionAdapter.ViewHolder> {

  private Context context;
  List<TravelModeModel> travelModeModels;

  public ModeSelectionAdapter(Context context, List<TravelModeModel> travelModeModels) {
    this.context = context;
    this.travelModeModels = travelModeModels;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v  = LayoutInflater.from(context).inflate(R.layout.mode_selection_layout, parent, false);
    return new ViewHolder(v);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    holder.priceTxt.setText(String.valueOf(travelModeModels.get(position).getPrice()));
    holder.durationTxt.setText(String.valueOf(travelModeModels.get(position).getDuration()));
    holder.timeTxt.setText(String.valueOf(travelModeModels.get(position).getTime()));
    holder.carrierNameTxt.setText(String.valueOf(travelModeModels.get(position).getCarrierName()));
  }

  @Override
  public int getItemCount() {
    return travelModeModels.size();
  }

  static class ViewHolder extends RecyclerView.ViewHolder {
    View v;
    TextView carrierNameTxt;
    TextView timeTxt;
    TextView durationTxt;
    TextView priceTxt;

    public ViewHolder(View itemView) {
      super(itemView);
      v = itemView;
      carrierNameTxt = (TextView) itemView.findViewById(R.id.content_txt);
      timeTxt = (TextView) itemView.findViewById(R.id.time_txt);
      durationTxt = (TextView) itemView.findViewById(R.id.duration_txt);
      priceTxt = (TextView) itemView.findViewById(R.id.price_txt);
    }
  }
}
