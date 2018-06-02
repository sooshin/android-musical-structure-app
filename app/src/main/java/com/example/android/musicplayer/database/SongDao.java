package com.example.android.musicplayer.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface SongDao {

    @Query("SELECT * FROM song")
    LiveData<List<SongEntry>> loadAllSongs();

    @Insert
    void insertSong(SongEntry songEntry);

    @Delete
    void deleteSong(SongEntry songEntry);

    @Query("SELECT * FROM song WHERE song_title = :songTitle")
    LiveData<SongEntry> loadSongByTitle(String songTitle);
}
