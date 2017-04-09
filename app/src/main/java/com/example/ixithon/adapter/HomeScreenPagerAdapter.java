package com.example.ixithon.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ixithon.fragment.MyTripFrag;
import com.example.ixithon.fragment.PlaceholderFragment;

/**
 * Created by abhishek on 8/4/17.
 */

  /**
   * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
   * one of the sections/tabs/pages.
   */
  public class HomeScreenPagerAdapter extends FragmentPagerAdapter {

    public HomeScreenPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      // getItem is called to instantiate the fragment for the given page.
      // Return a ModeSelectionFragment (defined as a static inner class below).


      if (position == 0 ){
        return PlaceholderFragment.newInstance(position + 1);
      }else{
        return MyTripFrag.newInstance("","");
      }
    }

    @Override
    public int getCount() {
      // Show 3 total pages.
      return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      switch (position) {
        case 0:
          return "Plan your Holiday";
        case 1:
          return "My Trips";
      }
      return null;
    }
}
