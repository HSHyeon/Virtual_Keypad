package com.example.virtualkeypad

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class TestMainActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_main_activity)

        //        // 코틀린으로 바꾸면 자꾸 오류나서 자바 파일 그대로 사용
////        val intent = Intent(this, Chunjiinactivity_kt::class.java)
        val intent = Intent(this, Chunjiinactivity::class.java)
        startActivity(intent)
    }
}