package com.example.narein.kitchen_inventory;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ViewAnimator;


public class MainActivity extends AppCompatActivity {

    String msg="Android:";static boolean STATE=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Log.d(msg,"onCreate::MainActivity");
    }
    @Override
    protected void onStart(){
        super.onStart();
        if(STATE){this.finishAffinity();System.exit(0);}
        //Log.d(msg,"onStart::MainActivity");
    }
    @Override
    protected void onResume(){
        super.onResume();
        //Log.d(msg,"onResume::MainActivity");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this,inventory.class);
                startActivity(i);
                overridePendingTransition(R.anim.from_middle, R.anim.to_middle);
            }
        }, 3000);
    }
    @Override
    protected void onPause(){
        super.onPause();
        //Log.d(msg,"onPause::MainActivity");
    }
    @Override
    protected void onStop(){
        super.onStop();
        //Log.d(msg,"onStop::MainActivity");
        STATE=true;
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        //Log.d(msg,"onDestroy::MainActivity");
    }
}
