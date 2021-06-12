package com.example.nb;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.*;
import java.util.List;

public class Favorites extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        textView = findViewById(R.id.textView5);

        fAuth = FirebaseAuth.getInstance();
        String currentuser = fAuth.getCurrentUser().getUid();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            connection = DriverManager.getConnection(url, username, password);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        int bibID = 0;
        int bID = 0;
        String str = "";
        String title1 = "";
        try {
            PreparedStatement bstmt = connection.prepareStatement("SELECT bookshelf_id FROM Bookshelf WHERE client_id = " + "?" + " and name = 'Favorites'");
            bstmt.setString(1, currentuser);
            ResultSet rst = bstmt.executeQuery();

            PreparedStatement stmt = connection.prepareStatement("SELECT book_id FROM Book_in_bookshelf WHERE bookshelf_id = " + "?");
            if (rst.next()){
                bibID = rst.getInt(1);
            }
            stmt.setInt(1, bibID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                bID = rs.getInt(1);
                PreparedStatement st = connection.prepareStatement("SELECT title FROM Book WHERE book_id = " + "?");
                st.setInt(1, bID);
                ResultSet rsts = st.executeQuery();
                if (rsts.next()){
                    title1 = rsts.getString(1);
                }
                str = str + title1 + "\n";
            }
            textView.setText(str);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}