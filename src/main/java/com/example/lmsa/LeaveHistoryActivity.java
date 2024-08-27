package com.example.lmsa;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


    public class LeaveHistoryActivity extends AppCompatActivity {

        private ArrayList<String> leaveReasonsList = new ArrayList<>();
        private ListView leaveReasonListView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_leave_history);

            leaveReasonListView = findViewById(R.id.leaveReasonListView);

            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                leaveReasonsList = extras.getStringArrayList("leaveReasons");

                // Create an ArrayAdapter to populate the ListView
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1, leaveReasonsList);

                leaveReasonListView.setAdapter(adapter);
            }
        }

        public void addLeaveReason(String reason) {
            leaveReasonsList.add(reason);
        }
    }

