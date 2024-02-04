package com.karan.chainreaction

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private var grid = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createSpinner()

        val button:Button = findViewById(R.id.StartOfflineButton)
        button.setOnClickListener {
            val intent = Intent(this, OfflineGame::class.java)
            intent.putExtra("grid", grid.toString())
            startActivity(intent)
        }
    }
    private fun createSpinner(){
        val spinner : Spinner = findViewById(R.id.sizeOfGrid)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                spinner.setSelection(position)
                val value = parent?.getItemAtPosition(position).toString()
                grid = value.toInt()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        val list : ArrayList<Int> = ArrayList()
        list.add(10)
        list.add(15)
        list.add(20)
        list.add(25)
        val adapter:ArrayAdapter<Int> = ArrayAdapter(this,android.R.layout.simple_spinner_item,list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
}