package com.student.student_healthy

import android.app.Application;

import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;

class  MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this);
        Firebase.setAndroidContext(this);


    }


}