package com.example.livvy.verificationcode;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ShowableListMenu;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.verificationcode.view.VerificationCodeView;


public class MainActivity extends Activity implements VerificationCodeView.Countdown {


    private VerificationCodeView button;
    private EditText phone;
    private Button btn_err;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        button = (VerificationCodeView) findViewById(R.id.button);
        phone = (EditText) findViewById(R.id.phone);
        btn_err = (Button) findViewById(R.id.btn_err);


        //开启按钮监听
        button.setOnCountDownListener(this);

        //开始倒计时,并获得按钮点击事件
        button.start(MainActivity.this);


        btn_err.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //停止验证码
                button.stopCountDown();
            }
        });
    }

    /**
     * 倒计时开始之前的逻辑,用来判断验证码是否开始执行
     *
     * @return true 开始执行  false不执行
     */
    @Override
    public boolean beforeStart() {
        String mphone = phone.getText().toString().trim();

        if (TextUtils.isEmpty(mphone)) {
            Toast.makeText(MainActivity.this, "手机号输入不能为空", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    /**
     * 倒计时秒
     *
     * @param time
     */
    @Override
    public void timeCountdown(int time) {
        button.setText("验证码倒计时:" + time + "");
    }

    /**
     * 倒计时停止的逻辑
     */
    @Override
    public void stop() {
        button.setText("获取验证码");
    }
}
