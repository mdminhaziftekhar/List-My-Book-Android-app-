package com.example.listmybookspro;

import static com.example.listmybookspro.MainActivity.mUrlRequestGoogleBooks;

import android.content.Context;
import android.util.Log;

import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class DataLoader extends AsyncTaskLoader<List<NewBook>> {

    /**
     * Tag for log messages
     */
    private static final String LOG_TAG = DataLoader.class.getName();

    /**
     * Query URL
     */
    //private String mUrl;
    private String mUrl;

    /**
     * Constructs a new {@link DataLoader}.
     *
     * @param context of the activity
     * @param url     to load data from
     */
    public DataLoader(Context context, String url) {
        super(context);
        mUrl = url;

        Log.i(LOG_TAG, ": Loaded!");
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        Log.i("On start loading", ": Force loaded!");
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<NewBook> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of books.
        List<NewBook> books = QueryUtils.fetchBookData(mUrl);
        Log.i(LOG_TAG, ": Loaded in background!");
        return books;

    }

}
