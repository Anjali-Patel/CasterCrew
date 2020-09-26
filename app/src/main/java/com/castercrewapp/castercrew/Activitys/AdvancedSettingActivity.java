package com.castercrewapp.castercrew.Activitys;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.castercrewapp.castercrew.R;

public class AdvancedSettingActivity extends AppCompatActivity {
LinearLayout view_cantact,call_preferences,blocked_profiles,ignored_profiles,action_confirmation,linear_actionConfoirmation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_setting);
        view_cantact=findViewById(R.id.view_cantact);
        linear_actionConfoirmation=findViewById(R.id.linear_actionConfoirmation);
        call_preferences=findViewById(R.id.call_preferences);
        blocked_profiles=findViewById(R.id.blocked_profiles);
        ignored_profiles=findViewById(R.id.ignored_profiles);
        action_confirmation=findViewById(R.id.action_confirmation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Advance Settings");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        view_cantact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AdvancedSettingActivity.this,ViewAndContactActivity.class);
                startActivity(intent);
            }
        });
        call_preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AdvancedSettingActivity.this,CallPreferencesActivity.class);
                startActivity(intent);
            }
        });
        blocked_profiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AdvancedSettingActivity.this,BlockedProfilesActivity.class);
                startActivity(intent);
            }
        });
        ignored_profiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AdvancedSettingActivity.this,IgnoredProfilesActivity.class);
                startActivity(intent);
            }
        });
        action_confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_actionConfoirmation.setVisibility(View.VISIBLE);
            }
        });

    }
    @Override
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
    }
}