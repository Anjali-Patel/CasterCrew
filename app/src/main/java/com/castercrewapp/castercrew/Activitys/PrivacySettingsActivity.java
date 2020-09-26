package com.castercrewapp.castercrew.Activitys;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.castercrewapp.castercrew.Fragments.HoroscopeFragment;
import com.castercrewapp.castercrew.Fragments.MobileFragment;
import com.castercrewapp.castercrew.Fragments.PhotoFragment;
import com.castercrewapp.castercrew.R;
import com.google.android.material.tabs.TabLayout;

public class PrivacySettingsActivity extends AppCompatActivity {
    TabLayout tabLayout;
    FrameLayout frameLayout;
    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        tabLayout=(TabLayout)findViewById(R.id.mTabLayout);
        frameLayout=(FrameLayout)findViewById(R.id.fragment_container);
        fragment = new MobileFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new MobileFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();


                        break;
                    case 1:
                        fragment = new PhotoFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
                        break;
//                    case 2:
//                        fragment = new HoroscopeFragment();
//                        fragmentManager = getSupportFragmentManager();
//                        fragmentTransaction = fragmentManager.beginTransaction();
//                        fragmentTransaction.replace(R.id.fragment_container, fragment);
//                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                        fragmentTransaction.commit();
//                        break;


                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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
            intent.putExtra("cat","Artist");
            startActivity(intent);
//            Bundle bundle1 = new Bundle();
//                bundle1.putString("cat", "Artist");
//                HomeFragment categoryFragment = new HomeFragment();
//                categoryFragment.setArguments(bundle1);
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,categoryFragment).commit();
        }else if (id == R.id.action_profissional){
            Intent intent1 = new Intent(this, TalentPostListActivity.class);
            intent1.putExtra("cat","Professionals");
            startActivity(intent1);
        }
        else if (id == R.id.action_vender){
            Intent intent2 = new Intent(this, TalentPostListActivity.class);
            intent2.putExtra("cat","vendors");
            startActivity(intent2);
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


