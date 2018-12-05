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

public class ProductList extends ArrayAdapter<Product> {
    private Activity context;
    List<Product> products;

    public ProductList(Activity context, List<Product> products) {
        super(context, R.layout.test_layout_artist_list, products);
        this.context = context;
        this.products = products;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.test_layout_artist_list, null, true);

        TextView ViewName = listViewItem.findViewById(R.id.ViewName);
        TextView ViewDesc = listViewItem.findViewById(R.id.ViewDesc);
        TextView ViewPrice = listViewItem.findViewById(R.id.ViewPrice);
        TextView ViewQty = listViewItem.findViewById(R.id.ViewQty);

        Product product = products.get(position);
        ViewName.setText(product.getName());
        ViewDesc.setText(product.getDesc());
        ViewPrice.setText(String.valueOf(product.getPrice()));
        ViewQty.setText(String.valueOf(product.getQty()));

        return listViewItem;
    }
}