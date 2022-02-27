package com.listmybooks;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.InputStream;
import java.util.List;

public class DataAdapter extends ArrayAdapter<bookDataClass> {

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

        new DownloadImageTask((ImageView) listItemView.findViewById(R.id.bookImage)).execute(imageLink);

        return  listItemView;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
