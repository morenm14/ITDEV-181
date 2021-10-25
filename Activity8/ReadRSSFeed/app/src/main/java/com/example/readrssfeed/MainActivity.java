package com.example.readrssfeed;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView rssFeed;
    Handler myHandler = new Handler();
    ProgressDialog progressDialog;
    ArrayList<String> titles;
    ArrayList<String> links;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titles = new ArrayList<>();
        links = new ArrayList<>();
        rssFeed = findViewById(R.id.rssFeed);

        rssFeed.setOnItemClickListener((adapterView, view, i, l) -> {
            Uri uri = Uri.parse(links.get(i));
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        new FetchData().start();
    }

    //creates input stream
    public InputStream getInputStream(URL url) throws IOException{
        try {
            return url.openConnection().getInputStream();

        }finally {
            url.openConnection().getInputStream().close();
        }
    }

   //background process
    class FetchData extends Thread{

        Exception exception = null;

        public void run(){

            //pre execute
            myHandler.post(() -> {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Fetching data from RSS");
                progressDialog.setCancelable(false);
                progressDialog.show();
            });

            //do in background
            try {
                URL url = new URL("https://www.thisisanfield.com/feed/");
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(getInputStream(url), "UTF-8");

                boolean  isItem = false;
                int eventType = parser.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT){
                    if (eventType == XmlPullParser.START_TAG){
                        switch (parser.getName()) {
                            case "item":
                                isItem = true;
                                break;
                            case "title":
                                if (isItem) {
                                    titles.add(parser.nextText());
                                }
                                break;
                            case "link":
                                if (isItem) {
                                    links.add(parser.nextText());
                                }
                                break;
                        }
                    }else if (eventType == XmlPullParser.END_TAG && parser.getName().equals("item")){
                        isItem = false;
                    }
                    eventType = parser.next();
                }
            } catch (XmlPullParserException | IOException e){
                exception =e;
            }

            //post Execute
            myHandler.post(() -> {

                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, titles);
                rssFeed.setAdapter(adapter);

                if (progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
            });
        }
    }
}