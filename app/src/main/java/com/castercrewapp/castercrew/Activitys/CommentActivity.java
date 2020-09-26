package com.castercrewapp.castercrew.Activitys;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.castercrewapp.castercrew.R;

public class CommentActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText message;
    TextView post;
String str_message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        message=findViewById(R.id.message);
        post=findViewById(R.id.post);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_message=message.getText().toString().trim();
                if(str_message.equalsIgnoreCase("")){
                    Toast.makeText(CommentActivity.this,"Please enter message",Toast.LENGTH_LONG).show();
                }else{

                }
            }
        });



    }
}
