package com.example.ixithon.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
      // Return a PlaceholderFragment (defined as a static inner class below).
      return PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
      // Show 3 total pages.
      return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      switch (position) {
        case 0:
          return "SECTION 1";
        case 1:
          return "SECTION 2";
        case 2:
          return "SECTION 3";
      }
      return null;
    }
}
