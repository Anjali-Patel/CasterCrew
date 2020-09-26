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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.castercrewapp.castercrew.R;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

public class DeleteAccountActivity extends AppCompatActivity {
LinearLayout expandableButton1,expandableButton2,expandableButton3;
ImageView img1,img2,img3;
TextView need_help1,need_help2;
    ExpandableRelativeLayout expandableLayout1,expandableLayout2,expandableLayout3;
    LinearLayout linear_marri_radio1,linear_marri_radio2,linear_marri_radio3;
    LinearLayout linear1,linear2,linear3,linear4,other_reason_click4,other_reason_click3,other_reason_click2,other_reason_click1;
TextView click1,click2,click3;
ViewGroup viewGroup;
RadioGroup radio1,radio2,radio3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        need_help1=findViewById(R.id.need_story1);
        need_help2=findViewById(R.id.need_help2);
        linear_marri_radio1=findViewById(R.id.linear_marri_radio1);
        linear_marri_radio2=findViewById(R.id.linear_marri_radio2);
        linear_marri_radio3=findViewById(R.id.linear_marri_radio3);

        radio1=findViewById(R.id.radio1);
        radio2=findViewById(R.id.radio2);
        radio3=findViewById(R.id.radio3);
        linear1=findViewById(R.id.linear1);
        linear2=findViewById(R.id.linear2);
        linear3=findViewById(R.id.linear3);
        linear4=findViewById(R.id.linear4);
        click1=findViewById(R.id.click1);
        click2=findViewById(R.id.click2);
        click3=findViewById(R.id.click3);

        expandableButton1=findViewById(R.id.expandableButton1);
        expandableButton2=findViewById(R.id.expandableButton2);
        img1=findViewById(R.id.img1);
        img2=findViewById(R.id.img2);
        img3=findViewById(R.id.img3);
        expandableButton3=findViewById(R.id.expandableButton3);
        expandableLayout1=findViewById(R.id.expandableLayout1);
        expandableLayout2=findViewById(R.id.expandableLayout2);
        expandableLayout3=findViewById(R.id.expandableLayout3);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Delete Account");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        expandableButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableLayout1.toggle();
                img1.setImageResource(R.drawable.ic_expand_less_black_24dp);
            }
        });
        expandableButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableLayout2.toggle();
                img2.setImageResource(R.drawable.ic_expand_less_black_24dp);
                radio2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch(checkedId){
                            case R.id.marri_radio1:
                                linear_marri_radio1.setVisibility(View.VISIBLE);
                                linear_marri_radio2.setVisibility(View.GONE);
                                linear_marri_radio3.setVisibility(View.GONE);
                                break;

                            case R.id.marri_radio2:
                                linear_marri_radio2.setVisibility(View.VISIBLE);
                                linear_marri_radio1.setVisibility(View.GONE);
                                linear_marri_radio3.setVisibility(View.GONE);
                                break;
                            case R.id.marri_radio3:
                                linear_marri_radio3.setVisibility(View.VISIBLE);
                                linear_marri_radio1.setVisibility(View.GONE);
                                linear_marri_radio2.setVisibility(View.GONE);
                                break;
                        }

                    }
                });
            }
        });
        expandableButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableLayout3.toggle();
                img3.setImageResource(R.drawable.ic_expand_less_black_24dp);
            }
        });
        need_help1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DeleteAccountActivity.this);
                //ViewGroup viewGroup =context. findViewById(android.R.id.content);

                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.story_dialog, viewGroup, false);
                Button cancel = dialogView.findViewById(R.id.cancel);
                Button ok = dialogView.findViewById(R.id.ok);

                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();

                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        alertDialog.dismiss();
                    }
                });
            }
        });
        need_help2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DeleteAccountActivity.this);
                //ViewGroup viewGroup =context. findViewById(android.R.id.content);

                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.story_dialog, viewGroup, false);
                Button cancel = dialogView.findViewById(R.id.cancel);
                Button ok = dialogView.findViewById(R.id.ok);

                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();

                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        alertDialog.dismiss();
                    }
                });
            }
        });

        click1.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          AlertDialog.Builder builder = new AlertDialog.Builder(DeleteAccountActivity.this);
                                          //ViewGroup viewGroup =context. findViewById(android.R.id.content);

                                          View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.delete_profile_dialog, viewGroup, false);
                                          TextView cancel = dialogView.findViewById(R.id.cancel);
                                          TextView delete = dialogView.findViewById(R.id.delete);

                                          builder.setView(dialogView);
                                          final AlertDialog alertDialog = builder.create();
                                          alertDialog.show();
                                          delete.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  alertDialog.dismiss();

                                              }
                                          });
                                          cancel.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {

                                                  alertDialog.dismiss();
                                              }
                                          });
                                      }
                                  });
                click2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(DeleteAccountActivity.this);
                        //ViewGroup viewGroup =context. findViewById(android.R.id.content);

                        View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.delete_profile_dialog, viewGroup, false);
                        TextView cancel = dialogView.findViewById(R.id.cancel);
                        TextView delete = dialogView.findViewById(R.id.delete);

                        builder.setView(dialogView);
                        final AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        delete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.dismiss();

                            }
                        });
                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                alertDialog.dismiss();
                            }
                        });
                    }});


        click3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DeleteAccountActivity.this);
                //ViewGroup viewGroup =context. findViewById(android.R.id.content);

                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.delete_profile_dialog, viewGroup, false);
                TextView cancel = dialogView.findViewById(R.id.cancel);
                TextView delete = dialogView.findViewById(R.id.delete);

                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();

                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        alertDialog.dismiss();
                    }
                });

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