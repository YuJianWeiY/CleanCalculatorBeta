package com.example.cleancalculatorbeta;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.ImageDecoder;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private Vibrator vibrator;
    private final long VIBRATION_DURATION = 100; // 震动持续时间100毫秒


    private String first_num="";//第一个操作数
    private String next_num="";//下一个操作数
    private String operator="";//运算符
    private TextView tv_result;//声明一个文本视图对象
    private String result="";//当前的计算结果
    private  String showText="";//显示的文本内容


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        Button button_0=findViewById(R.id.button_0);//获取按钮0实例
        Button button_1=findViewById(R.id.button_1);//获取按钮1实例
        Button button_2=findViewById(R.id.button_2);//获取按钮2实例
        Button button_3=findViewById(R.id.button_3);//获取按钮3实例
        Button button_4=findViewById(R.id.button_4);//获取按钮4实例
        Button button_5=findViewById(R.id.button_5);//获取按钮5实例
        Button button_6=findViewById(R.id.button_6);//获取按钮6实例
        Button button_7=findViewById(R.id.button_7);//获取按钮7实例
        Button button_8=findViewById(R.id.button_8);//获取按钮8实例
        Button button_9=findViewById(R.id.button_9);//获取按钮9实例
        Button button_add=findViewById(R.id.button_add);//获取加法按钮实例
        Button button_subtract=findViewById(R.id.button_subtract);//获取减法按钮实例
        Button button_multiply=findViewById(R.id.button_multiply);//获取乘法按钮实例
        Button button_divide=findViewById(R.id.button_divide);//获取除法按钮实例
        Button button_del=findViewById(R.id.button_del);//获取删除按钮实例
        Button button_point=findViewById(R.id.button_point);//获取点按钮实例
        ImageButton button_sqrt=findViewById(R.id.button_sqrt);//获取根号按钮实例
        Button button_equal=findViewById(R.id.button_equal);//获取等于按钮实例
        tv_result=findViewById(R.id.tv_result);//从布局文件中获取tv_result文本视图


        //为每个button按钮控件注册点击监听器
        button_0.setOnClickListener(this);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);
        button_add.setOnClickListener(this);
        button_subtract.setOnClickListener(this);
        button_multiply.setOnClickListener(this);
        button_divide.setOnClickListener(this);
        button_del.setOnClickListener(this);
        button_point.setOnClickListener(this);
        button_sqrt.setOnClickListener(this);
        button_equal.setOnClickListener(this);


        //播放gif动图
        ImageView gif_alice=findViewById(R.id.gif_alice);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.P)
        {
            try {
                ImageDecoder.Source source=ImageDecoder.createSource(getResources(),R.drawable.alice);//利用Android9.0新增的ImageDecoder读取GIF动画
                @SuppressLint("WrongThread") Drawable drawable=ImageDecoder.decodeDrawable(source);//从数据源中解码得到GIF图形数据
                gif_alice.setImageDrawable(drawable);//设置图像视图的图形为GIF图片
                if(drawable instanceof Animatable)
                {
                    Animatable animatable=(Animatable) gif_alice.getDrawable();
                    animatable.start();
                }//如果是动画图形则开始播放动画
            }catch (Exception e){
                e.printStackTrace();
            }
        }//如果系统的版本为Android9.0以上，则利用新增的AnimatedImageDrawable显示GIF动画

        ImageView gif_epinel=findViewById(R.id.gif_epinel);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.P)
        {
            try {
                ImageDecoder.Source source=ImageDecoder.createSource(getResources(),R.drawable.epinel);
                @SuppressLint("WrongThread") Drawable drawable=ImageDecoder.decodeDrawable(source);
                gif_epinel.setImageDrawable(drawable);
                if(drawable instanceof Animatable)
                {
                    Animatable animatable=(Animatable) gif_epinel.getDrawable();
                    animatable.start();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }


        //点击button按钮缩小，松开恢复
        button_0.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        //缩小按钮
                        ScaleAnimation shrinkAnimation=new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        shrinkAnimation.setDuration(100);
                        shrinkAnimation.setFillAfter(true);
                        v.startAnimation(shrinkAnimation);
                        break;
                    case MotionEvent.ACTION_UP:
                        //恢复按钮
                        ScaleAnimation restoreAnimation=new ScaleAnimation(0.9f, 1.0f, 0.9f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        restoreAnimation.setDuration(100);
                        restoreAnimation.setFillAfter(true);
                        v.startAnimation(restoreAnimation);
                        break;
                }
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        // 按下时开始震动
                        vibrator.vibrate(VibrationEffect.createOneShot(VIBRATION_DURATION, VibrationEffect.DEFAULT_AMPLITUDE));
                        break;
                    case MotionEvent.ACTION_UP:
                        // 松开时停止震动
                        vibrator.cancel();
                        break;
                }
                return false;
            }
        });

        button_1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        ScaleAnimation shrinkAnimation=new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        shrinkAnimation.setDuration(100);
                        shrinkAnimation.setFillAfter(true);
                        v.startAnimation(shrinkAnimation);
                        break;
                    case MotionEvent.ACTION_UP:
                        ScaleAnimation restoreAnimation=new ScaleAnimation(0.9f, 1.0f, 0.9f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        restoreAnimation.setDuration(100);
                        restoreAnimation.setFillAfter(true);
                        v.startAnimation(restoreAnimation);
                        break;
                }
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        // 按下时开始震动
                        vibrator.vibrate(VibrationEffect.createOneShot(VIBRATION_DURATION, VibrationEffect.DEFAULT_AMPLITUDE));
                        break;
                    case MotionEvent.ACTION_UP:
                        // 松开时停止震动
                        vibrator.cancel();
                        break;
                }
                return false;
            }
        });

        button_2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        ScaleAnimation shrinkAnimation=new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        shrinkAnimation.setDuration(100);
                        shrinkAnimation.setFillAfter(true);
                        v.startAnimation(shrinkAnimation);
                        break;
                    case MotionEvent.ACTION_UP:
                        ScaleAnimation restoreAnimation=new ScaleAnimation(0.9f, 1.0f, 0.9f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        restoreAnimation.setDuration(100);
                        restoreAnimation.setFillAfter(true);
                        v.startAnimation(restoreAnimation);
                        break;
                }
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        // 按下时开始震动
                        vibrator.vibrate(VibrationEffect.createOneShot(VIBRATION_DURATION, VibrationEffect.DEFAULT_AMPLITUDE));
                        break;
                    case MotionEvent.ACTION_UP:
                        // 松开时停止震动
                        vibrator.cancel();
                        break;
                }
                return false;
            }
        });

        button_3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        ScaleAnimation shrinkAnimation=new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        shrinkAnimation.setDuration(100);
                        shrinkAnimation.setFillAfter(true);
                        v.startAnimation(shrinkAnimation);
                        break;
                    case MotionEvent.ACTION_UP:
                        ScaleAnimation restoreAnimation=new ScaleAnimation(0.9f, 1.0f, 0.9f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        restoreAnimation.setDuration(100);
                        restoreAnimation.setFillAfter(true);
                        v.startAnimation(restoreAnimation);
                        break;
                }
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        // 按下时开始震动
                        vibrator.vibrate(VibrationEffect.createOneShot(VIBRATION_DURATION, VibrationEffect.DEFAULT_AMPLITUDE));
                        break;
                    case MotionEvent.ACTION_UP:
                        // 松开时停止震动
                        vibrator.cancel();
                        break;
                }
                return false;
            }
        });

        button_4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        ScaleAnimation shrinkAnimation=new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        shrinkAnimation.setDuration(100);
                        shrinkAnimation.setFillAfter(true);
                        v.startAnimation(shrinkAnimation);
                        break;
                    case MotionEvent.ACTION_UP:
                        ScaleAnimation restoreAnimation=new ScaleAnimation(0.9f, 1.0f, 0.9f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        restoreAnimation.setDuration(100);
                        restoreAnimation.setFillAfter(true);
                        v.startAnimation(restoreAnimation);
                        break;
                }
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        // 按下时开始震动
                        vibrator.vibrate(VibrationEffect.createOneShot(VIBRATION_DURATION, VibrationEffect.DEFAULT_AMPLITUDE));
                        break;
                    case MotionEvent.ACTION_UP:
                        // 松开时停止震动
                        vibrator.cancel();
                        break;
                }
                return false;
            }
        });

        button_5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        ScaleAnimation shrinkAnimation=new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        shrinkAnimation.setDuration(100);
                        shrinkAnimation.setFillAfter(true);
                        v.startAnimation(shrinkAnimation);
                        break;
                    case MotionEvent.ACTION_UP:
                        ScaleAnimation restoreAnimation=new ScaleAnimation(0.9f, 1.0f, 0.9f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        restoreAnimation.setDuration(100);
                        restoreAnimation.setFillAfter(true);
                        v.startAnimation(restoreAnimation);
                        break;
                }
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        // 按下时开始震动
                        vibrator.vibrate(VibrationEffect.createOneShot(VIBRATION_DURATION, VibrationEffect.DEFAULT_AMPLITUDE));
                        break;
                    case MotionEvent.ACTION_UP:
                        // 松开时停止震动
                        vibrator.cancel();
                        break;
                }
                return false;
            }
        });

        button_6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        ScaleAnimation shrinkAnimation=new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        shrinkAnimation.setDuration(100);
                        shrinkAnimation.setFillAfter(true);
                        v.startAnimation(shrinkAnimation);
                        break;
                    case MotionEvent.ACTION_UP:
                        ScaleAnimation restoreAnimation=new ScaleAnimation(0.9f, 1.0f, 0.9f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        restoreAnimation.setDuration(100);
                        restoreAnimation.setFillAfter(true);
                        v.startAnimation(restoreAnimation);
                        break;
                }
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        // 按下时开始震动
                        vibrator.vibrate(VibrationEffect.createOneShot(VIBRATION_DURATION, VibrationEffect.DEFAULT_AMPLITUDE));
                        break;
                    case MotionEvent.ACTION_UP:
                        // 松开时停止震动
                        vibrator.cancel();
                        break;
                }
                return false;
            }
        });

        button_7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        ScaleAnimation shrinkAnimation=new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        shrinkAnimation.setDuration(100);
                        shrinkAnimation.setFillAfter(true);
                        v.startAnimation(shrinkAnimation);
                        break;
                    case MotionEvent.ACTION_UP:
                        ScaleAnimation restoreAnimation=new ScaleAnimation(0.9f, 1.0f, 0.9f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        restoreAnimation.setDuration(100);
                        restoreAnimation.setFillAfter(true);
                        v.startAnimation(restoreAnimation);
                        break;
                }
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        // 按下时开始震动
                        vibrator.vibrate(VibrationEffect.createOneShot(VIBRATION_DURATION, VibrationEffect.DEFAULT_AMPLITUDE));
                        break;
                    case MotionEvent.ACTION_UP:
                        // 松开时停止震动
                        vibrator.cancel();
                        break;
                }
                return false;
            }
        });

        button_8.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        ScaleAnimation shrinkAnimation=new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        shrinkAnimation.setDuration(100);
                        shrinkAnimation.setFillAfter(true);
                        v.startAnimation(shrinkAnimation);
                        break;
                    case MotionEvent.ACTION_UP:
                        ScaleAnimation restoreAnimation=new ScaleAnimation(0.9f, 1.0f, 0.9f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        restoreAnimation.setDuration(100);
                        restoreAnimation.setFillAfter(true);
                        v.startAnimation(restoreAnimation);
                        break;
                }
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        // 按下时开始震动
                        vibrator.vibrate(VibrationEffect.createOneShot(VIBRATION_DURATION, VibrationEffect.DEFAULT_AMPLITUDE));
                        break;
                    case MotionEvent.ACTION_UP:
                        // 松开时停止震动
                        vibrator.cancel();
                        break;
                }
                return false;
            }
        });

        button_9.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        ScaleAnimation shrinkAnimation=new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        shrinkAnimation.setDuration(100);
                        shrinkAnimation.setFillAfter(true);
                        v.startAnimation(shrinkAnimation);
                        break;
                    case MotionEvent.ACTION_UP:
                        ScaleAnimation restoreAnimation=new ScaleAnimation(0.9f, 1.0f, 0.9f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        restoreAnimation.setDuration(100);
                        restoreAnimation.setFillAfter(true);
                        v.startAnimation(restoreAnimation);
                        break;
                }
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        // 按下时开始震动
                        vibrator.vibrate(VibrationEffect.createOneShot(VIBRATION_DURATION, VibrationEffect.DEFAULT_AMPLITUDE));
                        break;
                    case MotionEvent.ACTION_UP:
                        // 松开时停止震动
                        vibrator.cancel();
                        break;
                }
                return false;
            }
        });

        button_add.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        ScaleAnimation shrinkAnimation=new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        shrinkAnimation.setDuration(100);
                        shrinkAnimation.setFillAfter(true);
                        v.startAnimation(shrinkAnimation);
                        break;
                    case MotionEvent.ACTION_UP:
                        ScaleAnimation restoreAnimation=new ScaleAnimation(0.9f, 1.0f, 0.9f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        restoreAnimation.setDuration(100);
                        restoreAnimation.setFillAfter(true);
                        v.startAnimation(restoreAnimation);
                        break;
                }
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        // 按下时开始震动
                        vibrator.vibrate(VibrationEffect.createOneShot(VIBRATION_DURATION, VibrationEffect.DEFAULT_AMPLITUDE));
                        break;
                    case MotionEvent.ACTION_UP:
                        // 松开时停止震动
                        vibrator.cancel();
                        break;
                }
                return false;
            }
        });

        button_subtract.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        ScaleAnimation shrinkAnimation=new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        shrinkAnimation.setDuration(100);
                        shrinkAnimation.setFillAfter(true);
                        v.startAnimation(shrinkAnimation);
                        break;
                    case MotionEvent.ACTION_UP:
                        ScaleAnimation restoreAnimation=new ScaleAnimation(0.9f, 1.0f, 0.9f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        restoreAnimation.setDuration(100);
                        restoreAnimation.setFillAfter(true);
                        v.startAnimation(restoreAnimation);
                        break;
                }
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        // 按下时开始震动
                        vibrator.vibrate(VibrationEffect.createOneShot(VIBRATION_DURATION, VibrationEffect.DEFAULT_AMPLITUDE));
                        break;
                    case MotionEvent.ACTION_UP:
                        // 松开时停止震动
                        vibrator.cancel();
                        break;
                }
                return false;
            }
        });

        button_multiply.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        ScaleAnimation shrinkAnimation=new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        shrinkAnimation.setDuration(100);
                        shrinkAnimation.setFillAfter(true);
                        v.startAnimation(shrinkAnimation);
                        break;
                    case MotionEvent.ACTION_UP:
                        ScaleAnimation restoreAnimation=new ScaleAnimation(0.9f, 1.0f, 0.9f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        restoreAnimation.setDuration(100);
                        restoreAnimation.setFillAfter(true);
                        v.startAnimation(restoreAnimation);
                        break;
                }
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        // 按下时开始震动
                        vibrator.vibrate(VibrationEffect.createOneShot(VIBRATION_DURATION, VibrationEffect.DEFAULT_AMPLITUDE));
                        break;
                    case MotionEvent.ACTION_UP:
                        // 松开时停止震动
                        vibrator.cancel();
                        break;
                }
                return false;
            }
        });

        button_divide.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        ScaleAnimation shrinkAnimation=new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        shrinkAnimation.setDuration(100);
                        shrinkAnimation.setFillAfter(true);
                        v.startAnimation(shrinkAnimation);
                        break;
                    case MotionEvent.ACTION_UP:
                        ScaleAnimation restoreAnimation=new ScaleAnimation(0.9f, 1.0f, 0.9f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        restoreAnimation.setDuration(100);
                        restoreAnimation.setFillAfter(true);
                        v.startAnimation(restoreAnimation);
                        break;
                }
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        // 按下时开始震动
                        vibrator.vibrate(VibrationEffect.createOneShot(VIBRATION_DURATION, VibrationEffect.DEFAULT_AMPLITUDE));
                        break;
                    case MotionEvent.ACTION_UP:
                        // 松开时停止震动
                        vibrator.cancel();
                        break;
                }
                return false;
            }
        });

        button_del.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        ScaleAnimation shrinkAnimation=new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        shrinkAnimation.setDuration(100);
                        shrinkAnimation.setFillAfter(true);
                        v.startAnimation(shrinkAnimation);
                        break;
                    case MotionEvent.ACTION_UP:
                        ScaleAnimation restoreAnimation=new ScaleAnimation(0.9f, 1.0f, 0.9f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        restoreAnimation.setDuration(100);
                        restoreAnimation.setFillAfter(true);
                        v.startAnimation(restoreAnimation);
                        break;
                }
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        // 按下时开始震动
                        vibrator.vibrate(VibrationEffect.createOneShot(VIBRATION_DURATION, VibrationEffect.DEFAULT_AMPLITUDE));
                        break;
                    case MotionEvent.ACTION_UP:
                        // 松开时停止震动
                        vibrator.cancel();
                        break;
                }
                return false;
            }
        });

        button_point.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        ScaleAnimation shrinkAnimation=new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        shrinkAnimation.setDuration(100);
                        shrinkAnimation.setFillAfter(true);
                        v.startAnimation(shrinkAnimation);
                        break;
                    case MotionEvent.ACTION_UP:
                        ScaleAnimation restoreAnimation=new ScaleAnimation(0.9f, 1.0f, 0.9f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        restoreAnimation.setDuration(100);
                        restoreAnimation.setFillAfter(true);
                        v.startAnimation(restoreAnimation);
                        break;
                }
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        // 按下时开始震动
                        vibrator.vibrate(VibrationEffect.createOneShot(VIBRATION_DURATION, VibrationEffect.DEFAULT_AMPLITUDE));
                        break;
                    case MotionEvent.ACTION_UP:
                        // 松开时停止震动
                        vibrator.cancel();
                        break;
                }
                return false;
            }
        });

        button_sqrt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        ScaleAnimation shrinkAnimation=new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        shrinkAnimation.setDuration(100);
                        shrinkAnimation.setFillAfter(true);
                        v.startAnimation(shrinkAnimation);
                        break;
                    case MotionEvent.ACTION_UP:
                        ScaleAnimation restoreAnimation=new ScaleAnimation(0.9f, 1.0f, 0.9f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        restoreAnimation.setDuration(100);
                        restoreAnimation.setFillAfter(true);
                        v.startAnimation(restoreAnimation);
                        break;
                }
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        // 按下时开始震动
                        vibrator.vibrate(VibrationEffect.createOneShot(VIBRATION_DURATION, VibrationEffect.DEFAULT_AMPLITUDE));
                        break;
                    case MotionEvent.ACTION_UP:
                        // 松开时停止震动
                        vibrator.cancel();
                        break;
                }
                return false;
            }
        });

        button_equal.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        ScaleAnimation shrinkAnimation=new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        shrinkAnimation.setDuration(100);
                        shrinkAnimation.setFillAfter(true);
                        v.startAnimation(shrinkAnimation);
                        break;
                    case MotionEvent.ACTION_UP:
                        ScaleAnimation restoreAnimation=new ScaleAnimation(0.9f, 1.0f, 0.9f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        restoreAnimation.setDuration(100);
                        restoreAnimation.setFillAfter(true);
                        v.startAnimation(restoreAnimation);
                        break;
                }
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        // 按下时开始震动
                        vibrator.vibrate(VibrationEffect.createOneShot(VIBRATION_DURATION, VibrationEffect.DEFAULT_AMPLITUDE));
                        break;
                    case MotionEvent.ACTION_UP:
                        // 松开时停止震动
                        vibrator.cancel();
                        break;
                }
                return false;
            }
        });


        /*//在 onTouch 方法中触发震动效果
        //由于Android第二个OnClickListener覆盖第一个OnClickLister不知道该如何解决，所以采取在 onTouch 方法中触发震动效果
        //然后通过合并这两个OnTouchListener达到现如今的效果
        button_0.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // 按下时开始震动
                        vibrator.vibrate(VibrationEffect.createOneShot(VIBRATION_DURATION, VibrationEffect.DEFAULT_AMPLITUDE));
                        break;
                    case MotionEvent.ACTION_UP:
                        // 松开时停止震动
                        vibrator.cancel();
                        break;
                }
                return false;
            }
        });*/
        //点击button按钮震动效果
        /*Vibrator vibrator0=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);//获取Vibrator实例
        button_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator0.vibrate(100);//在button按钮被点击时震动100毫秒
            }
        });

        Vibrator vibrator1=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);//获取Vibrator实例
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator1.vibrate(100);//在button按钮被点击时震动100毫秒
            }
        });

        Vibrator vibrator2=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);//获取Vibrator实例
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator2.vibrate(100);//在button按钮被点击时震动100毫秒
            }
        });

        Vibrator vibrator3=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);//获取Vibrator实例
        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator3.vibrate(100);//在button按钮被点击时震动100毫秒
            }
        });

        Vibrator vibrator4=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);//获取Vibrator实例
        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator4.vibrate(100);//在button按钮被点击时震动100毫秒
            }
        });

        Vibrator vibrator5=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);//获取Vibrator实例
        button_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator5.vibrate(100);//在button按钮被点击时震动100毫秒
            }
        });

        Vibrator vibrator6=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);//获取Vibrator实例
        button_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator6.vibrate(100);//在button按钮被点击时震动100毫秒
            }
        });

        Vibrator vibrator7=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);//获取Vibrator实例
        button_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator7.vibrate(100);//在button按钮被点击时震动100毫秒
            }
        });

        Vibrator vibrator8=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);//获取Vibrator实例
        button_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator8.vibrate(100);//在button按钮被点击时震动100毫秒
            }
        });

        Vibrator vibrator9=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);//获取Vibrator实例
        button_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator9.vibrate(100);//在button按钮被点击时震动100毫秒
            }
        });

        Vibrator vibratoradd=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);//获取Vibrator实例
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibratoradd.vibrate(100);//在button按钮被点击时震动100毫秒
            }
        });

        Vibrator vibratorsubtract=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);//获取Vibrator实例
        button_subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibratorsubtract.vibrate(100);//在button按钮被点击时震动100毫秒
            }
        });

        Vibrator vibratormultiply=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);//获取Vibrator实例
        button_multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibratormultiply.vibrate(100);//在button按钮被点击时震动100毫秒
            }
        });

        Vibrator vibratordivide=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);//获取Vibrator实例
        button_divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibratordivide.vibrate(100);//在button按钮被点击时震动100毫秒
            }
        });

        Vibrator vibratordel=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);//获取Vibrator实例
        button_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibratordel.vibrate(100);//在button按钮被点击时震动100毫秒
            }
        });

        Vibrator vibratorpoint=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);//获取Vibrator实例
        button_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibratorpoint.vibrate(100);//在button按钮被点击时震动100毫秒
            }
        });

        Vibrator vibratorsqrt=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);//获取Vibrator实例
        button_sqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibratorsqrt.vibrate(100);//在button按钮被点击时震动100毫秒
            }
        });

        Vibrator vibratorequal=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);//获取Vibrator实例
        button_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibratorequal.vibrate(100);//在button按钮被点击时震动100毫秒
            }
        });*/


        //保持竖屏
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                double angle = Math.atan2(y, x) * 180 / Math.PI;
                if (angle < -45 && angle > -135) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
                } else if (angle > 45 && angle < 135) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);


    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v)
    {
        String inputText;
        if(v.getId()==R.id.button_0)
        {
            inputText="0";
        }
        else if (v.getId()==R.id.button_1)
        {
            inputText="1";
        }
        else if (v.getId()==R.id.button_2)
        {
            inputText="2";
        }
        else if (v.getId()==R.id.button_3)
        {
            inputText="3";
        }
        else if (v.getId()==R.id.button_4)
        {
            inputText="4";
        }
        else if (v.getId()==R.id.button_5)
        {
            inputText="5";
        }
        else if (v.getId()==R.id.button_6)
        {
            inputText="6";
        }
        else if (v.getId()==R.id.button_7)
        {
            inputText="7";
        }
        else if (v.getId()==R.id.button_8)
        {
            inputText="8";
        }
        else if (v.getId()==R.id.button_9)
        {
            inputText="9";
        }
        else if (v.getId()==R.id.button_add)
        {
            inputText="+";
            if(first_num.equals(""))
            {
                //若输入加号时前面无运算数字则弹出提示窗口
                Toast.makeText(MainActivity.this,"请输入数字",Toast.LENGTH_SHORT).show();
                inputText="";
            }
            if(!operator.equals(""))
            {
                //若已经输过运算符，则弹出提示窗口
                Toast.makeText(MainActivity.this,"已有运算符",Toast.LENGTH_SHORT).show();
                String now_result="";
                now_result=tv_result.getText().toString();
                now_result=now_result.substring(0,now_result.length()-1);
                refreshResult(now_result);
                refreshText(now_result);
            }
        }
        else if (v.getId()==R.id.button_subtract)
        {
            inputText="-";
            if(first_num.equals(""))
            {
                Toast.makeText(MainActivity.this,"请输入数字",Toast.LENGTH_SHORT).show();
                inputText="";
            }
            if(!operator.equals(""))
            {
                Toast.makeText(MainActivity.this,"已有运算符",Toast.LENGTH_SHORT).show();
                String now_result="";
                now_result=tv_result.getText().toString();
                now_result=now_result.substring(0,now_result.length()-1);
                refreshResult(now_result);
                refreshText(now_result);
            }
        }
        else if (v.getId()==R.id.button_multiply)
        {
            inputText="×";
            if(first_num.equals(""))
            {
                Toast.makeText(MainActivity.this,"请输入数字",Toast.LENGTH_SHORT).show();
                inputText="";
            }
            if(!operator.equals(""))
            {
                Toast.makeText(MainActivity.this,"已有运算符",Toast.LENGTH_SHORT).show();
                String now_result="";
                now_result=tv_result.getText().toString();
                now_result=now_result.substring(0,now_result.length()-1);
                refreshResult(now_result);
                refreshText(now_result);
            }
        }
        else if (v.getId()==R.id.button_divide)
        {
            inputText="÷";
            if(first_num.equals(""))
            {
                Toast.makeText(MainActivity.this,"请输入数字",Toast.LENGTH_SHORT).show();
                inputText="";
            }
            if(!operator.equals(""))
            {
                Toast.makeText(MainActivity.this,"已有运算符",Toast.LENGTH_SHORT).show();
                String now_result="";
                now_result=tv_result.getText().toString();
                now_result=now_result.substring(0,now_result.length()-1);
                refreshResult(now_result);
                refreshText(now_result);
            }
        }
        else if(v.getId()==R.id.button_point)
        {
            inputText=".";
            if(operator.equals("")&&first_num.contains("."))
            {
                //若已经输过小数点则弹出提示窗口(两个小数点重复)
                Toast.makeText(MainActivity.this,"小数点已存在",Toast.LENGTH_SHORT).show();
                inputText="";
            }
            if(!operator.equals("")&&next_num.contains("."))
            {
                //若已经输过小数点则弹出提示窗口(两个小数点重复)
                Toast.makeText(MainActivity.this,"小数点已存在",Toast.LENGTH_SHORT).show();
                inputText="";
            }
        }
        else if(v.getId()==R.id.button_sqrt)
        {
            inputText="";
            if(first_num.equals(""))
            {
                //开根号时缺少底数弹出提示窗口
                Toast.makeText(MainActivity.this,"请输入数字",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        else if (v.getId()==R.id.button_equal)
        {
            inputText="";
            if(operator.equals(""))
            {
                //若无运算符
                Toast.makeText(MainActivity.this,"请输入运算符",Toast.LENGTH_SHORT).show();
            }
            if (next_num.equals(""))
            {
                //若无第二个运算数字
                Toast.makeText(MainActivity.this,"请输入数字",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        else
        {
            inputText="";
        }
        switch (v.getId())
        {
            case R.id.button_del:
                String now_result="";
                now_result=tv_result.getText().toString();
                if(now_result.equals(""))
                {
                    Toast.makeText(MainActivity.this,"已无可删除内容",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(now_result.length()==1)
                    {
                        now_result="";
                    }
                    else
                    {
                        now_result=now_result.substring(0,now_result.length()-1);
                    }
                    refreshResult(now_result);
                    refreshText(now_result);
                }
                break;
            case R.id.button_add:
            case R.id.button_subtract:
            case R.id.button_multiply:
            case R.id.button_divide:
                operator=inputText;
                refreshText(showText+operator);
                break;
            case R.id.button_equal:
                double fourOperation_result=fourOperation();
                refreshResult(String.valueOf(fourOperation_result));
                refreshText(result);
                break;
            case R.id.button_sqrt:
                double sqrt_result=Math.sqrt(Double.parseDouble(first_num));
                refreshResult(String.valueOf(sqrt_result));
                refreshText(result);
                break;
            default:
                if(result.length()>0&&operator.equals(""))
                {
                    refreshResult("");
                    refreshText("");
                }
                if(operator.equals(""))
                {
                    first_num=first_num+inputText;
                }
                else
                {
                    next_num=next_num+inputText;
                }
                if(showText.equals("0")&&!inputText.equals("."))
                {
                    //整数前面不需要0
                    refreshText(inputText);
                }
                else
                {
                    refreshText(showText+inputText);
                }
        }
    }


    //加减乘除四则运算，返回计算结果
    @RequiresApi(api = Build.VERSION_CODES.N)
    private double fourOperation()
    {
        switch (operator)
        {
            case "+":
                return Double.parseDouble(first_num)+Double.parseDouble(next_num);
            case "-":
                return Double.parseDouble(first_num)-Double.parseDouble(next_num);
            case "×":
                return Double.parseDouble(first_num)*Double.parseDouble(next_num);
            case "÷":
                if(operator.equals("÷")&&Double.parseDouble(next_num)==0)
                {
                    //除数不能为0
                    Toast.makeText(MainActivity.this,"除数不能为零",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    return Double.parseDouble(first_num)/Double.parseDouble(next_num);
                }
        }
        return 0;
    }


    //刷新运算结果
    private void refreshResult(String new_result)
    {
        result=new_result;
        first_num=result;
        next_num="";
        operator="";
    }


    //刷新文本显示
    private void refreshText(String new_showText)
    {
        showText=new_showText;
        tv_result.setText(showText);
    }


}