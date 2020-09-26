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
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.castercrewapp.castercrew.R;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.razorpay.PaymentResultListener;

public class PaymentModeActivity extends AppCompatActivity  {
    LinearLayout neft_rtgs,pay_atBranch;
    LinearLayout expandableButton1,expandableButton2,expandableButton3;
    ExpandableRelativeLayout expandableLayout1,expandableLayout2,expandableLayout3;
    LinearLayout chat,call;
    ViewGroup viewGroup;
    EditText expiry_month,expiry_year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_mode);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        neft_rtgs=findViewById(R.id.neft_rtgs);
        expiry_month=findViewById(R.id.expiry_month);

        expiry_year=findViewById(R.id.expiry_year);
        pay_atBranch=findViewById(R.id.pay_atBranch);
        expandableLayout1=findViewById(R.id.expandableLayout1);
        expandableLayout2=findViewById(R.id.expandableLayout2);
        expandableLayout3=findViewById(R.id.expandableLayout3);
        expandableButton1=findViewById(R.id.expandableButton1);
        expandableButton2=findViewById(R.id.expandableButton2);
        expandableButton3=findViewById(R.id.expandableButton3);
        call=findViewById(R.id.call);
        chat=findViewById(R.id.chat);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + "9490009305" ));
                startActivity(callIntent);
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PaymentModeActivity.this);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.chat_dialog, viewGroup, false);
                ImageView close=dialogView.findViewById(R.id.cross);
                final ImageView up_expand=dialogView.findViewById(R.id.up_expand);
                up_expand.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        up_expand.setImageResource(R.drawable.expand_down);
                    }});
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }});
            }});
        expiry_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PaymentModeActivity.this);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.expiry_year_dialog, viewGroup, false);
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }});
        expiry_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PaymentModeActivity.this);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.expiry_monthdialog, viewGroup, false);
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }});
        pay_atBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten= new Intent(PaymentModeActivity.this,HelpcenetrBranchLocatorActivity.class);
                startActivity(inten);
            }
        });
        neft_rtgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten= new Intent(PaymentModeActivity.this,PaymentActivity.class);
                startActivity(inten);
            }
        });
        expandableButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableLayout1.toggle();
            }
        });
        expandableButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableLayout2.toggle();
            }
        });
        expandableButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableLayout3.toggle();
            }
        });
    }


}
