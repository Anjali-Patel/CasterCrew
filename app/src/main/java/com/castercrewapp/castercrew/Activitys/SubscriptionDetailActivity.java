package com.castercrewapp.castercrew.Activitys;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.castercrewapp.castercrew.MainActivity;
import com.castercrewapp.castercrew.R;

public class SubscriptionDetailActivity extends AppCompatActivity {
Button done;
TextView validity,pack_name,ammount,u_uid,transcation_id;
String strvalidity="",strAmmount="",strUid="",strPackage="",str_transaction_id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Subscription Detail");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        str_transaction_id=getIntent().getStringExtra("transaction_id");
        strvalidity=getIntent().getStringExtra("validity");
        strAmmount=getIntent().getStringExtra("transaction_ammount");

        strPackage=getIntent().getStringExtra("membership");
        strUid=getIntent().getStringExtra("uid");
        transcation_id=findViewById(R.id.transcation_id);
        done=findViewById(R.id.done);
        validity=findViewById(R.id.validity);
        pack_name=findViewById(R.id.pack_name);
        ammount=findViewById(R.id.ammount);
        u_uid=findViewById(R.id.u_uid);
        validity.setText(strvalidity+"days");
        pack_name.setText(strPackage);
        ammount.setText("Rs."+strAmmount);
        u_uid.setText(strUid);
        transcation_id.setText(str_transaction_id);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SubscriptionDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}