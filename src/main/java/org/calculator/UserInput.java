package org.calculator;

import javafx.scene.control.TextField;

public class UserInput {
    private TextField firstNumber;
    private TextField secondNumber;


    UserInput(TextField firstNumber, TextField secondNumber){
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    public TextField getFirstNumber() { return firstNumber;}
    public TextField getSecondNumber() { return secondNumber;}

    public int convertFirstNumberTextFieldToInt(){
        return Integer.parseInt(firstNumber.getText());
    }

    public int convertSecondNumberTextFieldToInt(){
        return Integer.parseInt(secondNumber.getText());
    }
}
