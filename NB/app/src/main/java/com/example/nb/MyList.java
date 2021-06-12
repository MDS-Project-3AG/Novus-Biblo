package com.example.nb;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import androidx.core.app.ActivityCompat;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MyList extends AppCompatActivity {

    private int user_id;
    private List<BookDetails> book_list;
    private static String ip = "192.168.1.16";
    private static String port = "1433";
    //private static String Classes = "nb.c54iovni0dyv.us-east-2.rds.amazonaws.com";
    private static String database = "nb";
    private static String username = "admin";
    private static String password = "NovusBiblo1";
    private static String url = "jdbc:mysql://nb.c54iovni0dyv.us-east-2.rds.amazonaws.com/nb";
    private Connection connection = null;
    private TextView textView;
    FirebaseAuth fAuth;

    //public MyList(){};

//    public MyList(int user_id, List<BookDetails> book_list) {
//        this.user_id = user_id;
//        this.book_list = book_list;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        textView = findViewById(R.id.textView4);

        fAuth = FirebaseAuth.getInstance();
        String currentuser = fAuth.getCurrentUser().getUid();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            connection = DriverManager.getConnection(url, username, password);
            textView.setText("SUCCESS");
        }

        catch (SQLException e) {
            e.printStackTrace();
            textView.setText("FAILURE");
        }


    }
}