package com.example.dynamicworkscheduler;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SignIn extends AppCompatActivity {

    TextView sign_in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        sign_in = findViewById(R.id.sign_up_TV);

        Toast.makeText(this, sign_in.getText(), Toast.LENGTH_SHORT).show();
        sign_in.setTextColor(getResources().getColor(R.color.teal_200));
//        SpannableStringBuilder spannable = new SpannableStringBuilder("kjashdhdfasdkjfouw");
//        spannable.setSpan(  new ForegroundColorSpan(Color.RED),
//                2, // start
//                9, // end
//                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//        );
//        ClickableSpan clickableSpan = new ClickableSpan() {
//            @Override
//            public void onClick(@NonNull View view) {
//                startActivity(new Intent(SignIn.this, CreateTask.class));
//            }
//        };
    }

    public void openSignUpPage(View view)
    {
        startActivity(new Intent(SignIn.this, CreateTask.class));
        finish();
    }
}