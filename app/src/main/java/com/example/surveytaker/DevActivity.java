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
    private Button buttonSaveTestSurveyRecordToDatabase;
    private EditText  EditTextSurveyID;
    private EditText EditTextSurveyName;
    private EditText EditTextSurveyDescription;


    //Question
    private Button buttonClearQuestionsTable;
    private Button buttonSaveTestQuestionRecordToDatabase;
    private EditText  EditTextQuestionSurveyID;
    private EditText EditTextQuestionID;
    private EditText EditTextQuestion;

        public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev);


        DbHandler db = new DbHandler(this);
        buttonGoHome = (Button) findViewById(R.id.btnGoHome);

        //Surveys Test Data
        buttonClearSurveysTable = (Button) findViewById(R.id.btnClearSurveysTable);
        buttonSaveTestSurveyRecordToDatabase = (Button) findViewById(R.id.btnSaveTestSurveyRecordToDatabase);
        EditTextSurveyID = (EditText) findViewById(R.id.txtSurveyID);
        EditTextSurveyName = (EditText) findViewById(R.id.txtSurveyName);
        EditTextSurveyDescription = (EditText) findViewById(R.id.txtSurveyDescription);

        //Questions Test Data
        buttonClearQuestionsTable = (Button) findViewById(R.id.btnClearQuestionsTable);
        buttonSaveTestQuestionRecordToDatabase = (Button) findViewById(R.id.btnSaveTestQuestionRecordToDatabase);
        EditTextQuestionSurveyID = (EditText) findViewById(R.id.txtQuestionSurveyID);
        EditTextQuestionID = (EditText) findViewById(R.id.txtQuestionID);
        EditTextQuestion = (EditText) findViewById(R.id.txtQuestion);

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

        buttonSaveTestSurveyRecordToDatabase.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                db.insertSurveyDetails(EditTextSurveyID.getText().toString(), EditTextSurveyName.getText().toString(), EditTextSurveyDescription.getText().toString());

            }
        });


        buttonClearQuestionsTable.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                db.deleteQuestions();

            }
        });

        buttonSaveTestQuestionRecordToDatabase.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                db.insertQuestionDetails(EditTextQuestionSurveyID.getText().toString(), EditTextQuestionID.getText().toString(), EditTextQuestion.getText().toString());

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
