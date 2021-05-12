package com.android_code_challenge.stl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android_code_challenge.stl.Adapter.BooksAdapter;
import com.android_code_challenge.stl.Controller.BooksController;
import com.android_code_challenge.stl.JsonParser.BooksJsonParser;

import java.util.ArrayList;
import java.util.List;

public class OpenLibraryBooks extends AppCompatActivity {

    BooksJsonParser jsonParser;
    public List<BooksController> books = new ArrayList<>();
    // International Standard Book Number's for Open Library OpenLibraryBooks
    String ISBNs = "ISBN:0670872911," +
                    "ISBN:9780375868313," +
                    "ISBN:0439309107," +
                    "ISBN:9781596437203," +
                    "ISBN:0545221714," +
                    "ISBN:1459804287," +
                    "ISBN:0823413497";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            jsonParser = (BooksJsonParser) new BooksJsonParser(this, this, OpenLibraryURL.olbURL + ISBNs).execute();

            ListView listView;
            listView = (ListView)this.findViewById(R.id.listviewOfBooks);
            BooksAdapter adapter = new BooksAdapter(this, books);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                    // Toast.makeText(OpenLibraryBooks.this, "" + position, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(OpenLibraryBooks.this, OpenLibraryBookDetails.class);
                    String ISBN = ((TextView) arg1.findViewById(R.id.tvISBN)).getText().toString();
                    intent.putExtra("ISBN", ISBN);
                    startActivity(intent);
                }
            });


        } catch (Exception ex){
            ex.printStackTrace();
            Toast.makeText(OpenLibraryBooks.this, "" + ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}