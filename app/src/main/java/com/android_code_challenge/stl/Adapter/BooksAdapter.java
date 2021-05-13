package com.android_code_challenge.stl.Adapter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.android_code_challenge.stl.Controller.BooksController;
import com.android_code_challenge.stl.Controller.DBController;
import com.android_code_challenge.stl.R;
import com.bumptech.glide.Glide;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class BooksAdapter extends ArrayAdapter<BooksController> {

    // Initializing variables for the books adapter class
    List<BooksController> booksList;
    private Context context;
    DBController myDb;

    public BooksAdapter(@NonNull Context context, List<BooksController> booksList) {
        super(context, R.layout.book_item, booksList);
        this.context = context;
        this.booksList = booksList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.book_item, null);

        }
        TextView isbn = (TextView) convertView.findViewById(R.id.tvISBN);
        ImageView coverImage = (ImageView) convertView.findViewById(R.id.ivCoverImage);
        TextView title = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView author = (TextView) convertView.findViewById(R.id.tvAuthor);
        TextView yearOfPublication = (TextView) convertView.findViewById(R.id.tvYearOfPublication);
        Button btnBookmark = (Button) convertView.findViewById(R.id.btnBookmark);

        myDb = new DBController(convertView.getContext());

        Cursor res = myDb.getAllData();

        while (res.moveToNext()) {
            // buffer.append("bookmark_id :"+ res.getString(1)+"\n");
            String bookmarked = res.getString(1);
            if ((position + "").equals(bookmarked)) {
                btnBookmark.setBackgroundColor(Color.RED);
                btnBookmark.setText("Bookmarked");
            }
        }

        btnBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!myDb.getData(position+"")){
                    // Add bookmark
                    ////////////////////////////////////////////////////////////////////////////////
                    myDb.insertData(position+"");
                    btnBookmark.setBackgroundColor(Color.RED);
                    btnBookmark.setText("Bookmarked");
                    // Toast.makeText(v.getContext(), "Bookmarked", Toast.LENGTH_LONG).show();
                    ////////////////////////////////////////////////////////////////////////////////
                } else {
                    // Remove bookmark
                    ////////////////////////////////////////////////////////////////////////////////
                    myDb.deleteData(position+"");
                    btnBookmark.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.purple_500));
                    btnBookmark.setText("Bookmark");
                    // Toast.makeText(v.getContext(), "Unmarked", Toast.LENGTH_LONG).show();
                    ////////////////////////////////////////////////////////////////////////////////
                }
            }
        });

        final BooksController bookDetails = booksList.get(position);
        isbn.setText(bookDetails.getISBN());
        title.setText(bookDetails.getTitle());
        author.setText(bookDetails.getAuthor());
        yearOfPublication.setText(bookDetails.getYearOfPublication());

        try {
            URL imageUrl = new URL(bookDetails.getCoverImage());
            Glide.with(context).load(imageUrl).into(coverImage);
            // Bitmap bmp = BitmapFactory.decodeStream(imageUrl.openStream());
            // Bitmap bmp = BitmapFactory.decodeStream() */
            // image.setImageResource(R.drawable.logo)

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return convertView;
    }
}
