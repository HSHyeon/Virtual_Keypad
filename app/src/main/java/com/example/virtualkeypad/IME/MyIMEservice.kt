package com.example.virtualkeypad.IME

import android.inputmethodservice.InputMethodService
import android.view.View
import com.example.virtualkeypad.R

class MyIMEservice: InputMethodService() {
    override fun onCreateInputView(): View {
        val myKeyboardView: View = layoutInflater.inflate(R.layout.in_app_keyboard, null)
        return myKeyboardView
    }
}
