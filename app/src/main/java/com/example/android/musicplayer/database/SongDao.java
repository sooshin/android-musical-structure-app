package com.example.android.musicplayer.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface SongDao {

    @Query("SELECT * FROM song")
    List<SongEntry> loadAllSongs();

    @Insert
    void insertSong(SongEntry songEntry);

    @Delete
    void deleteSong(SongEntry songEntry);
}
