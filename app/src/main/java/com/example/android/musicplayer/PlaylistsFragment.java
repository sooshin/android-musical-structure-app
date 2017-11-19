
package com.example.android.musicplayer;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaylistsFragment extends Fragment {

    public PlaylistsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_playlists, container, false);

        // Find the View that shows the All Musics
        TextView allMusicsTextView = rootView.findViewById(R.id.all_musics_text);



        // Find the View that shows the Favorite
        TextView favoriteTextView = rootView.findViewById(R.id.favorite_text);

        // Set a click listener on favoriteTextView
        favoriteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link FavoriteActivity}
                Intent favoriteIntent = new Intent(getActivity(), FavoriteActivity.class);

                // Start the new activity
                startActivity(favoriteIntent);
            }
        });
        return rootView;
    }

}
