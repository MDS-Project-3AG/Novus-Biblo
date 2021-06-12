package com.example.nb;
import android.os.Bundle;
<<<<<<< HEAD
=======
import android.os.StrictMode;
>>>>>>> 1cad84ece6238ae19906d1e9175b3ffa84a8ebb8
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

<<<<<<< HEAD
=======
import com.google.firebase.auth.FirebaseAuth;
>>>>>>> 1cad84ece6238ae19906d1e9175b3ffa84a8ebb8
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

<<<<<<< HEAD
=======
import java.sql.*;
>>>>>>> 1cad84ece6238ae19906d1e9175b3ffa84a8ebb8
import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {

    private RequestQueue mRequestQueue;
    private ArrayList<BookInfo> bookArray;
    private EditText search_input;
    private ImageButton search_btn;
    private ProgressBar progress_bar;

<<<<<<< HEAD
=======
    FirebaseAuth fAuth;
    private static String ip = "192.168.1.16";
    private static String port = "1433";
    //private static String Classes = "nb.c54iovni0dyv.us-east-2.rds.amazonaws.com";
    private static String database = "nb";
    private static String username_db = "admin";
    private static String password_db = "NovusBiblo1";
    private static String url = "jdbc:mysql://nb.c54iovni0dyv.us-east-2.rds.amazonaws.com/nb";
    private Connection connection = null;
>>>>>>> 1cad84ece6238ae19906d1e9175b3ffa84a8ebb8

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        progress_bar = findViewById(R.id.idLoadingPB);
        search_input = findViewById(R.id.idEdtSearchBooks);
        search_btn = findViewById(R.id.idBtnSearch);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress_bar.setVisibility(View.VISIBLE);

                if (search_input.getText().toString().isEmpty()) { //if user entered an input
                    search_input.setError("Enter a word for searching!");
                    return;
                }
                getBooks(search_input.getText().toString()); //load the books from API
            }
        });
<<<<<<< HEAD
    }

=======

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
    }



>>>>>>> 1cad84ece6238ae19906d1e9175b3ffa84a8ebb8
    private void getBooks(String query) {
        
        bookArray = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(AllBooksActivity.this); //initialize for request queue
        mRequestQueue.getCache().clear(); //clear cache when updating

        String url = "https://www.googleapis.com/books/v1/volumes?q=" + query; //get data from API
        RequestQueue queue = Volley.newRequestQueue(AllBooksActivity.this); //created new request queue

        JsonObjectRequest booksObjrequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progress_bar.setVisibility(View.GONE);

                try {
                    JSONArray itemsArray = response.getJSONArray("items");
                    for (int i = 0; i < itemsArray.length(); i++) {
                        JSONObject items_obj = itemsArray.getJSONObject(i);
                        JSONObject volume_obj = items_obj.getJSONObject("volumeInfo");
                        String title = volume_obj.optString("title");
                        String subtitle = volume_obj.optString("subtitle");
                        //JSONArray authorsArray = volume_obj.getJSONArray("authors");
                        String publisher = volume_obj.optString("publisher");
                        String published_date = volume_obj.optString("published_date");
                        String description = volume_obj.optString("description");
                        int pages = volume_obj.optInt("pages");

                        JSONObject imageLinks = volume_obj.optJSONObject("imageLinks");
                        String thumbnail = imageLinks.optString("thumbnail");
                        String preview_link = volume_obj.optString("preview_link");
                        String info_link = volume_obj.optString("info_link");
                        JSONObject sale_info = items_obj.optJSONObject("saleInfo");
                        String buy_link = sale_info.optString("buy_link");
                        String aut = "";
                        ArrayList<String> authorsArrayList = new ArrayList<>();
//                        if (authorsArray.length() != 0) {
//                            for (int j = 0; j < authorsArray.length(); j++) {
//                                authorsArrayList.add(authorsArray.optString(i));
//                                aut = aut + authorsArray.optString(i);
//                            }
//                        }
                        System.out.println(aut);

                        BookInfo bookInfo = new BookInfo(title, subtitle, authorsArrayList, publisher, published_date, description, pages, thumbnail, preview_link, info_link, buy_link);
<<<<<<< HEAD
=======


                        fAuth = FirebaseAuth.getInstance();
                        String currentuser = fAuth.getCurrentUser().getUid();

                        int binfoID = 0;
                        try{
                            PreparedStatement pstmt = connection.prepareStatement("SELECT MAX(book_id) + 1 FROM Book");
                            ResultSet rs = pstmt.executeQuery();
                            if (rs.next()){
                                binfoID = rs.getInt(1);
                            }
                            String query = "INSERT INTO Book (book_id, title, published_date) " + "values (?, ?, ?)";
                            PreparedStatement stmt = connection.prepareStatement(query);
                            stmt.setInt(1, binfoID);
                            stmt.setString(2, title);
                            stmt.setString(3, published_date);
                            stmt.executeUpdate();
                        }
                        catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }

>>>>>>> 1cad84ece6238ae19906d1e9175b3ffa84a8ebb8
                        System.out.println(title);
                        bookArray.add(bookInfo); //aded to booksArray

                        RecyclerView recyclerView = findViewById(R.id.idRVBooks);
                        LinearLayoutManager manager = new LinearLayoutManager(AllBooksActivity.this);
                        recyclerView.setLayoutManager(manager);
                        recyclerView.setHasFixedSize(true);
                        BookAdapter adapter = new BookAdapter(bookArray, AllBooksActivity.this);
                        recyclerView.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(AllBooksActivity.this, "No data found!" + e, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AllBooksActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(booksObjrequest); //added json obj to request queue
    }
}