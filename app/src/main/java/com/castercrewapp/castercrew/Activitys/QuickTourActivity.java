package com.castercrewapp.castercrew.Activitys;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import me.relex.circleindicator.CircleIndicator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;

import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.adapter.QuickTourAdapter;

import java.util.ArrayList;

public class QuickTourActivity extends AppCompatActivity {


    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] XMEN= {/*R.drawable.screenone,R.drawable.screentwo,R.drawable.screen_three,R.drawable.screen_four,R.drawable.screen_five,R.drawable.screen_six*/};
    private static final String[]title={/*"Protect Your Child","Safe Your Parents","Track The L:ocation","Connect to Family"*/};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();

    @SuppressLint("NewApi")


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_tour);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        init();

    }

    private void init() {
        for(int i=0;i<XMEN.length;i++)
            XMENArray.add(XMEN[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new QuickTourAdapter(QuickTourActivity.this,XMENArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };

    }


}
