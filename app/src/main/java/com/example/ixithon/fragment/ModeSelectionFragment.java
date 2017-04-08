package com.example.ixithon.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.example.ixithon.R;
import com.example.ixithon.adapter.ModeSelectionAdapter;
import com.example.ixithon.model.TravelModeModel;
import com.example.ixithon.network.SingltonRequestQueue;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ModeSelectionFragment extends Fragment {
  /**
   * The fragment argument representing the section number for this
   * fragment.
   */
  private static final String ARG_TRAVEL_MODE = "mode";
  private static final String ARG_SOURCE_ID = "sourceId";
  private static final String ARG_DESTINATION_ID = "destinationId";

  private RecyclerView recyclerView;

  public ModeSelectionFragment() {
  }

  /**
   * Returns a new instance of this fragment for the given section
   * number.
   */
  public static ModeSelectionFragment newInstance(String type, String sourceId, String destinationId) {
    ModeSelectionFragment fragment = new ModeSelectionFragment();
    Bundle args = new Bundle();
    args.putString(ARG_TRAVEL_MODE, type);
    args.putString(ARG_SOURCE_ID, sourceId);
    args.putString(ARG_DESTINATION_ID, destinationId);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    fetchDataFromServer(getArguments().getString(ARG_SOURCE_ID, "1065223"), getArguments().getString(ARG_DESTINATION_ID, "1077380"));
    View rootView = inflater.inflate(R.layout.fragment_mode, container, false);
    recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
    return rootView;
  }

  /**
   * this will fetch the responce from the list of items to be displayed.
   */
  private void fetchDataFromServer(String originId, String destinationId) {
    String JSON_URL = "http://build2.ixigo.com/api/v2/a2b/modes?apiKey=ixicode!2$&originCityId=" + originId + "&destinationCityId=" + destinationId;
    final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "Loading", "Please wait while the we load the data for you");
    StringRequest stringRequest = new StringRequest(JSON_URL.replace(" ", ""),
        new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
            Log.v("MSG", response);
            List<TravelModeModel> travelModeModelList = TravelModeModel.getListOfModeFromServer(response, getArguments().getString(ARG_TRAVEL_MODE));
            LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(new ModeSelectionAdapter(getActivity(), travelModeModelList));
            progressDialog.dismiss();
          }
        },
        new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            progressDialog.dismiss();
            Toast.makeText(getActivity(), "Unable to load the list", Toast.LENGTH_SHORT).show();
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
    RequestQueue requestQueue = SingltonRequestQueue.getInstance(getActivity()).getRequestQueue();
    requestQueue.add(stringRequest);
  }
}