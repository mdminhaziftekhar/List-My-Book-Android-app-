package com.listmybooks.Fragments;

import static com.listmybooks.MainActivity.Request_URL;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.listmybooks.DataAdapter;
import com.listmybooks.R;
import com.listmybooks.bookDataClass;
import com.listmybooks.bookLoader;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment  implements LoaderManager.LoaderCallbacks<List<bookDataClass>> {
    public FirstFragment() {
        // Required empty public constructor
    }

    private DataAdapter mAdapter;

//    private TextView mEmptytextView = getView().findViewById(R.id.EmptyText);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);

        //Find a reference to the listview
        ListView bookListView = (ListView) view.findViewById(R.id.listNew);

//        //EmptyView
//        bookListView.setEmptyView(mEmptytextView);

        //a new adapter that takes empty list of books
        mAdapter = new DataAdapter(getContext(), new ArrayList<bookDataClass>());

        //set the adapter on the listview so the list can be populated in the ui
        bookListView.setAdapter(mAdapter);

        //setting on item click listener on the listview
//        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                //Find the current book that was clicked on
//                bookDataClass currentBook = mAdapter.getItem(i);
//
//                //convert the string url into a URI object to pass into the intent constructor
//                Uri bookUri = Uri.parse(currentBook.getMlink());
//
//                //create a new intent to view the earthquake URI
//                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, bookUri);
//
//                //send the intent to launch a new activity
//                startActivity(websiteIntent);
//            }
//        });

        // Get a reference to the LoaderManager, in order to interact with loaders.
        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        LoaderManager.getInstance(this).initLoader(1, null, this);

        return view;
    }

    @NonNull
    @Override
    public Loader<List<bookDataClass>> onCreateLoader(int id, @Nullable Bundle args) {
        return new bookLoader(getContext(), Request_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<bookDataClass>> loader, List<bookDataClass> data) {
        //Hide loading indicator
//        View loadingIndicator = getView().findViewById(R.id.loading_indicator);
//        loadingIndicator.setVisibility(View.GONE);

        //Clear the adapter of previous data
        mAdapter.clear();

        //If there is a valid list of books then add them to adapters data set
        if(data != null && !data.isEmpty()){
            mAdapter.addAll(data);
        }

        //check if network is connected or not
//        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(getContext().CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//        if(activeNetwork == null){
//            //state that there is no internet connection
//            mEmptytextView.setText("No Internet Connection Found :( ");
//        } else if(activeNetwork != null && activeNetwork.isConnected()){
//            //There is internet but the list is empty
//            mEmptytextView.setText("No book data found :( ");
//        }
//        else{
//            //set the empty state text to display
//            mEmptytextView.setText("No Data found");
//        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<bookDataClass>> loader) {
        //Loader reset, so we can clear out our existing data
        mAdapter.clear();
    }
}