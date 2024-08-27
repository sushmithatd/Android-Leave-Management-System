package com.example.lmsa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText phoneEditText;
    private EditText addressEditText;
    private Button nextButton;

    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "EmployeePrefs";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_ADDRESS = "address";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        nameEditText = findViewById(R.id.name_EditText);
        phoneEditText = findViewById(R.id.phone_EditText);
        addressEditText = findViewById(R.id.address_EditText);
        nextButton = findViewById(R.id.next_Button);

        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString().trim();
                String phone = phoneEditText.getText().toString().trim();
                String address = addressEditText.getText().toString();

                if (validateName(name) && validatePhone(phone)) {
                    // Save the employee information to shared preferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_NAME, name);
                    editor.putString(KEY_PHONE, phone);
                    editor.putString(KEY_ADDRESS, address);
                    editor.apply();

                    // Pass the employee information to the next activity
                    Intent intent = new Intent(SignupActivity.this, EmployeeLeaveApplyActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validateName(String name) {
        if (TextUtils.isEmpty(name)) {
            nameEditText.setError("Please enter your name");
            return false;
        } else if (name.equals("1234")) {
            nameEditText.setError("Invalid name");
            return false;
        }
        return true;
    }

    private boolean validatePhone(String phone) {
        if (TextUtils.isEmpty(phone)) {
            phoneEditText.setError("Please enter your phone number");
            return false;
        } else if (phone.equals("abc")) {
            phoneEditText.setError("Invalid phone number");
            return false;
        }
         else if (phone.isEmpty() || phone.length() < 10) {
            phoneEditText.setError("Please enter a valid phone number");
            return false;
        }
        return true;
    }


    private boolean validateAddress(String address) {
        if (TextUtils.isEmpty(address)) {
            addressEditText.setError("Please enter your address");
            return false;
        } else if (address.equals("12346777dghhc")) {
            addressEditText.setError("Invalid address");
            return false;
        }
        return true;
    }

    }

