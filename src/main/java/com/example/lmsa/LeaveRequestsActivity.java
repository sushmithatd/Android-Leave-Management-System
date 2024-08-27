package com.example.lmsa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class LeaveRequestsActivity extends AppCompatActivity {

    private ListView leaveRequestsListView;
    private List<String> leaveRequestsList;
    private LeaveRequestsAdapter leaveRequestsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_requests);

        leaveRequestsListView = findViewById(R.id.leaveRequestsListView);
        leaveRequestsList = getLeaveRequests(); // Get the leave requests from the Leave History

        leaveRequestsAdapter = new LeaveRequestsAdapter(this, leaveRequestsList);
        leaveRequestsListView.setAdapter(leaveRequestsAdapter);
    }

    private List<String> getLeaveRequests() {
        // Implement the logic to retrieve the leave requests from the Leave History
        // e.g., Fetch it from a database or shared preferences

        // For demonstration, let's assume we have some sample leave requests
        List<String> leaveRequests = new ArrayList<>();
        leaveRequests.add("Leave request 1");
        leaveRequests.add("Leave request 2");
        leaveRequests.add("Leave request 3");

        return leaveRequests;
    }

    private class LeaveRequestsAdapter extends ArrayAdapter<String> {

        public LeaveRequestsAdapter(Context context, List<String> leaveRequests) {
            super(context, 0, leaveRequests);
        }

        @NonNull
        @Override
        public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_leave_request, parent, false);
            }

            final String leaveRequest = getItem(position);

            TextView leaveRequestTextView = convertView.findViewById(R.id.leaveRequest_TextView);
            Button approveButton = convertView.findViewById(R.id.approve_Button);
            Button rejectButton = convertView.findViewById(R.id.reject_Button);

            leaveRequestTextView.setText(leaveRequest);

            // Set click listeners for the approve and reject buttons
            approveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle approve button click
                    // Remove the approved leave request from the list
                    remove(leaveRequest);
                    notifyDataSetChanged();

                    // Show a toast message for approval
                    Toast.makeText(getContext(), "Leave request approved", Toast.LENGTH_SHORT).show();

                    // Navigate to LeaveHistoryActivity
                    Intent intent = new Intent(LeaveRequestsActivity.this, LeaveHistoryActivity.class);
                    // Pass any necessary data to LeaveHistoryActivity
                    startActivity(intent);
                }
            });

            rejectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle reject button click
                    // No action needed, the leave request remains in the list

                    // Show a toast message for rejection
                    Toast.makeText(getContext(), "Leave request rejected", Toast.LENGTH_SHORT).show();
                }
            });

            return convertView;
        }
    }
}
