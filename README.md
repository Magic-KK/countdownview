# android 倒计时控件

 ## 1、添加
      compile 'com.github.zhangkexpz:Countdown:v1.0.1'

 ## 2、添加 
 
    allprojects{
    repositories {
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}


## 3、添加
        <com.example.verificationcode.view.VerificationCodeView
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="验证码点击" />


        //开启按钮监听
        button.setOnCountDownListener(this);

        //开始倒计时,并获得按钮点击事件
        button.start(MainActivity.this, 10);
        
        //停止倒计时
        button.stopCountDown();
        
        
        
        
     /**
     * 倒计时开始之前的逻辑,用来判断验证码是否开始执行
     *
     * @return true 开始执行  false不执行
     */
    @Override
    public boolean beforeStart() {
        //用来写一些逻辑判断
        return false;
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
         
        


