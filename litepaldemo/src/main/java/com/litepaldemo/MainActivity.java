package com.litepaldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 插入数据
        Person person1 = new Person("aa", 21, "student");
        Person person2 = new Person("bb", 22, "teacher");
        Person person3 = new Person("cc", 23, "worker");
        person1.save();
        person2.save();
        person3.save();


    }
}
