package com.example.android.musicplayer.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.musicplayer.adapter.AlbumAdapter;
import com.example.android.musicplayer.activity.AlbumsActivity;
import com.example.android.musicplayer.R;
import com.example.android.musicplayer.Song;

import java.util.ArrayList;

/**
 * The ArtistsFragment is a simple {@link Fragment} subclass that shows artists and album art.
 */
public class ArtistsFragment extends Fragment {

    public ArtistsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_artists, container, false);

        // Create an list of songs
        final ArrayList<Song> songs = new ArrayList<Song>();

        songs.add(new Song(getString(R.string.ed_sheeran), R.drawable.ed_sheeran_divide));
        songs.add(new Song(getString(R.string.camila_cabello), R.drawable.camila_cabello_havana));
        songs.add(new Song(getString(R.string.imagine_dragones), R.drawable.imagine_dragones_evolve));
        songs.add(new Song(getString(R.string.taylor_swift), R.drawable.taylor_swift_reputation));
        songs.add(new Song(getString(R.string.maroon_5), R.drawable.maroon5_red_pill_blues));
        songs.add(new Song(getString(R.string.portugal_the_man), R.drawable.portugal_the_man_woodstock));
        songs.add(new Song(getString(R.string.sam_smith), R.drawable.sam_smith_the_thrill_of_it_all));
        songs.add(new Song(getString(R.string.halsey), -1));

        // Create an {@link AlbumAdapter}, whose data source is a list of songs.
        AlbumAdapter albumAdapter = new AlbumAdapter(getActivity(), songs);

        // Find the {@link GridView} object in the view hierarchy of the {@link fragment}.
        GridView gridView = rootView.findViewById(R.id.gridview);

        // Make the {@link GridView} use the {@link AlbumAdapter} we created above, so that the
        // {@link GridView} will display grid items for each {@link Song} in the list.
        gridView.setAdapter(albumAdapter);

        // Set the background resource
        gridView.setBackgroundResource(R.drawable.background);

        // Set a click listener on gridView
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // The code in this method will be executed when the grid item is clicked on.
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Get the {@link Song} object at the given position the user clicked on
                Song song = songs.get(position);

                // Create a new intent to open the {@link AlbumsActivity}
                Intent albumsIntent = new Intent(getActivity(), AlbumsActivity.class);

                // Pass artist name value to {@link AlbumsActivity}
                albumsIntent.putExtra(getString(R.string.artist_name), song.getArtistName());

                // Start the new activity
                startActivity(albumsIntent);
            }
        });
        return rootView;
    }
}

