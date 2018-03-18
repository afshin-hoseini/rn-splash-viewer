package ir.afshin;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;


/**
 * Created by afshinhoseini on 3/18/18.
 */

public class SplashActivity extends Activity {

    private int backPressCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        overridePendingTransition(R.anim.no_anim, R.anim.no_anim);
        super.onCreate(savedInstanceState);

        getWindow().setBackgroundDrawableResource(R.drawable.splash_scene);
    }

    @Override
    protected void onResume() {

        super.onResume();

        this.registerReceiver(broadcastReceiver, new IntentFilter(SplashViewerModule.HIDE_SPLASH_SCENE));
        android.util.Log.e("SPLASH_SCENE", "Registered receiver");

        if(SplashViewerModule.isHideCalled) {

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {

                    SplashActivity.this.finish();
                }
            }, 800);
        }
    }

    @Override
    protected void onPause() {

        this.unregisterReceiver(broadcastReceiver);
        android.util.Log.e("SPLASH_SCENE", "Unregistered receiver");

        super.onPause();
    }

    @Override
    public void onBackPressed() {

        backPressCount ++;
        if(backPressCount > 2)
            super.onBackPressed();
    }

    @Override
    public void finish() {

        super.finish();
        overridePendingTransition(R.anim.no_anim, R.anim.splash_out);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            android.util.Log.e("SPLASH_SCENE", "Hide command recieved");

            if (SplashViewerModule.HIDE_SPLASH_SCENE.equals(intent.getAction())) {

                if(! SplashActivity.this.isFinishing())
                    SplashActivity.this.finish();
            }
        }
    };
}
