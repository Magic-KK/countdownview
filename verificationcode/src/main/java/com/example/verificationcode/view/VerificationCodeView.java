package com.example.verificationcode.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;


/**
 * name: zk
 * content: 验证码按钮控件
 * date:  2017/06/26 04:38 PM
 * email:358238964@qq.com
 * version:1.0
 */

public class VerificationCodeView extends Button {


    /**
     * 倒计时默认时间  60s
     */
    private int mCountdowntime = 60;

    /**
     * 动态记录倒计时时间
     */
    private int mNowtime = 0;

    //设置timertask
    TimerTask validateTask = null;
    Timer validateTimer = new Timer();
    private Activity mActivity;

    /**
     * 是否开始
     */
    private boolean isstart;

    public Countdown getOnCountDownListener() {
        return onCountDownListener;
    }

    public void setOnCountDownListener(Countdown onCountDownListener) {
        this.onCountDownListener = onCountDownListener;
    }

    private Countdown onCountDownListener;


    public VerificationCodeView(Context context) {
        super(context);

    }

    public VerificationCodeView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public VerificationCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    /**
     * 验证码倒计时开始
     *
     * @param activity 当前activity
     */
    public void start(final Activity activity) {
        //获取按钮点击监听
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCountDownListener != null) {
                    isstart = onCountDownListener.beforeStart();
                }
                if (!isstart) {
                    return;
                }

                mActivity = activity;
                setValidateCodeTime();
            }
        });
    }

    /**
     * 验证码倒计时开始,设置倒计时时间
     *
     * @param activity 当前activity
     * @param time     单位 s
     */
    public void start(final Activity activity, final int time) {
        //获取按钮点击监听
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onCountDownListener != null) {
                    isstart = onCountDownListener.beforeStart();
                }

                if (!isstart) {
                    return;
                }

                mCountdowntime = time;
                mActivity = activity;
                setValidateCodeTime();
            }
        });
    }

    /**
     * 倒计时停止
     */
    public void stopCountDown() {
        if (validateTask == null) {
            return;
        }

        validateTask.cancel();
        setEnabled(true);
        mNowtime = mCountdowntime;
        if (onCountDownListener != null) {
            onCountDownListener.stop();
        }
    }

    public interface Countdown {

        /**
         * 倒计时开始之前的逻辑
         *
         * @return true 倒计时开始 false 倒计时结束
         */
        boolean beforeStart();

        /**
         * 倒计时剩余时间
         *
         * @param time
         */
        void timeCountdown(int time);

        /**
         * 倒计时停止
         */
        void stop();
    }


    /**
     * 设置获取验证码间隔时间
     */
    private void setValidateCodeTime() {
        mNowtime = mCountdowntime;
        validateTask = new TimerTask() {
            @Override
            public void run() {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mNowtime <= 0) {
                            setEnabled(true);
                            if (onCountDownListener != null) {
                                onCountDownListener.stop();
                            }
                            validateTask.cancel();
                        } else {
                            setEnabled(false);
                            if (onCountDownListener != null) {
                                onCountDownListener.timeCountdown(mNowtime);
                            }
                        }
                        mNowtime--;
                    }
                });
            }
        };
        mNowtime = mNowtime - 1;
        validateTimer.schedule(validateTask, 0, 1000);
    }
}
