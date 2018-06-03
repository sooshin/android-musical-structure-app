
package com.example.android.musicplayer.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.musicplayer.R;
import com.example.android.musicplayer.Song;
import com.example.android.musicplayer.activity.FavoriteActivity;
import com.example.android.musicplayer.adapter.PlaylistAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaylistsFragment extends Fragment implements PlaylistAdapter.ItemClickListener{

    private ArrayList<Song> mSongs;

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

        // Create an list of songs
        mSongs = new ArrayList<>();

        mSongs.add(new Song(getString(R.string.ed_sheeran), R.drawable.ed_sheeran_divide));
        mSongs.add(new Song(getString(R.string.camila_cabello), R.drawable.camila_cabello_havana));
        mSongs.add(new Song(getString(R.string.imagine_dragones), R.drawable.imagine_dragones_evolve));
        mSongs.add(new Song(getString(R.string.taylor_swift), R.drawable.taylor_swift_reputation));
        mSongs.add(new Song(getString(R.string.maroon_5), R.drawable.maroon5_red_pill_blues));
        mSongs.add(new Song(getString(R.string.portugal_the_man), R.drawable.portugal_the_man_woodstock));
        mSongs.add(new Song(getString(R.string.sam_smith), R.drawable.sam_smith_the_thrill_of_it_all));
        mSongs.add(new Song(getString(R.string.halsey), -1));

        // Get the reference to our RecyclerView from xml. This allows us to do things like set
        // the adapter of the RecyclerView and toggle the visibility.
        RecyclerView recyclerView = rootView.findViewById(R.id.rv_playlist);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        // Set LinearLayoutManager which is responsible for measuring and positioning item views within
        // a RecyclerView into a linear list.
        recyclerView.setLayoutManager(layoutManager);

        // Use this setting to improve performance if you know that changes in content do not
        // change the child layout size in the RecyclerView
        recyclerView.setHasFixedSize(true);

        // The PlaylistAdapter is responsible for displaying each item in the list
        PlaylistAdapter playlistAdapter = new PlaylistAdapter(getContext(), mSongs, this);
        recyclerView.setAdapter(playlistAdapter);

        return rootView;
    }

    @Override
    public void onItemClickListener(int itemId) {

    }
}
