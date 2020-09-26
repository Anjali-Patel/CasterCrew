package com.castercrewapp.castercrew.Activitys;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.castercrewapp.castercrew.R;

public class NotificationActivity extends AppCompatActivity  {
    TextView more_alerts,other_settings,premium,premium_description;
    LinearLayout linear_membership;
    View membership_view;
    Switch daily_recommendation,phone_number_views,profile_views,based_onactivity,new_matches,chats,shortlist,request;
    ViewGroup viewGroup;
TextView enable_all;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        more_alerts=findViewById(R.id.more_alerts);
        enable_all=findViewById(R.id.enable_all);
        other_settings=findViewById(R.id.other_settings);
        daily_recommendation=findViewById(R.id.daily_recommendation);
        phone_number_views=findViewById(R.id.phone_number_views);
        profile_views=findViewById(R.id.profile_views);

        based_onactivity=findViewById(R.id.based_onactivity);
        new_matches=findViewById(R.id.new_matches);
        chats=findViewById(R.id.chats);
        shortlist=findViewById(R.id.shortlist);
        request=findViewById(R.id.request);

if(daily_recommendation.isChecked()==false){
    enable_all.setVisibility(View.VISIBLE);


    enable_all.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            daily_recommendation.isChecked();
            phone_number_views.isChecked();
            profile_views.isChecked();
            based_onactivity.isChecked();
            new_matches.isChecked();
            chats.isChecked();
            shortlist.isChecked();
            request.isChecked();
            enable_all.setVisibility(View.GONE);

        }
    });
}else if(phone_number_views.isChecked()==false){
    enable_all.setVisibility(View.VISIBLE);
    enable_all.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            daily_recommendation.isChecked();
            phone_number_views.isChecked();
            profile_views.isChecked();
            based_onactivity.isChecked();
            new_matches.isChecked();
            chats.isChecked();
            shortlist.isChecked();
            request.isChecked();
            enable_all.setVisibility(View.GONE);

        }
    });
}else if(profile_views.isChecked()==false){
    enable_all.setVisibility(View.VISIBLE);
    enable_all.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            daily_recommendation.isChecked();
            phone_number_views.isChecked();
            profile_views.isChecked();
            based_onactivity.isChecked();
            new_matches.isChecked();
            chats.isChecked();
            shortlist.isChecked();
            request.isChecked();
            enable_all.setVisibility(View.GONE);

        }
    });
}else if(based_onactivity.isChecked()==false){
    enable_all.setVisibility(View.VISIBLE);
    enable_all.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            daily_recommendation.isChecked();
            phone_number_views.isChecked();
            profile_views.isChecked();
            based_onactivity.isChecked();
            new_matches.isChecked();
            chats.isChecked();
            shortlist.isChecked();
            request.isChecked();
            enable_all.setVisibility(View.GONE);

        }
    });
}else if(new_matches.isChecked()==false){
    enable_all.setVisibility(View.VISIBLE);
    enable_all.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            daily_recommendation.isChecked();
            phone_number_views.isChecked();
            profile_views.isChecked();
            based_onactivity.isChecked();
            new_matches.isChecked();
            chats.isChecked();
            shortlist.isChecked();
            request.isChecked();
            enable_all.setVisibility(View.GONE);

        }
    });
}else if(chats.isChecked()==false){
    enable_all.setVisibility(View.VISIBLE);
    enable_all.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            daily_recommendation.isChecked();
            phone_number_views.isChecked();
            profile_views.isChecked();
            based_onactivity.isChecked();
            new_matches.isChecked();
            chats.isChecked();
            shortlist.isChecked();
            request.isChecked();
            enable_all.setVisibility(View.GONE);

        }
    });
}else if(shortlist.isChecked()==false){
    enable_all.setVisibility(View.VISIBLE);
    enable_all.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            daily_recommendation.isChecked();
            phone_number_views.isChecked();
            profile_views.isChecked();
            based_onactivity.isChecked();
            new_matches.isChecked();
            chats.isChecked();
            shortlist.isChecked();
            request.isChecked();
            enable_all.setVisibility(View.GONE);

        }
    });
}else if(request.isChecked()==false){
    enable_all.setVisibility(View.VISIBLE);
    enable_all.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            daily_recommendation.isChecked();
            phone_number_views.isChecked();
            profile_views.isChecked();
            based_onactivity.isChecked();
            new_matches.isChecked();
            chats.isChecked();
            shortlist.isChecked();
            request.isChecked();
            enable_all.setVisibility(View.GONE);

        }
    });
}else{

}



        enable_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daily_recommendation.isChecked();
                phone_number_views.isChecked();
                profile_views.isChecked();
                based_onactivity.isChecked();
                new_matches.isChecked();
                chats.isChecked();
                shortlist.isChecked();
                request.isChecked();
                enable_all.setVisibility(View.GONE);

            }
        });
        daily_recommendation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AlertDialog.Builder builder = new AlertDialog.Builder(NotificationActivity.this);
                //ViewGroup viewGroup =context. findViewById(android.R.id.content);

                View dialogView = LayoutInflater.from(buttonView.getContext()).inflate(R.layout.notification_dialog, viewGroup, false);
                Button enable=dialogView.findViewById(R.id.enable);
                Button cancel=dialogView.findViewById(R.id.cancel);

                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();

                    }
                });
                enable.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        daily_recommendation.isChecked();
                        alertDialog.dismiss();
                    }
                });
            }

        });
        phone_number_views.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AlertDialog.Builder builder = new AlertDialog.Builder(NotificationActivity.this);
                //ViewGroup viewGroup =context. findViewById(android.R.id.content);

                View dialogView = LayoutInflater.from(buttonView.getContext()).inflate(R.layout.notification_dialog, viewGroup, false);
                Button enable=dialogView.findViewById(R.id.enable);
                Button cancel=dialogView.findViewById(R.id.cancel);


                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();

                    }
                });
                enable.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        phone_number_views.isChecked();
                        alertDialog.dismiss();
                    }
                });
            }

        });
        profile_views.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AlertDialog.Builder builder = new AlertDialog.Builder(NotificationActivity.this);
                //ViewGroup viewGroup =context. findViewById(android.R.id.content);

                View dialogView = LayoutInflater.from(buttonView.getContext()).inflate(R.layout.notification_dialog, viewGroup, false);
                Button enable=dialogView.findViewById(R.id.enable);
                Button cancel=dialogView.findViewById(R.id.cancel);

                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();

                    }
                });
                enable.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        profile_views.isChecked();
                        alertDialog.dismiss();
                    }
                });
            }

        });

        premium=findViewById(R.id.premium);

        premium_description=findViewById(R.id.premium_description);

        linear_membership=findViewById(R.id.linear_membership);

        membership_view=findViewById(R.id.membership_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        more_alerts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(NotificationActivity.this,MoreAlertsActivity.class);
                startActivity(intent);
            }
        });


        other_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                other_settings.setVisibility(View.GONE);
                linear_membership.setVisibility(View.VISIBLE);
                membership_view.setVisibility(View.VISIBLE);

                premium.setVisibility(View.VISIBLE);

                premium_description.setVisibility(View.VISIBLE);

            }
        });






    }
}
