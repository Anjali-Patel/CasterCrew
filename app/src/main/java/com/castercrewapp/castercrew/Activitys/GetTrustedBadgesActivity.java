package com.castercrewapp.castercrew.Activitys;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.castercrewapp.castercrew.R;

public class GetTrustedBadgesActivity extends AppCompatActivity {
Button facebook,profie_bage,salary_slip,education_certificate,choose_document;
    public static final int CAMERA_PIC_REQUEST = 1;
    ViewGroup viewGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_trusted_badges);
        facebook=findViewById(R.id.facebook);
        profie_bage=findViewById(R.id.profie_bage);
        salary_slip=findViewById(R.id.salary_slip);
        education_certificate=findViewById(R.id.education_certificate);
        choose_document=findViewById(R.id.choose_document);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Safe CasterCrew");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        facebook.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent myIntent = new Intent(GetTrustedBadgesActivity.this,SocialWebViewActivity.class);
                                            myIntent.putExtra("url","https://www.facebook.com/");
                                            startActivity(myIntent);
                                        }
                                    }
        );
        profie_bage.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent photo= new Intent("android.media.action.IMAGE_CAPTURE");
                                            startActivityForResult(photo, CAMERA_PIC_REQUEST);
                                        }
                                    }
        );
        salary_slip.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent photo= new Intent("android.media.action.IMAGE_CAPTURE");
                                            startActivityForResult(photo, CAMERA_PIC_REQUEST);
                                        }
                                    }
        );
        education_certificate.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent photo= new Intent("android.media.action.IMAGE_CAPTURE");
                                            startActivityForResult(photo, CAMERA_PIC_REQUEST);
                                        }
                                    }
        );
        choose_document.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(GetTrustedBadgesActivity.this);
                                            //ViewGroup viewGroup =context. findViewById(android.R.id.content);

                                            View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.identity_badge_layout, viewGroup, false);
                                            TextView passport=dialogView.findViewById(R.id.passport);
                                            TextView pan_card=dialogView.findViewById(R.id.pan_card);
                                            TextView driving_license=dialogView.findViewById(R.id.driving_license);

                                            builder.setView(dialogView);
                                            final AlertDialog alertDialog = builder.create();
                                            alertDialog.show();
                                            driving_license.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent photo= new Intent("android.media.action.IMAGE_CAPTURE");
                                                    startActivityForResult(photo, CAMERA_PIC_REQUEST);
                                                    alertDialog.dismiss();

                                                }
                                            });
                                            passport.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent photo= new Intent("android.media.action.IMAGE_CAPTURE");
                                                    startActivityForResult(photo, CAMERA_PIC_REQUEST);
                                                    alertDialog.dismiss();
                                                }
                                            });
                                            pan_card.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent photo= new Intent("android.media.action.IMAGE_CAPTURE");
                                                    startActivityForResult(photo, CAMERA_PIC_REQUEST);
                                                    alertDialog.dismiss();
                                                }
                                            });

                                        }
                                    }
        );



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
