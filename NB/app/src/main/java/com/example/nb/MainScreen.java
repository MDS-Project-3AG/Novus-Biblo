package com.example.nb;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;

import java.sql.*;

public class MainScreen extends AppCompatActivity {
//
//    Connection connect;
//    String connectionresult = "";
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_screen);
//    }
//
//    public void getTextFromSql(View v){
//        TextView t1 = (TextView) findViewById(R.id.textView2);
//        TextView t2 = (TextView) findViewById(R.id.textView3);
//
//        try{
//            ConnectionHelper connectionhelper = new ConnectionHelper();
//            connect = connectionhelper.connectionclass();
//            if(connect != null){
//                System.out.println("daaa. a intrat");
//                String query = "Select * from Client";
//                Statement st = connect.createStatement();
//                ResultSet res = st.executeQuery(query);
//
//                while(res.next()){
//                    t1.setText(res.getString(1));
//                    t2.setText(res.getString(2));
//                }
//            }
//            else{
//                connectionresult = "check connection";
//            }
//        }
//        catch (Exception ex){
//            Log.e("Error", ex.getMessage());
//
//        }
//
//    }

    private TextView textView;
    private TextView textView1;

    private static String ip = "192.168.1.16";
    private static String port = "1433";
    //private static String Classes = "nb.c54iovni0dyv.us-east-2.rds.amazonaws.com";
    private static String database = "nb";
    private static String username = "admin";
    private static String password = "NovusBiblo1";
    private static String url = "jdbc:mysql://nb.c54iovni0dyv.us-east-2.rds.amazonaws.com/nb";
    private Connection connection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        textView = findViewById(R.id.textView2);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            System.out.println("inainte");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("am trecut");
            textView.setText("SUCCESS");
        }
//        catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            textView.setText("ERROR");
//        }
        catch (SQLException e) {
            e.printStackTrace();
            textView.setText("FAILURE");
        }
    }

    public void sqlButton(View view){
        if (connection!=null){
            Statement statement = null;
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("Select * from Client;");
                while (resultSet.next()){
                    textView.setText(resultSet.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            textView.setText("Connection is null");
        }
    }
}