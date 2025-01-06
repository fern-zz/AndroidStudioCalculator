package View;

import android.view.View;

import Controller.MainController;

public class listener implements View.OnClickListener{
        MainController mainController;
        static int number = 0;
        int digit = 0;

    public listener(MainController mainController) {
            this.mainController = mainController;
            digit = number;
            number++;
    }

    @Override
    public void onClick(View v) {
        mainController.getDigit(digit);
    }
}