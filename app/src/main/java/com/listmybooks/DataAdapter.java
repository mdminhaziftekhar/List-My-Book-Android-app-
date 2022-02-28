package com.listmybooks;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class DataAdapter extends ArrayAdapter<bookDataClass> {

    private Handler mainHandler = new Handler();
    private Bitmap bitmap;

    public DataAdapter(@NonNull Context context, List<bookDataClass> books) {
        super(context, 0,  books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.newlist, parent, false);
        }

        bookDataClass currentData = getItem(position);

        //ImageView img = (ImageView) listItemView.findViewById(R.id.bookImage);
        TextView title = (TextView) listItemView.findViewById(R.id.bookTitle);
        TextView author = (TextView) listItemView.findViewById(R.id.bookAuthor);
        TextView date = (TextView) listItemView.findViewById(R.id.bookDate);

        //setting the title, author and date
        String author1 = "- ";
        author1 = author1 + currentData.getMbookAuthor();
        String date1 = "Publish Date : ";
        date1 += currentData.getMbookDate();
        title.setText(currentData.getMbookTitle());
        author.setText(author1);
        date.setText(date1);

        //get Image link
        String imageLink = currentData.getmImageLink();

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.bookImage);

//        new fetchImage(imageLink).start();

        //try
        InputStream inputStream = null;

        try{
            inputStream = new URL(imageLink).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e){
            e.printStackTrace();
        }
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap(bitmap);
            }
        });

        // new DownloadImageTask((ImageView) listItemView.findViewById(R.id.bookImage)).execute(imageLink);
        //For testing
//        imageView.setImageResource(R.mipmap.ic_launcher);

        return  listItemView;
    }

//    Class for loading image from url
//    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
//        ImageView bmImage;
//
//        public DownloadImageTask(ImageView bmImage) {
//            this.bmImage = bmImage;
//        }
//
//        protected Bitmap doInBackground(String... urls) {
//            String urldisplay = urls[0];
//            Bitmap mIcon11 = null;
//            try {
//                InputStream in = new java.net.URL(urldisplay).openStream();
//                mIcon11 = BitmapFactory.decodeStream(in);
//            } catch (Exception e) {
//                Log.e("Error", e.getMessage());
//                e.printStackTrace();
//            }
//            return mIcon11;
//        }
//
//        protected void onPostExecute(Bitmap result) {
//            bmImage.setImageBitmap(result);
//        }
//    }

//    private class fetchImage extends Thread{
//        private String URL;
//        private Bitmap bitmap;
//
//        fetchImage(String URL){
//            this.URL = URL;
//        }
//
//        @Override
//        public void run(){
//
//            InputStream inputStream = null;
//
//            try{
//                inputStream = new URL(URL).openStream();
//                bitmap = BitmapFactory.decodeStream(inputStream);
//            } catch (IOException e){
//                e.printStackTrace();
//            }
//
//            mainHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    binding.imageView.setImageBitmap(bitmap);
//                }
//            });
//
//        }
//    }



}
