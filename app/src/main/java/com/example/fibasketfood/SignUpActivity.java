package com.example.fibasketfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fibasketfood.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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


        FirebaseDatabase database = FirebaseDatabase.getInstance();     //init firebase
        final DatabaseReference table_user = database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child(edtTxtLogin.getText().toString()).exists()) {        //перевіряємо чи існує користувач в базі
                            Toast.makeText(SignUpActivity.this, "Already registered", Toast.LENGTH_LONG).show();
                        }
                        else {
                            User user = new User(edtTxtName.getText().toString(), edtTxtPassword.getText().toString());
                            table_user.child(edtTxtLogin.getText().toString()).setValue(user);
                            Toast.makeText(SignUpActivity.this, "Sign up successfully", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }

    public void SignInA(View view) {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        finish();
    }
}



//                        txtSignUp.setText("Зареєстровано");
//                        btnSignUp.setClickable(false);