package com.example.nb;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    Connection con;
    String username, pass, port, ip, database;
    @SuppressLint("NewApi")

    public Connection connectionclass(){
        ip = "192.168.1.16";
        database = "db1";
        pass = "NovusBiblo1";
        username = "Admin1";
        port = "1433";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionUrl = null;

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionUrl = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";" + "databasename=" + database + ";user=" + username + ";password=" + pass + ";";
            connection = DriverManager.getConnection(ConnectionUrl);
        }
        catch(Exception ex){
            Log.e("Error", ex.getMessage());
        }

        return connection;
    }

}
