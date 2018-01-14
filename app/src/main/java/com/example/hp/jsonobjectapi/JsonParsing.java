package com.example.hp.jsonobjectapi;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;

public class JsonParsing extends AppCompatActivity {
TextView output;
    Button Json;
    String JsonObject;
    String URL = "http://serviceapi.skholingua.com/open-feeds/simple_json.php";

    //  ArrayList<JsonArray> jsonarrays;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_parsing);
        // jsonarrays = new ArrayList<>();
        JsonObject = "{ \"student_db\":[ {\"Name\":\"Vasuki\" ,\"id\":\"222\", \"email_id\":\"vasuki@gmail.com\"}, { \"Name\":\"Vaishnavi\", \"id\":\"333\", \"email_id\":\"vasuki@gmail.com\"} ]}";

        output = (TextView) findViewById(R.id.output);
        Json = (Button) findViewById(R.id.json);

//String datatobeparsed="Click on button to parse JSON \n \n JSON DATA:\n\n"+JsonObject;
        //output.setText(datatobeparsed);


        Json.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncTask().execute();


                // String outputdata = "";

                JSONObject jsonresponse;


                try {
                    jsonresponse = new JSONObject(JsonObject);
                    JSONArray array = jsonresponse.optJSONArray("student_db");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj1 = array.getJSONObject(i);
                        String name = obj1.getString("Name");
                        int id1 = obj1.getInt("id");
                        String email = obj1.getString("email_id");

                        output.setText("Student details: " + String.valueOf(obj1));

                        Log.e("Name:", name);
                        Log.e("Id:", id1 + "");
                        Log.e("Email:", email);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        });
    }
        class AsyncTask extends android.os.AsyncTask<String,String,JSONObject> {
            ProgressDialog progressDialog;
            String name;
            String version;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();


                progressDialog = new ProgressDialog(JsonParsing.this);
                // progressDialog.setIndeterminate(false);
                progressDialog.setMessage("Getting Data");
                // progressDialog.setCancelable(true);
                progressDialog.show();
                // output.setText("Getting Data");
            }

            @Override
            protected JSONObject doInBackground(String... strings) {



                String data = HttpConnection.getData(URL);

                JSONObject jsonresponse;
                try {

                    jsonresponse = new JSONObject(data);
                    name = jsonresponse.getString("name");
                    version = jsonresponse.getString("version");

                        /*JSONArray array = jsonresponse.optJSONArray("student_db");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject obj1 = array.getJSONObject(i);
                            String name = obj1.getString("Name");
                            int id1 = obj1.getInt("id");
                            String email = obj1.getString("email_id");


                            Log.e("Name:", name);
                            Log.e("Id:", id1 + "");
                            Log.e("Email:", email);

                        }*/
                } catch (JSONException e) {
                    e.printStackTrace();


                }


                return null;
            }


            @Override
            protected void onPostExecute(JSONObject jsonObject) {
                super.onPostExecute(jsonObject);
                progressDialog.dismiss();

                Log.e("Name:",name);
                Log.e("Version:",version);
            }

        }}
