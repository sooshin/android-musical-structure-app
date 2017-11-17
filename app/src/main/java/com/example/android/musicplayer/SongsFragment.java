package com.example.android.musicplayer;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongsFragment extends Fragment {

    public SongsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_songs, container, false);

        // Create an list of songs
        final ArrayList<Song> songs = new ArrayList<Song>();

        songs.add(new Song(getString(R.string.shape_of_you), getString(R.string.ed_sheeran),
                getString(R.string.length_shape_of_you), R.drawable.ed_sheeran_divide));
        songs.add(new Song(getString(R.string.perfect), getString(R.string.ed_sheeran),
                getString(R.string.length_perfect), R.drawable.ed_sheeran_divide));
        songs.add(new Song(getString(R.string.nina), getString(R.string.ed_sheeran),
                getString(R.string.length_nina), R.drawable.ed_sheeran_multiply));
        songs.add(new Song(getString(R.string.havana), getString(R.string.camila_cabello),
                getString(R.string.length_havana), R.drawable.camila_cabello_havana));
        songs.add(new Song(getString(R.string.thunder), getString(R.string.imagine_dragones),
                getString(R.string.length_thunder), R.drawable.imagine_dragones_evolve));
        songs.add(new Song(getString(R.string.call_it_what_you_want), getString(R.string.taylor_swift),
                getString(R.string.length_call_it_what_you_want), R.drawable.taylor_swift_reputation));
        songs.add(new Song(getString(R.string.what_lovers_do), getString(R.string.maroon_5),
                getString(R.string.length_what_lovers_do), R.drawable.maroon5_red_pill_blues));
        songs.add(new Song(getString(R.string.feel_it_still), getString(R.string.portugal_the_man),
                getString(R.string.length_feel_it_still), R.drawable.portugal_the_man_woodstock));
        songs.add(new Song(getString(R.string.too_good_at_goodbyes), getString(R.string.sam_smith),
                getString(R.string.length_too_good_at_goodbyes), R.drawable.sam_smith_the_thrill_of_it_all));
        songs.add(new Song(getString(R.string.bad_at_love), getString(R.string.halsey),
                getString(R.string.length_bad_at_love), -1));
        songs.add(new Song(getString(R.string.galway_girl), getString(R.string.ed_sheeran),
                getString(R.string.length_galway_girl), R.drawable.ed_sheeran_divide));
        songs.add(new Song(getString(R.string.happier), getString(R.string.ed_sheeran),
                getString(R.string.length_happier), R.drawable.ed_sheeran_divide));
        songs.add(new Song(getString(R.string.new_man), getString(R.string.ed_sheeran),
                getString(R.string.length_new_man), R.drawable.ed_sheeran_divide));
        songs.add(new Song(getString(R.string.hearts_do_not_break_around_here), getString(R.string.ed_sheeran),
                getString(R.string.length_hearts_do_not_break_around_here), R.drawable.ed_sheeran_divide));

        // Create an {@link SongAdapter}, whose data source is a list of songs.
        SongAdapter songAdapter = new SongAdapter(getActivity(), songs);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link SongAdapter} we created above
        listView.setAdapter(songAdapter);

        // Set the background resource
        listView.setBackgroundResource(R.drawable.background);

        // Set a click listener on listView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Get the {@link Song} object at the given position the user clicked on
                Song song = songs.get(position);

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
        });
        return rootView;
    }

}
