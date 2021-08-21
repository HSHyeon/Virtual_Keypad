package com.example.virtualkeypad.IME

import android.inputmethodservice.InputMethodService
import android.view.View
import android.view.inputmethod.InputConnection
import android.widget.Button
import android.widget.EditText
import com.example.virtualkeypad.Chunjiin
import com.example.virtualkeypad.Chunjiin_kt
import com.example.virtualkeypad.R

class MyIMEservice: InputMethodService() {

    public var chunjiin: Chunjiin? = null
    public lateinit var btn: Array<Button?>
    public var et: EditText? = null

    override fun onCreateInputView(): View {
        val myKeyboardView: View = layoutInflater.inflate(R.layout.in_app_keyboard, null)



        et = myKeyboardView.findViewById<EditText>(R.id.chunjiin_text)
        btn = arrayOfNulls(21)
        btn[0] = myKeyboardView.findViewById<View>(R.id.chunjiin_button0) as Button
        btn[1] = myKeyboardView.findViewById<View>(R.id.chunjiin_button1) as Button
        btn[2] = myKeyboardView.findViewById<View>(R.id.chunjiin_button2) as Button
        btn[3] = myKeyboardView.findViewById<View>(R.id.chunjiin_button3) as Button
        btn[4] = myKeyboardView.findViewById<View>(R.id.chunjiin_button4) as Button
        btn[5] = myKeyboardView.findViewById<View>(R.id.chunjiin_button5) as Button
        btn[6] = myKeyboardView.findViewById<View>(R.id.chunjiin_button6) as Button
        btn[7] = myKeyboardView.findViewById<View>(R.id.chunjiin_button7) as Button
        btn[8] = myKeyboardView.findViewById<View>(R.id.chunjiin_button8) as Button
        btn[9] = myKeyboardView.findViewById<View>(R.id.chunjiin_button9) as Button
        btn[10] = myKeyboardView.findViewById<View>(R.id.chunjiin_buttonex1) as Button
        btn[11] = myKeyboardView.findViewById<View>(R.id.chunjiin_buttonex2) as Button
        btn[12] = myKeyboardView.findViewById<View>(R.id.chunjiin_buttonex3) as Button

        btn[13] = myKeyboardView.findViewById<View>(R.id.chunjiin_button_left) as Button
        btn[14] = myKeyboardView.findViewById<View>(R.id.chunjiin_button_right) as Button
        btn[15] = myKeyboardView.findViewById<View>(R.id.chunjiin_button_up) as Button
        btn[16] = myKeyboardView.findViewById<View>(R.id.chunjiin_button_down) as Button
        btn[17] = myKeyboardView.findViewById<View>(R.id.chunjiin_button_ok) as Button
        btn[18] = myKeyboardView.findViewById<View>(R.id.chunjiin_button_cancel) as Button
        btn[19] = myKeyboardView.findViewById<View>(R.id.chunjiin_button_home) as Button
        btn[20] = myKeyboardView.findViewById<View>(R.id.chunjiin_button_menu) as Button



        val ic = currentInputConnection
        chunjiin = Chunjiin(et!!, btn, ic)






        return myKeyboardView
    }
}
