package com.example.prateekpande.patientmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadStartupData();
    }

    /**
     * This method loads initially
     * required data.
     */
    public void loadStartupData(){

        //TODO:load app startup data
        Thread thread = new Thread(){

            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
            }
        };
        thread.start();
        // remove the above code
        loadHomeActivity();
    }

    /**
     * This method fires an intent to start
     * home activity.
     */
    public void loadHomeActivity(){

        startActivity(new Intent(SplashScreen.this,HomeActivity.class));
    }
}
