package com.example.surveytaker;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

public class UploadSurveysActivity extends AppCompatActivity {
    private String TAG = UploadSurveysActivity.class.getSimpleName();
    private Button buttonGoHome;
    private Button buttonUploadSurveys;
    Intent intent;
    DbHandler db = new DbHandler(this);
    String jsonInString;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_surveys);

        buttonGoHome = (Button) findViewById(R.id.btnGoHome);
        buttonGoHome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                intent = new Intent(UploadSurveysActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        buttonUploadSurveys = (Button) findViewById(R.id.btnUploadSurveys);
        buttonUploadSurveys.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                //intent = new Intent(UploadSurveysActivity.this,MainActivity.class);
                //startActivity(intent);

                jsonInString = getAllCompletedSurveyInstances();

                try {

                    String s1 = jsonInString;
                    Log.e(TAG, "Json parsing: " + jsonInString);
                    //s1 = s1.replace("{\"dataList\":[", "");
                    s1 = s1.replace("{\"dataList\":", "");
                    //String s2 = s1.replace("}]", "");
                   // String s2 = s1.replace("}", "");
                   // jsonInString = s2;
                    StringBuilder s2 = new StringBuilder(s1);
                    s2.replace(s2.length()-1, s2.length(), "");
                    //s2.toString();



                    jsonInString = s2.toString();
                    Log.e(TAG, "Json parsing: " + jsonInString);
                    new UploadSurveys().execute();

                } catch (Exception ex) {

                }
            }
        });


    }


    private class UploadSurveys extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected Void doInBackground(Void... arg0) {

            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "http://10.0.2.2:8000/ReceiveJSON/";



            sh.postServiceCall(url,  jsonInString);

           // Log.e(TAG, "Response from url: " + jsonStr);
          //  if (jsonStr != null) {
                //try {
               //     JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    //JSONArray surveys = jsonObj.getJSONArray("surveys");

                    // looping through All Surveys
                    //for (int i = 0; i < surveys.length(); i++) {
                    //    JSONObject c = surveys.getJSONObject(i);
                    //    String surveyid = c.getString("surveyid");
                     //   String surveyname = c.getString("surveyname");
                     //   String surveydescription = c.getString("surveydescription");

                     //   db.insertSurveyDetails(surveyid, surveyname, surveydescription);

                        // tmp hash map for single contact
                      //  HashMap<String, String> survey = new HashMap<>();

                        // adding each child node to HashMap key => value
                      //  survey.put("surveyid", surveyid);
                     //   survey.put("surveyname", surveyname);
                     //   survey.put("surveydescription", surveydescription);

                        // adding survey to survey list
                     //   surveyList.add(survey);
                 //   }
              //  } catch (final JSONException e) {
               //     Log.e(TAG, "Json parsing error: " + e.getMessage());
               //     runOnUiThread(new Runnable() {
               //         @Override
               //         public void run() {

               //         }
                //    });

            //    }

          //  } else {
          //      Log.e(TAG, "Couldn't get json from server.");
           //     runOnUiThread(new Runnable() {
           //         @Override
           //         public void run() {

            //        }
            //    });
           // }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

        }
    }



    public String getAllCompletedSurveyInstances() {


        // Cursor is loaded with data
        Cursor cursor = db.getAllCompletedSurveyInstances();



        ArrayList<Data> dataList = new ArrayList<Data>();

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                Data data = new Data();
                data.surveyid = cursor.getString(0);
                data.questionid = cursor.getString(1);
                data.userid = cursor.getString(2);
                data.surveyinstanceid = cursor.getString(3);
                data.answer = cursor.getString(4);

                // Add into the ArrayList here
                dataList.add(data);

            } while (cursor.moveToNext());

                   // Now create the object to be passed to GSON
            ListOfData mydataList = new ListOfData();
            mydataList.dataList = dataList;

            Gson gson = new Gson();
            String jsonInString = gson.toJson(mydataList);

            cursor.close();

            return jsonInString;


        }
return  jsonInString;
    }


}



