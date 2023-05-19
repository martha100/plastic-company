package com.example.finalproject

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PlasticsActivity : AppCompatActivity() {
    lateinit var mListPlastics:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plastics)
        mListPlastics = findViewById(R.id.mListPlastics)
        var cars:ArrayList<Ulpoad> = ArrayList()
        var myAdapter = PlasticsAdapter(applicationContext,cars)
        var progress = ProgressDialog(this)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")

        //Access the table in the database

        var my_db = FirebaseDatabase.getInstance().reference.child("Plastics")
        //Start retrieving data
        progress.show()
        my_db.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                //Get the data and put it on the arraylist users
                cars.clear()
                for (snap in p0.children){
                    var car = snap.getValue(Ulpoad::class.java)
                    cars.add(car!!)
                }
                //Notify the adapter that data has changed
                myAdapter.notifyDataSetChanged()
                progress.dismiss()
            }

            override fun onCancelled(p0: DatabaseError) {
                progress.dismiss()
                Toast.makeText(applicationContext,"DB Locked",Toast.LENGTH_LONG).show()
            }
        })

        mListPlastics.adapter = myAdapter


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_add_cars -> {
                Toast.makeText(this,"Add plastics clicked",Toast.LENGTH_LONG).show()
                startActivity(Intent(this,AddplasticsActivity::class.java))
                true
            }

            R.id.action_view_cars -> {
                Toast.makeText(this,"View plastics clicked",Toast.LENGTH_LONG).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}