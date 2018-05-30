package com.example.android.musicplayer.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.musicplayer.R;
import com.example.android.musicplayer.Song;
import com.example.android.musicplayer.SongViewModel;
import com.example.android.musicplayer.adapter.SongAdapter;
import com.example.android.musicplayer.database.SongDatabase;
import com.example.android.musicplayer.database.SongEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * In the {@link NowplayingActivity}, when user tap the favorite image button,
 * the song is saved in this FavoriteActivity.
 */

public class FavoriteActivity extends AppCompatActivity {

    /** SongDatabase variable */
    private SongDatabase mDb;

    private ListView mListView;
    private SongAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

//        // When user tap the favorite image button, get data from NowplayingActivity.
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(FavoriteActivity.this);

//        String songTitle = prefs.getString(getString(R.string.favorite_song_title), "");
//        String artistName = prefs.getString(getString(R.string.favorite_artist_name), "");
//        String songLength = prefs.getString(getString(R.string.favorite_song_length), "");
//        int albumArtId = prefs.getInt(getString(R.string.favorite_album_art_id), -1);

        //Create an list of favorite songs
        final ArrayList<Song> favoriteSongs = new ArrayList<Song>();
        mDb = SongDatabase.getInstance(getApplicationContext());

        mAdapter = new SongAdapter(this, favoriteSongs);
        setupViewModel();

        // Add new song based on the data from NowplayingActivity.
//        favoriteSongs.add(new Song(songTitle,artistName, songLength, albumArtId));

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        mListView = findViewById(R.id.list);

        // Set the background resource
        mListView.setBackgroundResource(R.drawable.background);

        // If Favorite track is empty, there is no need to set SongAdapter to the listView.
//        if (!artistName.equals("")) {
//            // Create an {@link SongAdapter}, whose data source is a list of favortieSongs.
//            SongAdapter songAdapter = new SongAdapter(this, favoriteSongs);
//
//            // Make the {@link ListView} use the {@link SongAdapter} we created above, so that the
//            // {@link ListView} will display list items for each {@link Song} in the list.
//            listView.setAdapter(songAdapter);
//        }

        // Set a click listener on listView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Get the {@link Song} object at the given position the user clicked on
                Song song = favoriteSongs.get(position);

                // Create a new intent to open the {@link Nowplaying Activity}
                Intent intent = new Intent(FavoriteActivity.this, NowplayingActivity.class);

                // Pass value to {@link NowplayingActivity}
                intent.putExtra(getString(R.string.song_title), song.getSongTitle());
                intent.putExtra(getString(R.string.artist_name), song.getArtistName());
                intent.putExtra(getString(R.string.song_length), song.getSongLength());
                intent.putExtra(getString(R.string.album_art_id), song.getAlbumArtId());

                // Start the new activity
                startActivity(intent);
            }
        });

        // Navigate with the app icon in the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setupViewModel() {
        SongViewModel viewModel = ViewModelProviders.of(this).get(SongViewModel.class);
        viewModel.getSongs().observe(this, new Observer<List<SongEntry>>() {

            @Override
            public void onChanged(@Nullable List<SongEntry> songEntries) {

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