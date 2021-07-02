///*
//* Released under the MIT license
//* Copyright (c) 2014 KimYs(a.k.a ZeDA)
//* http://blog.naver.com/irineu2
//*
//Permission is hereby granted, free of charge, to any person
//obtaining a copy of this software and associated documentation
//files (the "Software"), to deal in the Software without
//restriction, including without limitation the rights to use,
//copy, modify, merge, publish, distribute, sublicense, and/or sell
//copies of the Software, and to permit persons to whom the
//Software is furnished to do so, subject to the following
//conditions:
//
//The above copyright notice and this permission notice shall be
//included in all copies or substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
//EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
//OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
//NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
//HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
//WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
//FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
//OTHER DEALINGS IN THE SOFTWARE.
//*/
//package com.example.virtualkeypad
//
////요녀석이 import 되게 되면, Android에서 관리하는 R.java 파일을 import 하게 되는데, 그럼 Android 기본적으로 제공하는 Resource만 사용하게 되는 것 입니다. 우리에게 필요한건 현재 프로젝트의 Resource 들인데 말이죠.
////출처: https://arabiannight.tistory.com/78 [아라비안나이트]
////import android.R
//import android.app.Activity
//import android.os.Bundle
//import android.view.View
//import android.widget.Button
//import android.widget.EditText
//
//class Chunjiinactivity : Activity() {
//    private var chunjiin: Chunjiin? = null
//    private var btn: Array<Button?> = TODO()
//    private var et: EditText? = null
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_chunjiin)
//        et = findViewById<View>(R.id.chunjiin_text) as EditText
//        btn = arrayOfNulls(13)
//        btn[0] = findViewById<View>(R.id.chunjiin_button0) as Button
//        btn[1] = findViewById<View>(R.id.chunjiin_button1) as Button
//        btn[2] = findViewById<View>(R.id.chunjiin_button2) as Button
//        btn[3] = findViewById<View>(R.id.chunjiin_button3) as Button
//        btn[4] = findViewById<View>(R.id.chunjiin_button4) as Button
//        btn[5] = findViewById<View>(R.id.chunjiin_button5) as Button
//        btn[6] = findViewById<View>(R.id.chunjiin_button6) as Button
//        btn[7] = findViewById<View>(R.id.chunjiin_button7) as Button
//        btn[8] = findViewById<View>(R.id.chunjiin_button8) as Button
//        btn[9] = findViewById<View>(R.id.chunjiin_button9) as Button
//        btn[10] = findViewById<View>(R.id.chunjiin_buttonex1) as Button
//        btn[11] = findViewById<View>(R.id.chunjiin_buttonex2) as Button
//        btn[12] = findViewById<View>(R.id.chunjiin_buttonex3) as Button
//        chunjiin = Chunjiin(et!!, btn)
//    }
//}