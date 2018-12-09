package com.example.nutz.dimdamlalwaniromero_midtermexam.Cart;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nutz.dimdamlalwaniromero_midtermexam.Admin.Product;
import com.example.nutz.dimdamlalwaniromero_midtermexam.R;

import java.util.List;

public class CartProductList extends ArrayAdapter<AddToCart> {
    private Activity context;
    List<AddToCart> products;

    public CartProductList(Activity context, List<AddToCart> products) {
        super(context, R.layout.cart_product_list, products);
        this.context = context;
        this.products = products;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.cart_product_list, null, true);

        TextView ViewName = listViewItem.findViewById(R.id.ViewName);
        TextView ViewPrice = listViewItem.findViewById(R.id.ViewPrice);
        TextView ViewQty = listViewItem.findViewById(R.id.ViewQty);
        TextView ViewItemPrice = listViewItem.findViewById(R.id.ViewItemPrice);

        AddToCart product = products.get(position);
        ViewName.setText(product.getName());
        ViewPrice.setText("$" + String.valueOf(product.getPrice()));
        ViewQty.setText("Quantity: " + String.valueOf(product.getQty()));
        ViewItemPrice.setText("Item Total: $" + String.valueOf(product.getItemPrice()));

        return listViewItem;
    }
}