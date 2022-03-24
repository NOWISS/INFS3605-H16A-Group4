package com.example.covidscreeningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


// Not sure if this class is necessary
public class EmployeeLogin extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;
    private TextView tvLoginError;

    private String username1 = "employee";
    private String password1 = "12345";

    boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_login);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

        etUsername.addTextChangedListener(loginTextWatcher);
        etPassword.addTextChangedListener(loginTextWatcher);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setEnabled(false);

        tvLoginError = (TextView) findViewById(R.id.tvLoginError);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                isValid = validate(username, password);

                if(!isValid){
                    tvLoginError.setText("Username or password is incorrect, please try again");
                }else{
                    Intent intent = new Intent(EmployeeLogin.this, EmployeeMain.class);
                    intent.putExtra("EmpUserName",username);
                    intent.putExtra("EmpPassword",password);
                    startActivity(intent);
                }
            }
        });
    }

    // Idea for using textWatcher to disable button when editText is empty taken from:
    // Title: textWatcherExample source code
    // Author: Coding in Flow
    // Date: 7/4/2018
    // Availability: https://www.youtube.com/watch?v=Vy_4sZ6JVHM&ab_channel=CodinginFlow
    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String usernameInput = etUsername.getText().toString();
            String passwordInput = etPassword.getText().toString();
            btnLogin.setEnabled(!usernameInput.isEmpty() && !passwordInput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    private boolean validate(String user, String pass){
        if(user.equals(username1) && pass.equals(password1)){
            return true;
        }
        return false;
    }
}