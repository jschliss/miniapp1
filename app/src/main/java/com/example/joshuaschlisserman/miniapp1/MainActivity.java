package com.example.joshuaschlisserman.miniapp1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private Movie mMovie;
    private Context mContext;
    private String mhasSeen;
    private MovieAdapter mMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        mhasSeen = new String();
        //data to display
        final ArrayList<Movie> movieList = Movie.getMoviesFromFile("movies.json", this);

        //create the adapter
        mMovieAdapter = new MovieAdapter(this, movieList);

        //find the listview in the layout
        //set the adapter to listview
        mListView = findViewById(R.id.movie_list_view);
        mListView.setAdapter(mMovieAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mMovie = movieList.get(position);
                Intent newIntent = new Intent(mContext, MovieDetailActivity.class);
                newIntent.putExtra("title", mMovie.title);
                newIntent.putExtra("poster", mMovie.poster);
                newIntent.putExtra("description", mMovie.description);
                startActivityForResult(newIntent, 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                mhasSeen = data.getStringExtra("Already Seen");
                mMovie.hSeen = mhasSeen;
                mMovieAdapter.notifyDataSetChanged();
            }
        }

    }

}