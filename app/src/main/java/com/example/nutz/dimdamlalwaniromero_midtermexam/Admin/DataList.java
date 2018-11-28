package com.example.nutz.dimdamlalwaniromero_midtermexam.Admin;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nutz.dimdamlalwaniromero_midtermexam.R;

import java.util.List;

public class DataList {
    private Activity context;
    List<Data>dataList;

    public DataList(Activity context, List<Data> dataList) {
        //super(context, R.layout.test_layout_artist_list, dataList);
        this.context = context;
        this.dataList = dataList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_data_list, null, true);

        TextView ViewName = listViewItem.findViewById(R.id.ViewName);
        TextView ViewDesc = listViewItem.findViewById(R.id.ViewDesc);

        Data data = dataList.get(position);
            ViewName.setText(data.getName());
            ViewDesc.setText(data.getDesc());

        return listViewItem;
    }
}