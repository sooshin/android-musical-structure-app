package com.example.android.musicplayer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * {@link SongAdapter} is an {@link ArrayAdapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link Song} objects.
 */
public class SongAdapter extends ArrayAdapter<Song> {

    public SongAdapter (Activity context, ArrayList<Song> songs) {
        super(context, 0, songs);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Song} object located at this position in the list
        Song currentSong = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID song_title_text_view
        TextView songTitleTextView = listItemView.findViewById(R.id.song_title_text_view);
        // Get the song title from the current Song object and
        // set this text on the song title TextView
        songTitleTextView.setText(currentSong.getSongTitle());

        // Find the TextView in the list_item.xml layout with the ID artist_text_view
        TextView artistTextView = listItemView.findViewById(R.id.artist_text_view);
        // Get the artist name from the current Song object and
        // set this text on the Artist TextView
        artistTextView.setText(currentSong.getArtistName());

        // Find the TextView in the list_item.xml layout with the ID song_length_text_view
        TextView songLengthTextView = listItemView.findViewById(R.id.song_length_text_view);
        // Get the song length from the current Song object and
        // set this text on the song length TextView
        songLengthTextView.setText(currentSong.getSongLength());

        // Find the ImageView in the list_item.xml layout with the ID image
        ImageView imageView = listItemView.findViewById(R.id.image);
        // Check if an album art is provided for this song or not
        if(currentSong.hasAlbumArtId()) {
            // If an album art is available, display the provided album art based on the resource ID
            imageView.setImageResource(currentSong.getAlbumArtId());

        } else {
            // Otherwise display alternate image
            imageView.setImageResource(R.drawable.notes);
        }

        // Return the whole list item layout (containing 3 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
