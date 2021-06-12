package com.example.nb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
<<<<<<< HEAD
=======
import android.os.StrictMode;
>>>>>>> 1cad84ece6238ae19906d1e9175b3ffa84a8ebb8
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

<<<<<<< HEAD
import com.squareup.picasso.Picasso;

=======
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.sql.*;
>>>>>>> 1cad84ece6238ae19906d1e9175b3ffa84a8ebb8
import java.util.ArrayList;

public class BookDetails extends AppCompatActivity {

    String title, subtitle, publisher, publishedDate, description, thumbnail, previewLink, infoLink, buyLink;
    int pageCount;
    private ArrayList<String> authors;

    TextView titleTV, subtitleTV, publisherTV, descTV, pageTV, publishDateTV;
<<<<<<< HEAD
    Button previewBtn, buyBtn;
    private ImageView bookIV;

=======
    Button listBtn, favBtn;
    private ImageView bookIV;

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
        setContentView(R.layout.activity_book_details);

        titleTV = findViewById(R.id.idTVTitle);
        subtitleTV = findViewById(R.id.idTVSubTitle);
        publisherTV = findViewById(R.id.idTVpublisher);
        descTV = findViewById(R.id.idTVDescription);
        pageTV = findViewById(R.id.idTVNoOfPages);
        publishDateTV = findViewById(R.id.idTVPublishDate);
<<<<<<< HEAD
        previewBtn = findViewById(R.id.idBtnPreview);
        buyBtn = findViewById(R.id.idBtnBuy);
=======
        listBtn = findViewById(R.id.addToMyList);
        favBtn = findViewById(R.id.addToFav);
>>>>>>> 1cad84ece6238ae19906d1e9175b3ffa84a8ebb8
        bookIV = findViewById(R.id.idIVbook);

        title = getIntent().getStringExtra("title");
        subtitle = getIntent().getStringExtra("subtitle");
        publisher = getIntent().getStringExtra("publisher");
        publishedDate = getIntent().getStringExtra("publishedDate");
        description = getIntent().getStringExtra("description");
        pageCount = getIntent().getIntExtra("pageCount", 0);
        thumbnail = getIntent().getStringExtra("thumbnail");
        previewLink = getIntent().getStringExtra("previewLink");
        infoLink = getIntent().getStringExtra("infoLink");
        buyLink = getIntent().getStringExtra("buyLink");

        titleTV.setText(title);
        subtitleTV.setText(subtitle);
        publisherTV.setText(publisher);
        publishDateTV.setText("Published On : " + publishedDate);
        descTV.setText(description);
        pageTV.setText("No Of Pages : " + pageCount);
        Picasso.get().load(thumbnail).into(bookIV);

<<<<<<< HEAD
        previewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (previewLink.isEmpty()) {
                    Toast.makeText(BookDetails.this, "No preview Link present", Toast.LENGTH_SHORT).show();
                    return;
                }
                Uri uri = Uri.parse(previewLink);
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });

        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buyLink.isEmpty()) {
                    Toast.makeText(BookDetails.this, "No buy page present for this book", Toast.LENGTH_SHORT).show();
                    return;
                }
                Uri uri = Uri.parse(buyLink);
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });
=======
        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                int bibID = 0;
                int bID = 0;
                try{
                    PreparedStatement pstmt = connection.prepareStatement("SELECT bookshelf_id FROM Bookshelf WHERE client_id = " + "?" + " and name = 'My List'");
                    pstmt.setString(1, currentuser);
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()){
                        bibID = rs.getInt(1);
                    }
                    pstmt = connection.prepareStatement("SELECT book_id FROM Book WHERE title = " + "?");
                    pstmt.setString(1, title);
                    rs = pstmt.executeQuery();
                    if (rs.next()){
                        bID = rs.getInt(1);
                    }
                    String query = "INSERT INTO Book_in_bookshelf (book_id, bookshelf_id) " + "values (?, ?)";
                    PreparedStatement stmt = connection.prepareStatement(query);
                    stmt.setInt(1, bID);
                    stmt.setInt(2, bibID);
                    stmt.executeUpdate();
                    Toast.makeText(BookDetails.this, "Book added.", Toast.LENGTH_SHORT).show();
                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                    Toast.makeText(BookDetails.this, "Book not valid.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                int bibID = 0;
                int bID = 0;
                try{
                    PreparedStatement pstmt = connection.prepareStatement("SELECT bookshelf_id FROM Bookshelf WHERE client_id = " + "?" + " and name = 'Favorites'");
                    pstmt.setString(1, currentuser);
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()){
                        bibID = rs.getInt(1);
                    }
                    pstmt = connection.prepareStatement("SELECT book_id FROM Book WHERE title = " + "?");
                    pstmt.setString(1, title);
                    rs = pstmt.executeQuery();
                    if (rs.next()){
                        bID = rs.getInt(1);
                    }
                    String query = "INSERT INTO Book_in_bookshelf (book_id, bookshelf_id) " + "values (?, ?)";
                    PreparedStatement stmt = connection.prepareStatement(query);
                    stmt.setInt(1, bID);
                    stmt.setInt(2, bibID);
                    stmt.executeUpdate();
                    Toast.makeText(BookDetails.this, "Book added.", Toast.LENGTH_SHORT).show();
                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                    Toast.makeText(BookDetails.this, "Book not valid.", Toast.LENGTH_SHORT).show();

                }
            }
        });

>>>>>>> 1cad84ece6238ae19906d1e9175b3ffa84a8ebb8
    }
}