package com.example.virtualkeypad


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MotionEvent
import android.view.View
import android.widget.Toast

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view: View = MyView(this)
        setContentView(view)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  menuInflater.inflate(R.menu.main, menu) 뭔지 모르겟음
        return true
    }

    protected inner class MyView(context: Context?) : View(context) {
        override fun onTouchEvent(event: MotionEvent): Boolean {
         
            super.onTouchEvent(event)

            //event
            //event 종류/각각의 특성
            if (event.action == MotionEvent.ACTION_DOWN) {
                val x = event.x
                val y = event.y
                val msg = "터치를 입력받음 : $x / $y"
                Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
                return true
            }
            return false
        }
    }}
