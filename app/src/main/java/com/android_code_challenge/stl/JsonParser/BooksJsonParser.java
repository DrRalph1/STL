package com.android_code_challenge.stl.JsonParser;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.android_code_challenge.stl.Adapter.BooksAdapter;
import com.android_code_challenge.stl.Controller.BooksController;
import com.android_code_challenge.stl.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BooksJsonParser extends AsyncTask<String, String, Void> {

    // Instantiating variables of teh BooksAdapter Class
    public List<BooksController> books = new ArrayList<>();
    BufferedInputStream inputStream;
    JSONObject jsonObject;
    String result = "";
    public ProgressDialog progressDialog;
    Activity activity;
    Context context;
    String url;

    public BooksJsonParser(Activity activity, Context context, String url){
        this.activity = activity;
        this.context = context;
        this.url = url;
        this.progressDialog = new ProgressDialog(this.context);
    }

    // The onPreExecute function below shows Loading.. when the page is being loaded using a progress dialog
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.dismiss();
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        // progressDialog.setOnCancelListener((DialogInterface::dismiss));
        progressDialog.setCancelable(false);
    }


    // The doInBackground function below gets the API resource (i.e. the list of books) in the background
    @Override
    protected Void doInBackground(String... strings) {
        HttpURLConnection httpURLConnection = null;
        try{

            URL url = new URL(this.url);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            inputStream =  new BufferedInputStream(httpURLConnection.getInputStream());
            result = readStream();
            result = result.substring(result.indexOf("{"), result.lastIndexOf("}") + 1);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    // The readStream function below reads the content returned by the API
    private String readStream() throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuffer = new StringBuilder();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return stringBuffer.toString();
    }

    // Function to capitalize the first letter of each word
    public static String capitalize(@NonNull String input) {
        String[] words = input.toLowerCase().split(" ");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            if (i > 0 && word.length() > 0) {
                builder.append(" ");
            }

            String cap = word.substring(0, 1).toUpperCase() + word.substring(1);
            builder.append(cap);
        }
        return builder.toString();
    }

    // This onPostExecute function below checks if the JSONObject from the API is not null,
    // and then assigns the JSONObject values to the books object.
    @Override
    protected void onPostExecute(Void aVoid) {
        try{
            jsonObject = new JSONObject(result);

            // Log.wtf("jsonOBJECT: ", String.valueOf(jsonObject));

            Iterator<String> iter = jsonObject.keys();
            while (iter.hasNext()) {
                String key = iter.next();
                try {
                    JSONObject value = (JSONObject) jsonObject.get(key);

                    BooksController bookDetails =  new BooksController();
                    // Set ISBN
                    bookDetails.isbn = key;

                    JSONObject details = new JSONObject(value.getString("details"));
                    Iterator<String> iterDetails = details.keys();
                    while (iterDetails.hasNext()) {
                        String detailsKey = iterDetails.next();
                        try {
                            // Set Title
                            bookDetails.title = capitalize(details.getString("title"));

                            // Set Author
                            JSONArray getArrayAuthors = new JSONArray(details.getString("authors"));
                            for(int i = 0; i < getArrayAuthors.length(); i++)
                            {
                                JSONObject objects = getArrayAuthors.optJSONObject(i);
                                bookDetails.author = objects.getString("name");
                            }

                            // Set Cover Image
                            String coverImage = details.getString("covers");
                            coverImage = coverImage.replaceAll("\\p{P}","");
                            bookDetails.coverImage = "https://covers.openlibrary.org/b/id/" + coverImage + "-M.jpg";

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    books.add(bookDetails);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            ListView  listView;
            listView = (ListView)this.activity.findViewById(R.id.listviewOfBooks);
            BooksAdapter adapter = new BooksAdapter(this.context, books);
            listView.setAdapter(adapter);

            progressDialog.dismiss();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        super.onPostExecute(aVoid);
    }

}

