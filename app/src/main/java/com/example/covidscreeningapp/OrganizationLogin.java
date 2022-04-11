package com.example.covidscreeningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class OrganizationLogin extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;
    private TextView tvLoginError;
    private ImageView lefticon;


    // user names and passwords are assigned to organizations therefore they are not allowed to register
    private String username1 = "admin";
    private String password1 = "12345";

    boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_login);

        etUsername = (EditText) findViewById(R.id.OrgUsername);
        etPassword = (EditText) findViewById(R.id.OrgPassword);

        etUsername.addTextChangedListener(loginTextWatcher);
        etPassword.addTextChangedListener(loginTextWatcher);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setEnabled(false);
        tvLoginError = (TextView) findViewById(R.id.tvLoginError);

        lefticon = findViewById(R.id.back);
        // Make the return button
        lefticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(OrganizationLogin.this,MainActivity.class);
                startActivity(intent1);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                isValid = validate(username, password);

                if(!isValid){
                    tvLoginError.setText("Username or password is incorrect, please try again");
                }else{
                    Intent intent = new Intent(OrganizationLogin.this, SelectPortal.class);
                    intent.putExtra("OrgUserName",username);
                    intent.putExtra("OrgPassword",password);
                    startActivity(intent);
                }
            }
        });
    }
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