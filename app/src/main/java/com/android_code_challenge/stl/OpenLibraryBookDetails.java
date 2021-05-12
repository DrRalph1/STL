package com.android_code_challenge.stl;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android_code_challenge.stl.JsonParser.BookDetailsJsonParser;

public class OpenLibraryBookDetails extends AppCompatActivity {

    BookDetailsJsonParser jsonParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        //Get the bundle
        Bundle bundle = getIntent().getExtras();
        // Extract the ISBN
        String ISBN = bundle.getString("ISBN");

        try {
            jsonParser = (BookDetailsJsonParser) new BookDetailsJsonParser(this, this, OpenLibraryURL.olbURL + ISBN).execute();

        } catch (Exception ex){
            ex.printStackTrace();
            // Toast.makeText(this, "" + ex.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}