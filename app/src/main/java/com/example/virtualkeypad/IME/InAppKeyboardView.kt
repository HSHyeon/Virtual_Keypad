package com.example.virtualkeypad.IME

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.virtualkeypad.R

class InAppKeyboardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet?=null, defStyleAttr: Int=0):
        ConstraintLayout(context, attrs, defStyleAttr) {

            init {
                LayoutInflater
                    .from(context)
//                    .inflate(R.layout.activity_chunjiin, this, true)
                    .inflate(R.layout.in_app_keyboard, this, true)
            }
}