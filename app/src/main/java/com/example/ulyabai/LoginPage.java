package com.example.ulyabai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {

    EditText email, pswrd;
    Button btnLogin, btnReg;
    FirebaseAuth auth;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        btnLogin = findViewById(R.id.btnLogin);
        btnReg = findViewById(R.id.btnReg);
        email = findViewById(R.id.email);
        pswrd = findViewById(R.id.password);
        auth = FirebaseAuth.getInstance();

        dialog = new ProgressDialog(this);
        dialog.setMessage("Профильіңізге кіруде...");

        btnLogin.setOnClickListener(this);
        btnReg.setOnClickListener(this);

        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null){
            Intent intent = new Intent(LoginPage.this, HomePage.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:

                if (TextUtils.isEmpty(email.getText())) {
                    email.setError("Толық толтырыңыз!");
                    return;
                }

                if (TextUtils.isEmpty(pswrd.getText())) {
                    pswrd.setError("Толық толтырыңыз!");
                    return;
                }

                String uEmail = email.getText().toString();
                String uPswrd = pswrd.getText().toString();

                dialog.show();

                auth.signInWithEmailAndPassword(uEmail, uPswrd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            dialog.dismiss();
                            Toast.makeText(LoginPage.this, "Сіз профильіңізге сәтті кірдіңіз!", Toast.LENGTH_SHORT).show();
                            Intent intent  = new Intent(LoginPage.this, HomePage.class);
                            startActivity(intent);
                        } else{
                            dialog.dismiss();
                            Toast.makeText(LoginPage.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                break;


            case R.id.btnReg:
                Intent intent1 = new Intent(LoginPage.this, RegistrationPage.class);
                startActivity(intent1);
                break;
        }
    }
}