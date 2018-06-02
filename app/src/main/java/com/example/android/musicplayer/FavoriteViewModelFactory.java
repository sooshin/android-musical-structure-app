package com.example.android.musicplayer;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.android.musicplayer.database.SongDatabase;

public class FavoriteViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final SongDatabase mDb;
    private final String mSongTitle;

    public FavoriteViewModelFactory(SongDatabase songDatabase, String songTitle) {
        mDb = songDatabase;
        mSongTitle = songTitle;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new FavoriteViewModel(mDb, mSongTitle);
    }
}
