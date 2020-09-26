package com.castercrewapp.castercrew.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.castercrewapp.castercrew.R;

public class RecommendedActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView like,dislike,whatsapp,facebook,share,comment;
    Button view_profile;
    LinearLayout title;
    ImageView profile_pic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended);

        title=findViewById(R.id.title);
        profile_pic=findViewById(R.id.profile_pic);
        profile_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RecommendedActivity.this,NewsPosterResultActivity.class);
                intent.putExtra("name","NewsTrack Live");
                startActivity(intent);
            }
        });
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RecommendedActivity.this,NewsPosterResultActivity.class);
                intent.putExtra("name","NewsTrack Live");


                startActivity(intent);
            }
        });


        view_profile=findViewById(R.id.view_profile);
        comment=findViewById(R.id.comment);
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RecommendedActivity.this,CommentActivity.class);
                startActivity(intent);
            }
        });

        like=findViewById(R.id.like);
        dislike=findViewById(R.id.dislike);
        whatsapp=findViewById(R.id.whatsapp);
        facebook=findViewById(R.id.facebook);
        share=findViewById(R.id.share);
        view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(RecommendedActivity.this,SocialWebViewActivity.class);
                myIntent.putExtra("url","https://www.instagram.com/samantharuthprabhuoffl/?utm_source=ig_embed");
                startActivity(myIntent);
            }
        });
        like.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                Toast.makeText(RecommendedActivity.this,"We will recommended more articles you like",Toast.LENGTH_LONG).show();
            }
        });
        dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RecommendedActivity.this," Thanks for your feedback,we will deal it with soon",Toast.LENGTH_LONG).show();
            }
        });
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String  image_path = item.getFull_name();
//                String website_url=item.getArtist_name()+","+item.getTalent_title()+","+item.getReg_dt();
//                title=Items.getTitle();

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("image/png");
//                i.putExtra(Intent.EXTRA_SUBJECT, title);
//                i.putExtra(Intent.EXTRA_TEXT, image_path+"\n \n"+website_url);
//                i.putExtra(Intent.EXTRA_TEXT,image_path);
                startActivity(Intent.createChooser(i, "Via "));

            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String  image_path = item.getFull_name();
//                String website_url=item.getArtist_name()+","+item.getTalent_title()+","+item.getReg_dt();
//                title=Items.getTitle();

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("image/png");
//                i.putExtra(Intent.EXTRA_SUBJECT, title);
//                i.putExtra(Intent.EXTRA_TEXT, image_path+"\n \n"+website_url);
//                i.putExtra(Intent.EXTRA_TEXT,image_path);
                startActivity(Intent.createChooser(i, "Via "));
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String  image_path = item.getFull_name();
//                String website_url=item.getArtist_name()+","+item.getTalent_title()+","+item.getReg_dt();
//                title=Items.getTitle();

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("image/png");
//                i.putExtra(Intent.EXTRA_SUBJECT, title);
//                i.putExtra(Intent.EXTRA_TEXT, image_path+"\n \n"+website_url);
//                i.putExtra(Intent.EXTRA_TEXT,image_path);
                startActivity(Intent.createChooser(i, "Via "));
            }
        });





    }
}
