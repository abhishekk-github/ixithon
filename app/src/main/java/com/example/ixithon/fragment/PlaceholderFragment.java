package com.example.ixithon.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.ixithon.DetailsActivity;
import com.example.ixithon.R;
import com.example.ixithon.RecyclerItemClickListener;
import com.example.ixithon.adapter.SearchAdapter;
import com.example.ixithon.adapter.inspiredAdapter;
import com.example.ixithon.model.City;
import com.example.ixithon.model.InspiredCity;
import com.example.ixithon.network.SingltonRequestQueue;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import static com.example.ixithon.model.City.getCityListFromServer;
import static com.example.ixithon.model.InspiredCity.getInspiredCityListFromServer;

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
  private SearchAdapter mSearchAdapter;
  private inspiredAdapter mInspiredAdapter;
  private RecyclerView inspiredRV;

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
    inspiredRV = (RecyclerView) rootView.findViewById(R.id.insipire_recycler_view);
    recyclerView = (RecyclerView) rootView.findViewById(R.id.search_result_recycler_view);
    LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    EditText editText = (EditText) rootView.findViewById(R.id.inputSearch);
   // recyclerView.addOnChildAttachStateChangeListener(new ChildAttachListener(layoutManager));
    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
        layoutManager.getOrientation());
    recyclerView.addItemDecoration(dividerItemDecoration);
    inputSearchTextChangedListener(editText);


    LinearLayoutManager horizontalLayoutManagaer
        = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
    inspiredRV.setLayoutManager(horizontalLayoutManagaer);
    fetchInspiredDataFromServer();
     setOnitemClickListener();
    setOnitemClickListenerForInspire();
  }
  /**
   * Handling of the item click. Mostly open the browser.
   */
  public void setOnitemClickListener() {
    recyclerView.addOnItemTouchListener(
        new RecyclerItemClickListener(mContext, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
          @Override public void onItemClick(View view, int position) {
            // do whatever
            City items =  mSearchAdapter.getItem(position);
            Intent intent = new Intent(mContext, DetailsActivity.class);
            intent.putExtra("CityID", items.getID());
            startActivity(intent);
          }
          @Override public void onLongItemClick(View view, int position) {
          }
        })
    );
  }

  public void setOnitemClickListenerForInspire() {
    inspiredRV.addOnItemTouchListener(
        new RecyclerItemClickListener(mContext, inspiredRV ,new RecyclerItemClickListener.OnItemClickListener() {
          @Override public void onItemClick(View view, int position) {
            // do whatever
            InspiredCity items =  mInspiredAdapter.getItem(position);
            Intent intent = new Intent(mContext, DetailsActivity.class);
            intent.putExtra("CityID", items.getCityId());
            startActivity(intent);
          }
          @Override public void onLongItemClick(View view, int position) {
          }
        })
    );
  }

  /**
   * text change listeners
   *
   * @param inputEditText inputEditText
   */
  public void inputSearchTextChangedListener(EditText inputEditText) {
    final EditText inputSearchEditText = inputEditText;
    inputEditText.addTextChangedListener(new TextWatcher() {
      @Override
      public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
        String text = inputSearchEditText.getText().toString().trim();
        if (text.length() >= 2 && (text.length() % 2) == 0) {
          recyclerView.setVisibility(View.VISIBLE);
          fetchDataFromServer(text);
        }
        if(text.length()<2){
          recyclerView.setVisibility(View.GONE);
        }
      }

      @Override
      public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
      }

      @Override
      public void afterTextChanged(Editable arg0) {
      }
    });
  }



  /**
   * this will fetch the responce from the list of items to be displayed.
   */
  private void fetchDataFromServer(String city) {
    String JSON_URL = "http://build2.ixigo.com/action/content/zeus/autocomplete?searchFor=tpAutoComplete&neCategories=City&query=" + city;
    StringRequest stringRequest = new StringRequest(JSON_URL.replace(" ",""),
        new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
            Log.v("MSG",response);
            mSearchAdapter = new SearchAdapter(mContext,getCityListFromServer(response));
            recyclerView.setAdapter(mSearchAdapter);
          }
        },
        new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            recyclerView.setVisibility(View.GONE);
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
    RequestQueue requestQueue = SingltonRequestQueue.getInstance(mContext).getRequestQueue();
    requestQueue.add(stringRequest);
  }

  /**
   * this will fetch the responce from the list of items to be displayed.
   */
  private void fetchInspiredDataFromServer() {
    String JSON_URL = "http://build2.ixigo.com/api/v2/widgets/brand/inspire?product=1&apiKey=ixicode!2$";
    StringRequest stringRequest = new StringRequest(JSON_URL.replace(" ",""),
        new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
            Log.v("MSG",response);
             mInspiredAdapter = new inspiredAdapter(mContext,getInspiredCityListFromServer(response));
            inspiredRV.setAdapter(mInspiredAdapter);
          }
        },
        new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            recyclerView.setVisibility(View.GONE);
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
    RequestQueue requestQueue = SingltonRequestQueue.getInstance(mContext).getRequestQueue();
    requestQueue.add(stringRequest);
  }
}

