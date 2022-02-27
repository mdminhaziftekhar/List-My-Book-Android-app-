package com.listmybooks;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {

    private QueryUtils(){}

    private static URL createUrl(String stringUrl){
        URL url = null;

        try{
            url = new URL(stringUrl);
        } catch(MalformedURLException e){
            Log.e("Problem in url", "Problem Building the URL", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException{
        String jsonResponse = "";

        //If the url is null then return early
        if(url == null) return jsonResponse;

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(1000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //If the request was succesful (response code 200)
            //then read the input stream and parse the response
            if(urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
            else{
                Log.e("Query", "Error Response Code : "+urlConnection.getResponseCode());
            }
        } catch (IOException e){
            Log.e("Query", "Problem retrieving the earthquake JSON response", e);
        } finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(inputStream != null){
                inputStream.close();
            }
        }

        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();

        if(inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while(line != null){
                output.append(line);
                line = reader.readLine();
            }
        }

        return output.toString();
    }

    public static List<bookDataClass> extractFeatureFromJson(String bookJson){
        //If the JSON String is empty or null, then return early
        if(TextUtils.isEmpty(bookJson)) return null;

        //create empty ArrayList
        List<bookDataClass> books = new ArrayList<bookDataClass>();

        //Parse JSON
        try{
            //Create JSON Object
            JSONObject baseJsonResponse = new JSONObject(bookJson);

            //extract JSONArray assosiated with the key items
            JSONArray itemsArray = baseJsonResponse.optJSONArray("items");

            //For each position
            for(int i = 0; i<itemsArray.length(); i++){
                //get a single book at position i
                JSONObject currentBook = itemsArray.optJSONObject(i);

                //get volumeInfo JSONObject
                JSONObject volumeInfo = currentBook.optJSONObject("volumeInfo");

                //Extract String value Title
                String title = volumeInfo.optString("title");

                //get array named author
                JSONArray authorArray = volumeInfo.optJSONArray("authors");

                //get authors name
                String author = authorArray.optString(0);

                //publish date
                String date = volumeInfo.optString("publishedDate");

                //Object called imageLinks
                JSONObject imageLinks = volumeInfo.optJSONObject("imageLinks");
                //string thumbnail
                String image = imageLinks.optString("thumbnail");

                //Preview link
                String link = volumeInfo.optString("canonicalVolumeLink");

                books.add(new bookDataClass(image, title, author, date, link));
            }

        } catch (JSONException e){
            Log.e("QueryUtils", "Problem parsing the data", e);

        }

        return books;
    }

    public static List<bookDataClass> fetchBookData(String requestUrl){
        //Create url object
        URL url = createUrl(requestUrl);

        //perform HTTP request to the URL and receive a JSON Response
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e){
            Log.e("URL fetching", "Problem making the HTTP request", e);
        }

        //Extract relevant fields from the JSON response and create a list of books
        List<bookDataClass> books = extractFeatureFromJson(jsonResponse);

        //Return the list of books
        return books;
    }
}
