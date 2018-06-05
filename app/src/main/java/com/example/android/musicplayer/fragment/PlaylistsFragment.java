
package com.example.android.musicplayer.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.example.android.musicplayer.activity.SongsActivity;
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
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

        mSongs.add(new Song(getString(R.string.ed_sheeran), getString(R.string.divide),
                R.drawable.ed_sheeran_divide));
        mSongs.add(new Song(getString(R.string.camila_cabello), getString(R.string.havana_album),
                R.drawable.camila_cabello_havana));
        mSongs.add(new Song(getString(R.string.imagine_dragons), getString(R.string.evolve),
                R.drawable.imagine_dragons_evolve));
        mSongs.add(new Song(getString(R.string.ed_sheeran), getString(R.string.x),
                R.drawable.ed_sheeran_multiply));
        mSongs.add(new Song(getString(R.string.taylor_swift), getString(R.string.reputation),
                R.drawable.taylor_swift_reputation));
        mSongs.add(new Song(getString(R.string.maroon_5), getString(R.string.red_pill_blues),
                R.drawable.maroon5_red_pill_blues));
        mSongs.add(new Song(getString(R.string.portugal_the_man), getString(R.string.woodstock),
                R.drawable.portugal_the_man_woodstock));
        mSongs.add(new Song(getString(R.string.sam_smith), getString(R.string.the_thrill_of_it_all),
                R.drawable.sam_smith_the_thrill_of_it_all));
        mSongs.add(new Song(getString(R.string.halsey), getString(R.string.hopeless_fountain_kingdom),
                -1));

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
    public void onItemClickListener(int position) {
        // Get the {@link Song} object at the given position the user clicked on
        Song song = mSongs.get(position);

        // Create a new intent to open the {@link SongsActivity}
        Intent songsIntent = new Intent(getActivity(), SongsActivity.class);

        // Pass album name value to {@link SongsActivity}
        songsIntent.putExtra(getString(R.string.album_name), song.getAlbumName());

        // Start the new activity
        startActivity(songsIntent);
    }
}
