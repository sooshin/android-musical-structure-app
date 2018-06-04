package com.example.android.musicplayer.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;

import com.example.android.musicplayer.AppExecutors;
import com.example.android.musicplayer.R;
import com.example.android.musicplayer.SongViewModel;
import com.example.android.musicplayer.adapter.FavoriteAdapter;
import com.example.android.musicplayer.database.SongDatabase;
import com.example.android.musicplayer.database.SongEntry;

import java.util.List;

/**
 * In the {@link NowplayingActivity}, when user tap the favorite image button,
 * the song is saved in this FavoriteActivity.
 */

public class FavoriteActivity extends AppCompatActivity implements FavoriteAdapter.ItemClickListener{

    /** SongDatabase variable */
    private SongDatabase mDb;

    /** References to RecyclerView and Adapter */
    private RecyclerView mRecyclerView;
    private FavoriteAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        // Get the reference to our RecyclerView from xml. This allows us to do things like set
        // the adapter of the RecyclerView and toggle the visibility.
        mRecyclerView = findViewById(R.id.recyclerview);

        // Set LinearLayoutManager which is responsible for measuring and positioning item views within
        // a RecyclerView into a linear list.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Use this setting to improve performance if you know that changes in content do not
        // change the child layout size in the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // The FavoriteAdapter is responsible for displaying each item in the list
        mAdapter = new FavoriteAdapter(this, this);
        mRecyclerView.setAdapter(mAdapter);

        /*
         Add a touch helper to the RecyclerView to recognize when a user swipes to delete an item.
         An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
         and uses callbacks to signal when a user is performing these actions.
         */
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            // Called when a user swipes left or right on a ViewHolder
            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        int position = viewHolder.getAdapterPosition();
                        List<SongEntry> songEntries = mAdapter.getSongs();
                        mDb.songDao().deleteSong(songEntries.get(position));
                    }
                });
            }
        }).attachToRecyclerView(mRecyclerView);

        mDb = SongDatabase.getInstance(getApplicationContext());
        // Setup ViewModel to cache data. The ViewModel allows data to survive to configuration changes
        setupViewModel();

        // Navigate with the app icon in the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setupViewModel() {
        SongViewModel viewModel = ViewModelProviders.of(this).get(SongViewModel.class);
        viewModel.getSongs().observe(this, new Observer<List<SongEntry>>() {

            @Override
            public void onChanged(@Nullable List<SongEntry> songEntries) {
                mAdapter.setSongs(songEntries);
            }
        });
    }

    @Override
    public void onItemClickListener(int position) {
        List<SongEntry> songEntries = mAdapter.getSongs();
        SongEntry songEntry = songEntries.get(position);

        Intent intent = new Intent(FavoriteActivity.this, NowplayingActivity.class);

        // Pass value to {@link NowplayingActivity}
        intent.putExtra(getString(R.string.song_title), songEntry.getSongTitle());
        intent.putExtra(getString(R.string.artist_name), songEntry.getArtistName());
        intent.putExtra(getString(R.string.song_length), songEntry.getSongLength());
        intent.putExtra(getString(R.string.album_art_id), songEntry.getAlbumArtId());

        startActivity(intent);
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