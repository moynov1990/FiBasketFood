package com.example.fibasketfood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    private EditText edtTxtName,edtTxtLogin, edtTxtPassword;
    private Button btnSignUp;
    private TextView txtSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtTxtName = findViewById(R.id.edtTxtName);
        edtTxtLogin = findViewById(R.id.edtTxtLogin);
        edtTxtPassword = findViewById(R.id.edtTxtPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        txtSignIn = findViewById(R.id.txtSignIn);       // android:onClick="SignInA"
    }
}