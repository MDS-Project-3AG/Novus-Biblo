package com.example.nb;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainScreen extends AppCompatActivity {

    Connection connect;
    String connectionresult = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }

    public void getTextFromSql(View v){
        TextView t1 = (TextView) findViewById(R.id.textView2);
        TextView t2 = (TextView) findViewById(R.id.textView3);

        try{
            ConnectionHelper connectionhelper = new ConnectionHelper();
            connect = connectionhelper.connectionclass();
            if(connect != null){
                System.out.println("daaa. a intrat");
                String query = "Select * from Client";
                Statement st = connect.createStatement();
                ResultSet res = st.executeQuery(query);

                while(res.next()){
                    t1.setText(res.getString(1));
                    t2.setText(res.getString(2));
                }
            }
            else{
                connectionresult = "check connection";
            }
        }
        catch (Exception ex){
            Log.e("Error", ex.getMessage());

        }

    }
}