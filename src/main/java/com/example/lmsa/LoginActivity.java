package com.example.lmsa;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private CheckBox employeeCheckbox;
    private CheckBox managerCheckbox;
    private Button loginButton;
    private Button signUpButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.username_EditText);
        passwordEditText = findViewById(R.id.password_EditText);
        employeeCheckbox = findViewById(R.id.employee_Checkbox);
        managerCheckbox = findViewById(R.id.manager_Checkbox);
        loginButton = findViewById(R.id.login_Button);
        signUpButton = findViewById(R.id.signUp_Button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (employeeCheckbox.isChecked()) {
                    // Handle employee login
                    // Validate employee's credentials
                    // Example: Check against hardcoded values or a database
                    if (isValidEmployeeCredentials(username, password)) {
                        // Start EmployeeActivity or any other activity for employees
                        // Example: Intent intent = new Intent(LoginActivity.this, EmployeeActivity.class);
                        Intent intent = new Intent(LoginActivity.this, EmployeeLeaveApplyActivity.class);
                        startActivity(intent);
                        finish(); // Optional: Close the login activity to prevent navigating back to it
                    } else {
                        showToast("Invalid employee credentials");
                    }
                } else if (managerCheckbox.isChecked()) {
                    // Handle manager login
                    // Validate manager's credentials
                    if (isValidManagerCredentials(username, password)) {
                        // Start ManagerDashboardActivity or any other activity for managers
                        Intent intent = new Intent(LoginActivity.this, ManagerDashboardActivity.class);
                        startActivity(intent);
                        finish(); // Optional: Close the login activity to prevent navigating back to it
                    } else {
                        showToast("Invalid manager credentials");
                    }
                } else {
                    showToast("Please select employee or manager login");
                }
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (employeeCheckbox.isChecked()) {
                    String username = usernameEditText.getText().toString();
                    String password = passwordEditText.getText().toString();

                    // Validate employee's credentials for sign up
                    if (isValidEmployeeCredentials(username, password)) {
                        // Start SignupActivity for employees
                        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                        intent.putExtra("username", username);
                        intent.putExtra("password", password);
                        startActivity(intent);
                    } else {
                        showToast("Invalid employee credentials");
                    }
                } else {
                    showToast("Only employees can sign up");
                }
            }
        });
    }

    private boolean isValidEmployeeCredentials(String username, String password) {
        // Implement your validation logic for employee credentials
        // Return true if the credentials are valid, false otherwise
        // Example: You can check against hardcoded values or a database
        return username.equals("employee") && password.equals("password");
    }

    private boolean isValidManagerCredentials(String username, String password) {
        // Hardcoded values for manager credentials
        String managerUsername = "manager";
        String managerPassword = "password";

        // Compare the entered username and password with the manager credentials
        return username.equals(managerUsername) && password.equals(managerPassword);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
