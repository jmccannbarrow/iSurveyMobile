package com.example.surveytaker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class DevActivity extends AppCompatActivity {

    private Button buttonGoHome;
    private Button buttonClearSurveyInstanceTable;
    Intent intent;

    //Survey
    private Button buttonClearSurveysTable;


    //Question
    private Button buttonClearQuestionsTable;


        public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev);


        DbHandler db = new DbHandler(this);
        buttonGoHome = (Button) findViewById(R.id.btnGoHome);

        //Surveys Test Data
        buttonClearSurveysTable = (Button) findViewById(R.id.btnClearSurveysTable);


        //Questions Test Data
        buttonClearQuestionsTable = (Button) findViewById(R.id.btnClearQuestionsTable);

        buttonClearSurveyInstanceTable = (Button) findViewById(R.id.btnClearSurveyInstanceTable);

        buttonGoHome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                intent = new Intent(DevActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });


        buttonClearSurveysTable.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                db.deleteSurveys();

            }
        });



        buttonClearQuestionsTable.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                db.deleteQuestions();

            }
        });


            buttonClearSurveyInstanceTable.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    db.deleteSurveyInstances();

                }
            });


    }
}
