package com.student.student_healthy;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
public class MySharedPrefernces {
    public static final String NAME = "MySharedPrefernces";

    // 儲存 userid
    public static final String KEY_USERID = "userid";

    public static void saveUserId(Context context, String userid) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        sp.edit().putString(KEY_USERID, userid).commit();


    }

    public static String getUserId(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        return sp.getString(KEY_USERID, "");
    }

    // 儲存 username
    public static final String KEY_USERNAME = "username";

    public static void saveUserName(Context context, String userid) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        sp.edit().putString(KEY_USERNAME, userid).commit();


    }

    public static String getUserName(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        return sp.getString(KEY_USERNAME, "");
    }



    public static final String KEY_PHOTO = "userphoto";

    public static void saveUserPhoto(Context context, String userid) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        sp.edit().putString(KEY_PHOTO, userid).commit();


    }

    public static String getUserphoto(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        return sp.getString(KEY_PHOTO,"");
    }

    public  static  final  String KEY_SHOP_ARRAY = "shoparray";


    public static void saveArrayList(Context context,ArrayList<String> list){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(KEY_SHOP_ARRAY, json);
        editor.apply();

    }

    public static ArrayList<String> getArrayList(Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = prefs.getString(KEY_SHOP_ARRAY, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }



    public  static  final  String KEY_SHOP_PRICE = "shopprice";


    public static void savePriceArrayList(Context context,ArrayList<String> list){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(KEY_SHOP_PRICE, json);
        editor.apply();

    }

    public static ArrayList<String> getPriceArrayList(Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = prefs.getString(KEY_SHOP_PRICE, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }
}