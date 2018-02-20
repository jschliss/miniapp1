package com.example.joshuaschlisserman.miniapp1;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by JoshuaSchlisserman on 2/10/18.
 */

public class MovieAdapter extends BaseAdapter {

    //adapter takes the app itself and a list of data to display
    //instance variables/fields

    private Context mContext;
    private ArrayList<Movie> mMovieList;
    private LayoutInflater mInflater;

    //constructor
    public MovieAdapter(Context mContext, ArrayList<Movie> mMovieList){

        //initialize instance variables
        this.mContext = mContext;
        this.mMovieList = mMovieList;
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    //methods
    //a list of methods we need to override

    //gives you number of movies in data source

    @Override
    public int getCount(){
        return mMovieList.size();
    }

    //returns the item at specific position in the data source
    @Override
    public Object getItem(int position){
        return mMovieList.get(position);
    }

    //returns the row id associated with the specific position in the list
    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;

        //check if the view already exists
        //if yes, you don't need to inflate and findViewbyID again
        if (convertView == null){
            //inflate
            convertView = mInflater.inflate(R.layout.list_item_movie, parent, false);
            //add the views to the holder
            holder = new ViewHolder();
            //views
            //put the view into the holder
            holder.titleTextView = convertView.findViewById(R.id.movie_list_title);
            holder.descriptionTextView = convertView.findViewById(R.id.movie_list_description);
            holder.mainCharactersTextView = convertView.findViewById(R.id.movie_list_main_characters);
            holder.hasSeenTextView = convertView.findViewById(R.id.movie_list_has_seen);
            holder.thumbnailImageView = convertView.findViewById(R.id.movie_list_thumbnail);
            //add the holder to the view
            //for future use
            convertView.setTag(holder);
        }
        else{
            //get the holder from convertView
            holder = (ViewHolder)convertView.getTag();
        }

        //get relevant subview of the row view
        TextView titleTextView = holder.titleTextView;
        TextView descriptionTextView = holder.descriptionTextView;
        TextView mainCharactersTextView = holder.mainCharactersTextView;
        TextView hasSeenTextView = holder.hasSeenTextView;
        ImageView thumbnailImageView = holder.thumbnailImageView;

        //get corresponding movie for each row

        Movie movie = (Movie)getItem(position);

        //update the row view's textviews and imageview to display the information


        //titleTextView
        titleTextView.setText(movie.title);

        //descriptionTextView
        descriptionTextView.setText(movie.description);

        //mainCharactersTextView
        //displays only three characters
        String mainCharacterString = new String();
        ArrayList<String> mainCharacterList = movie.main_characters;
        boolean lastCharacter = false;
        for (int j = 0; j < 3; j++) {
            if (!lastCharacter) {
                mainCharacterString += mainCharacterList.get(j) + ", ";
                //for less than 3 characters
                if (j == 1){
                    lastCharacter = true;
                }
            }
            else
                mainCharacterString += mainCharacterList.get(j);
        }
        mainCharactersTextView.setText(mainCharacterString);

        //hasSeenTextView
        hasSeenTextView.setText(movie.hSeen);
        if(movie.hSeen.equals("Already Seen")){
            hasSeenTextView.setTextColor(Color.parseColor("#ff3399"));
        }


        //imageView
        //use Picasso library to load image from the image url
        Picasso.with(mContext).load(movie.poster).into(thumbnailImageView);

        return convertView;
    }

    //viewHolder
    //is used to customize what you want to put into the view
    //it depends on the layout design of your row
    //this will be a private static class you have to define

    private static class ViewHolder{
        public TextView titleTextView;
        public TextView descriptionTextView;
        public TextView mainCharactersTextView;
        public TextView hasSeenTextView;
        public ImageView thumbnailImageView;
    }


    //intent is used to pass information between activities
    //intent -> package
    //sender, receiver

}
