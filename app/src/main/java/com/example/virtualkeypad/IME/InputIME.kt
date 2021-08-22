//package com.example.virtualkeypad.IME
//
//import android.content.Intent
//import android.inputmethodservice.InputMethodService
//import android.util.Log
//import com.example.virtualkeypad.Chunjiinactivity
//
//class InputIME : InputMethodService(){
////
//
////    override fun onCreate(){
////        super.onCreate()
////        val intent = Intent(this, Chunjiinactivity::class.java)
////        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
////        Log.d("keypad", "start Activity")
////        startActivity(intent)
////    }
//
////    override fun onCreateInputView(): View {
////        val myKeyboardView = layoutInflater.inflate(R.id.)
////
////        return super.onCreateInputView()
////
////    }
//
////    override fun onCreateInputView(): View {
////        return layoutInflater.inflate(R.layout.activity_chunjiin, null).apply {
////            if (this is MyKeyboardView) {
////                setOnKeyboardActionListener(this@MyInputMethod)
////                keyboard = latinKeyboard
////            }
////        }
////    }
//
//}