package com.example.citidemo.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.citidemo.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private TextInputLayout tilFullName, tilEmail, tilPassword, tilConfirmPassword;
    private TextView tvForgotPassword;
    private MaterialButton btnSubmit;

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

        // Handle tab changes
        setupTabLayout();

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

    private void authentication(){

    }
}
