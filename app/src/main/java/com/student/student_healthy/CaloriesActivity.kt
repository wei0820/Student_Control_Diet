package com.student.student_healthy

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.student.student_searchmap.R

class CaloriesActivity : AppCompatActivity() {
    lateinit var mButton1: Button
    lateinit var mButton2: Button
    lateinit var mButton3: Button
    lateinit var mButton4: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calories)
        initLayout()

    }
    fun setData(title :String){
        var intent = Intent(KotlinActivity@this, Main2Activity::class.java)
        var bundle = Bundle()
        bundle.putString("title",title)
        intent.putExtras(bundle)
        startActivityForResult(intent, 0)

    }
    fun initLayout(){
        mButton1 = findViewById(R.id.button1)
        mButton2 = findViewById(R.id.button2)
        mButton3 = findViewById(R.id.button3)
        mButton4 = findViewById(R.id.button4)
        mButton1.setOnClickListener {
            setData("早餐")
        }
        mButton2.setOnClickListener {
            setData("午餐")


        }
        mButton3.setOnClickListener {
            setData("晚餐")

        }
        mButton4.setOnClickListener {
            setData("點心")

        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 0){
            var name = data?.getStringExtra("name")
            Log.d("ACETEST", "name: " + name)
        }
    }

}
