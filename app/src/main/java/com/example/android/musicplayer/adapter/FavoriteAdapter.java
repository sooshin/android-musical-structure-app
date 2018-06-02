package com.example.android.musicplayer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.musicplayer.R;
import com.example.android.musicplayer.database.SongEntry;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.SongViewHolder> {

    /**
     * An on-click handler that we've defined to make it easy for an Activity to interface with our RecyclerView
     */
    final private ItemClickListener mItemClickListener;

    private Context mContext;
    private List<SongEntry> mSongEntries;

    /**
     * The interface that receives onClick messages.
     */
    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }

    /**
     * Constructor for FavoriteAdapter
     *
     * @param context
     * @param listener Listener for list item clicks
     */
    public FavoriteAdapter(Context context, ItemClickListener listener) {
        mContext = context;
        mItemClickListener = listener;
    }

    /**
     *
     * @param parent that these ViewHolders are contained within.
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        SongEntry songEntry = mSongEntries.get(position);
        holder.songTitleView.setText(songEntry.getSongTitle());
        holder.artistNameView.setText(songEntry.getArtistName());
        holder.songLengthView.setText(songEntry.getSongLength());
        // Check if an album art is provided for this song or not
        if(songEntry.hasAlbumArtId()) {
            // If an album art is available, display the provided album art based on the resource ID
            holder.albumArtImageView.setImageResource(songEntry.getAlbumArtId());
        } else {
            // Otherwise display alternate image
            holder.albumArtImageView.setImageResource(R.drawable.notes);
        }
    }


    /**
     * Returns the number of items to display.
     *
     * @return The number of items available
     */
    @Override
    public int getItemCount() {
        if (mSongEntries == null) {
            return 0;
        } else {
            return mSongEntries.size();
        }
    }

    public void setSongs(List<SongEntry> songEntries) {
        mSongEntries = songEntries;
        notifyDataSetChanged();
    }

    public List<SongEntry> getSongs() {
        return mSongEntries;
    }

    class SongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView songTitleView;
        private TextView artistNameView;
        private TextView songLengthView;
        private ImageView albumArtImageView;

        public SongViewHolder(View itemView) {
            super(itemView);

            songTitleView = itemView.findViewById(R.id.song_title_text_view);
            artistNameView = itemView.findViewById(R.id.artist_text_view);
            songLengthView = itemView.findViewById(R.id.song_length_text_view);
            albumArtImageView = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
        }

        /**
         * Called whenever a user clicks on a item in the list
         * @param v The View that was clicked
         */
        @Override
        public void onClick(View v) {
            int itemId = mSongEntries.get(getAdapterPosition()).getId();
            mItemClickListener.onItemClickListener(itemId);
        }
    }
}
