package com.example.virtualkeypad
import android.R
import android.content.Context
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


/*
* Released under the MIT license
* Copyright (c) 2014 KimYs(a.k.a ZeDA)
* http://blog.naver.com/irineu2
*
Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:
The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
*/



class Chunjiin(private val et: EditText, view:Context) {
    private lateinit var btn: Array<Button>
    private var now_mode = HANGUL

    private inner class Hangul {
        var chosung = ""
        var jungsung = ""
        var jongsung = ""
        var jongsung2 = ""
        var step = 0
        var flag_writing = false
        var flag_dotused = false
        var flag_doubled = false
        var flag_addcursor = false
        var flag_space = false
        fun init() {
            chosung = ""
            jungsung = ""
            jongsung = ""
            jongsung2 = ""
            step = 0
            flag_writing = false
            flag_dotused = false
            flag_doubled = false
            flag_addcursor = false
            flag_space = false
        }
    }

    private val hangul = Hangul()
    private var engnum = ""
    private var flag_initengnum = false
    private var flag_engdelete = false
    private var flag_upper = true


    private fun setButton(view:Context?){

    }

        /*
        tn = inputbtn;
        for(int i=0;i<12;i++)
        btn[i].setOnClickListener(btnlistner);
        btn[12].setOnClickListener(btnchglistner);
        setBtnText(now_mode); */
        //setBtnText(now_mode)


    private val otl = OnTouchListener { v, event ->
        v.onTouchEvent(event)
        val imm = v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm?.hideSoftInputFromWindow(v.windowToken, 0)
        hangul.init()
        init_engnum()
        true
    }
    private val btnlistner = View.OnClickListener { v ->
        var input = -1

        var curX:Float=v.x
        var curY:Float=v.y

        if(curX.toInt() in 44..107){
            Log.d("curX for", "success!")
            if(curY.toInt() in 18..81){
                Log.d("2중 for", "success!")
                //Toast.makeText(this@MainActivity, "좌표인식!", Toast.LENGTH_SHORT).show()
            }
        }
        /*
        when (v.id) {
            R.id.chunjiin_button0 -> input = 0
            R.id.chunjiin_button1 -> input = 1
            R.id.chunjiin_button2 -> input = 2
            R.id.chunjiin_button3 -> input = 3
            R.id.chunjiin_button4 -> input = 4
            R.id.chunjiin_button5 -> input = 5
            R.id.chunjiin_button6 -> input = 6
            R.id.chunjiin_button7 -> input = 7
            R.id.chunjiin_button8 -> input = 8
            R.id.chunjiin_button9 -> input = 9
            R.id.chunjiin_buttonex1 -> input = 10
            R.id.chunjiin_buttonex2 -> input = 11
        } */
        if (input == -1) return@OnClickListener
        if (now_mode == HANGUL) hangulMake(input) else if (now_mode == ENGLISH || now_mode == UPPER_ENGLISH) engMake(input) else  // if(now_mode == NUMBER)
            numMake(input)
        write(now_mode)
    }
    private val btnchglistner = View.OnClickListener {
        now_mode = if (now_mode == NUMBER) HANGUL else now_mode + 1
        setBtnText(now_mode)
        hangul.init()
        init_engnum()
    }

    private fun init_engnum() {
        engnum = ""
        flag_initengnum = false
        flag_engdelete = false
    }

    private fun write(mode: Int) {
        var position = et.selectionStart
        var origin = ""
        var str = ""
        origin = et.text.toString()
        if (mode == HANGUL) {
            var dotflag = false
            var doubleflag = false
            var spaceflag = false
            var impossiblejongsungflag = false
            val unicode: Char
            var real_jongsung = checkDouble(hangul.jongsung, hangul.jongsung2)
            if (real_jongsung.length == 0) {
                real_jongsung = hangul.jongsung
                if (hangul.jongsung2.length != 0) doubleflag = true
            }

            //bug fixed, 16.4.22 ~
            //added impossible jongsungflag.
            if (hangul.jongsung == "ㅃ" || hangul.jongsung == "ㅉ" || hangul.jongsung == "ㄸ") {
                doubleflag = true
                impossiblejongsungflag = true
                unicode = getUnicode("").toChar()
            } else unicode = getUnicode(real_jongsung).toChar()
            // ~ bug fixed, 16.4.22
            str += if (!hangul.flag_writing) origin.substring(0, position) else if (hangul.flag_dotused) {
                if (hangul.chosung.length == 0) origin.substring(0, position - 1) else origin.substring(0, position - 2)
            } else if (hangul.flag_doubled) origin.substring(0, position - 2) else origin.substring(0, position - 1)
            if (unicode.toInt() != 0) str += unicode.toString()
            if (hangul.flag_space) {
                str += " "
                hangul.flag_space = false
                spaceflag = true
            }
            if (doubleflag) {
                str += if (impossiblejongsungflag) hangul.jongsung else hangul.jongsung2
            }
            if (hangul.jungsung == "·") {
                str += "·"
                dotflag = true
            } else if (hangul.jungsung == "‥") {
                str += "‥"
                dotflag = true
            }
            str += origin.substring(position, origin.length)
            et.setText(str)
            if (dotflag) position++
            if (doubleflag) {
                if (!hangul.flag_doubled) position++
                hangul.flag_doubled = true
            } else {
                if (hangul.flag_doubled) position--
                hangul.flag_doubled = false
            }
            if (spaceflag) position++
            if (unicode.toInt() == 0 && dotflag == false) position--
            if (hangul.flag_addcursor) {
                hangul.flag_addcursor = false
                position++
            }
            if (hangul.flag_dotused) {
                if (hangul.chosung.length == 0 && dotflag == false) et.setSelection(position) else et.setSelection(position - 1)
            } else if (!hangul.flag_writing && dotflag == false) et.setSelection(position + 1) else et.setSelection(position)
            hangul.flag_dotused = false
            hangul.flag_writing = if (unicode.toInt() == 0 && dotflag == false) false else true
        } else  //if(mode == ENGLISH || mode == UPPER_ENGLISH || mode == NUMBER)
        {
            str += if (flag_engdelete) origin.substring(0, position - 1) else origin.substring(0, position)
            str += if (flag_upper || mode == NUMBER) engnum else engnum.toLowerCase()
            if (flag_engdelete) {
                str += origin.substring(position, origin.length)
                et.setText(str)
                et.setSelection(position)
                flag_engdelete = false
            } else {
                str += origin.substring(position, origin.length)
                et.setText(str)
                if (engnum.length == 0) et.setSelection(position) else et.setSelection(position + 1)
            }
            if (flag_initengnum) init_engnum()
        }
    }

    private fun delete() {
        val position = et.selectionStart
        if (position == 0) return
        var origin = ""
        var str = ""
        origin = et.text.toString()
        str += origin.substring(0, position - 1)
        str += origin.substring(position, origin.length)
        et.setText(str)
        et.setSelection(position - 1)
    }

    private fun engMake(input: Int) {
        if (input == 10) // 띄어쓰기
        {
            engnum = if (engnum.length == 0) " " else ""
            flag_initengnum = true
        } else if (input == 11) // 지우기
        {
            delete()
            init_engnum()
        } else {
            var str = ""
            str = when (input) {
                0 -> "@?!"
                1 -> ".QZ"
                2 -> "ABC"
                3 -> "DEF"
                4 -> "GHI"
                5 -> "JKL"
                6 -> "MNO"
                7 -> "PRS"
                8 -> "TUV"
                9 -> "WXY"
                else -> return
            }
            val ch = str.toCharArray()
            if (engnum.length == 0) engnum = ch[0].toString() else if (engnum == ch[0].toString()) {
                engnum = ch[1].toString()
                flag_engdelete = true
            } else if (engnum == ch[1].toString()) {
                engnum = ch[2].toString()
                flag_engdelete = true
            } else if (engnum == ch[2].toString()) {
                engnum = ch[0].toString()
                flag_engdelete = true
            } else engnum = ch[0].toString()
        }
    }

    private fun numMake(input: Int) {
        if (input == 10) // 띄어쓰기
            engnum = " " else if (input == 11) // 지우기
            delete() else engnum = Integer.toString(input)
        flag_initengnum = true
    }

    private fun hangulMake(input: Int) {
        var beforedata = ""
        var nowdata = ""
        var overdata = ""
        if (input == 10) //띄어쓰기
        {
            if (hangul.flag_writing) hangul.init() else hangul.flag_space = true
        } else if (input == 11) //지우기
        {
            if (hangul.step == 0) {
                if (hangul.chosung.length == 0) {
                    delete()
                    hangul.flag_writing = false
                } else hangul.chosung = ""
            } else if (hangul.step == 1) {
                if (hangul.jungsung == "·" || hangul.jungsung == "‥") {
                    delete()
                    if (hangul.chosung.length == 0) hangul.flag_writing = false
                }
                hangul.jungsung = ""
                hangul.step = 0
            } else if (hangul.step == 2) {
                hangul.jongsung = ""
                hangul.step = 1
            } else if (hangul.step == 3) {
                hangul.jongsung2 = ""
                hangul.step = 2
            }
        } else if (input == 1 || input == 2 || input == 3) //모음
        {
            //받침에서 떼어오는거 추가해야함
            var batchim = false
            if (hangul.step == 2) {
                delete()
                val s = hangul.jongsung
                //bug fixed, 16.4.22 ~
                if (!hangul.flag_doubled) {
                    hangul.jongsung = ""
                    hangul.flag_writing = false
                    write(now_mode)
                }
                // ~ bug fixed, 16.4.22
                hangul.init()
                hangul.chosung = s
                hangul.step = 0
                batchim = true
            } else if (hangul.step == 3) {
                val s = hangul.jongsung2
                if (hangul.flag_doubled) delete() else {
                    delete()
                    hangul.jongsung2 = ""
                    hangul.flag_writing = false
                    write(now_mode)
                }
                hangul.init()
                hangul.chosung = s
                hangul.step = 0
                batchim = true
            }
            beforedata = hangul.jungsung
            hangul.step = 1
            if (input == 1) // ㅣ ㅓ ㅕ ㅐ ㅔ ㅖㅒ ㅚ ㅟ ㅙ ㅝ ㅞ ㅢ
            {
                if (beforedata.length == 0) nowdata = "ㅣ" else if (beforedata == "·") {
                    nowdata = "ㅓ"
                    hangul.flag_dotused = true
                } else if (beforedata == "‥") {
                    nowdata = "ㅕ"
                    hangul.flag_dotused = true
                } else if (beforedata == "ㅏ") nowdata = "ㅐ" else if (beforedata == "ㅑ") nowdata = "ㅒ" else if (beforedata == "ㅓ") nowdata = "ㅔ" else if (beforedata == "ㅕ") nowdata = "ㅖ" else if (beforedata == "ㅗ") nowdata = "ㅚ" else if (beforedata == "ㅜ") nowdata = "ㅟ" else if (beforedata == "ㅠ") nowdata = "ㅝ" else if (beforedata == "ㅘ") nowdata = "ㅙ" else if (beforedata == "ㅝ") nowdata = "ㅞ" else if (beforedata == "ㅡ") nowdata = "ㅢ" else {
                    hangul.init()
                    hangul.step = 1
                    nowdata = "ㅣ"
                }
            } else if (input == 2) // ·,‥,ㅏ,ㅑ,ㅜ,ㅠ,ㅘ
            {
                if (beforedata.length == 0) {
                    nowdata = "·"
                    if (batchim) hangul.flag_addcursor = true
                } else if (beforedata == "·") {
                    nowdata = "‥"
                    hangul.flag_dotused = true
                } else if (beforedata == "‥") {
                    nowdata = "·"
                    hangul.flag_dotused = true
                } else if (beforedata == "ㅣ") nowdata = "ㅏ" else if (beforedata == "ㅏ") nowdata = "ㅑ" else if (beforedata == "ㅡ") nowdata = "ㅜ" else if (beforedata == "ㅜ") nowdata = "ㅠ" else if (beforedata == "ㅚ") nowdata = "ㅘ" else {
                    hangul.init()
                    hangul.step = 1
                    nowdata = "·"
                }
            } else if (input == 3) // ㅡ, ㅗ, ㅛ
            {
                if (beforedata.length == 0) nowdata = "ㅡ" else if (beforedata == "·") {
                    nowdata = "ㅗ"
                    hangul.flag_dotused = true
                } else if (beforedata == "‥") {
                    nowdata = "ㅛ"
                    hangul.flag_dotused = true
                } else {
                    hangul.init()
                    hangul.step = 1
                    nowdata = "ㅡ"
                }
            }
            hangul.jungsung = nowdata
        } else  //자음
        {
            if (hangul.step == 1) {
                if (hangul.jungsung == "·" || hangul.jungsung == "‥") hangul.init() else hangul.step = 2
            }
            if (hangul.step == 0) beforedata = hangul.chosung else if (hangul.step == 2) beforedata = hangul.jongsung else if (hangul.step == 3) beforedata = hangul.jongsung2
            if (input == 4) // ㄱ, ㅋ, ㄲ, ㄺ
            {
                if (beforedata.length == 0) {
                    if (hangul.step == 2) {
                        if (hangul.chosung.length == 0) overdata = "ㄱ" else nowdata = "ㄱ"
                    } else nowdata = "ㄱ"
                } else if (beforedata == "ㄱ") nowdata = "ㅋ" else if (beforedata == "ㅋ") nowdata = "ㄲ" else if (beforedata == "ㄲ") nowdata = "ㄱ" else if (beforedata == "ㄹ" && hangul.step == 2) {
                    hangul.step = 3
                    nowdata = "ㄱ"
                } else overdata = "ㄱ"
            } else if (input == 5) // ㄴ ㄹ
            {
                if (beforedata.length == 0) {
                    if (hangul.step == 2) {
                        if (hangul.chosung.length == 0) overdata = "ㄴ" else nowdata = "ㄴ"
                    } else nowdata = "ㄴ"
                } else if (beforedata == "ㄴ") nowdata = "ㄹ" else if (beforedata == "ㄹ") nowdata = "ㄴ" else overdata = "ㄴ"
            } else if (input == 6) // ㄷ, ㅌ, ㄸ, ㄾ
            {
                if (beforedata.length == 0) {
                    if (hangul.step == 2) {
                        if (hangul.chosung.length == 0) overdata = "ㄷ" else nowdata = "ㄷ"
                    } else nowdata = "ㄷ"
                } else if (beforedata == "ㄷ") nowdata = "ㅌ" else if (beforedata == "ㅌ") nowdata = "ㄸ" else if (beforedata == "ㄸ") nowdata = "ㄷ" else if (beforedata == "ㄹ" && hangul.step == 2) {
                    hangul.step = 3
                    nowdata = "ㄷ"
                } else overdata = "ㄷ"
            } else if (input == 7) // ㅂ, ㅍ, ㅃ, ㄼ, ㄿ
            {
                if (beforedata.length == 0) {
                    if (hangul.step == 2) {
                        if (hangul.chosung.length == 0) overdata = "ㅂ" else nowdata = "ㅂ"
                    } else nowdata = "ㅂ"
                } else if (beforedata == "ㅂ") nowdata = "ㅍ" else if (beforedata == "ㅍ") nowdata = "ㅃ" else if (beforedata == "ㅃ") nowdata = "ㅂ" else if (beforedata == "ㄹ" && hangul.step == 2) {
                    hangul.step = 3
                    nowdata = "ㅂ"
                } else overdata = "ㅂ"
            } else if (input == 8) // ㅅ, ㅎ, ㅆ, ㄳ, ㄶ, ㄽ, ㅀ, ㅄ
            {
                if (beforedata.length == 0) {
                    if (hangul.step == 2) {
                        if (hangul.chosung.length == 0) overdata = "ㅅ" else nowdata = "ㅅ"
                    } else nowdata = "ㅅ"
                } else if (beforedata == "ㅅ") nowdata = "ㅎ" else if (beforedata == "ㅎ") nowdata = "ㅆ" else if (beforedata == "ㅆ") nowdata = "ㅅ" else if (beforedata == "ㄱ" && hangul.step == 2) {
                    hangul.step = 3
                    nowdata = "ㅅ"
                } else if (beforedata == "ㄴ" && hangul.step == 2) {
                    hangul.step = 3
                    nowdata = "ㅅ"
                } else if (beforedata == "ㄹ" && hangul.step == 2) {
                    hangul.step = 3
                    nowdata = "ㅅ"
                } else if (beforedata == "ㅂ" && hangul.step == 2) {
                    hangul.step = 3
                    nowdata = "ㅅ"
                } else overdata = "ㅅ"
            } else if (input == 9) // ㅈ, ㅊ, ㅉ, ㄵ
            {
                if (beforedata.length == 0) {
                    if (hangul.step == 2) {
                        if (hangul.chosung.length == 0) overdata = "ㅈ" else nowdata = "ㅈ"
                    } else nowdata = "ㅈ"
                } else if (beforedata == "ㅈ") nowdata = "ㅊ" else if (beforedata == "ㅊ") nowdata = "ㅉ" else if (beforedata == "ㅉ") nowdata = "ㅈ" else if (beforedata == "ㄴ" && hangul.step == 2) {
                    hangul.step = 3
                    nowdata = "ㅈ"
                } else overdata = "ㅈ"
            } else if (input == 0) // ㅇ, ㅁ, ㄻ
            {
                if (beforedata.length == 0) {
                    if (hangul.step == 2) {
                        if (hangul.chosung.length == 0) overdata = "ㅇ" else nowdata = "ㅇ"
                    } else nowdata = "ㅇ"
                } else if (beforedata == "ㅇ") nowdata = "ㅁ" else if (beforedata == "ㅁ") nowdata = "ㅇ" else if (beforedata == "ㄹ" && hangul.step == 2) {
                    hangul.step = 3
                    nowdata = "ㅇ"
                } else overdata = "ㅇ"
            }
            if (nowdata.length > 0) {
                if (hangul.step == 0) hangul.chosung = nowdata else if (hangul.step == 2) hangul.jongsung = nowdata else  //if(hangul.step == 3)
                    hangul.jongsung2 = nowdata
            }
            if (overdata.length > 0) {
                hangul.flag_writing = false
                hangul.init()
                hangul.chosung = overdata
            }
        }
    }

    private fun setBtnText(mode: Int) {
        when (mode) {
            HANGUL -> {
                btn[0].text = "ㅇㅁ"
                btn[1].text = "ㅣ"
                btn[2].text = "·"
                btn[3].text = "ㅡ"
                btn[4].text = "ㄱㅋ"
                btn[5].text = "ㄴㄹ"
                btn[6].text = "ㄷㅌ"
                btn[7].text = "ㅂㅍ"
                btn[8].text = "ㅅㅎ"
                btn[9].text = "ㅈㅊ"
            }
            UPPER_ENGLISH -> {
                btn[0].text = "@?!"
                btn[1].text = ".QZ"
                btn[2].text = "ABC"
                btn[3].text = "DEF"
                btn[4].text = "GHI"
                btn[5].text = "JKL"
                btn[6].text = "MNO"
                btn[7].text = "PRS"
                btn[8].text = "TUV"
                btn[9].text = "WXY"
                flag_upper = true
            }
            ENGLISH -> {
                btn[0].text = "@?!"
                btn[1].text = ".qz"
                btn[2].text = "abc"
                btn[3].text = "def"
                btn[4].text = "ghi"
                btn[5].text = "jkl"
                btn[6].text = "mno"
                btn[7].text = "prs"
                btn[8].text = "tuv"
                btn[9].text = "wxy"
                flag_upper = false
            }
            NUMBER -> {
                var i = 0
                while (i < 10) {
                    btn[i].text = Integer.toString(i)
                    i++
                }
            }
        }
    }

    private fun getUnicode(real_jong: String): Int {
        val cho: Int
        val jung: Int
        val jong: Int
        //초성
        if (hangul.chosung.length == 0) {
            if (hangul.jungsung.length == 0 || hangul.jungsung == "·" || hangul.jungsung == "‥") return 0
        }
        cho = if (hangul.chosung == "ㄱ") 0 else if (hangul.chosung == "ㄲ") 1 else if (hangul.chosung == "ㄴ") 2 else if (hangul.chosung == "ㄷ") 3 else if (hangul.chosung == "ㄸ") 4 else if (hangul.chosung == "ㄹ") 5 else if (hangul.chosung == "ㅁ") 6 else if (hangul.chosung == "ㅂ") 7 else if (hangul.chosung == "ㅃ") 8 else if (hangul.chosung == "ㅅ") 9 else if (hangul.chosung == "ㅆ") 10 else if (hangul.chosung == "ㅇ") 11 else if (hangul.chosung == "ㅈ") 12 else if (hangul.chosung == "ㅉ") 13 else if (hangul.chosung == "ㅊ") 14 else if (hangul.chosung == "ㅋ") 15 else if (hangul.chosung == "ㅌ") 16 else if (hangul.chosung == "ㅍ") 17 else  /*if ( hangul.chosung.equals("ㅎ"))*/ 18
        if (hangul.jungsung.length == 0 && hangul.jongsung.length == 0) return 0x1100 + cho
        if (hangul.jungsung == "·" || hangul.jungsung == "‥") return 0x1100 + cho

        // 중성
        jung = if (hangul.jungsung == "ㅏ") 0 else if (hangul.jungsung == "ㅐ") 1 else if (hangul.jungsung == "ㅑ") 2 else if (hangul.jungsung == "ㅒ") 3 else if (hangul.jungsung == "ㅓ") 4 else if (hangul.jungsung == "ㅔ") 5 else if (hangul.jungsung == "ㅕ") 6 else if (hangul.jungsung == "ㅖ") 7 else if (hangul.jungsung == "ㅗ") 8 else if (hangul.jungsung == "ㅘ") 9 else if (hangul.jungsung == "ㅙ") 10 else if (hangul.jungsung == "ㅚ") 11 else if (hangul.jungsung == "ㅛ") 12 else if (hangul.jungsung == "ㅜ") 13 else if (hangul.jungsung == "ㅝ") 14 else if (hangul.jungsung == "ㅞ") 15 else if (hangul.jungsung == "ㅟ") 16 else if (hangul.jungsung == "ㅠ") 17 else if (hangul.jungsung == "ㅡ") 18 else if (hangul.jungsung == "ㅢ") 19 else  /*if ( hangul.jungsung.equals("ㅣ"))*/ 20
        if (hangul.chosung.length == 0 && hangul.jongsung.length == 0) return 0x1161 + jung

        // 종성
        jong = if (real_jong.length == 0) 0 else if (real_jong == "ㄱ") 1 else if (real_jong == "ㄲ") 2 else if (real_jong == "ㄳ") 3 else if (real_jong == "ㄴ") 4 else if (real_jong == "ㄵ") 5 else if (real_jong == "ㄶ") 6 else if (real_jong == "ㄷ") 7 else if (real_jong == "ㄹ") 8 else if (real_jong == "ㄺ") 9 else if (real_jong == "ㄻ") 10 else if (real_jong == "ㄼ") 11 else if (real_jong == "ㄽ") 12 else if (real_jong == "ㄾ") 13 else if (real_jong == "ㄿ") 14 else if (real_jong == "ㅀ") 15 else if (real_jong == "ㅁ") 16 else if (real_jong == "ㅂ") 17 else if (real_jong == "ㅄ") 18 else if (real_jong == "ㅅ") 19 else if (real_jong == "ㅆ") 20 else if (real_jong == "ㅇ") 21 else if (real_jong == "ㅈ") 22 else if (real_jong == "ㅊ") 23 else if (real_jong == "ㅋ") 24 else if (real_jong == "ㅌ") 25 else if (real_jong == "ㅍ") 26 else  /*if ( real_jong.equals("ㅎ"))*/ 27
        return if (hangul.chosung.length == 0 && hangul.jungsung.length == 0) 0x11a8 + jong else 44032 + cho * 588 + jung * 28 + jong
    }

    private fun checkDouble(jong: String, jong2: String): String {
        var s = ""
        if (jong == "ㄱ") {
            if (jong2 == "ㅅ") s = "ㄳ"
        } else if (jong == "ㄴ") {
            if (jong2 == "ㅈ") s = "ㄵ" else if (jong2 == "ㅎ") s = "ㄶ"
        } else if (jong == "ㄹ") {
            if (jong2 == "ㄱ") s = "ㄺ" else if (jong2 == "ㅁ") s = "ㄻ" else if (jong2 == "ㅂ") s = "ㄼ" else if (jong2 == "ㅅ") s = "ㄽ" else if (jong2 == "ㅌ") s = "ㄾ" else if (jong2 == "ㅍ") s = "ㄿ" else if (jong2 == "ㅎ") s = "ㅀ"
        } else if (jong == "ㅂ") {
            if (jong2 == "ㅅ") s = "ㅄ"
        }
        return s
    }

    companion object {
        private const val HANGUL = 0
        private const val UPPER_ENGLISH = 1
        private const val ENGLISH = 2
        private const val NUMBER = 3
    }

    init {
        et.setOnTouchListener(otl)
        setButton(view)
    }
}