package com.listmybooks;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class bookLoader extends AsyncTaskLoader<List<bookDataClass>> {

    //Query URL
    private String mUrl;

    public bookLoader(@NonNull Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public List<bookDataClass> loadInBackground() {
       //Don't perform the request if there are no urls, or first one is null
        if(mUrl == null) return null;

        List<bookDataClass> booksResult = QueryUtils.fetchBookData(mUrl);
        return booksResult;
    }
}
