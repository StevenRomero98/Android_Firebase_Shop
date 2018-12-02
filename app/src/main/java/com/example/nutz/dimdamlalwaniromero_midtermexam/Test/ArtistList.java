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

public class ArtistList extends ArrayAdapter<Artist> {
    private Activity context;
    List<Artist> artists;

    public ArtistList(Activity context, List<Artist> artists) {
        super(context, R.layout.test_layout_artist_list, artists);
        this.context = context;
        this.artists = artists;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.test_layout_artist_list, null, true);

        TextView ViewName = listViewItem.findViewById(R.id.ViewName);
        TextView ViewDesc = listViewItem.findViewById(R.id.ViewDesc);
        TextView ViewPrice = listViewItem.findViewById(R.id.ViewPrice);
        TextView ViewQty = listViewItem.findViewById(R.id.ViewQty);

        Artist artist = artists.get(position);
        ViewName.setText(artist.getName());
        ViewDesc.setText(artist.getDesc());
        ViewPrice.setText(String.valueOf(artist.getPrice()));
        ViewQty.setText(String.valueOf(artist.getQty()));

        return listViewItem;
    }
}