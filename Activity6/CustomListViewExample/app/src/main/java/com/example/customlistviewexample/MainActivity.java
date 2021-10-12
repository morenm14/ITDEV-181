package com.example.customlistviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView productList;
    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productList = findViewById(R.id.productList);
        products = new ArrayList<>();

        products.add( new Product(
                "Dell Latitude 3500",
                "The world's most secure, most manageable and most reliable business-class laptops.",
                "Laptop",
                14500.99,
                true));

        products.add(new Product(
                "Acer Aspire 7",
                "Revolutionary convertible computers that feature powerful innovation and forward-thinking design.",
                "Laptop",
                12500.99,
                true));

        products.add( new Product(
                "SANDISK 16 GB Cruzer",
                "Low-cost, no-nonsense way of storing and transporting files.",
                "Memory",
                299.99,
                true));

        products.add( new Product(
                "Verbatim 1TB",
                "Verbatim's portable hard drive product offerings are exceptionally reliable and fashionably thin.",
                "HDD",
                1020.99,
                false));

        ProductAdapter adapter = new ProductAdapter(this, products);
        productList.setAdapter(adapter);

    }
}