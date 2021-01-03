package com.example.virtualkeypad


import android.app.Activity
import android.content.Context
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MotionEvent
import android.view.View
import android.view.ViewDebug
import android.widget.Toast
var count: Int = 1

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

           // super.onTouchEvent(event)

            //event
            //event 종류/각각의 특성

            if (event.action == MotionEvent.ACTION_DOWN) {
                if(count>=5){
                    Toast.makeText(this@MainActivity, "끝", Toast.LENGTH_SHORT).show()
                    }
                else {
                    //TODO 특정 5개의 범위의 좌표를 연속적으로 5번 터치할 때만 count하기
                    count++
                    val x = event.x
                    val y = event.y
                    val msg = "터치를 입력받음 : $x / $y"
                    Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
                    Toast.makeText(this@MainActivity, count.toString(), Toast.LENGTH_SHORT).show()
                    return true
            }
            }
            return false

        }
    }}
