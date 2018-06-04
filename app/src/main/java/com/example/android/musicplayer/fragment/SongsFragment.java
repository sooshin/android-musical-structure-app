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

import com.example.android.musicplayer.R;
import com.example.android.musicplayer.Song;
import com.example.android.musicplayer.activity.NowplayingActivity;
import com.example.android.musicplayer.adapter.SongAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongsFragment extends Fragment implements SongAdapter.ItemClickListener{

    ArrayList<Song> mSongs;
    public SongsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_songs, container, false);

        // Create an list of songs
        mSongs = new ArrayList<>();

        mSongs.add(new Song(getString(R.string.shape_of_you), getString(R.string.ed_sheeran),
                getString(R.string.length_shape_of_you), R.drawable.ed_sheeran_divide));
        mSongs.add(new Song(getString(R.string.perfect), getString(R.string.ed_sheeran),
                getString(R.string.length_perfect), R.drawable.ed_sheeran_divide));
        mSongs.add(new Song(getString(R.string.nina), getString(R.string.ed_sheeran),
                getString(R.string.length_nina), R.drawable.ed_sheeran_multiply));
        mSongs.add(new Song(getString(R.string.havana), getString(R.string.camila_cabello),
                getString(R.string.length_havana), R.drawable.camila_cabello_havana));
        mSongs.add(new Song(getString(R.string.thunder), getString(R.string.imagine_dragones),
                getString(R.string.length_thunder), R.drawable.imagine_dragones_evolve));
        mSongs.add(new Song(getString(R.string.call_it_what_you_want), getString(R.string.taylor_swift),
                getString(R.string.length_call_it_what_you_want), R.drawable.taylor_swift_reputation));
        mSongs.add(new Song(getString(R.string.what_lovers_do), getString(R.string.maroon_5),
                getString(R.string.length_what_lovers_do), R.drawable.maroon5_red_pill_blues));
        mSongs.add(new Song(getString(R.string.feel_it_still), getString(R.string.portugal_the_man),
                getString(R.string.length_feel_it_still), R.drawable.portugal_the_man_woodstock));
        mSongs.add(new Song(getString(R.string.too_good_at_goodbyes), getString(R.string.sam_smith),
                getString(R.string.length_too_good_at_goodbyes), R.drawable.sam_smith_the_thrill_of_it_all));
        mSongs.add(new Song(getString(R.string.bad_at_love), getString(R.string.halsey),
                getString(R.string.length_bad_at_love), -1));
        mSongs.add(new Song(getString(R.string.galway_girl), getString(R.string.ed_sheeran),
                getString(R.string.length_galway_girl), R.drawable.ed_sheeran_divide));
        mSongs.add(new Song(getString(R.string.happier), getString(R.string.ed_sheeran),
                getString(R.string.length_happier), R.drawable.ed_sheeran_divide));
        mSongs.add(new Song(getString(R.string.new_man), getString(R.string.ed_sheeran),
                getString(R.string.length_new_man), R.drawable.ed_sheeran_divide));
        mSongs.add(new Song(getString(R.string.hearts_do_not_break_around_here), getString(R.string.ed_sheeran),
                getString(R.string.length_hearts_do_not_break_around_here), R.drawable.ed_sheeran_divide));


        // Get the reference to our RecyclerView from xml. This allows us to do things like set
        // the adapter of the RecyclerView and toggle the visibility.
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerview);

        // Set LinearLayoutManager which is responsible for measuring and positioning item views within
        // a RecyclerView into a linear list.
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        // Use this setting to improve performance if you know that changes in content do not
        // change the child layout size in the RecyclerView
        recyclerView.setHasFixedSize(true);

        // The SongAdapter is responsible for displaying each item in the list
        SongAdapter adapter = new SongAdapter(this.getContext(), mSongs, this);
        recyclerView.setAdapter(adapter);

        return rootView;
    }


    @Override
    public void onItemClickListener(int itemId) {
        // Get the {@link Song} object at the given position the user clicked on
        Song song = mSongs.get(itemId);

        // Create a new intent to open the {@link Nowplaying Activity}
        Intent nowIntent = new Intent(getActivity(), NowplayingActivity.class);

        // Pass value to {@link NowplayingActivity}
        nowIntent.putExtra(getString(R.string.song_title), song.getSongTitle());
        nowIntent.putExtra(getString(R.string.artist_name), song.getArtistName());
        nowIntent.putExtra(getString(R.string.song_length), song.getSongLength());
        nowIntent.putExtra(getString(R.string.album_art_id), song.getAlbumArtId());

        // Start the new activity
        startActivity(nowIntent);
    }
}
