package com.example.virtualkeypad.IME;//package com.example.virtualkeypad;
//
//import android.app.Activity;
//import android.inputmethodservice.InputMethodService;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.EditText;
//
//public class ChunjiinActicityIME extends InputMethodService {
//
//    private Chunjiin chunjiin;
//    private Button btn[];
//    private EditText et;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chunjiin);
//
////        et = (EditText)findViewById(R.id.chunjiin_text);
//        et = (EditText)findViewById(R.id.chunjiin_text);
//        btn = new Button[14];
//        btn[0] = (Button)findViewById(R.id.chunjiin_button0);
//        btn[1] = (Button)findViewById(R.id.chunjiin_button1);
//        btn[2] = (Button)findViewById(R.id.chunjiin_button2);
//        btn[3] = (Button)findViewById(R.id.chunjiin_button3);
//        btn[4] = (Button)findViewById(R.id.chunjiin_button4);
//        btn[5] = (Button)findViewById(R.id.chunjiin_button5);
//        btn[6] = (Button)findViewById(R.id.chunjiin_button6);
//        btn[7] = (Button)findViewById(R.id.chunjiin_button7);
//        btn[8] = (Button)findViewById(R.id.chunjiin_button8);
//        btn[9] = (Button)findViewById(R.id.chunjiin_button9);
//        btn[10] = (Button)findViewById(R.id.chunjiin_buttonex1);
//        btn[11] = (Button)findViewById(R.id.chunjiin_buttonex2);
//        btn[12] = (Button)findViewById(R.id.chunjiin_buttonex3);
//
//        btn[13] = (Button)findViewById(R.id.chunjiin_button_left);
//
//        chunjiin = new Chunjiin(et, btn);
//    }
//}
