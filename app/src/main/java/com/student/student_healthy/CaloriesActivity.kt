package com.student.student_healthy

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.student.student_searchmap.R

class CaloriesActivity : AppCompatActivity() {
    lateinit var mButton1: Button
    lateinit var mButton2: Button
    lateinit var mButton3: Button
    lateinit var mButton4: Button
    lateinit var mAddButton: Button
    lateinit var mHaveDateLayout:RelativeLayout

    lateinit var mNoDateLayout:RelativeLayout
    var type :Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calories)
        initLayout()

    }
    fun initLayout(){
        mHaveDateLayout = findViewById(R.id.havelayout)
        mNoDateLayout = findViewById(R.id.nodatelayout)

        mAddButton = findViewById(R.id.addbutton)
        mButton1 = findViewById(R.id.button1)
        mButton2 = findViewById(R.id.button2)
        mButton3 = findViewById(R.id.button3)
        mButton4 = findViewById(R.id.button4)
        mButton1.setOnClickListener {
            mNoDateLayout.visibility = View.VISIBLE
            mHaveDateLayout.visibility = View.GONE
            type = 0

        }
        mButton2.setOnClickListener {
//            setData("午餐")
            mNoDateLayout.visibility = View.GONE
            mHaveDateLayout.visibility=View.VISIBLE
            type = 1

        }
        mButton3.setOnClickListener {
//            setData("晚餐")
            mNoDateLayout.visibility= View.VISIBLE
            mHaveDateLayout.visibility=View.GONE
            type = 2

        }
        mButton4.setOnClickListener {
//            setData("點心")
            mNoDateLayout.visibility = View.GONE
            mHaveDateLayout.visibility = View.VISIBLE
            type = 3
        }
        mAddButton.setOnClickListener {
            when(type){

                0 -> setData("0")
                1 -> setData("1")
                2 -> setData("2")
                3 -> setData("3")



            }



        }



    }
    fun setData(string: String){
        var intent  =  Intent()
        var bundle  = Bundle()
        bundle.putString("type",string)
        intent.putExtras(bundle)
        intent.setClass(this,Main2Activity::class.java)
        startActivity(intent)

    }

    override fun onResume() {
        super.onResume()
        if (MySharedPrefernces.getFood1Array(this)!=null&&
                MySharedPrefernces.getFood1Array(this).size!=0){
            MySharedPrefernces.getFood1Array(this).forEach {
                Log.d("Jack",it.name)
               
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 0){


        }
    }

}
