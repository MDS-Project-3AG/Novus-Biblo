package com.example.nb;

import android.content.Intent;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.*;


public class Register extends AppCompatActivity {
    EditText email, confirm_password, password;
    Button registered;
    Button register_button;
    FirebaseAuth fAuth;
    private static String ip = "192.168.1.16";
    private static String port = "1433";
    //private static String Classes = "nb.c54iovni0dyv.us-east-2.rds.amazonaws.com";
    private static String database = "nb";
    private static String username_db = "admin";
    private static String password_db = "NovusBiblo1";
    private static String url = "jdbc:mysql://nb.c54iovni0dyv.us-east-2.rds.amazonaws.com/nb";
    private Connection connection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        register_button = findViewById(R.id.register_button);
        registered = findViewById(R.id.button2);

        fAuth = FirebaseAuth.getInstance();

//        if(fAuth.getCurrentUser() != null){
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            finish();
//        }

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String semail = email.getText().toString().trim();
                System.out.println(semail);
                String spass = password.getText().toString().trim();

                if (TextUtils.isEmpty(semail)){
                    email.setError("Email is required!");
                    return;
                }

                if (TextUtils.isEmpty(spass)){
                    password.setError("Email is required!");
                    return;
                }

                if (spass.length() < 7){
                    password.setError("Password must have at least 7 characters.");
                    return;
                }



                fAuth.createUserWithEmailAndPassword(semail,spass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "User Created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Dummy.class));
                        }
                        else{
                            Toast.makeText(Register.this, "Error." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                try {
                    connection = DriverManager.getConnection(url, username_db, password_db);
                    System.out.println("SUCCESS");
                }

                catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("FAILURE");
                }
                fAuth = FirebaseAuth.getInstance();
                String currentuser = fAuth.getCurrentUser().getUid();

                // Create Client
//                try{
//                    System.out.println("Add Client");
//                    //PreparedStatement pstmt = connection.prepareStatement("SELECT MAX(client_id) + 1 FROM Client");
//                    //String query = "INSERT INTO Bookshelf (client_id, email, password, first_name, last_name) " + "values (?, ?, ?, ?, ?)";
//                    String query = "INSERT INTO Bookshelf (client_id) " + "values (?)";
//                    PreparedStatement stmt = connection.prepareStatement(query);
//                    String name = "";
//                    stmt.setString(1, currentuser);
////                    stmt.setString(2, semail);
////                    stmt.setString(3, spass);
////                    stmt.setString(4, name);
////                    stmt.setString(5, name);
//                    stmt.executeUpdate();
//                }
//                catch (SQLException throwables) {
//                    System.out.println("catch Client");
//                    throwables.printStackTrace();
//                }

                // Create Bookshelf for My List
                int bookshelfID = 0;
                try{
                    PreparedStatement pstmt = connection.prepareStatement("SELECT MAX(bookshelf_id) + 1 FROM Bookshelf");
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()){
                        bookshelfID = rs.getInt(1);
                    }
                    if (bookshelfID == 0){
                        bookshelfID += 1;
                    }
                    String query = "INSERT INTO Bookshelf (bookshelf_id, client_id, name) " + "values (?, ?, ?)";
                    PreparedStatement stmt = connection.prepareStatement(query);
                    stmt.setInt(1, bookshelfID);
                    stmt.setString(2, currentuser);
                    stmt.setString(3, "My List");
                    stmt.executeUpdate();
                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                // Create Bookshelf for Favorites
                try{
                    PreparedStatement pstmt = connection.prepareStatement("SELECT MAX(bookshelf_id) + 1 FROM Bookshelf");
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()){
                        bookshelfID = rs.getInt(1);
                    }
                    if (bookshelfID == 0){
                        bookshelfID += 1;
                    }
                    String query = "INSERT INTO Bookshelf (bookshelf_id, client_id, name) " + "values (?, ?, ?)";
                    PreparedStatement stmt = connection.prepareStatement(query);
                    stmt.setInt(1, bookshelfID);
                    stmt.setString(2, currentuser);
                    stmt.setString(3, "Favorites");
                    stmt.executeUpdate();
                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });

    }


}