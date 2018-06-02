package com.example.android.musicplayer.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.musicplayer.AppExecutors;
import com.example.android.musicplayer.FavoriteViewModel;
import com.example.android.musicplayer.FavoriteViewModelFactory;
import com.example.android.musicplayer.R;
import com.example.android.musicplayer.database.SongDatabase;
import com.example.android.musicplayer.database.SongEntry;
import com.example.android.musicplayer.fragment.SongsFragment;

/**
 * The NowplayingActivity is the activity that appears when a list item is clicked on a {@link SongsActivity}
 * and a {@link SongsFragment}
 */
public class NowplayingActivity extends AppCompatActivity {

    private final String TAG = NowplayingActivity.class.getSimpleName();

    private String songTitle;
    private String artistName;
    private String songLength;
    private int albumArtId;

    private ImageButton favoriteImageButton;

    /** True if the song in Now playing Activity exist in the Favorite list, otherwise false */
    private boolean exist;
    /** Member variable for the SongEntry */
    private SongEntry mSongEntry;

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

        // Create a new SongEntry
        mSongEntry = new SongEntry(songTitle, artistName, songLength, albumArtId);
        // Set exist value to true or false whether the song exists in the Favorite list
        exist = existInFavorite();

        // Find the Favorite Image Button
        favoriteImageButton = findViewById(R.id.favorite_image_button);

        // Set a click listener on favorite image button
        favoriteImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFavoriteButtonClicked();
            }
        });

        // Navigate with the app icon in the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    /**
     * If the song is in the Favorite list, set favoriteImage to full heart image and return true.
     * Otherwise, set favoriteImage to border heart image and return false.
     */
    private boolean existInFavorite() {
        FavoriteViewModelFactory factory = new FavoriteViewModelFactory(mDb, songTitle);
        final FavoriteViewModel favoriteViewModel = ViewModelProviders.of(this, factory).get(FavoriteViewModel.class);

        favoriteViewModel.getSongEntry().observe(this, new Observer<SongEntry>() {
            @Override
            public void onChanged(@Nullable SongEntry songEntry) {
                if (favoriteViewModel.getSongEntry().getValue() == null) {
                    favoriteImageButton.setImageResource(R.drawable.ic_favorite_border);
                    exist = false;
                } else {
                    favoriteImageButton.setImageResource(R.drawable.ic_favorite_white_24dp);
                    exist = true;
                }
            }
        });
        return exist;
    }

    /**
     * Called when the "FavoriteImage" button is clicked.
     * If the song does not exist in the Favorite list, insert the
     * mSongEntry data into the database. Otherwise, delete the songEntry data from the database.
     */
    private void onFavoriteButtonClicked() {
        // Change image resource every time favorite image button is clicked
        if (!exist) {
            // Make Toast message that shows the song is added to Favorite
            Toast.makeText(NowplayingActivity.this, getString(R.string.added_to_favorite),Toast.LENGTH_SHORT).show();

            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    // Insert the mSongEntry to the SongDatabase by using the songDao
                    mDb.songDao().insertSong(mSongEntry);
                }
            });

        } else {
            // Make Toast message that shows the song is removed from Favorite
            Toast.makeText(NowplayingActivity.this, getString(R.string.removed_from_favorite), Toast.LENGTH_SHORT).show();

            FavoriteViewModelFactory factory = new FavoriteViewModelFactory(mDb, songTitle);
            FavoriteViewModel favoriteViewModel = ViewModelProviders.of(this, factory).get(FavoriteViewModel.class);
            final SongEntry songEntry = favoriteViewModel.getSongEntry().getValue();

            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    // Delete the songEntry from the SongDatabase by using the songDao
                    mDb.songDao().deleteSong(songEntry);
                }
            });
        }
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
