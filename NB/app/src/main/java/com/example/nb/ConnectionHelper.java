package com.example.nb;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
//    Connection con;
//    String username, pass, port, ip, database;
//    @SuppressLint("NewApi")
//
//    public Connection connectionclass() {
//        ip = "192.168.1.16";
//        database = "db1";
//        pass = "NovusBiblo1";
//        username = "Admin1";
//        port = "1433";
//
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//        Connection connection = null;
//        String ConnectionUrl = null;
//
//        try {
//            Class.forName("net.sourceforge.jtds.jdbc.Driver");
//            System.out.println("inainte");
//            ConnectionUrl = "jdbc:sqlserver://novusbiblo.database.windows.net:1433;database=db1;user=Admin1@novusbiblo;password=NovusBiblo1;ssl=require;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
//            System.out.println("AM TRECUT");
//            connection = DriverManager.getConnection(ConnectionUrl, pass, username);
//        } catch (Exception ex) {
//            Log.e("Error", ex.getMessage());
//        }
//
//        return connection;
//
//    }



}
