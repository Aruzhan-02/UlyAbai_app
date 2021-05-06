package com.example.ulyabai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegistrationPage extends AppCompatActivity implements View.OnClickListener {

    Button btnReg;
    EditText username, email, phoneNumber, pswrd, confirmPswrd;
    FirebaseAuth auth;
    FirebaseFirestore database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        btnReg = findViewById(R.id.btnReg);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phoneNumber = findViewById(R.id.phone_number);
        pswrd = findViewById(R.id.password);
        confirmPswrd = findViewById(R.id.repassword);

        auth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();

        btnReg.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (TextUtils.isEmpty(username.getText())) {
            username.setError("Толық толтырыңыз!");
            return;
        }
        if (TextUtils.isEmpty(email.getText())) {
            email.setError("Толық толтырыңыз!");
            return;
        }
        if (TextUtils.isEmpty(phoneNumber.getText())) {
            phoneNumber.setError("Толық толтырыңыз!");
            return;
        }
        if (TextUtils.isEmpty(pswrd.getText())) {
            pswrd.setError("Толық толтырыңыз!");
            return;
        }
        if (TextUtils.isEmpty(confirmPswrd.getText())) {
            confirmPswrd.setError("Толық толтырыңыз!");
            return;
        }

        String uEmail = email.getText().toString();
        String uPswrd = pswrd.getText().toString();

        auth.createUserWithEmailAndPassword(uEmail, uPswrd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    addDatatoFirestore();

                } else{
                    Toast.makeText(RegistrationPage.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void addDatatoFirestore() {
        String uEmail = email.getText().toString();
        String uPswrd = pswrd.getText().toString();
        String uName = username.getText().toString();
        String uPhoneNumber = phoneNumber.getText().toString();
        String uConPswrd = confirmPswrd.getText().toString();

        User user = new User(uName, uEmail, uPhoneNumber, uPswrd, uConPswrd);

        String emailFormat = user.getEmail().replace(".", "-");

        database
                .collection("users")
                .document(emailFormat)
                .set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegistrationPage.this, "Сіз сәтті тіркелдіңіз!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationPage.this, LoginPage.class);
                    startActivity(intent);
                }
            }
        });
    }
}