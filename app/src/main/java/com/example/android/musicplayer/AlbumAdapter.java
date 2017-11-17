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
 * {@link AlbumAdapter} is an {@link ArrayAdapter} that can provide the layout for each grid item
 * based on a data source, which is a list of {@link Song} objects.
 */
public class AlbumAdapter extends ArrayAdapter<Song> {

    /**
     * Create a new {@link AlbumAdapter} object.
     *
     * @param context is the current context (i.e. Activity) that the adapter is being created in.
     * @param songs is the list of {@link Song}s to be displayed.
     */
    public AlbumAdapter (Activity context, ArrayList<Song> songs) {
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
        View gridItemView = convertView;
        if(gridItemView == null) {
            gridItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.grid_item, parent, false);
        }

        // Get the {@link Song} object located at this position in the list
        Song currentSong = getItem(position);

        // Find the ImageView in the grid_item.xml layout with the ID image
        ImageView imageView = (ImageView) gridItemView.findViewById(R.id.image);
        // Check if an album art is provided for this song or not
        if(currentSong.hasAlbumArtId()) {
            // If an album art is available, display the provided album art based on the resource ID
            imageView.setImageResource(currentSong.getAlbumArtId());

        } else {
            // Otherwise display alternate image
            imageView.setImageResource(R.drawable.notes);
        }

        // Find the TextView in the grid_item.xml layout with the ID album_name_text_view
        TextView albumNameTextView = (TextView) gridItemView.findViewById(R.id.album_name_text_view);

        // No album name is needed in the ArtistsFragment.
        if(currentSong.getAlbumName() == null){
            // In this case, hide the albumNameTextView.
            albumNameTextView.setVisibility(View.GONE);
        }else{
            // Get the album name from the current Song object and
            // set this text on the album name TextView
            albumNameTextView.setText(currentSong.getAlbumName());


        }

        // Find the TextView in the grid_item.xml layout with the ID artist_text_view
        TextView artistTextView = (TextView) gridItemView.findViewById(R.id.artist_text_view);
        // Get the artist name from the current Song object and
        // set this text on the artist TextView
        artistTextView.setText(currentSong.getArtistName());

        // Return the whole grid item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the Grid View.
        return gridItemView;
    }
}
