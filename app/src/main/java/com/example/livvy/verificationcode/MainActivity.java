package com.example.livvy.verificationcode;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.livvy.verificationcode.view.VerificationCodeView;

public class MainActivity extends Activity implements VerificationCodeView.Countdown {

    private VerificationCodeView button;
    private Button err;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init() {

        button = (VerificationCodeView) findViewById(R.id.button);

        err = (Button) findViewById(R.id.err);

        //开始倒计时  默认60s
        button.start(MainActivity.this, 10);
        //设置监听
        button.setOnCountDownListener(this);

        err.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.stopCountDown();
            }
        });

    }

    @Override
    public void timeCountdown(int time) {
        button.setText("验证码倒计时:" + time + "");
    }

    @Override
    public void stop() {
        button.setText("获取验证码");
    }
}
