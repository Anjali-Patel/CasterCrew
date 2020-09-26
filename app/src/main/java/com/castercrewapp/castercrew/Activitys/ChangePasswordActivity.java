package com.castercrewapp.castercrew.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.castercrewapp.castercrew.R;

public class ChangePasswordActivity extends AppCompatActivity {
EditText enter_otp;
Button submit;
TextView resend;
String str_otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        enter_otp=findViewById(R.id.enter_otp);
        submit=findViewById(R.id.submit);
        resend=findViewById(R.id.resend);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_otp= enter_otp.getText().toString().trim();
            }
        });
        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
