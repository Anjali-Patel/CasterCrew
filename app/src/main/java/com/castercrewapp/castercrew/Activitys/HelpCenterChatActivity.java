package com.castercrewapp.castercrew.Activitys;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.castercrewapp.castercrew.R;

public class HelpCenterChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Inbox");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
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
        }

        return super.onOptionsItemSelected(item);
    }

}
