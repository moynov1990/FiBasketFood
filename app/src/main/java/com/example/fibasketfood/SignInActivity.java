package com.example.fibasketfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.fibasketfood.Common.Common;
import com.example.fibasketfood.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {

    private EditText edtTxtLogin, edtTxtPassword;
    private Button btnSignIn;
    private TextView txtSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        edtTxtLogin = findViewById(R.id.edtTxtLogin);
        edtTxtPassword = findViewById(R.id.edtTxtPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        txtSignUp = findViewById(R.id.txtSignUp);       // android:onClick="SignUpA"

        FirebaseDatabase database = FirebaseDatabase.getInstance();     //init firebase
        final DatabaseReference table_user = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                ProgressBar mBar = new ProgressBar(SignInActivity.this);   //   проаналізувати реалізацію панелі прогресу завантаження

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child(edtTxtLogin.getText().toString()).exists()) {        //перевіряємо чи існує користувач в базі

                            User user = dataSnapshot.child(edtTxtLogin.getText().toString()).getValue(User.class);      //отримуємо інформацію про User
                            if (user.getPassword().equals(edtTxtPassword.getText().toString())) {                       //перевіряємо чи співпадає пароль із тим що в базі
                                Intent homeIntent = new Intent(SignInActivity.this, HomeActivity.class);
                                Common.currentUser = user;                                                              //записуємо User в тимчасове сховище
                                startActivity(homeIntent);
                                finish();
                            } else {
                                Toast.makeText(SignInActivity.this, "Sign in failed", Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(SignInActivity.this, "User not exist", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    public void SignUpA(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }
}