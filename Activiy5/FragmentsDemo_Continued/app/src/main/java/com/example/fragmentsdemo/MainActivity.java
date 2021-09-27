package com.example.fragmentsdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements ListFrag.Listener {

    String [] descriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        descriptions = getResources().getStringArray(R.array.descriptions);
        //portrait mode
        if (findViewById(R.id.layout_portrait) != null){

            FragmentManager manager = this.getSupportFragmentManager();
            manager.beginTransaction()
                    .show(manager.findFragmentById(R.id.listFrag))
                    .commit();
        }
        //landscape
        if (findViewById(R.id.layout_land) != null){

            FragmentManager manager = this.getSupportFragmentManager();
            manager.beginTransaction()
                    .show(manager.findFragmentById(R.id.detailFrag))
                    .show(manager.findFragmentById(R.id.listFrag))
                    .commit();

        }
    }

    @Override
    public void clickedItem(int index) {

        Bundle bundle = new Bundle();
        bundle.putString("myKey", descriptions[index]);
        getSupportFragmentManager().setFragmentResult("requestKey", bundle);

        if (findViewById(R.id.layout_portrait) != null) {

            FragmentManager manager = this.getSupportFragmentManager();
            manager.beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.listFrag, DetailFrag.class, null)
                    .addToBackStack(null)
                    .commit();
        }


    }
}