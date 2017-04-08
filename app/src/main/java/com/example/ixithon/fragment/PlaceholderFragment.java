package com.example.ixithon.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ixithon.R;
import com.example.ixithon.adapter.SearchAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import static com.example.ixithon.model.City.getCityListFromServer;

/**
 * Created by abhishek on 8/4/17.
 */

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
  /**
   * The fragment argument representing the section number for this
   * fragment.
   */
  private static final String ARG_SECTION_NUMBER = "section_number";

  Context mContext;
  private RecyclerView recyclerView;
  private SearchAdapter mAdapter;

  public PlaceholderFragment() {
  }

  /**
   * Returns a new instance of this fragment for the given section
   * number.
   */
  public static PlaceholderFragment newInstance(int sectionNumber) {
    PlaceholderFragment fragment = new PlaceholderFragment();
    Bundle args = new Bundle();
    args.putInt(ARG_SECTION_NUMBER, sectionNumber);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    mContext = context;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_home_screen, container, false);
    EditText editText = (EditText) rootView.findViewById(R.id.inputSearch);
    init(rootView);
    return rootView;
  }

  @Override
  public void onStart() {
    super.onStart();
  }

  /**
   * Initializing all the component and providing the listeners to them.
   */
  private void init(View rootView) {
    recyclerView = (RecyclerView) rootView.findViewById(R.id.search_result_recycler_view);
    LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
   // recyclerView.addOnChildAttachStateChangeListener(new ChildAttachListener(layoutManager));
    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
        layoutManager.getOrientation());
    recyclerView.addItemDecoration(dividerItemDecoration);
    fetchDataFromServer();
   // setOnitemClickListener();
  }

  /**
   * this will fetch the responce from the list of items to be displayed.
   */
  private void fetchDataFromServer() {
    String JSON_URL = "http://build2.ixigo.com/action/content/zeus/autocomplete?searchFor=tpAutoComplete&neCategories=City&query=indore";
    StringRequest stringRequest = new StringRequest(JSON_URL.replace(" ",""),
        new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
            Log.v("MSG",response);
            mAdapter = new SearchAdapter(mContext,getCityListFromServer(response));
            recyclerView.setAdapter(mAdapter);
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
    RequestQueue requestQueue = Volley.newRequestQueue(mContext);
    requestQueue.add(stringRequest);
  }
}

