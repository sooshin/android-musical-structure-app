package com.example.android.musicplayer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_nowplaying.xml layout file
        setContentView(R.layout.activity_nowplaying);

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
        TextView songTitleTextView = (TextView) findViewById(R.id.song_title);
        // Set the songTitle on that TextView
        songTitleTextView.setText(songTitle);

        // Find the View that shows artist name
        TextView artistNameTextView = (TextView) findViewById(R.id.artist_name);
        // Set the artistName on that TextView
        artistNameTextView.setText(artistName);

        // Find the View that shows the length of the song
        TextView songLengthTextView =(TextView) findViewById(R.id.song_length);
        // Set the songLength on that TextView
        songLengthTextView.setText(songLength);

        // Find the ImageView that shows album art image
        ImageView imageView = (ImageView) findViewById(R.id.image);
        // Check if an album art is provided for this song or not
        if (albumArtId != -1) {
            // If an album art is available, display the provided album art based on the resource ID
            imageView.setImageResource(albumArtId);
        } else {
            // Otherwise display alternate image
            imageView.setImageResource(R.drawable.notes);
        }

        // Find the ImageButton and set image resource to show ic_favorite_border
        favoriteImageButton = (ImageButton) findViewById(R.id.favorite_image_button);
        favoriteImageButton.setImageResource(R.drawable.ic_favorite_border);

        prefs = PreferenceManager.getDefaultSharedPreferences(NowplayingActivity.this);

        // Set a click listener on favorite image button
        favoriteImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Change image resource when favorite image button is clicked
                favoriteImageButton.setImageResource(R.drawable.ic_favorite_white_24dp);

                SharedPreferences.Editor editor = prefs.edit();

                // Store string songTitle value
                editor.putString(getString(R.string.favorite_song_title), songTitle);
                // Store string artistName value
                editor.putString(getString(R.string.favorite_artist_name), artistName);
                // Store string songLength value
                editor.putString(getString(R.string.favorite_song_length), songLength);
                // Store int albumArtId value
                editor.putInt(getString(R.string.favorite_album_art_id), albumArtId);

                // Save the changes into SharedPreferences
                editor.commit();

                // Make Toast message that shows the song is added to Favorite
                Toast.makeText(NowplayingActivity.this, getString(R.string.added_to_favorite),Toast.LENGTH_SHORT).show();
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
