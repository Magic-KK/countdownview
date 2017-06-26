# android 倒计时控件

 ## 1、添加
      compile 'com.github.zhangkexpz:Countdown:v1.0'

 ## 2、添加 
 
    allprojects{
    repositories {
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}

        VerificationCodeView button = (VerificationCodeView) findViewById(R.id.button);
        //开始倒计时  默认60s
        button.start(MainActivity.this);
        //开始倒计时,并设置倒计时时间
         button.start(MainActivity.this,100);
        //设置点击监听
        button.setOnCountDownListener(this);

        //倒计时时间
        @Override
        public void timeCountdown(int time) {
       
        }
       //倒计时停止
       @Override
       public void stop() {
       
       }
         
        


