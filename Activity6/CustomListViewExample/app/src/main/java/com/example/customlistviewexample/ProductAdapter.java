package com.example.customlistviewexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {
    private  final Context context;
    private final ArrayList<Product> list;

    public ProductAdapter(Context context, ArrayList<Product> list){
        super(context, R.layout.row_layout, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_layout,parent,false);
        //View rowView = inflater.inflate(R.layout.row_layout,parent,false);

        TextView productName = rowView.findViewById(R.id.productName);
        TextView productPrice = rowView.findViewById(R.id.productPrice);
        TextView productDescription = rowView.findViewById(R.id.productDescription);

        ImageView imageProduct =  rowView.findViewById(R.id.imageProduct);
        ImageView imageSale =  rowView.findViewById(R.id.imageSale);

        productName.setText(list.get(position).getName());
        productPrice.setText("R" + list.get(position).getPrice());
        productDescription.setText(list.get(position).getDescription());

        if (list.get(position).getSale()){
            imageSale.setImageResource(R.mipmap.on_sale_foreground);
        }else{
            imageSale.setImageResource(R.mipmap.best_price_foreground);
        }

        if (list.get(position).getType().equals("Laptop")){
            imageProduct.setImageResource(R.mipmap.laptop_foreground);
        }else if (list.get(position).getType().equals("Memory")){
            imageProduct.setImageResource(R.mipmap.memory_foreground);
        }else if (list.get(position).getType().equals("Screen")){
            imageProduct.setImageResource(R.mipmap.screen_foreground);
        }else{
            imageProduct.setImageResource(R.mipmap.hdd_foreground);
        }

        return rowView;
    }
}
