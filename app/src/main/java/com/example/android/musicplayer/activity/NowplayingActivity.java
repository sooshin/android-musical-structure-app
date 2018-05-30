package com.example.android.musicplayer.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.musicplayer.AppExecutors;
import com.example.android.musicplayer.R;
import com.example.android.musicplayer.database.SongDatabase;
import com.example.android.musicplayer.database.SongEntry;
import com.example.android.musicplayer.fragment.SongsFragment;

/**
 * The NowplayingActivity is the activity that appears when a list item is clicked on a {@link SongsActivity}
 * and a {@link SongsFragment}
 */
public class NowplayingActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    private String songTitle;
    private String artistName;
    private String songLength;
    private int albumArtId;

    private ImageButton favoriteImageButton;
    private boolean full = false;

    /** SongDatabase variable */
    private SongDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_nowplaying.xml layout file
        setContentView(R.layout.activity_nowplaying);

        mDb = SongDatabase.getInstance(getApplicationContext());

        // Get an intent from the {@link SongsFragment}
        Intent songsIntent = getIntent();

        // Get a song title from the intent
        songTitle = songsIntent.getStringExtra(getString(R.string.song_title));
        // Get an artist name from the intent
        artistName = songsIntent.getStringExtra(getString(R.string.artist_name));
        // Get a song length from the intent
        songLength = songsIntent.getStringExtra(getString(R.string.song_length));
        // Get a album art Id from the intent
        albumArtId = songsIntent.getIntExtra(getString(R.string.album_art_id), -1);

        // Find the View that shows song title
        TextView songTitleTextView = findViewById(R.id.song_title);
        // Set the songTitle on that TextView
        songTitleTextView.setText(songTitle);

        // Find the View that shows artist name
        TextView artistNameTextView = findViewById(R.id.artist_name);
        // Set the artistName on that TextView
        artistNameTextView.setText(artistName);

        // Find the View that shows the length of the song
        TextView songLengthTextView = findViewById(R.id.song_length);
        // Set the songLength on that TextView
        songLengthTextView.setText(songLength);

        // Find the ImageView that shows album art image
        ImageView imageView = findViewById(R.id.image);
        // Check if an album art is provided for this song or not
        if (albumArtId != -1) {
            // If an album art is available, display the provided album art based on the resource ID
            imageView.setImageResource(albumArtId);
        } else {
            // Otherwise display alternate image
            imageView.setImageResource(R.drawable.notes);
        }

        // Find the ImageButton and set image resource to show ic_favorite_border
        favoriteImageButton = findViewById(R.id.favorite_image_button);
        favoriteImageButton.setImageResource(R.drawable.ic_favorite_border);

        // Set a click listener on favorite image button
        favoriteImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Change image resource every time favorite image button is clicked
                if (!full) {
                    favoriteImageButton.setImageResource(R.drawable.ic_favorite_white_24dp);
                    full = true;
                    // Make Toast message that shows the song is added to Favorite
                    Toast.makeText(NowplayingActivity.this, getString(R.string.added_to_favorite),Toast.LENGTH_SHORT).show();

                    final SongEntry songEntry = new SongEntry(songTitle, artistName, songLength, albumArtId);
                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            // Insert the songEntry to the SongDatabase by using the songDao
                            mDb.songDao().insertSong(songEntry);
                        }
                    });

                } else {
                    favoriteImageButton.setImageResource(R.drawable.ic_favorite_border);
                    full = false;
                    // Make Toast message that shows the song is removed from Favorite
                    Toast.makeText(NowplayingActivity.this, getString(R.string.removed_from_favorite), Toast.LENGTH_SHORT).show();

                    final SongEntry songEntry = new SongEntry(songTitle, artistName, songLength, albumArtId);
                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            // Delete the songEntry from the SongDatabase by using the songDao
                            mDb.songDao().deleteSong(songEntry);
                        }
                    });
                }
            }
        });

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
}
