package com.example.advancedwidgetdemo;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.widget.ArrayAdapter;
import android.widget.RemoteViews;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class NewsService extends Service {

    ArrayList<String> titles;
    ArrayList<String> links;
    Handler myHandler = new Handler();
    Intent intent;


    public NewsService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        links = new ArrayList<>();
        titles = new ArrayList<>();
        this.intent = intent;

        RemoteViews views = new RemoteViews("com.example.advancedwidgetdemo", R.layout.new_app_widget);
        views.setTextViewText(R.id.titleText, "Fetching Data...");

        AppWidgetManager.getInstance(getApplicationContext()).updateAppWidget(intent.getIntExtra("appWidgetId", 0),views);
        new FetchData().start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //creates input stream
    public InputStream getInputStream(URL url) throws IOException {
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
                Random random = new Random();
                int randomValue = random.nextInt(titles.size());

                RemoteViews views = new RemoteViews("com.example.advancedwidgetdemo", R.layout.new_app_widget);
                views.setTextViewText(R.id.titleText, titles.get(randomValue));

                Uri uri = Uri.parse(links.get(randomValue));

                Intent linkIntent = new Intent(Intent.ACTION_VIEW, uri);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0 , linkIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                views.setOnClickPendingIntent(R.id.titleText, pendingIntent);

                PendingIntent refreshPendingIntent = PendingIntent.getService(getApplicationContext(), 0 , intent, PendingIntent.FLAG_UPDATE_CURRENT);
                views.setOnClickPendingIntent(R.id.refresh, refreshPendingIntent);

                AppWidgetManager.getInstance(getApplicationContext()).updateAppWidget(intent.getIntExtra("appWidgetId",0), views);


            });
        }
    }
}