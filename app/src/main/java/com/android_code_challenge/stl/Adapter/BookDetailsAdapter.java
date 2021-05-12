package com.android_code_challenge.stl.Adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android_code_challenge.stl.Controller.BooksController;
import com.android_code_challenge.stl.R;
import com.bumptech.glide.Glide;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class BookDetailsAdapter extends ArrayAdapter<BooksController> {

    // Instantioting variables for the custom adapter class
    List<BooksController> booksList;
    private Context context;

    public BookDetailsAdapter(@NonNull Context context, List<BooksController>booksList) {
        super(context, R.layout.book_details, booksList);
        this.context = context;
        this.booksList = booksList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null){

            convertView = inflater.inflate(R.layout.book_details, null);

        }
        ImageView coverImage = (ImageView)convertView.findViewById(R.id.ivCoverImage);
        TextView title = (TextView)convertView.findViewById(R.id.tvTitle);
        TextView author = (TextView)convertView.findViewById(R.id.tvAuthor);
        TextView yearOfPublication = (TextView)convertView.findViewById(R.id.tvYearOfPublication);
        TextView description = (TextView)convertView.findViewById(R.id.tvDescription);

        final BooksController bookDetails = booksList.get(position);
        title.setText(bookDetails.getTitle());
        author.setText(bookDetails.getAuthor());
        yearOfPublication.setText(bookDetails.getYearOfPublication());
        description.setText(bookDetails.getDescription());

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
