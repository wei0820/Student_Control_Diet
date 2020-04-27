package com.student.student_healthy

import android.app.Activity
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.student.student_searchmap.R

class CustomAdapter(val userList: ArrayList<User>,var activity:Activity) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(userList[position],activity)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: User,activity: Activity) {
            val textViewName = itemView.findViewById(R.id.textViewUsername) as TextView
            val textViewAge  = itemView.findViewById(R.id.textViewAge) as ImageView
            textViewName.text = user.name
            textViewAge.setImageResource(user.age)
            itemView.setOnClickListener({
                Toast.makeText(itemView.context, "您選擇了:"+user.name, Toast.LENGTH_SHORT).show()
                    var intent = Intent()
                intent.putExtra("name", user.name)
                intent.putExtra("img", user.age)
                activity.setResult(Activity.RESULT_OK, intent)
                activity.finish()

            })
        }
    }
}