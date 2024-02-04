package com.karan.chainreaction

import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import processing.android.PFragment
import processing.core.PApplet

class OfflineGame : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offline_game)
        val intent = intent
        val message = intent.getStringExtra("grid")
        if (message != null) {
            Log.i("lamao",message)
        }
        val canvasContainer = findViewById<FrameLayout>(R.id.processingCanvas)
        val globalLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                Log.i("ok", "${canvasContainer.width}  ${canvasContainer.height}")

                canvasContainer.viewTreeObserver.removeOnGlobalLayoutListener(this)
                val grid = intent.getStringExtra("grid")?.toInt()
                val sketch: PApplet = Sketch(canvasContainer.width, canvasContainer.height, grid)

                val fragment = PFragment(sketch)
                fragment.setView(canvasContainer, this@OfflineGame)
            }
        }
        canvasContainer.viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)

    }
}