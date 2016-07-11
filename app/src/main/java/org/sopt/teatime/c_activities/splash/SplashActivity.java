package org.sopt.teatime.c_activities.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import org.sopt.teatime.R;
import org.sopt.teatime.a_others.ApplicationController;
import org.sopt.teatime.c_activities.main.view.MainActivity;

import java.lang.ref.WeakReference;

public class SplashActivity extends AppCompatActivity {

    private final TypefaceHandler handler = new TypefaceHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ApplicationController.getInstance().buildTypefaces();
                handler.sendEmptyMessage(0);
            }
        });

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                thread.start();
            }
        }, 1000);
    }

    private static class TypefaceHandler extends Handler {
        private final WeakReference<SplashActivity> mActivity;

        public TypefaceHandler(SplashActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            SplashActivity activity = mActivity.get();
            if (activity != null) {
                Intent intent = new Intent(activity.getApplicationContext(), MainActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }
        }
    }
}
