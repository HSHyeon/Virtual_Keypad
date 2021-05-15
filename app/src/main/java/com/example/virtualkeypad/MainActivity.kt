package com.example.virtualkeypad


import android.app.Activity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Button
import com.example.virtualkeypad.databinding.ActivityMainBinding
import java.util.*


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

var count: Int = 0
private var mTTS: TextToSpeech? = null
// 입력받은 5개 좌표 들어갈 배열
// count.toFloat()로 한 이유: 0.0으로 초기화 하니까 Double로 되어 오류 나서.. 바꿔 말하자면 타입 맞추려고 한 것임.
var pointX =  Array<Float>(5){count.toFloat()} //사이즈는 5이고 값은 0
var pointY =  Array<Float>(5){count.toFloat()} //사이즈는 5이고 값은 0


class MainActivity : Activity() {
    private lateinit var binding: ActivityMainBinding
    private var viewText = ""
    private lateinit var chunjiin: Chunjiin
    private lateinit var btn: Array<Button>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)

        viewText=binding.showText.toString()
        mTTS = TextToSpeech(this, TextToSpeech.OnInitListener { i ->
            if (i == TextToSpeech.SUCCESS) {
                val result = mTTS!!.setLanguage(Locale.US)
                if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED
                ) {
                    Log.e("TTS", "Language Not Supported")
                }
            } else {
                Log.e("TTS", "Initialization Failed")
            }
        })


        binding.showText.setOnTouchListener(listener)
        //keyEvent()
        //textView=findViewById(R.id.show_text)
        setContentView(binding.root)

        //chunjiin=Chunjiin(binding.showText,this)
    }

    private fun printString(s: String) {
        Log.d("버튼 클릭!",s)

        binding.showText.append(s)
    }

    private val listener= OnTouchListener { v, event ->
        val action = event.action
        val curX = event.x //눌린 곳의 X좌표
        val curY = event.y //눌린 곳의 Y좌표

        val msg = "터치를 입력받음 : $curX / $curY"

        Log.d("test", msg)

        if(curX in 44.09..107.08){
            if(curY in 18.90..81.89){
                printString("1")
            }
            if(curY in 107.09..170.08){
                printString("4")
            }
            if(curY in 195.28..258.27){
                printString("7")
            }
            if(curY in 283.47..346.46){
                printString("*")
            }
            if(curY in 611.03..674.02){
                printString("취소")
            }
        }
        else if(curX in 170.07..233.06){
            if(curY in 18.90..81.89){
                printString("2")
            }
            if(curY in 107.09..170.08){
                printString("5")
            }
            if(curY in 195.28..258.27){
                printString("8")
            }
            if(curY in 283.47..346.46){
                printString("0")
            }
            if(curY in 611.03..674.02){
                printString("확인")
            }
        }
        else if(curX in 296.05..359.04){
            if(curY in 18.90..81.89){
                printString("3")
            }
            if(curY in 107.09..170.08){
                printString("6")
            }
            if(curY in 195.28..258.27){
                printString("9")
            }
            if(curY in 283.47..346.46){
                printString("#")
            }
            if(curY in 611.03..674.02){
                printString("메뉴")
            }
        }
        if(curY in 447.25..510.24){ //가운데
            if(curX in 81.89..144.88){
                printString("왼쪽")
            }
            else if(curX in 157.48..220.47){
                printString("가운데")
            }
            else if(curX in 233.07..296.06){
                printString("오른쪽")
            }
        }
        else if(curY in 371.66..434.65){ //제일 위
            if(curX in 157.48..220.47){
                printString("위")
            }
        }
        else if(curY in 522.84..585.83){ //아래
            if(curX in 157.48..220.47){
                printString("아래")
            }
        }
        true
    }

/*
    private val listener=View.onTouchListener(){
        v.onTouchEvent(event)
        Log.d("test","터치입력")
        var curX:Float=event!!.x
        var curY:Float=event!!.y
        val msg = "터치를 입력받음 : $curX / $curY"

        Log.d("test", msg)
        //Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()


        // super.onTouchEvent(event)

        //event
        //event 종류/각각의 특성

        if (event.action == MotionEvent.ACTION_DOWN) {
            if(count>5){
                count = 0
//                    Toast.makeText(this@MainActivity, "끝", Toast.LENGTH_SHORT).show()
                Toast.makeText(this@MainActivity, "카운트 초기화", Toast.LENGTH_SHORT).show()
            }
            else if (count == 5){
                //입력받은 5개의 좌표가 제대로 되었는지 연산하기

                // 다시 터치했을 때 if문의 다른 조건 으로 들어갈 수 있게 해준다.
                count++

                // ~~ 입력받은 좌표 중 아래쪽과 위쪽의 좌표를 알아내고, 인덱스 저장
                var upIdx =  Array<Int>(3){-1}
                var downIdx =  Array<Int>(2){-1}

                var down1: Int = 0 // 제일 아래 좌표
                var down2: Int = 0 // 제일 위 좌표



                // 입력받은 좌표 중 아래쪽 좌표 2개의 "인덱스" 알아내기

                if (pointY[0] >= pointY[1]){
                    down2 = 1
                }

                for (i in 1..4) {
                    if ( pointY[down1] < pointY[i]){
                        down2 = down1
                        down1 = i
                    }
                    else if (pointY[down2]<=pointY[i])
                        down2 = i
                }


//                    for (i in 1..4) {
//                        if (pointY[down1] <= pointY[i]) {
//                            down1 = i
//                        }
//                    }
//
//                    for (i in 1..4) {
//
//                        if ((i != down1) &&  pointY[down2] <= pointY[i]) {
//                            down2 = i
//                        }
//                    }


                // 아쪽 좌표 2개의 인덱스를 downIdx에 저장
                downIdx[0] = down1
                downIdx[1] = down2

                if (down1 == down2) {
                    val msg = "최댓값 좌표 같다: $down1 == $down2 "
                    Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
                    return true
                }

                // 위쪽 좌표 3개의 인덱스를 upIdx에 저장
                var tmpIdx: Int = 0
                for (i in 0..4) {
                    if ( (i != down1) && (i != down2))
                    {
                        upIdx[tmpIdx] = i
                        tmpIdx++
                    }
                }

                // 위쪽과 아래쪽을 잘 나누는지 확인 --> 확인 완료
                val msg1 = "인덱스 - 위: ${upIdx[0]} / ${upIdx[1]}/ ${upIdx[2]},\n  아래 : ${downIdx[0]} / ${downIdx[1]}\n\n"
                val msg2 = "위: ${pointY[upIdx[0]]} / ${pointY[upIdx[1]]}/ ${pointY[upIdx[2]]},\n  아래 : ${pointY[downIdx[0]]} / ${pointY[downIdx[1]]}"
                val msg3 = msg1+msg2
                Toast.makeText(this@MainActivity, msg3, Toast.LENGTH_SHORT).show()

                // ~~~x축 기준 정렬-> 왼쪽에 있을수록 작은 인덱스쪽에 있도록 정렬
                // 위쪽 좌표의 왼쪽, 중간, 오른쪽 찾기
                if (pointX[upIdx[0]] > pointX[upIdx[1]]){
                    val tmp = upIdx[0]
                    upIdx[0] = upIdx[1]
                    upIdx[1] = tmp
                }
                if (pointX[upIdx[1]] > pointX[upIdx[2]]){
                    val tmp = upIdx[1]
                    upIdx[1] = upIdx[2]
                    upIdx[2] = tmp
                }
                if (pointX[upIdx[0]] > pointX[upIdx[1]]){
                    val tmp = upIdx[0]
                    upIdx[0] = upIdx[1]
                    upIdx[1] = tmp
                }


                // 아래쪽 좌표의 왼쪽, 오른쪽 찾기
                if (pointX[downIdx[0]] > pointX[downIdx[1]]){
                    val tmp = downIdx[0]
                    downIdx[0] = downIdx[1]
                    downIdx[1] = tmp
                }

//                    // 왼쪽과 오른쪽을 잘 나누는지 확인: 각 그룹 내에서 좌표가 X좌표 기준 오름차순으로 나옴 --> 확인 완료
                val msg = "위: ${pointX[upIdx[0]]},  ${pointY[upIdx[0]]} / \n ${pointX[upIdx[1]]},  ${pointY[upIdx[1]]}/ \n ${pointX[upIdx[2]]},  ${pointY[upIdx[2]]}\n  아래 : ${pointX[downIdx[0]]},  ${pointY[downIdx[0]]} / \n ${pointX[downIdx[1]]},  ${pointY[downIdx[0]]}}"
                Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()



                // ~~ 부착이 성공적인지 확인하고 사용자에게 알리기
                val msgFail = "부착 실패\n"
                val msgSuccess = "부착 성공"

                // Y값 평균
                var meanUpY = (pointY[upIdx[0]]+pointY[upIdx[1]]+pointY[upIdx[2]])/3
                var meanDownY = (pointY[downIdx[0]]+pointY[downIdx[1]])/2
                // 아래 수치는 대강 잡아둠. 나중에 고쳐야 함
                var outerDistY: Int = 1500

                var innerDistY: Int = 100
                var innerDistX: Int = 100

                // 1. 간격이 적절한가
                // y좌표: 위쪽 y좌표의 평균과 아래쪽 y좌표의 평균이 "일정 수치"(outerDistY)와 비슷한지 비교 -> 1300~1650
                var distMeanY = meanDownY-meanUpY
                // !!!! 1. 간격이 적절하지 못하면: 완전 잘못된 것임. 다시 실리콘 붙이고 인식 해달라고 요청 -  return true
                if (!(distMeanY >= 1300 && distMeanY <= 1650)){
                    msgFail+"1-1. y좌표 간격 부적절"
                    Toast.makeText(this@MainActivity, msgFail, Toast.LENGTH_SHORT).show()
                    return true
                }

                // x좌표: 위쪽, 아래쪽끼리 각 점 사이 간격이 innerDistX와 비슷한지 비교 -> 140~420
                var distUpX1 = pointX[upIdx[1]]-pointX[upIdx[0]]
                var distUpX2 = pointX[upIdx[2]]-pointX[upIdx[1]]
                var distDownX = pointX[downIdx[1]]-pointX[downIdx[0]]

                var distXArr = arrayOf(distUpX1, distUpX2, distDownX)

                for (i in distXArr) {
                    if (!(i >= 140 && i <= 420)){
                        msgFail+"1-2. x좌표 간격 부적절"
                        Toast.makeText(this@MainActivity, msgFail, Toast.LENGTH_SHORT).show()
                        return true
                    }
                }



                // 2. 수평선이 잘 되었는가 : 위쪽 / 아래쪽 그룹 내에서 y좌표가 비슷한지 비교.

                var distYArr = arrayOf(pointY[upIdx[1]]-pointY[upIdx[0]], pointY[upIdx[2]]-pointY[upIdx[1]], pointY[downIdx[1]]-pointY[downIdx[0]])
                // 위쪽: y 좌표의 평균과 각 점의 y값 차이가 innerDistY 이하여야 함. -> 0~140
                // 아래쪽

//                    // 차이를 알아야 하므로 절댓값으로 바꿔줌
//                    for (i in distYArr.indices)
//                    {
//                        if (distYArr[i] < 0)
//                        {
//                            distYArr[i] = distYArr[i] * (-1)
//                        }
//                    }

                // !!!! 2. 수평선이 잘 안 맞는다면 : 어느쪽으로 기울어졌는지 확인
                // 위쪽: 왼쪽과 오른쪽의 y좌표 비교 -> 어느쪽이 올라가 있는지 확인
                // 아래쪽:
                // 올라가 있는 방향이 같으면 사용자에게 그 쪽으로 올려달라고 요청
                // 다르면 다시 실리콘 붙이고 인식 해달라고 요청
                // return true
                var error2 = false
                for (i in distYArr) {
                    if (!(i >= -140 && i <= 140)) {
                        error2 = true
                        msgFail+"2. 수평 안 맞음"

                        break
                    }
                }

                if (error2 == true) {
                    var leftUp = true // 왼쪽을 올리는 방향으로 보정해야 한다
                    var rightUp = true
                    for (i in distYArr) {
                        // 오른쪽이 올라가있는 상태
                        if (i>=0) {
                            rightUp = false
                        }
                        // 왼쪽이 올라가있는 상태
                        if (i<=0){
                            leftUp = false
                        }
                    }
                    if (leftUp){
                        msgFail+"\n 왼쪽을 더 올려주세요"
                    }
                    if (rightUp){
                        msgFail+"\n 오른쪽을 더 올려주세요"
                    }

                    Toast.makeText(this@MainActivity, msgFail, Toast.LENGTH_SHORT).show()
                    return true
                }





                // 3. 통과! 사용자에게 인식 완료했다고 안내

                Toast.makeText(this@MainActivity, msgSuccess, Toast.LENGTH_SHORT).show()
                mTTS!!.speak(msgSuccess, TextToSpeech.QUEUE_FLUSH, null)
            }
            else {
                //특정 5개의 범위의 좌표를 연속적으로 5번 터치할 때만 count하기

                val x = event.x
                val y = event.y
                val msg = "터치를 입력받음 : $x / $y"

                // 입력받은 좌표를 배열에 넣기
                pointX[count] = x
                pointY[count] = y

                Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
//                    Toast.makeText(this@MainActivity, count.toString(), Toast.LENGTH_SHORT).show()

                count++
                return true
            }
        }
        return false
    }
*/
}
