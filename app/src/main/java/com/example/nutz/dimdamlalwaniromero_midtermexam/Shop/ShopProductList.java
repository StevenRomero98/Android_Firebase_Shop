package com.example.nutz.dimdamlalwaniromero_midtermexam.Shop;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nutz.dimdamlalwaniromero_midtermexam.Admin.Product;
import com.example.nutz.dimdamlalwaniromero_midtermexam.R;

import java.util.List;

public class ShopProductList extends ArrayAdapter<Product> {
    private Activity context;
    List<Product> products;

    public ShopProductList(Activity context, List<Product> products) {
        super(context, R.layout.shop_product_list, products);
        this.context = context;
        this.products = products;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.shop_product_list, null, true);

        TextView ViewName = listViewItem.findViewById(R.id.ViewName);
        TextView ViewDesc = listViewItem.findViewById(R.id.ViewDesc);
        TextView ViewPrice = listViewItem.findViewById(R.id.ViewPrice);
        TextView ViewQty = listViewItem.findViewById(R.id.ViewQty);

        Product product = products.get(position);
        ViewName.setText(product.getName());
        ViewDesc.setText(product.getDesc());
        ViewPrice.setText("$" + String.valueOf(product.getPrice()));
        ViewQty.setText(String.valueOf(product.getQty()));

        return listViewItem;
    }
}