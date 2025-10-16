package com.example.citidemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.citidemo.MainActivity;
import com.example.citidemo.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private TextInputLayout tilFullName, tilEmail, tilPassword, tilConfirmPassword;
    private TextInputEditText etEmail, etPassword, etConfirmPassword, etFullName;
    private TextView tvForgotPassword;
    private MaterialButton btnSubmit;

    private boolean isSignUpMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize all UI elements
        tabLayout = findViewById(R.id.tabLayout);
        tilFullName = findViewById(R.id.tilFullName);
        tilEmail = findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);
        tilConfirmPassword = findViewById(R.id.tilConfirmPassword);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        btnSubmit = findViewById(R.id.btnSubmit);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);


        // Handle tab changes
        setupTabLayout();
        btnSubmit.setOnClickListener(v -> authentication());

    }

    private void setupTabLayout() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText() == null) return;
                String tabName = tab.getText().toString();

                if (tabName.equals("Login")) {
                    showLoginFields();
                } else if (tabName.equals("Sign Up")) {
                    showSignUpFields();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }

    private void showLoginFields() {
        tilFullName.setVisibility(View.GONE);
        tilConfirmPassword.setVisibility(View.GONE);
        tvForgotPassword.setVisibility(View.VISIBLE);
        btnSubmit.setText("Login");
    }

    private void showSignUpFields() {
        tilFullName.setVisibility(View.VISIBLE);
        tilConfirmPassword.setVisibility(View.VISIBLE);
        tvForgotPassword.setVisibility(View.GONE);
        btnSubmit.setText("Sign Up");
    }
    //temporary authentication
    private void authentication(){
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isSignUpMode) {
            String confirmPassword = etConfirmPassword.getText().toString().trim();
            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
        }

        // Move to next screen
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Optional - closes LoginActivity
    }
}
