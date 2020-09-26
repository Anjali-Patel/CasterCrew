package com.castercrewapp.castercrew.Activitys;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.castercrewapp.castercrew.R;

public class SafeCasterCrewActivity extends AppCompatActivity {
TextView privacy_policy;
LinearLayout call_layout,linear_message,linear1,linear2;
ViewGroup viewGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_caster_crew);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        privacy_policy=findViewById(R.id.privacy_policy);
        call_layout=findViewById(R.id.call_layout);
        linear_message=findViewById(R.id.linear_message);
        linear1=findViewById(R.id.linear1);
        linear2=findViewById(R.id.linear2);
        linear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent= new Intent(SafeCasterCrewActivity.this,FraudnessActivity.class);
             startActivity(intent);
            }
        });
        linear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent= new Intent(SafeCasterCrewActivity.this,ReportProfileActivity.class);
             startActivity(intent);
            }
        });
        linear_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SafeCasterCrewActivity.this);
                //ViewGroup viewGroup =context. findViewById(android.R.id.content);

                View dialogView = LayoutInflater.from(SafeCasterCrewActivity.this).inflate(R.layout.safedialog, viewGroup, false);
                TextView cancel=dialogView.findViewById(R.id.cancel);
                TextView send=dialogView.findViewById(R.id.send);
                EditText report_castercrew=dialogView.findViewById(R.id.report_castercrew);

                EditText message=dialogView.findViewById(R.id.message);

                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String strreport,str_message;
                        strreport=report_castercrew.getText().toString().trim();
                        str_message=message.getText().toString().trim();
                        if (strreport.equalsIgnoreCase("") || str_message.equalsIgnoreCase("")) {
                            Toast.makeText(SafeCasterCrewActivity.this,"ID or message not entered.Please enter",Toast.LENGTH_LONG).show();
                        }
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
        call_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + "9490009305" ));
                startActivity(callIntent);
            }
        });
        privacy_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent= new Intent(SafeCasterCrewActivity.this,PrivacyPolicyActivity.class);
//                startActivity(intent);
                Uri webpage = Uri.parse("https://castercrew.com/profile/content_page/Privacy_Policy/3");
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }else{
                    Toast.makeText(SafeCasterCrewActivity.this,"Page not found",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
