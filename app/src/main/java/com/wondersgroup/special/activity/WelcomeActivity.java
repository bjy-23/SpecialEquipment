package com.wondersgroup.special.activity;

import android.content.Intent;
import android.os.Bundle;

import com.wondersgroup.special.R;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WelcomeActivity extends BaseActivity {
    private ScheduledExecutorService executor;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_welcome);
        setToolBarVisible(false);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.schedule(task, 1, TimeUnit.SECONDS);
    }

    private Runnable task = new Runnable() {

        @Override
        public void run() {
            startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
            finish();
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        executor.shutdownNow();
    }
}
