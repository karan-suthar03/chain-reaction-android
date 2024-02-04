package com.karan.chainreaction

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createSpinner();

        val button:Button = findViewById(R.id.StartOfflineButton);
        button.setOnClickListener {
            Log.i("ok", "ok")
        }
    }
    private fun createSpinner(){
        val spinner : Spinner = findViewById(R.id.sizeOfGrid)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                spinner.setSelection(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        val list : ArrayList<Int> = ArrayList()
        list.add(1)
        list.add(2)
        list.add(3)
        list.add(4)
        val adapter:ArrayAdapter<Int> = ArrayAdapter(this,android.R.layout.simple_spinner_item,list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
}