package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Provides the appropriate {@link Fragment} for a a pager view.
 */
public class CategoryFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context c;

    public CategoryFragmentPagerAdapter(FragmentManager fragmentManager, Context context){
        super(fragmentManager);
        c = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ColorsFragment();
        } else if(position == 1){
            return new FamilyMembersFragment();
        } else if(position == 2){
            return new NumbersFragment();
        } else {
            return new PhrasesFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return c.getString(R.string.category_colors);
        } else if(position == 1){
            return c.getString(R.string.category_family);
        } else if(position == 2){
            return c.getString(R.string.category_numbers);
        } else {
            return c.getString(R.string.category_phrases);
        }

    }
}

