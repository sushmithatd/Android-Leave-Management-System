

package com.example.lmsa;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ManagerDashboardActivity extends AppCompatActivity {

    private EditText managerNameEditText;
    private EditText managerPhoneEditText;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_dashboard);

        managerNameEditText = findViewById(R.id.managerNameEditText);
        managerPhoneEditText = findViewById(R.id.managerPhoneEditText);
        nextButton = findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String managerName = managerNameEditText.getText().toString().trim();
                String managerPhone = managerPhoneEditText.getText().toString().trim();


                if (isValidInput(managerName, managerPhone)) {

                    Intent intent = new Intent(ManagerDashboardActivity.this, LeaveRequestsActivity.class);
                    intent.putExtra("managerName", managerName);
                    intent.putExtra("managerPhone", managerPhone);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean isValidInput(String name, String phone) {
        if (name.isEmpty()) {
            managerNameEditText.setError("Please enter a valid name");
            return false;
        }
        else if (name.equals("1234")) {
            managerNameEditText.setError("Invalid name");
            return false;
        }

        if (phone.isEmpty() || phone.length() < 10) {
            managerPhoneEditText.setError("Please enter a valid phone number");
            return false;
        }
        else if (phone.equals("abc")) {
            managerPhoneEditText.setError("Invalid phone number");
            return false;
        }

        return true;
    }
}
