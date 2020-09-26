package com.castercrewapp.castercrew.Activitys;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.castercrewapp.castercrew.R;

public class ViewAndContactActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radio1,radio2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_and_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        radioGroup=findViewById(R.id.radioGroup);
        radio1=findViewById(R.id.radio1);
        radio2=findViewById(R.id.radio2);
    }
}
