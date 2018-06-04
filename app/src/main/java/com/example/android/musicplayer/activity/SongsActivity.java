package com.example.android.musicplayer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.android.musicplayer.R;
import com.example.android.musicplayer.Song;
import com.example.android.musicplayer.adapter.SongAdapter;

import java.util.ArrayList;

/**
 * The SongsActivity is the activity that appears when a grid item is clicked on an {@link AlbumsActivity}.
 */

public class SongsActivity extends AppCompatActivity implements SongAdapter.ItemClickListener{

    private ArrayList<Song> mAlbumNameResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        // Create an list of songs
        final ArrayList<Song> songs = new ArrayList<>();

        songs.add(new Song(getString(R.string.shape_of_you), getString(R.string.ed_sheeran),
                getString(R.string.length_shape_of_you), R.drawable.ed_sheeran_divide, getString(R.string.divide)));
        songs.add(new Song(getString(R.string.perfect), getString(R.string.ed_sheeran),
                getString(R.string.length_perfect), R.drawable.ed_sheeran_divide, getString(R.string.divide)));
        songs.add(new Song(getString(R.string.nina), getString(R.string.ed_sheeran),
                getString(R.string.length_nina), R.drawable.ed_sheeran_multiply, getString(R.string.x)));
        songs.add(new Song(getString(R.string.havana), getString(R.string.camila_cabello),
                getString(R.string.length_havana), R.drawable.camila_cabello_havana, getString(R.string.havana_album)));
        songs.add(new Song(getString(R.string.thunder), getString(R.string.imagine_dragones),
                getString(R.string.length_thunder), R.drawable.imagine_dragons_evolve, getString(R.string.evolve)));
        songs.add(new Song(getString(R.string.call_it_what_you_want), getString(R.string.taylor_swift),
                getString(R.string.length_call_it_what_you_want), R.drawable.taylor_swift_reputation, getString(R.string.reputation)));
        songs.add(new Song(getString(R.string.what_lovers_do), getString(R.string.maroon_5),
                getString(R.string.length_what_lovers_do), R.drawable.maroon5_red_pill_blues, getString(R.string.red_pill_blues)));
        songs.add(new Song(getString(R.string.feel_it_still), getString(R.string.portugal_the_man),
                getString(R.string.length_feel_it_still), R.drawable.portugal_the_man_woodstock, getString(R.string.woodstock)));
        songs.add(new Song(getString(R.string.too_good_at_goodbyes), getString(R.string.sam_smith),
                getString(R.string.length_too_good_at_goodbyes), R.drawable.sam_smith_the_thrill_of_it_all, getString(R.string.the_thrill_of_it_all)));
        songs.add(new Song(getString(R.string.bad_at_love), getString(R.string.halsey),
                getString(R.string.length_bad_at_love), -1, getString(R.string.hopeless_fountain_kingdom)));
        songs.add(new Song(getString(R.string.galway_girl), getString(R.string.ed_sheeran),
                getString(R.string.length_galway_girl), R.drawable.ed_sheeran_divide,getString(R.string.divide)));
        songs.add(new Song(getString(R.string.happier), getString(R.string.ed_sheeran),
                getString(R.string.length_happier), R.drawable.ed_sheeran_divide,getString(R.string.divide)));
        songs.add(new Song(getString(R.string.new_man), getString(R.string.ed_sheeran),
                getString(R.string.length_new_man), R.drawable.ed_sheeran_divide,getString(R.string.divide)));
        songs.add(new Song(getString(R.string.hearts_do_not_break_around_here), getString(R.string.ed_sheeran),
                getString(R.string.length_hearts_do_not_break_around_here), R.drawable.ed_sheeran_divide,getString(R.string.divide)));

        // Get intent from the {@link AlbumsActivity}
        Intent albumsIntent = getIntent();

        // Get an album name from the intent
        String albumName = albumsIntent.getStringExtra(getString(R.string.album_name));

        // Create an list of songs to search for artist name that matches the album name
        mAlbumNameResult = new ArrayList<>();

        // Search for artist name that matches the album name
        for (int i = 0; i < songs.size(); i++) {
            if (albumName.equals(songs.get(i).getAlbumName())) {
                mAlbumNameResult.add(new Song(songs.get(i).getSongTitle(),
                        songs.get(i).getArtistName(),
                        songs.get(i).getSongLength(),
                        songs.get(i).getAlbumArtId(),
                        songs.get(i).getAlbumName()));
            }
        }

        // Get the reference to our RecyclerView from xml. This allows us to do things like set
        // the adapter of the RecyclerView and toggle the visibility.
        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        // Set LinearLayoutManager which is responsible for measuring and positioning item views within
        // a RecyclerView into a linear list.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Use this setting to improve performance if you know that changes in content do not
        // change the child layout size in the RecyclerView
        recyclerView.setHasFixedSize(true);

        // The SongAdapter is responsible for displaying each item in the list
        SongAdapter songAdapter = new SongAdapter(this, mAlbumNameResult, this);
        recyclerView.setAdapter(songAdapter);

        // Navigate with the app icon in the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onItemClickListener(int itemId) {
        // Get the {@link Song} object at the given position the user clicked on
        Song song = mAlbumNameResult.get(itemId);

        // Create a new intent to open the {@link Nowplaying Activity}
        Intent nowIntent = new Intent(this, NowplayingActivity.class);

        // Pass value to {@link NowplayingActivity}
        nowIntent.putExtra(getString(R.string.song_title), song.getSongTitle());
        nowIntent.putExtra(getString(R.string.artist_name), song.getArtistName());
        nowIntent.putExtra(getString(R.string.song_length), song.getSongLength());
        nowIntent.putExtra(getString(R.string.album_art_id), song.getAlbumArtId());

        // Start the new activity
        startActivity(nowIntent);
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
