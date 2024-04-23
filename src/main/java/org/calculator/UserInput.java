package org.calculator;

import javafx.scene.control.TextField;

public class UserInput {
    private final TextField firstNumber;
    private final TextField secondNumber;


    UserInput(TextField firstNumber, TextField secondNumber){
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    public TextField getFirstNumber() { return firstNumber;}
    public TextField getSecondNumber() { return secondNumber;}

    public double convertFirstNumberTextFieldToInt(){ return Double.parseDouble(firstNumber.getText()); }

    public double convertSecondNumberTextFieldToInt(){ return Double.parseDouble(secondNumber.getText()); }

    public String sumOfInput(){
        return Double.toString(convertFirstNumberTextFieldToInt() + convertSecondNumberTextFieldToInt());
    }

    public String subOfInput(){
        return Double.toString(convertFirstNumberTextFieldToInt() - convertSecondNumberTextFieldToInt());
    }

    public String mulOfInput(){
        return Double.toString(convertFirstNumberTextFieldToInt() * convertSecondNumberTextFieldToInt());
    }

    public String divOfInput(){
        return Double.toString(convertFirstNumberTextFieldToInt() / convertSecondNumberTextFieldToInt());
    }
}
