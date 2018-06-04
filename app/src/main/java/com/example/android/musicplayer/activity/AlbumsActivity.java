package com.example.android.musicplayer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.android.musicplayer.R;
import com.example.android.musicplayer.Song;
import com.example.android.musicplayer.adapter.SongAdapter;
import com.example.android.musicplayer.fragment.ArtistsFragment;

import java.util.ArrayList;

/**
 * The AlbumsActivity is the activity that appears when a grid item is clicked on an {@link ArtistsFragment}.
 */
public class AlbumsActivity extends AppCompatActivity implements SongAdapter.ItemClickListener {

    private ArrayList<Song> mSongs;
    private ArrayList<Song> mArtistNameResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        // Create an list of songs
        mSongs = new ArrayList<>();

        mSongs.add(new Song(getString(R.string.ed_sheeran), getString(R.string.divide),
                R.drawable.ed_sheeran_divide));
        mSongs.add(new Song(getString(R.string.camila_cabello), getString(R.string.havana_album),
                R.drawable.camila_cabello_havana));
        mSongs.add(new Song(getString(R.string.imagine_dragones), getString(R.string.evolve),
                R.drawable.imagine_dragones_evolve));
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


        // Get an intent from the {@link ArtistsFragment}.
        Intent artistsIntent = getIntent();

        // Get an artist name from the intent
        String artistName = artistsIntent.getStringExtra(getString(R.string.artist_name));

        // Create an list of songs to search for album names that match the artist's name
        mArtistNameResult = new ArrayList<>();

        // Search for album names that match the artist's name
        for (int i = 0; i < mSongs.size(); i++) {
            if (artistName.equals( mSongs.get(i).getArtistName())) {
                mArtistNameResult.add(new Song(mSongs.get(i).getArtistName(), mSongs.get(i).getAlbumName(), mSongs.get(i).getAlbumArtId()));
            }
        }

        // Get the reference to our RecyclerView from xml. This allows us to do things like set
        // the adapter of the RecyclerView and toggle the visibility.
        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        // Set LinearLayoutManager which is responsible for measuring and positioning item views within
        // a RecyclerView into a linear list.
        recyclerView.setLayoutManager(new GridLayoutManager(AlbumsActivity.this, 2));

        // Use this setting to improve performance if you know that changes in content do not
        // change the child layout size in the RecyclerView
        recyclerView.setHasFixedSize(true);

        // The SongAdapter is responsible for displaying each item in the list
        SongAdapter songAdapter = new SongAdapter(this, mArtistNameResult, this);
        recyclerView.setAdapter(songAdapter);

        // Navigate with the app icon in the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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

    @Override
    public void onItemClickListener(int itemId) {
        // Get the {@link Song} object at the given position the user clicked on
        Song song = mArtistNameResult.get(itemId);

        // Create a new intent to open the {@link SongsActivity}
        Intent songsIntent = new Intent(AlbumsActivity.this, SongsActivity.class);

        // Pass album name value to {@link SongsActivity}
        songsIntent.putExtra(getString(R.string.album_name), song.getAlbumName());

        // Start the new activity
        startActivity(songsIntent);
    }
}
