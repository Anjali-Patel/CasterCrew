package com.castercrewapp.castercrew.Activitys;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.castercrewapp.castercrew.R;

public class AudioActivity extends AppCompatActivity  {

    LinearLayout other,events,ads,short_film,movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Audios");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        other=findViewById(R.id.other);
        events=findViewById(R.id.events);

        ads=findViewById(R.id.ads);

        movies=findViewById(R.id.movies);

        short_film=findViewById(R.id.short_film);


        movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AudioActivity.this,AudioPostDetailActivity.class);
                startActivity(intent);
            }
        });
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AudioActivity.this,AudioPostDetailActivity.class);
                startActivity(intent);
            }
        });
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AudioActivity.this,AudioPostDetailActivity.class);
                startActivity(intent);
            }
        });
        ads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AudioActivity.this,AudioPostDetailActivity.class);
                startActivity(intent);
            }
        });
        short_film.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AudioActivity.this,AudioPostDetailActivity.class);
                startActivity(intent);
            }
        });

    }
  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            return true;
        }else if (id == R.id.action_artist){

            Intent intent = new Intent(this, TalentPostListActivity.class);
            // intent.putExtra("cat","Artist");
            startActivity(intent);
//            Bundle bundle1 = new Bundle();
//                bundle1.putString("cat", "Artist");
//                HomeFragment categoryFragment = new HomeFragment();
//                categoryFragment.setArguments(bundle1);
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,categoryFragment).commit();
        }else if (id == R.id.action_profissional){
            Intent intent = new Intent(this, TalentPostListActivity.class);
            intent.putExtra("cat","Professionals");
            startActivity(intent);
        }
        else if (id == R.id.action_vender){
            Intent intent = new Intent(this, TalentPostListActivity.class);
            intent.putExtra("cat","vendors");
            startActivity(intent);
        }

        else if (id == R.id.action_inistitute){
            Intent intent = new Intent(this, TalentPostListActivity.class);
            intent.putExtra("cat","institutes");
            startActivity(intent);
        }else if (id == R.id.action_activist){
            Intent intent = new Intent(this, TalentPostListActivity.class);
            intent.putExtra("cat","activists");
            startActivity(intent);
        }else if (id == android.R.id.home){
            onBackPressed();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }*/

    @Override
    protected void onStart() {
        super.onStart();
    }





}

