package com.example.joshuaschlisserman.miniapp1;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by JoshuaSchlisserman on 2/10/18.
 */

public class Movie {

    //instance variables or fields
    //constructor
    //method

    public String title;
    public int episode_number;
    public ArrayList<String> main_characters;
    public String description;
    public String poster;
    public String url;
    public String hSeen;
    //constructor
    //default

    //method
    //static methods that the read the json file in and load into Recipe

    //static method that loads our recipes.json using the helper method
    //this method will return an array list of movies constructed from the JSON file
    public static ArrayList<Movie> getMoviesFromFile(String filename, Context context){
        ArrayList<Movie> movieList = new ArrayList<Movie>();

        //try to read from JSON file
        //get information by using the tags
        //construct a Movie Object for each movie in JSON
        //Add the object to arrayList
        //return arrayList
        try{
            String jsonString = loadJsonFromAsset("movies.json", context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray movies = json.getJSONArray("movies");

            //for loop to go through each movie in your movies array

            for(int i = 0; i < movies.length(); i++){
                Movie movie = new Movie();
                movie.title = movies.getJSONObject(i).getString("title");
                movie.description = movies.getJSONObject(i).getString("description");
                //main characters are stored in a Json Array
                JSONArray jsonArray = movies.getJSONObject(i).getJSONArray("main_characters");
                ArrayList<String> arrayList = new ArrayList<>();
                for(int j = 0; j <jsonArray.length(); j++){
                    arrayList.add(jsonArray.getString(j));
                }
                movie.main_characters = arrayList;
                //end of main characters
                movie.poster = movies.getJSONObject(i).getString("poster");
                movie.url = movies.getJSONObject(i).getString("url");
                movie.episode_number = movies.getJSONObject(i).getInt("episode_number");
                movie.hSeen = "Has Seen?";
                //add to arrayList
                movieList.add(movie);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieList;
    }

    //helper method that loads from any Json file
    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }
}
