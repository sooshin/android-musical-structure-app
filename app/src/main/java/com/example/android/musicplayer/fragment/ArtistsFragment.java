package com.example.android.musicplayer.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.musicplayer.R;
import com.example.android.musicplayer.Song;
import com.example.android.musicplayer.activity.AlbumsActivity;
import com.example.android.musicplayer.adapter.SongAdapter;

import java.util.ArrayList;

/**
 * The ArtistsFragment is a simple {@link Fragment} subclass that shows artists and album art.
 */
public class ArtistsFragment extends Fragment implements SongAdapter.ItemClickListener {

    private ArrayList<Song> mSongs;

    public ArtistsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_songs, container, false);

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
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerview);

        // Set LinearLayoutManager which is responsible for measuring and positioning item views within
        // a RecyclerView into a linear list.
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // Use this setting to improve performance if you know that changes in content do not
        // change the child layout size in the RecyclerView
        recyclerView.setHasFixedSize(true);

        // The SongAdapter is responsible for displaying each item in the list
        SongAdapter songAdapter = new SongAdapter(getContext(), mSongs, this);
        recyclerView.setAdapter(songAdapter);

        return rootView;
    }

    @Override
    public void onItemClickListener(int itemId) {
        // Get the {@link Song} object at the given position the user clicked on
        Song song = mSongs.get(itemId);

        // Create a new intent to open the {@link AlbumsActivity}
        Intent albumsIntent = new Intent(getActivity(), AlbumsActivity.class);

        // Pass artist name value to {@link AlbumsActivity}
        albumsIntent.putExtra(getString(R.string.artist_name), song.getArtistName());

        // Start the new activity
        startActivity(albumsIntent);
    }
}

