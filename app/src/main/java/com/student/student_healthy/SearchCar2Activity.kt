package com.student.student_healthy

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.student.student_healthy.Data.BigTaichungActivity
import com.student.student_searchmap.R

class SearchCar2Activity : AppCompatActivity() {
    lateinit var button:Button
    lateinit var button2 :Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_car)
        button = findViewById(R.id.button2)
        button2 = findViewById(R.id.button3)
        button.setOnClickListener {
            startActivity(Intent(this, BigTaichungActivity::class.java))
        }
        button2.setOnClickListener {
            startActivity(Intent(this, BigMannerActivity::class.java))

        }

    }
}
