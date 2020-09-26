package com.castercrewapp.castercrew.Activitys;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.castercrewapp.castercrew.R;

public class ChatActivity extends AppCompatActivity {
Button upgrading_now;
ImageView Send;
EditText Message;
String str_message;
ViewGroup viewGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        upgrading_now=findViewById(R.id.upgrading_now);
        Send=findViewById(R.id.Send);
        Message=findViewById(R.id.Message);
        upgrading_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ChatActivity.this);
                //ViewGroup viewGroup =context. findViewById(android.R.id.content);

                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.chat_toolbar, viewGroup, false);
                TextView upgrade=dialogView.findViewById(R.id.upgrade);
                TextView close=dialogView.findViewById(R.id.close);

                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();

                    }
                });
                upgrade.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(ChatActivity.this, MembershipPackage.class);
                        startActivity(intent);
                        alertDialog.dismiss();
                    }
                });
            }
        });
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            str_message=Message.getText().toString().trim();
            if(str_message.equalsIgnoreCase("")){
                Toast.makeText(ChatActivity.this,"Please enter some text",Toast.LENGTH_LONG).show();
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(ChatActivity.this);
                //ViewGroup viewGroup =context. findViewById(android.R.id.content);

                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.chat_toolbar, viewGroup, false);
                TextView upgrade=dialogView.findViewById(R.id.upgrade);
                TextView close=dialogView.findViewById(R.id.close);

                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();

                    }
                });
                upgrade.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(ChatActivity.this, MembershipPackage.class);
                        startActivity(intent);
                        alertDialog.dismiss();
                    }
                });

            }
            }
        });
    }
}
