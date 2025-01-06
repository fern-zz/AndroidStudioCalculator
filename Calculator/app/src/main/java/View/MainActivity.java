package View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Controller.MainController;
import edu.utsa.cs3443.calculator.R;

import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {
    //Buttons
    Button[] numbers;
    Button plusminus;
    Button[] operators;
    Button clr;
    Button equal;
    Button DEC;
    Button BIN;
    // Views
    TextView display;
    TextView base;
    TextView state;
    TextView stack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainController mainC = new MainController();

        display = findViewById(R.id.display);
        display.setText("0");
        base = findViewById(R.id.base);
        base.setText("Base: 10");
        state = findViewById(R.id.state);
        state.setText("State: ");
        stack = findViewById(R.id.stack);
        stack.setText("Stack: \n");
        numbers = new Button[10];
        numbers[0] = findViewById(R.id.zero);

        for (int i = 0; i < numbers.length; i++) {
            switch (i) {
                case 0:
                    numbers[i] = findViewById(R.id.zero);
                    break;
                case 1:
                    numbers[i] = findViewById(R.id.one);
                    break;
                case 2:
                    numbers[i] = findViewById(R.id.two);
                    break;
                case 3:
                    numbers[i] = findViewById(R.id.three);
                    break;
                case 4:
                    numbers[i] = findViewById(R.id.four);
                    break;
                case 5:
                    numbers[i] = findViewById(R.id.five);
                    break;
                case 6:
                    numbers[i] = findViewById(R.id.six);
                    break;
                case 7:
                    numbers[i] = findViewById(R.id.seven);
                    break;
                case 8:
                    numbers[i] = findViewById(R.id.eight);
                    break;
                case 9:
                    numbers[i] = findViewById(R.id.nine);
                    break;
            }

            numbers[i].setText(String.format("%d", i));
            numbers[i].setOnClickListener(new listener(mainC));
        }

        operators = new Button[4];

        operators[0] = findViewById(R.id.add);
        operators[0].setText("+");
        operators[0].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mainC.pushOperation(operators[0].getText().charAt(0));
            }
        });
        operators[1] = findViewById(R.id.sub);
        operators[1].setText("-");
        operators[1].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mainC.pushOperation(operators[1].getText().charAt(0));
            }
        });
        operators[2] = findViewById(R.id.mul);
        operators[2].setText("X");
        operators[2].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mainC.pushOperation(operators[2].getText().charAt(0));
            }
        });
        operators[3] = findViewById(R.id.div);
        operators[3].setText("/");
        operators[3].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mainC.pushOperation(operators[3].getText().charAt(0));
            }
        });

        plusminus = findViewById(R.id.plusminus);
        plusminus.setText("+/-");
        plusminus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mainC.negateNumber();
            }
        });

        clr = findViewById(R.id.clr);
        clr.setText("C");
        clr.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mainC.clear();
            }
        });

        equal = findViewById(R.id.equ);
        equal.setText("=");
        equal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mainC.equal();
            }
        });

        DEC = findViewById(R.id.dec);
        DEC.setText("DEC");
        DEC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mainC.displayDEC();
            }
        });

        BIN = findViewById(R.id.bin);
        BIN.setText("BIN");
        BIN.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mainC.displayBIN();
            }
        });

        mainC.setDisplay(display);
        mainC.setStackView(stack);
        mainC.setBase(base);
        mainC.setState(state);
    }
}