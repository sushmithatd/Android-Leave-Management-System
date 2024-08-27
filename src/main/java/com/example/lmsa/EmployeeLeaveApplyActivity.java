package com.example.lmsa;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EmployeeLeaveApplyActivity extends AppCompatActivity {

    private RadioGroup leaveTypeRadioGroup;
    private RadioButton selectedLeaveType;
    private EditText reasonEditText;
    private EditText startDateEditText;
    private EditText endDateEditText;
    private Button applyLeaveButton;
    private Button viewLeaveHistoryButton;

    private DatePickerDialog startDatePickerDialog;
    private DatePickerDialog endDatePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_leave_apply);

        leaveTypeRadioGroup = findViewById(R.id.leaveTypeRadioGroup);
        reasonEditText = findViewById(R.id.reasonEditText);
        startDateEditText = findViewById(R.id.startDatePickerEditText);
        endDateEditText = findViewById(R.id.endDatePickerEditText);
        applyLeaveButton = findViewById(R.id.applyLeaveButton);
        viewLeaveHistoryButton = findViewById(R.id.viewLeaveHistoryButton);

        // Initialize the date formatter
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        // Set initial dates in the date fields
        Calendar newCalendar = Calendar.getInstance();
        startDateEditText.setText(dateFormatter.format(newCalendar.getTime()));
        endDateEditText.setText(dateFormatter.format(newCalendar.getTime()));

        // Create DatePickerDialog instances for start and end dates
        startDatePickerDialog = createDatePickerDialog(startDateEditText);
        endDatePickerDialog = createDatePickerDialog(endDateEditText);

        // Set onClickListeners for the start and end date fields
        startDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDatePickerDialog.show();
            }
        });

        endDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endDatePickerDialog.show();
            }
        });

        applyLeaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Apply leave logic here
                applyLeave();
            }
        });

        viewLeaveHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // View leave history logic here
                viewLeaveHistory();
            }
        });
    }

    private void applyLeave() {
        // Get the selected leave type
        int selectedRadioButtonId = leaveTypeRadioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId != -1) {
            selectedLeaveType = findViewById(selectedRadioButtonId);
            String leaveType = selectedLeaveType.getText().toString();
            String reason = reasonEditText.getText().toString();
            String startDate = startDateEditText.getText().toString();
            String endDate = endDateEditText.getText().toString();

            // Perform validation and submission logic here
            if (validateInput(leaveType, reason, startDate, endDate)) {
                // Submit the leave application
                submitLeaveApplication(leaveType, reason, startDate, endDate);
            }
        } else {
            // No leave type is selected
            Toast.makeText(EmployeeLeaveApplyActivity.this, "Please select a leave type", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateInput(String leaveType, String reason, String startDate, String endDate) {
        // Add your validation logic here
        if (leaveType.isEmpty()) {
            Toast.makeText(EmployeeLeaveApplyActivity.this, "Please select a leave type", Toast.LENGTH_SHORT).show();
            return false;
        } else if (reason.isEmpty()) {
            Toast.makeText(EmployeeLeaveApplyActivity.this, "Please provide a reason", Toast.LENGTH_SHORT).show();
            return false;
        } else if (startDate.isEmpty() || endDate.isEmpty()) {
            Toast.makeText(EmployeeLeaveApplyActivity.this, "Please select start and end dates", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void submitLeaveApplication(String leaveType, String reason, String startDate, String endDate) {
        // Add your leave application submission logic here
        // This is just a sample toast message
        String message = "Leave Application submitted!\nLeave Type: " + leaveType + "\nReason: " + reason
                + "\nStart Date: " + startDate + "\nEnd Date: " + endDate;
        Toast.makeText(EmployeeLeaveApplyActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private void viewLeaveHistory() {
        // Get the leave reasons
        String leaveReason = reasonEditText.getText().toString();

        // Create an ArrayList to hold the leave reasons
        ArrayList<String> leaveReasonsList = new ArrayList<>();
        leaveReasonsList.add(leaveReason);

        // Create an Intent to start LeaveHistoryActivity
        Intent intent = new Intent(EmployeeLeaveApplyActivity.this, LeaveHistoryActivity.class);
        intent.putStringArrayListExtra("leaveReasons", leaveReasonsList);
        startActivity(intent);
    }


    private Date getSelectedDate(String dateStr) {
        try {
            return dateFormatter.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private DatePickerDialog createDatePickerDialog(final EditText editText) {
        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar selectedCalendar = Calendar.getInstance();
                        selectedCalendar.set(year, monthOfYear, dayOfMonth);
                        editText.setText(dateFormatter.format(selectedCalendar.getTime()));
                    }
                },
                newCalendar.get(Calendar.YEAR),
                newCalendar.get(Calendar.MONTH),
                newCalendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        return datePickerDialog;
    }

    // Leave class with Parcelable implementation
    public static class Leave implements Parcelable {
        private String reason;
        private Date startDate;
        private Date endDate;

        public Leave(String reason, Date startDate, Date endDate) {
            this.reason = reason;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        protected Leave(Parcel in) {
            reason = in.readString();
            long startDateMillis = in.readLong();
            startDate = new Date(startDateMillis);
            long endDateMillis = in.readLong();
            endDate = new Date(endDateMillis);
        }

        public static final Creator<Leave> CREATOR = new Creator<Leave>() {
            @Override
            public Leave createFromParcel(Parcel in) {
                return new Leave(in);
            }

            @Override
            public Leave[] newArray(int size) {
                return new Leave[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(reason);
            dest.writeLong(startDate.getTime());
            dest.writeLong(endDate.getTime());
        }
    }
}
