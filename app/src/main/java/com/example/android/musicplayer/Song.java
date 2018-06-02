package com.example.android.musicplayer;

/**
 * {@link Song} represents a song title, artist name, song length, album art, and album name.
 */

public class Song {

    /** Song title for the song */
    private String mSongTitle;

    /** Artist name for the song */
    private String mArtistName;

    /** Song length for the song */
    private String mSongLength;

    /** Image resource ID of AlbumArt for the song */
    private int mAlbumArtId = NO_IMAGE_PROVIDED;

    /** Album name for the song*/
    private String mAlbumName;

    /** Constant value that represents no image was provided for this song */
    public static final int NO_IMAGE_PROVIDED = -1;

    /**
     * Create a new Song object.
     *
     * @param songTitle is song title for the song
     * @param artistName is artist's name
     * @param songLength is the length of the song
     * @param albumArtId is the image resource ID of AlbumArt
     * @param albumName is the album name for the song
     */
    public Song(String songTitle, String artistName, String songLength, int albumArtId, String albumName) {
        mSongTitle = songTitle;
        mArtistName = artistName;
        mSongLength = songLength;
        mAlbumArtId = albumArtId;
        mAlbumName = albumName;
    }

    /**
     * Create a new Song object.
     *
     * @param songTitle is song title for the song
     * @param artistName is artist's name
     * @param songLength is the length of the song
     * @param albumArtId is the image resource ID of AlbumArt
     */
    public Song(String songTitle, String artistName, String songLength, int albumArtId) {
        mSongTitle = songTitle;
        mArtistName = artistName;
        mSongLength = songLength;
        mAlbumArtId = albumArtId;
    }

    /**
     * Create a new Song object.
     *
     * @param artistName is artist's name
     * @param albumName is the album name for the song
     * @param albumArtId is the image resource ID of AlbumArt
     */
    public Song(String artistName, String albumName, int albumArtId) {
        mArtistName = artistName;
        mAlbumName = albumName;
        mAlbumArtId = albumArtId;
    }

    /**
     * Create a new Song object.
     *
     * @param artistName is artist's name
     * @param albumArtId is the image resource ID of AlbumArt
     */
    public Song(String artistName, int albumArtId) {
        mArtistName = artistName;
        mAlbumArtId = albumArtId;
    }

    /**
     * Get the song title of the song.
     */
    public String getSongTitle() {
        return mSongTitle;
    }

    /**
     * Get the artist's name of the song.
     */
    public String getArtistName() {
        return mArtistName;
    }

    /**
     * Get the length of the song.
     */
    public String getSongLength() {
        return mSongLength;
    }

    /**
     * Get the album art of the song.
     */
    public int getAlbumArtId() {
        return mAlbumArtId;
    }

    /**
     * Returns whether or not there is an Album art for this song.
     */
    public boolean hasAlbumArtId(){
        return mAlbumArtId != NO_IMAGE_PROVIDED;
    }

    /**
     * Get the album name of the song.
     */
    public String getAlbumName() {
        return mAlbumName;
    }

    @Override
    public String toString() {
        return "Song{" +
                "mSongTitle='" + mSongTitle + '\'' +
                ", mArtistName='" + mArtistName + '\'' +
                ", mSongLength='" + mSongLength + '\'' +
                ", mAlbumArtId=" + mAlbumArtId +
                ", mAlbumName='" + mAlbumName + '\'' +
                '}';
    }
}
