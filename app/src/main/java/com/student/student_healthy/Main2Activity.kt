package com.student.student_healthy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.Menu
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import android.view.View
import android.widget.Button
import com.student.student_healthy.Data.AddFoodData
import com.student.student_searchmap.R
import kotlinx.android.synthetic.main.activity_main_2.*


class Main2Activity : AppCompatActivity() {

    //crating an arraylist to store users using the data class user
    val users = ArrayList<User>()

    //list filtered by the searchbar
    val filteredUsers = ArrayList<User>()

    //creating our adapter

    //creating our adapter
    var name = arrayOf("白飯", "英式紅茶", "酪梨", "紅心芭樂", "米粉", "英式紅茶拿鐵", "小農鮮奶咖啡", "小農雪點鮮奶茶", "芭樂", "四季翠玉青茶",
                "黑糖雪點奶茶", "小農經典拿鐵", "百香果", "奇異果", "柳橙", "芒果綠茶", "泡麵", "莊園拿鐵咖啡", "粥", "葡萄柚綠茶")
    var photo = arrayOf(R.mipmap.photo1, R.mipmap.photo2, R.mipmap.photo3, R.mipmap.photo4, R.mipmap.photo5,
            R.mipmap.photo6, R.mipmap.photo7, R.mipmap.photo8, R.mipmap.photo9, R.mipmap.photo10,
            R.mipmap.photo11,R.mipmap.photo12,R.mipmap.photo13,R.mipmap.photo14,R.mipmap.photo15,
            R.mipmap.photo16,R.mipmap.photo17,R.mipmap.photo18,R.mipmap.photo19,R.mipmap.photo20)

    lateinit var mAddButton: Button
    var typeInt :Int = 0
    var addFoodData  = AddFoodData()
    var mData: ArrayList<AddFoodData> = ArrayList()
    var adapter = CustomAdapter(users,this,typeInt)
    var filteredAdapter = CustomAdapter(filteredUsers,this,typeInt)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_2)

        setSupportActionBar(toolbar)
        getData()
        //adding a layoutmanager
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        for (i in name.indices) {
            users.add(User(name [i], photo[i]))

        }        //adding some dummy data to the list



        //now adding the adapter to recyclerview
        recyclerView.adapter = adapter

        title = "新增飲食紀錄"
        mAddButton = findViewById(R.id.addbutton)
        mAddButton.setOnClickListener {
          setType()

        }


    }
    fun setType(){


        var intent  =  Intent()
        var bundle  = Bundle()
        bundle.putInt("type",typeInt)
        intent.putExtras(bundle)
        intent.setClass(this,AddFoodActivity::class.java)
        startActivity(intent)
        this.finish()
    }
    fun getData(){
        typeInt =  intent.getStringExtra("type").toInt()
        adapter = CustomAdapter(users,this,typeInt)
        filteredAdapter = CustomAdapter(filteredUsers,this,typeInt)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        //getting the search view from the menu
        val searchViewItem = menu.findItem(R.id.menuSearch)

        //getting the search view
        val searchView = searchViewItem.actionView as SearchView

        //making the searchview consume all the toolbar when open
        searchView.maxWidth= Int.MAX_VALUE

        searchView.queryHint = "搜尋"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                //action while typing

                //hiding the empty textview
                tvEmpty.visibility= View.GONE

                if (newText.isEmpty()){
                    recyclerView.adapter = adapter

                }else{
                    filteredUsers.clear()
                    for (user in users){
                        if (user.name.toLowerCase().contains(newText.toLowerCase())){
                            filteredUsers.add(user)
                        }
                    }
                    if (filteredUsers.isEmpty()){
                        //showing the empty textview when the list is empty
                        tvEmpty.visibility= View.VISIBLE
                    }

                    recyclerView.adapter = filteredAdapter
                }

                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                //action when type Enter
                return false
            }

        })








        return true
    }
}
