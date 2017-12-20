package com.example.android.musicplayer.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.musicplayer.R;
import com.example.android.musicplayer.Song;
import com.example.android.musicplayer.fragment.ArtistsFragment;
import com.example.android.musicplayer.fragment.PlaylistsFragment;
import com.example.android.musicplayer.fragment.SongsFragment;

/**
 * {@link CategoryAdapter} is a {@link FragmentPagerAdapter} that can provide the layout for
 * each list item based on a data source which is a list of {@link Song} objects.
 */
public class CategoryAdapter extends FragmentPagerAdapter {

    /** Context of the app */
    private Context mContext;

    /**
     * Create a new {@link CategoryAdapter} object.
     *
     * @param context is the context of the app.
     * @param fm is the fragment manager that will keep each fragment's state in the adapter
     * across swipes.
     */
    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    /**
     * Return the {@link Fragment} that should be displayed for the given page number.
     */
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new PlaylistsFragment();
        } else if (position == 1){
            return new ArtistsFragment();
        } else{
            return new SongsFragment();
        }
    }

    /**
     * Return the total number of pages.
     */
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
       if (position == 0) {
           return mContext.getString(R.string.category_playlists);
       } else if (position == 1) {
           return mContext.getString(R.string.category_artists);
       } else {
           return mContext.getString(R.string.category_songs);
       }
    }
}
