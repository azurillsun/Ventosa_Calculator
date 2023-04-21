package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView resulttv,solutionTv;
    MaterialButton buttonC,buttonBrackOpen,buttonBrackClosed;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonMinus,buttonEqual;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       resulttv = findViewById(R.id.result_text);
       solutionTv = findViewById(R.id.formula_text)

       assignId(buttonC,R.id.button_c);
       assignId(buttonBrackOpen,R.id.button_open_bracket);
       assignId(buttonBrackClosed,R.id.button_closed_bracket);
       assignId(buttonDivide,R.id.button_divide);
       assignId(buttonMultiply,R.id.button_multiply);
       assignId(buttonPlus,R.id.button_plus);
       assignId(buttonMinus,R.id.button_subtract);
       assignId(buttonEqual,R.id.button_equal);
       assignId(button0,R.id.button_0);
       assignId(button1,R.id.button_1);
       assignId(button2,R.id.button_2);
       assignId(button3,R.id.button_3);
       assignId(button4,R.id.button_4);
       assignId(button5,R.id.button_5);
       assignId(button6,R.id.button_6);
       assignId(button7,R.id.button_7);
       assignId(button8,R.id.button_8);
       assignId(button9,R.id.button_9);
       assignId(buttonAC,R.id.button_All_Clear);
       assignId(buttonDot,R.id.button_dot);




    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }




    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataCalc = solutionTv.getText().toString();

        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resulttv.setText("");
            return;
        }
        if(buttonText.equals("=")){
            solutionTv.setText(resulttv.getText());
        }
        if(buttonText.equals("C")){
            dataCalc = dataCalc.substring(0,dataCalc.length()-1);
        }else {
            dataCalc = dataCalc+buttonText;
        }
        solutionTv.setText(dataCalc);

        String finalResult = getResult(dataCalc);

        if (!finalResult.equals("Err")){
            resulttv.setText(finalResult);
        }

    }

    String getResult(String data){
        try {
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript", 1, null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }

            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }
}