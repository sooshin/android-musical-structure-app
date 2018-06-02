package com.example.android.musicplayer.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.example.android.musicplayer.Song;

@Entity(tableName = "song")
public class SongEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "song_title")
    private String songTitle;

    @ColumnInfo(name = "artist_name")
    private String artistName;

    @ColumnInfo(name = "song_length")
    private String songLength;

    @ColumnInfo(name = "album_art_id")
    private int albumArtId;

    @Ignore
    public SongEntry(String songTitle, String artistName, String songLength, int albumArtId) {
        this.songTitle = songTitle;
        this.artistName = artistName;
        this.songLength = songLength;
        this.albumArtId = albumArtId;
    }

    public SongEntry(int id, String songTitle, String artistName, String songLength, int albumArtId) {
        this.id = id;
        this.songTitle = songTitle;
        this.artistName = artistName;
        this.songLength = songLength;
        this.albumArtId = albumArtId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

   public String getSongTitle() {
        return songTitle;
   }

    public String getArtistName() {
        return artistName;
    }

   public String getSongLength() {
        return songLength;
   }

   public int getAlbumArtId() {
        return albumArtId;
   }

    public boolean hasAlbumArtId(){
        return albumArtId != Song.NO_IMAGE_PROVIDED;
    }
}
