package com.student.student_healthy

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.student.student_healthy.Data.AddFoodData
import com.student.student_searchmap.R
import kotlinx.android.synthetic.main.activity_calories.*

class CaloriesActivity : AppCompatActivity() {
    lateinit var mButton1: Button
    lateinit var mButton2: Button
    lateinit var mButton3: Button
    lateinit var mButton4: Button
    lateinit var mAddButton: Button
    lateinit var mHaveDateAdd :Button
    lateinit var mHaveDateLayout:RelativeLayout

    lateinit var mNoDateLayout:RelativeLayout
    var type :Int = 0
    var mAdapter: MyAdapter? = null
    lateinit var mListView: ListView
    var mArray = ArrayList<AddFoodData>()
    var addFoodData  = AddFoodData()
    var mData: java.util.ArrayList<AddFoodData> = java.util.ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calories)
        initLayout()

    }
    fun initLayout(){
        mHaveDateLayout = findViewById(R.id.havelayout)
        mNoDateLayout = findViewById(R.id.nodatelayout)
        mListView = findViewById(R.id.listview)
        mAddButton = findViewById(R.id.addbutton)
        mHaveDateAdd = findViewById(R.id.add)
        mButton1 = findViewById(R.id.button1)
        mButton2 = findViewById(R.id.button2)
        mButton3 = findViewById(R.id.button3)
        mButton4 = findViewById(R.id.button4)
        mButton1.setOnClickListener {

            type = 0
            gettype(type)

        }
        mButton2.setOnClickListener {
            mNoDateLayout.visibility = View.GONE
            mHaveDateLayout.visibility=View.VISIBLE
            type = 1
            gettype(type)

        }
        mButton3.setOnClickListener {
            mNoDateLayout.visibility= View.VISIBLE
            mHaveDateLayout.visibility=View.GONE
            type = 2
            gettype(type)

        }
        mButton4.setOnClickListener {
            mNoDateLayout.visibility = View.GONE
            mHaveDateLayout.visibility = View.VISIBLE
            type = 3
            gettype(type)

        }
        mAddButton.setOnClickListener {
            when(type){

                0 -> setData("0")
                1 -> setData("1")
                2 -> setData("2")
                3 -> setData("3")



            }



        }
        mHaveDateAdd.setOnClickListener {
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
        startActivityForResult(intent, 0)

    }

    override fun onResume() {
        super.onResume()
        gettype(type)

    }
    fun gettype(type :Int){
        when(type){
            0 ->
                if (MySharedPrefernces.getFood1Array(this)!=null&&
                        MySharedPrefernces.getFood1Array(this).size!=0){
                    mArray.clear()
                    mArray = MySharedPrefernces.getFood1Array(this)
                    mAdapter = MyAdapter(mArray)
                    mListView.setAdapter(mAdapter)

                    mAdapter!!.notifyDataSetChanged()

                    MySharedPrefernces.getFood1Array(this).forEach {
                        Log.d("Jack",it.name)
                        mNoDateLayout.visibility = View.GONE
                        mHaveDateLayout.visibility = View.VISIBLE

                    }
                }else{
                    Toast.makeText(this,"無數據",Toast.LENGTH_SHORT).show()
                    mNoDateLayout.visibility = View.VISIBLE
                    mHaveDateLayout.visibility = View.GONE
                }
            1->
                if (MySharedPrefernces.getFood2Array(this)!=null&&
                        MySharedPrefernces.getFood2Array(this).size!=0){
                    mArray.clear()

                    mArray = MySharedPrefernces.getFood2Array(this)
                    mAdapter = MyAdapter(mArray)
                    mListView.setAdapter(mAdapter)

                    mAdapter!!.notifyDataSetChanged()

                    MySharedPrefernces.getFood2Array(this).forEach {
                        mNoDateLayout.visibility = View.GONE
                        mHaveDateLayout.visibility = View.VISIBLE
                    }
                }else{
                    Toast.makeText(this,"無數據",Toast.LENGTH_SHORT).show()
                    mNoDateLayout.visibility = View.VISIBLE
                    mHaveDateLayout.visibility = View.GONE
                }
            2->
                if (MySharedPrefernces.getFood3Array(this)!=null&&
                        MySharedPrefernces.getFood3Array(this).size!=0){
                    mArray = MySharedPrefernces.getFood3Array(this)
                    mAdapter = MyAdapter(mArray)
                    mListView.setAdapter(mAdapter)

                    mAdapter!!.notifyDataSetChanged()

                    MySharedPrefernces.getFood3Array(this).forEach {
                        Log.d("Jack",it.name)
                        mNoDateLayout.visibility = View.GONE
                        mHaveDateLayout.visibility = View.VISIBLE

                    }
                }else{
                    Toast.makeText(this,"無數據",Toast.LENGTH_SHORT).show()
                    mNoDateLayout.visibility = View.VISIBLE
                    mHaveDateLayout.visibility = View.GONE
                }
            3 ->
                if (MySharedPrefernces.getFood4Array(this)!=null&&
                        MySharedPrefernces.getFood4Array(this).size!=0){
                    mArray.clear()

                    mArray = MySharedPrefernces.getFood4Array(this)
                    mListView.setAdapter(mAdapter)

                    mAdapter!!.notifyDataSetChanged()


                    MySharedPrefernces.getFood4Array(this).forEach {
                        Log.d("Jack",it.name)
                        mNoDateLayout.visibility = View.GONE
                        mHaveDateLayout.visibility = View.VISIBLE

                    }
                }else{
                    Toast.makeText(this,"無數據",Toast.LENGTH_SHORT).show()
                    mNoDateLayout.visibility = View.VISIBLE
                    mHaveDateLayout.visibility = View.GONE
                }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 0){
            if(data==null){
                return
            }

            addFoodData.setName(data!!.getStringExtra("name"))
            addFoodData.setPhotoUrl(data!!.getStringExtra("img"))
            when (data!!.getIntExtra("type",-1)) {

                0 ->

                    if (MySharedPrefernces.getFood1Array(this) != null &&
                            MySharedPrefernces.getFood1Array(this).size != 0) {

                        mData = MySharedPrefernces.getFood1Array(this)
                        mData.add(addFoodData)
                        MySharedPrefernces.saveFood1Array(this, mData)
                        mData.clear()


                    } else {
                        MySharedPrefernces.saveFood1Array(this, mData)
                        mData.clear()

                    }

                1 ->
                    if (MySharedPrefernces.getFood2Array(this)!=null &&
                            MySharedPrefernces.getFood2Array(this).size!=0){
                        mData = MySharedPrefernces.getFood2Array(this)
                        mData.add(addFoodData)
                        MySharedPrefernces.saveFood2Array(this,mData)
                        mData.clear()


                    }else{
                        MySharedPrefernces.saveFood2Array(this,mData)
                        mData.clear()

                    }

                2 ->
                    if (MySharedPrefernces.getFood3Array(this)!=null &&
                            MySharedPrefernces.getFood3Array(this).size!=0){
                        mData = MySharedPrefernces.getFood3Array(this)
                        mData.add(addFoodData)
                        MySharedPrefernces.saveFood3Array(this,mData)
                        mData.clear()


                    }else{
                        MySharedPrefernces.saveFood3Array(this,mData)
                        mData.clear()

                    }

                3 ->
                    if (MySharedPrefernces.getFood4Array(this)!=null &&
                            MySharedPrefernces.getFood4Array(this).size!=0){
                        mData = MySharedPrefernces.getFood4Array(this)
                        mData.add(addFoodData)
                        MySharedPrefernces.saveFood4Array(this,mData)
                        mData.clear()


                    }else{
                        MySharedPrefernces.saveFood4Array(this,mData)
                        mData.clear()

                    }

            }

        }else{
            Toast.makeText(this,"失敗",Toast.LENGTH_SHORT).show()
        }


    }
    inner class MyAdapter(var mAllData: ArrayList<AddFoodData>?) : BaseAdapter() {
        fun updateData(datas: ArrayList<AddFoodData>) {
            mAllData = datas
            notifyDataSetChanged()
        }

        override fun getCount(): Int {
            return mAllData!!.size
        }

        override fun getItem(position: Int): Any {
            return mAllData!![position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var convertView = convertView
            val data = mAllData!![position]
            if (convertView == null)
                convertView = LayoutInflater.from(this@CaloriesActivity).inflate(
                        R.layout.listview_layout, null)
            var mTittleText : TextView = convertView!!.findViewById(R.id.listviewtext)
            var photoimg : ImageView = convertView!!.findViewById(R.id.listview_img)
            mTittleText.setText(data.name)
//            photoimg.setOnClickListener {
//                mArray.removeAt(position)
//                mprice.removeAt(position)
//                MySharedPrefernces.saveArrayList(this@mShopCarActivity, mArray)
//                MySharedPrefernces.savePriceArrayList(this@mShopCarActivity, mprice)
//                getPrice()
//
//
//            }


            return convertView
        }

    }
}
