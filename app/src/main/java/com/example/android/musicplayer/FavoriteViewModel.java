package com.example.android.musicplayer;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.musicplayer.database.SongDatabase;
import com.example.android.musicplayer.database.SongEntry;

public class FavoriteViewModel extends ViewModel {

    private LiveData<SongEntry> mSongEntry;

    public FavoriteViewModel(SongDatabase songDatabase, String songTitle) {
        mSongEntry = songDatabase.songDao().loadSongByTitle(songTitle);
    }

    public LiveData<SongEntry> getSongEntry() {
        return mSongEntry;
    }
}
