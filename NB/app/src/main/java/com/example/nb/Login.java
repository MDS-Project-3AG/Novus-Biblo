package com.example.nb;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity{
    EditText email, confirm_password, password;
    Button not_registered;
    ProgressBar progressBar;
    Button login_button;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        login_button = findViewById(R.id.login_button);
        progressBar = findViewById(R.id.progressBar);
        not_registered = findViewById(R.id.button);

        fAuth = FirebaseAuth.getInstance();

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String semail = email.getText().toString().trim();
                String spass = password.getText().toString().trim();
                System.out.println(semail);
                System.out.println(spass);

                if (TextUtils.isEmpty(semail)){
                    email.setError("Email is required!");
                    return;
                }

                if (TextUtils.isEmpty(spass)){
                    password.setError("Password is required!");
                    return;
                }

                if (spass.length() < 7){
                    password.setError("Password must have at least 7 characters.");
                    return;
                }

                //progressBar.setVisibility(View.VISIBLE);
//
                fAuth.signInWithEmailAndPassword(semail,spass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
<<<<<<< HEAD
                            System.out.println("daaaa");
=======
>>>>>>> 1cad84ece6238ae19906d1e9175b3ffa84a8ebb8
                            Toast.makeText(Login.this, "Logged in successfully.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainScreen.class));
                        }
                        else{
                            Toast.makeText(Login.this, "Errrrrrrrrrrrrrrrror." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

//                fAuth.createUserWithEmailAndPassword(semail, spass).addOnCompleteListener(Login.this, new OnCompleteListener() {
//                    @Override
//                    public void onComplete(@NonNull Task task) {
//
//                        if (!task.isSuccessful()) {
//                            Toast.makeText(Login.this.getApplicationContext(),
//                                    "SignUp unsuccessful: " + task.getException().getMessage(),
//                                    Toast.LENGTH_SHORT).show();
//                        } else {
//                            System.out.println("sdfsdfsdf NU MERGEEEE ");
//                            startActivity(new Intent(Login.this, MainScreen.class));
//
//                        }
//                    }
//                });


//                FirebaseAuth.getInstance().signInWithEmailAndPassword(semail, spass).addOnCompleteListener((OnCompleteListener<AuthResult>) task -> {
//
//                    if (task.isSuccessful()) {
//                        Intent intent = new Intent(Login.this, MainScreen.class);
//                        startActivity(intent);
//                    }
//                    else{
//                            Toast.makeText(Login.this, "Error." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                });

            }
        });

//        not_registered.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Login.this, Register.class);
//                startActivity(intent);
//            }
//        });
    }
}
