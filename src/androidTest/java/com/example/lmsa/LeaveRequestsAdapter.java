package com.example.lmsa;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

public class LeaveRequestsAdapter extends ArrayAdapter<String> {

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
                Intent intent = new Intent(getContext(), LeaveHistoryActivity.class);
                intent.putExtra("leaveReasons", leaveRequest);
                getContext().startActivity(intent);
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
