package com.example.android.musicplayer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * In the {@link NowplayingActivity}, when user tap the favorite image button,
 * the song is saved in this FavoriteActivity.
 */

public class FavoriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        // When user tap the favorite image button, get data from NowplayingActivity.
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(FavoriteActivity.this);

        String songTitle = prefs.getString(getString(R.string.favorite_song_title), "");
        String artistName = prefs.getString(getString(R.string.favorite_artist_name), "");
        String songLength = prefs.getString(getString(R.string.favorite_song_length), "");
        int albumArtId = prefs.getInt(getString(R.string.favorite_album_art_id), -1);

        //Create an list of favorite songs
        ArrayList<Song> favoriteSongs = new ArrayList<Song>();

        // Add new song based on the data from NowplayingActivity.
        favoriteSongs.add(new Song(songTitle,artistName, songLength, albumArtId));

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        ListView listView = (ListView) findViewById(R.id.list);

        // Set the background resource
        listView.setBackgroundResource(R.drawable.background);

        // If Favorite track is empty, there is no need to set SongAdapter to the listView.
        if (!artistName.equals("")) {
            // Create an {@link SongAdapter}, whose data source is a list of favortieSongs.
            SongAdapter songAdapter = new SongAdapter(this, favoriteSongs);

            // Make the {@link ListView} use the {@link SongAdapter} we created above, so that the
            // {@link ListView} will display list items for each {@link Song} in the list.
            listView.setAdapter(songAdapter);
        }
    }
}