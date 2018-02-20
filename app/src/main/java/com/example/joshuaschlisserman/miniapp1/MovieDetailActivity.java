package com.example.joshuaschlisserman.miniapp1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by JoshuaSchlisserman on 2/17/18.
 */

public class MovieDetailActivity extends AppCompatActivity{

    private Context mContext;
    private TextView mTitle;
    private TextView mDescription;
    private ImageView mPoster;
    private RadioGroup mButtonsGroup;
    private RadioButton mAlreadySeenButton;
    private RadioButton mWantToSeeButton;
    private RadioButton mDoNotLikeButton;
    private Button mSubmitButton;

    private Intent mIntent;
    private String mString;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        mContext = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        mTitle = findViewById(R.id.movie_detail_title);
        mDescription = findViewById(R.id.movie_detail_description);
        mPoster = findViewById(R.id.movie_detail_thumbnail);
        mButtonsGroup = findViewById(R.id.movie_detail_buttonGroup);
        mAlreadySeenButton = findViewById(R.id.movie_detail_alreadySeen);
        mWantToSeeButton = findViewById(R.id.movie_detail_wantToSee);
        mDoNotLikeButton = findViewById(R.id.movie_detail_doNotLike);
        mSubmitButton = findViewById(R.id.movie_detail_submitButton);

        //new intent
        Intent nIntent = getIntent();

        String title = this.getIntent().getExtras().getString("title");

        // set the title on the action bar
        setTitle(title);

        //Set texts for Description and Title
        mTitle.setText(nIntent.getStringExtra("title"));
        mDescription.setText(nIntent.getStringExtra("description"));
        //load images with new intent
        Picasso.with(mContext).load(nIntent.getStringExtra("poster")).into(mPoster);

        //On Checked Listener for Radio Buttons
        mButtonsGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.movie_detail_alreadySeen){
                    mString = "Already Seen";
                }
                else if(checkedId == R.id.movie_detail_wantToSee){
                    mString = "Want to See";
                }
                else if(checkedId == R.id.movie_detail_doNotLike){
                    mString = "Do Not Like";
                }
            }

        });

        //On Click Listener for Submit Buttons
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent();
                mIntent.putExtra("Already Seen", mString);
                setResult(RESULT_OK, mIntent);
                finish();

            }
        });

    }







}
