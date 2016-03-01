package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ke.myexpenses.R;

import java.util.Stack;

/**
 * Created by K@E on 2/16/2016.
 */
public class PopUpCalculator extends Activity implements View.OnClickListener {
    static final String KEY_RESULT = "resultField";

    Button btn0, btn1, btn3, btn4, btn5, btn6, btn7, btn8, btn2, btn9, btnadd, btnsub, btndiv, btnmult, btnclearall, btndot, btnequal, btndel, btnmode, btnparen, goback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_calculator);
        initialize();
    }

    private void initialize() {
        btn0 = (Button) findViewById(R.id.button0);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);
        btn5 = (Button) findViewById(R.id.button5);
        btn6 = (Button) findViewById(R.id.button6);
        btn7 = (Button) findViewById(R.id.button7);
        btn8 = (Button) findViewById(R.id.button8);
        btn9 = (Button) findViewById(R.id.button9);
        btnadd = (Button) findViewById(R.id.buttonAdd);
        btnsub = (Button) findViewById(R.id.buttonSub);
        btndiv = (Button) findViewById(R.id.buttonDiv);
        btnmult = (Button) findViewById(R.id.buttonMult);
        btnclearall = (Button) findViewById(R.id.buttonAC);
        btndot = (Button) findViewById(R.id.buttonDot);
        btnequal = (Button) findViewById(R.id.buttonEqual);
        btndel = (Button) findViewById(R.id.buttonDel);
        btnmode = (Button) findViewById(R.id.buttonMode);
        btnparen = (Button) findViewById(R.id.buttonParen);
        goback = (Button) findViewById(R.id.go_back);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnadd.setOnClickListener(this);
        btnsub.setOnClickListener(this);
        btndiv.setOnClickListener(this);
        btnmult.setOnClickListener(this);
        btnclearall.setOnClickListener(this);
        btndot.setOnClickListener(this);
        btnequal.setOnClickListener(this);
        btndel.setOnClickListener(this);
        btnmode.setOnClickListener(this);
        btnparen.setOnClickListener(this);
        goback.setOnClickListener(this);


    }

    private boolean checkOperator(char item) {
        switch (item) {
            case '+':
            case '-':
            case '*':
            case '/':
            case '%':
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        TextView tv = (TextView) findViewById(R.id.result);
        CharSequence curTxt = tv.getText();
        savedInstanceState.putCharSequence(KEY_RESULT, curTxt);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    private boolean checkDigit(char item) {
        switch (item) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return true;
            default:
                return false;
        }
    }

    private boolean checkFloatDigit(char item) {
        return (checkDigit(item) || item == '.');
    }

    private boolean checkExtraDecimal(CharSequence curTxt) {
        int lastNumDecCount = 0;
        for (int i = curTxt.length() - 1; i >= 0; i--) {
            if (curTxt.charAt(i) == '.') {
                lastNumDecCount++;
            } else if (!checkDigit(curTxt.charAt(i))) {
                break;
            }
        }
        if (lastNumDecCount >= 2) {
            return true;
        }
        return false;
    }

    private CharSequence popThenReplaceLast(CharSequence curTxt, char replacement) {
        curTxt = curTxt.subSequence(0, curTxt.length() - 1);
        StringBuilder temp = new StringBuilder(curTxt);
        temp.setCharAt(temp.length() - 1, replacement);
        return temp;
    }

    private CharSequence replaceLast(CharSequence curTxt, char replacement) {
        StringBuilder temp = new StringBuilder(curTxt);
        temp.setCharAt(temp.length() - 1, replacement);
        return temp;
    }

    private void autoScrollTV() {
        final ScrollView sv = (ScrollView) findViewById(R.id.resultScrollView);
        sv.post(new Runnable() {
            public void run() {
                sv.fullScroll(View.FOCUS_DOWN);
            }
        });
    }

    public void insertCharToTextField(View v) {
        TextView tv = (TextView) findViewById(R.id.result);

        CharSequence prevTxt = tv.getText();
        if (prevTxt.length() >= 1 &&
                prevTxt.charAt(prevTxt.length() - 1) == ')') {
            tv.append("*");
        }

        // insert button pressed
        switch (v.getId()) {
            case R.id.button0:
                tv.append("0");
                break;
            case R.id.button1:
                tv.append("1");
                break;
            case R.id.button2:
                tv.append("2");
                break;
            case R.id.button3:
                tv.append("3");
                break;
            case R.id.button4:
                tv.append("4");
                break;
            case R.id.button5:
                tv.append("5");
                break;
            case R.id.button6:
                tv.append("6");
                break;
            case R.id.button7:
                tv.append("7");
                break;
            case R.id.button8:
                tv.append("8");
                break;
            case R.id.button9:
                tv.append("9");
                break;
            case R.id.buttonAdd:
                tv.append("+");
                break;
            case R.id.buttonSub:
                tv.append("-");
                break;
            case R.id.buttonMult:
                tv.append("*");
                break;
            case R.id.buttonDiv:
                tv.append("/");
                break;
            case R.id.buttonDot:
                tv.append(".");
                break;
        }

        CharSequence curTxt = tv.getText();
        char curLast = curTxt.charAt(curTxt.length() - 1);
        char prevLast = ' ';
        if (curTxt.length() > 1) {
            prevLast = curTxt.charAt(curTxt.length() - 2);
        }

        // check previous last character for special cases
        if (curLast == '.') {
            if (checkExtraDecimal(curTxt)) {
                popChar(v);
            } else if (!checkDigit(prevLast)) {
                curTxt = replaceLast(curTxt, '0');
                tv.setText(curTxt);
                tv.append(".");
            }
        } else {
            if (curTxt.length() > 1) {
                if ((prevLast == '+' && curLast == '+') ||
                        (prevLast == '-' && curLast == '-') ||
                        (prevLast == '*' && curLast == '*') ||
                        (prevLast == '/' && curLast == '/')) {
                    popChar(v);
                } else if (checkOperator(prevLast) && checkOperator(curLast)) {
                    curTxt = popThenReplaceLast(curTxt, curLast);
                    if (curTxt.length() > 1) {
                        prevLast = curTxt.charAt(curTxt.length() - 2);
                    }
                    tv.setText(curTxt);
                } else if (checkOperator(curLast) && prevLast == '.') {
                    curTxt = replaceLast(curTxt, '0');
                    tv.setText(curTxt);
                    tv.append(String.valueOf(curLast));
                }
            }
            if (checkOperator(curLast) && curLast != '-') {
                if (curTxt.length() == 1 || prevLast == '(') {
                    popChar(v);
                }
            }
        }

        autoScrollTV();
    }

    public void paren(View v) {
        TextView tv = (TextView) findViewById(R.id.result);
        CharSequence curTxt = tv.getText();
        int leftParenCount = 0;
        int rightParenCount = 0;

        for (int i = 0; i < curTxt.length(); i++) {
            if (curTxt.charAt(i) == '(') {
                leftParenCount++;
            }
        }
        for (int i = 0; i < curTxt.length(); i++) {
            if (curTxt.charAt(i) == ')') {
                rightParenCount++;
            }
        }

        if (curTxt.length() <= 0) {
            tv.append("(");
        } else {
            if (curTxt.charAt(curTxt.length() - 1) == '.') {
                tv.append("0");
            }
            if (checkOperator(curTxt.charAt(curTxt.length() - 1))) {
                tv.append("(");
            } else if (rightParenCount == leftParenCount) {
                tv.append("*(");
            } else {
                tv.append(")");
            }
        }

        autoScrollTV();
    }

    public void popChar(View v) {
        TextView tv = (TextView) findViewById(R.id.result);
        CharSequence curTxt = tv.getText();
        if (curTxt.length() <= 1) {
            tv.setText("");
            return;
        }
        curTxt = curTxt.subSequence(0, curTxt.length() - 1);
        tv.setText(curTxt);
    }

    public void allClear(View v) {
        TextView tv = (TextView) findViewById(R.id.result);
        tv.setText("");
    }

    private double performOperation(double leftNum, double rightNum, char operation) {
        switch (operation) {
            case '+':
                return (leftNum + rightNum);
            case '-':
                return (leftNum - rightNum);
            case '*':
                return (leftNum * rightNum);
            case '/':
                return (leftNum / rightNum);
            case '%':
                return ((leftNum / rightNum) * 100);
            default:
                return Double.NEGATIVE_INFINITY;
        }
    }

    public void parseResult(View v) {

        TextView tv = (TextView) findViewById(R.id.result);
        String curTxt = tv.getText().toString();
        try {
            if (curTxt.length() <= 2) {
                return;
            }

            double lOperand, rOperand;
            boolean priority = false;
            boolean readingNum = false;
            String curNum = "";

            Stack<Double> operands = new Stack<Double>();
            Stack<Character> operators = new Stack<Character>();

            for (int i = 0; i < curTxt.length(); i++) {

                char curChar = curTxt.charAt(i);

                if (!readingNum && checkDigit(curChar)) {
                    curNum = String.valueOf(curChar);
                    readingNum = true;
                } else if (readingNum && checkFloatDigit(curChar)) {
                    curNum += curChar;
                }
                if (readingNum && (i == curTxt.length() - 1 || !checkFloatDigit(curChar))) {
                    operands.push(Double.parseDouble(curNum));
                    readingNum = false;
                    if (priority) {
                        rOperand = operands.pop();
                        lOperand = operands.pop();
                        operands.push(
                                performOperation(lOperand, rOperand, operators.pop()));
                        priority = false;
                    }
                }

                if (checkOperator(curChar)) {
                    operators.push(curChar);
                    if (curChar == '*' || curChar == '/') {
                        priority = true;
                    }
                } else if (curChar == '(') {
                    operators.push(curChar);
                    priority = false;
                } else if (curChar == ')') { // calculate until '('
                    char curOp = operators.pop();
                    while (curOp != '(') {
                        rOperand = operands.pop();
                        lOperand = operands.pop();
                        operands.push(
                                performOperation(lOperand, rOperand, curOp));
                        curOp = operators.pop();
                    }
                }
            }
            while (!operators.empty()) {
                rOperand = operands.pop();
                lOperand = operands.pop();
                operands.push(
                        performOperation(lOperand, rOperand, operators.pop()));
            }
            curNum = (operands.pop()).toString();
            tv.setText(curNum);
        } catch (Exception e) {
        } finally {
            if (curTxt == null)
                tv.setText("" + 0);
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button0:
                insertCharToTextField(btn0);
                break;
            case R.id.button1:
                insertCharToTextField(btn1);
                break;
            case R.id.button2:
                insertCharToTextField(btn2);
                break;
            case R.id.button3:
                insertCharToTextField(btn3);
                break;
            case R.id.button4:
                insertCharToTextField(btn4);
                break;
            case R.id.button5:
                insertCharToTextField(btn5);
                break;
            case R.id.button6:
                insertCharToTextField(btn6);
                break;
            case R.id.button7:
                insertCharToTextField(btn7);
                break;
            case R.id.button8:
                insertCharToTextField(btn8);
                break;
            case R.id.button9:
                insertCharToTextField(btn9);
                break;
            case R.id.buttonAdd:
                insertCharToTextField(btnadd);
                break;
            case R.id.buttonSub:
                insertCharToTextField(btnsub);
                break;
            case R.id.buttonDiv:
                insertCharToTextField(btndiv);
                break;
            case R.id.buttonMult:
                insertCharToTextField(btnmult);
                break;
            case R.id.buttonDot:
                insertCharToTextField(btndot);
                break;
            case R.id.buttonEqual:
                parseResult(btnequal);
                break;
            case R.id.buttonMode:
                //percent(btnmode);
                break;
            case R.id.buttonAC:
                allClear(btnclearall);
                break;
            case R.id.buttonDel:
                popChar(btndel);
                break;
            case R.id.buttonParen:
                paren(btnparen);
                break;
            case R.id.go_back:
                Toast.makeText(this, "Press Equal First", Toast.LENGTH_LONG).show();
                TextView tv = (TextView) findViewById(R.id.result);
                String message = tv.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("MESSAGE", message);
                setResult(5833, intent);
                finish();
                break;

        }
    }

    public void dataBack(View v) {
        TextView tv = (TextView) findViewById(R.id.result);

        String message = tv.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("MESSAGE", message);
        setResult(5833, intent);
        finish();

    }


}

   /* public void percent(View v){
        double leftNum=0 ,rightNum = 0;
        percentage(leftNum ,rightNum)   ;


    }
//public double percentage (double leftNum, double rightNum){

    return
}
    // parse string for int/float/str:
    /*for (int i = 0; i < firstWord.length(); i++) {
        char cur = firstWord.charAt(i);
        if (    isDigit(cur) ||
                (i == 0 && cur == '-')) {
            continue;
        }
        if (cur == '.' && numDots == 0) {
            flagInt = false;
            flagFlt = true;
            numDots++;
            continue;
        }
        flagFlt = flagInt = false;
        flagStr = true;
        break;
    }*/



