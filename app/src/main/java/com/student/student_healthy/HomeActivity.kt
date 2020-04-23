package com.student.student_healthy

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.student.student_searchmap.R

class HomeActivity : AppCompatActivity(){


    lateinit var  mButton_1: Button// 建議飲食
    lateinit var  mButton_2: Button// 體重紀錄

    lateinit var  mButton_3: Button // 睡眠紀錄

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initLayout()
    }
    fun initLayout(){
        mButton_1 = findViewById(R.id.button_1)
        mButton_2 = findViewById(R.id.button_2)
        mButton_3 = findViewById(R.id.button_3)
        mButton_1.setOnClickListener {
            // 跳到建議飲食頁面

            startActivity(Intent(this,MainActivity::class.java))
        }
        mButton_2.setOnClickListener {

        }
        mButton_3.setOnClickListener {

        }


    }


}
