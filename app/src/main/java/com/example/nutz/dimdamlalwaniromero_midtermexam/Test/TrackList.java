package com.example.nutz.dimdamlalwaniromero_midtermexam.Test;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nutz.dimdamlalwaniromero_midtermexam.R;

import java.util.List;

/**
 * Created by Belal on 2/26/2017.
 */

public class TrackList extends ArrayAdapter<Track> {
    private Activity context;
    List<Track> tracks;

    public TrackList(Activity context, List<Track> tracks) {
        super(context, R.layout.test_layout_artist_list, tracks);
        this.context = context;
        this.tracks = tracks;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.test_layout_artist_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewRating = (TextView) listViewItem.findViewById(R.id.textViewGenre);

        Track track = tracks.get(position);
        textViewName.setText(track.getTrackName());
        textViewRating.setText(String.valueOf(track.getRating()));

        return listViewItem;
    }
}