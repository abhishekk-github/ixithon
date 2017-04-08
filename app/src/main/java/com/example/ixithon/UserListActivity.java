package com.example.ixithon;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ixithon.model.TravellerInvite;
import com.example.ixithon.model.UserModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.ixithon.LoginActivity.DUMMY_CREDENTIALS;

public class UserListActivity extends AppCompatActivity {
    MyCustomAdapter dataAdapter = null;

  Set<TravellerInvite> selectedInvitedList = new HashSet<>();
  List<TravellerInvite> totalInvitedList = new ArrayList<>();
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_list);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    //Generate list View from ArrayList
    displayListView();
    }

private void displayListView() {

    //Array list of countries
    ArrayList<UserModel> userList = new ArrayList<UserModel>();
  for (String credential : DUMMY_CREDENTIALS) {
    String[] pieces = credential.split(":");
    UserModel userModel = new UserModel();
    userModel.setUserID(pieces[0]);
    userModel.setPassword(pieces[1]);
    userList.add(userModel);
    TravellerInvite invite = new TravellerInvite();
    invite.setPlanID(123);
    invite.setStatus(new TravellerInvite.TravelStatus(1));
    invite.setTravellerID(userModel.getUserID());
    invite.setTravellerName(userModel.getUserID());
    totalInvitedList.add(invite);
  }

    //create an ArrayAdaptar from the String Array
    dataAdapter = new MyCustomAdapter(this,
    R.layout.user_info_layout, userList);
    ListView listView = (ListView) findViewById(R.id.listView1);
    // Assign adapter to ListView
    listView.setAdapter(dataAdapter);


    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
public void onItemClick(AdapterView<?> parent, View view,
                        int position, long id) {
    // When clicked, show a toast with the TextView text
    UserModel country = (UserModel) parent.getItemAtPosition(position);
    Toast.makeText(getApplicationContext(),
    "Clicked on Row: " + country.getUserID(),
    Toast.LENGTH_LONG).show();
    }
    });

    }

private class MyCustomAdapter extends ArrayAdapter<UserModel> {

  private ArrayList<UserModel> userList;

  public MyCustomAdapter(Context context, int textViewResourceId,
                         ArrayList<UserModel> userList) {
    super(context, textViewResourceId, userList);
    this.userList = new ArrayList<UserModel>();
    this.userList.addAll(userList);
  }

  private class ViewHolder {
    TextView code;
    CheckBox name;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder holder = null;
    Log.v("ConvertView", String.valueOf(position));
    if (convertView == null) {
      LayoutInflater vi = (LayoutInflater)getSystemService(
          Context.LAYOUT_INFLATER_SERVICE);
      convertView = vi.inflate(R.layout.user_info_layout, null);

      holder = new ViewHolder();
      holder.code = (TextView) convertView.findViewById(R.id.des_name);
      holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
      convertView.setTag(holder);

      holder.name.setOnClickListener( new View.OnClickListener() {
        public void onClick(View v) {
          CheckBox cb = (CheckBox) v ;
          UserModel country = (UserModel) cb.getTag();
          Toast.makeText(getApplicationContext(),
              "Clicked on Checkbox: " + cb.getText() +
                  " is " + cb.isChecked(),
              Toast.LENGTH_LONG).show();
          country.setSelected(cb.isChecked());
          if(cb.isChecked()){
            //mInvites.add()
          }
        }
      });
    }
    else {
      holder = (ViewHolder) convertView.getTag();
    }
    UserModel user = userList.get(position);
    holder.name.setText(user.getUserID());
    holder.name.setChecked(user.isSelected());
    holder.name.setTag(user);
    return convertView;
  }
}
}
