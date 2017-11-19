package com.example.android.musicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * The AlbumsActivity is the activity that appears when a grid item is clicked on an {@link ArtistsFragment}.
 */
public class AlbumsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);

        // Create an list of songs
        final ArrayList<Song> songs = new ArrayList<Song>();

        songs.add(new Song(getString(R.string.ed_sheeran), getString(R.string.divide),
                R.drawable.ed_sheeran_divide));
        songs.add(new Song(getString(R.string.camila_cabello), getString(R.string.havana_album),
                R.drawable.camila_cabello_havana));
        songs.add(new Song(getString(R.string.imagine_dragones), getString(R.string.evolve),
                R.drawable.imagine_dragones_evolve));
        songs.add(new Song(getString(R.string.ed_sheeran), getString(R.string.x),
                R.drawable.ed_sheeran_multiply));
        songs.add(new Song(getString(R.string.taylor_swift), getString(R.string.reputation),
                R.drawable.taylor_swift_reputation));
        songs.add(new Song(getString(R.string.maroon_5), getString(R.string.red_pill_blues),
                R.drawable.maroon5_red_pill_blues));
        songs.add(new Song(getString(R.string.portugal_the_man), getString(R.string.woodstock),
                R.drawable.portugal_the_man_woodstock));
        songs.add(new Song(getString(R.string.sam_smith), getString(R.string.the_thrill_of_it_all),
                R.drawable.sam_smith_the_thrill_of_it_all));
        songs.add(new Song(getString(R.string.halsey), getString(R.string.hopeless_fountain_kingdom),
                -1));


        // Get an intent from the {@link ArtistsFragment}.
        Intent artistsIntent = getIntent();

        // Get an artist name from the intent
        String artistName = artistsIntent.getStringExtra(getString(R.string.artist_name));

        // Create an list of songs to search for album names that match the artist's name
        final ArrayList<Song> artistNameResult = new ArrayList<Song>();

        // Search for album names that match the artist's name
        for (int i = 0; i < songs.size(); i++) {
            if (artistName.equals( songs.get(i).getArtistName())) {
                artistNameResult.add(new Song(songs.get(i).getArtistName(), songs.get(i).getAlbumName(), songs.get(i).getAlbumArtId()));
            }
        }

        // Create an {@link AlbumAdapter}, whose data source is a list of artistNameResult.
        AlbumAdapter albumAdapter = new AlbumAdapter(this, artistNameResult);

        // Find the {@link GridView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link GridView} with the view ID called gridview, which is declared in the
        // activity_albums.xml layout file.
        GridView gridView = findViewById(R.id.gridview);

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
                Song song = artistNameResult.get(position);

                // Create a new intent to open the {@link SongsActivity}
                Intent songsIntent = new Intent(AlbumsActivity.this, SongsActivity.class);

                // Pass album name value to {@link SongsActivity}
                songsIntent.putExtra(getString(R.string.album_name), song.getAlbumName());

                // Start the new activity
                startActivity(songsIntent);
            }
        });
    }

    // Move to the previous screen when up button is clicked.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
