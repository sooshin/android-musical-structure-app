package com.example.android.musicplayer.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

@Database(entities =  {SongEntry.class}, version = 1, exportSchema = false)
public abstract class SongDatabase extends RoomDatabase {

    private static final String LOG_TAG = SongDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "musicplayer";
    private static SongDatabase sInstance;

    public static SongDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creates new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        SongDatabase.class, SongDatabase.DATABASE_NAME).build();
            }
        }
        Log.d(LOG_TAG, "Gets the database instance");
        return sInstance;
    }

    public abstract SongDao songDao();
}
