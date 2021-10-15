package com.example.backgroundtasks;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listJSON;
    Button fetchDataBtn;
    ArrayList<String> todoList;
    ArrayAdapter<String> listAdapter;
    Handler myHandler = new Handler();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listJSON = findViewById(R.id.listJSON);
        fetchDataBtn = findViewById(R.id.fetchDataBtn);

        initializeList();

        fetchDataBtn.setOnClickListener(view -> {
            new FetchData().start();
        });
    }

    private void initializeList() {
        todoList = new ArrayList<>();
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoList);
        listJSON.setAdapter(listAdapter);
    }

    class FetchData extends Thread {
        String data = "";

       //background task
        @Override
        public void run() {

            //executes before background task
            myHandler.post(new Runnable() {
                @Override
                public void run() {

                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setMessage("Fetching Data");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                }
            });


            try {
                URL url = new URL("https://jsonplaceholder.typicode.com/todos");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while ((line = bufferedReader.readLine())!= null){
                    data = data + line;
                }

                if (!data.isEmpty()){


                    JSONArray todos = new JSONArray(data);

                    todoList.clear();

                    for (int i = 0; i < todos.length(); i++) {

                        JSONObject titles = todos.getJSONObject(i);
                        String todo = titles.getString("title");
                        todoList.add(todo);
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

           //runs after the background task is finished
            myHandler.post(new Runnable() {
                @Override
                public void run() {

                    if (progressDialog.isShowing()){
                        progressDialog.dismiss();
                        listAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
    }
}