package com.example.oldageparadise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;


public class ConsultPhysiatrist extends AppCompatActivity implements View.OnClickListener {


    ImageButton psyCall1, psyCall2,psyCall3,psyCall4,psyCall5,psyCall6 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_physiatrist);


        psyCall1 = findViewById(R.id.psy_call1);
        psyCall2 = findViewById(R.id.psy_call2);
        psyCall3 = findViewById(R.id.psy_call3);
        psyCall4 = findViewById(R.id.psy_call4);
        psyCall5 = findViewById(R.id.psy_call5);
        psyCall6 = findViewById(R.id.psy_call6);
        psyCall1.setOnClickListener(this);
        psyCall2.setOnClickListener(this);
        psyCall3.setOnClickListener(this);
        psyCall4.setOnClickListener(this);
        psyCall5.setOnClickListener(this);
        psyCall6.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.psy_call1){
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+8801611216232"));
            startActivity(intent);
        }
        else if(view.getId()==R.id.psy_call2){
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:10606"));
            startActivity(intent);
        } else if(view.getId()==R.id.psy_call3){
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+8809610010615"));
            startActivity(intent);
        } else if(view.getId()==R.id.psy_call4){
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+8809613787801"));
            startActivity(intent);
        } else if(view.getId()==R.id.psy_call5){
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:10616"));
            startActivity(intent);
        } else if(view.getId()==R.id.psy_call6){
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+8809613787803"));
            startActivity(intent);
        }
    }
}