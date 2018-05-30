package com.example.android.musicplayer;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.musicplayer.database.SongDatabase;
import com.example.android.musicplayer.database.SongEntry;

import java.util.List;

public class SongViewModel extends AndroidViewModel {

    private static final String TAG = SongViewModel.class.getSimpleName();

    private LiveData<List<SongEntry>> mSongs;

    /**
     * Constructs SongViewModel object
     * @param application
     */
    public SongViewModel(@NonNull Application application) {
        super(application);
        SongDatabase database = SongDatabase.getInstance(this.getApplication());
        Log.d(TAG, "Retrieves the songs from the database");
        mSongs = database.songDao().loadAllSongs();
    }

    public LiveData<List<SongEntry>> getSongs() {
        return mSongs;
    }
}
