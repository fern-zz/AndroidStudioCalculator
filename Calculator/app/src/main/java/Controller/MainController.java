package Controller;

import android.widget.TextView;

import Model.CalcStack;


public class MainController {
    OperandBuffer opB;
    CalcStack calS;
    //Views
    TextView display;
    TextView stack;
    TextView base;
    TextView state;

    long result;

    boolean zeroDivision = false;
    boolean mathC = false;

    {
        opB = new OperandBuffer();
        calS = new CalcStack();
    }

    public void setState(TextView state) {
        this.state = state;
    }

    public void setBase(TextView base) {
        this.base = base;
    }

    public void setDisplay(TextView display) {
        this.display = display;
    }

    public void setStackView(TextView stack) {
        this.stack = stack;
    }

    public void calculate() {
        long operandTwo = opB.numeric;
        char op = calS.pop().charAt(0);
        long operandOne = Integer.parseInt(calS.pop());

        result = 0;

        switch(op) {
            case '+':
                result = operandOne + operandTwo;
                break;
            case '-':
                result = operandOne - operandTwo;
                break;
            case 'X':
                result = operandOne * operandTwo;
                break;
            case '/':

                if(operandTwo == 0) {
                    System.err.println("ERROR! Division by zero");
                    clear();
                    zeroDivision = true;
                    state.setText("ERROR");
                    return;
                }
                result = operandOne / operandTwo;
                break;
        }
        calS.push(result);
        opB.clear();
    }

    public void getDigit(int digit) {
        if(mathC) {
            mathC = false;
            calS.clear();
        }

        if(opB.base == 2) {
            String addTo = toBinary(opB.numeric);
            if(digit == 0 || digit == 1) {
                addTo += digit;
            }

            if(opB.isNegative) addTo = "-" + addTo;
            opB.numeric = toInteger(addTo);
        }
        else opB.extend(digit);

        state.setText("State: NUMBER");
        updateViews();
    }

    public long toInteger(String bin) {
        boolean isNegative = bin.charAt(0) == '-' ? true : false;

        if(isNegative) {
            bin = bin.substring(1);
        }

        int num = 0;

        for(int i = 0; i < bin.length(); i++) {
            char digit = bin.charAt(i);
            num = num * 2 + (digit - '0');
        }

        return isNegative ? Math.abs(num) * -1 : Math.abs(num);
    }

    public String toBinary(long num) {
        boolean isNegative = (num < 0) ? true : false;
        num = Math.abs(num);

        if(num == 0) return "0";

        StringBuilder sb = new StringBuilder();

        while(num > 0) {
            sb.insert(0, num % 2);
            num /= 2;
        }

        return sb.toString();
    }

    public void pushOperation(char op) {
        if(calS.size() >= 3) calculate();
        else if(calS.size() == 2 && opB.magnitude.isEmpty()) {
            calS.clear();
            updateViews();
            state.setText("State: NUMBER");
            display.setText("ERROR");
            return;
        } else if(calS.size() == 2) {
            calculate();
            String displayResult = String.valueOf(result);
            updateViews();
            display.setText(displayResult);
            calS.push(op);
        } else if(calS.size() == 1) calS.push(op);
        else if(calS.isEmpty()) {
            calS.push(opB.numeric);
            opB.clear();
            calS.push(op);
        }

        state.setText("State: OPERATOR");
        String displayString = display.getText().toString();
        updateViews();
        display.setText(displayString);
    }

    public void negateNumber() {
        opB.negate();
        state.setText("State: NUMBER");
        updateViews();
    }

    public void equal() {
        if(opB.display.equals("0") && calS.size() >= 2) {
            opB.clear();
            calS.clear();
            updateViews();
            state.setText("State: EQUALS");
            display.setText("ERROR");

            return;
        }

        if(calS.isEmpty()) calS.push(opB.numeric);
        else {
            calculate();
            mathC = true;
        }

        opB.clear();
        updateViews();
        display.setText(String.valueOf(result));
    }

    public void clear() {
        opB.clear();
        calS.clear();
        state.setText("State: NUMBER");
        updateViews();
    }

    public void displayDEC() {
        opB.setBase(10);
        base.setText("Base: 10");
        if(state.getText().equals("")) state.setText("State: EQUALS");
        updateViews();
    }

    public void displayBIN() {
        base.setText("Base: 2");
        opB.setBase(2);
        if(state.getText().equals("")) state.setText("State: EQUALS");
        updateViews();
    }

    private void updateViews() {
        if(opB.base == 10) {
            display.setText(String.valueOf(opB.numeric));
        }
        else {
            String toBin = toBinary(opB.numeric);
            if(opB.isNegative) {
                toBin = "-" + toBin;
            }
            display.setText(toBin);
        }

        stack.setText("Stack: \n" + calS.toString());

        if(mathC) {
            display.setText(calS.peek());
        }

        if(zeroDivision) {
            display.setText("ERROR!");
            zeroDivision = false;
        }
    }
}
