package com.example.android.musicplayer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.musicplayer.R;
import com.example.android.musicplayer.Song;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder> {

    /**
     * An on-click handler that we've defined to make it easy for an Activity to interface with our RecyclerView
     */
    final private ItemClickListener mItemClickListener;

    private Context mContext;
    private List<Song> mSongs;

    /**
     * The interface that receives onClick messages.
     */
    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }

    /**
     * Constructor for PlaylistAdapter
     *
     * @param context
     * @param listener Listener for list item clicks
     */
    public PlaylistAdapter(Context context, List<Song> songs, ItemClickListener listener) {
        mContext = context;
        mSongs = songs;
        mItemClickListener = listener;
    }

    @NonNull
    @Override
    public PlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.playlist_item, parent, false);
        return new PlaylistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistViewHolder holder, int position) {
        Song song = mSongs.get(position);

        // Check if an album art is provided for this song or not
        if(song.hasAlbumArtId()) {
            // If an album art is available, display the provided album art based on the resource ID
            holder.albumArtImageView.setImageResource(song.getAlbumArtId());
        } else {
            // Otherwise display alternate image
            holder.albumArtImageView.setImageResource(R.drawable.notes);
        }
        holder.albumNameTextView.setText(song.getAlbumName());
        holder.artistTextView.setText(song.getArtistName());
    }

    @Override
    public int getItemCount() {
        if (mSongs == null) {
            return 0;
        }
        return mSongs.size();
    }

    class PlaylistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView albumArtImageView;
        private CardView cardView;
        private TextView albumNameTextView;
        private TextView artistTextView;

        public PlaylistViewHolder(View itemView) {
            super(itemView);

            albumArtImageView = itemView.findViewById(R.id.image);
            cardView = itemView.findViewById(R.id.cardView);
            albumNameTextView = itemView.findViewById(R.id.tv_playlist_album_name);
            artistTextView = itemView.findViewById(R.id.tv_playlist_artist);
            itemView.setOnClickListener(this);
            cardView.setOnClickListener(this);
        }

        /**
         * Called whenever a user clicks on a item in the list
         * @param v The View that was clicked
         */
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mItemClickListener.onItemClickListener(position);
        }
    }
}
