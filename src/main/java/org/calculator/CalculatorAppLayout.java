package org.calculator;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class CalculatorAppLayout implements Display {
    // Declare values
    private Text firstNumberLabel;
    private TextField firstNumberField;
    private Text secondNumberLabel;
    private TextField secondNumberField;
    private Text resultLabel;
    private TextField resultField;
    private Button addButton;
    private Button clearButton;
    private GridPane gridPane;

    public GridPane createAndGetPositionedLayout() {
        // set-up Front-End Display and return grid layout
        createFrontEndElements();
        positionLayout();
        return gridPane;
    }

    public void credits(){
        Text author = new Text("Robert Andrei N. Bamba");
        gridPane.add(author, 0, 5);
    }

    @Override
    public void createFrontEndElements() {
        //creating label First Number
        firstNumberLabel = new Text("First Number:");
        //creating label Second Number
        secondNumberLabel = new Text("Second Number:");
        //creating label Result
        resultLabel = new Text("Result:");

        //Creating Text Field for First Number
        firstNumberField = new TextField();
        //Creating Text Field for Second Number
        secondNumberField = new TextField();
        //Creating Text Field for Result
        resultField = new TextField();

        //Creating Buttons
        addButton = new Button("Add");
        clearButton = new Button("Clear");
    }

    @Override
    public void positionLayout() {
        //Creating a Grid Pane
        gridPane = new GridPane();
        //Setting size for the pane
        gridPane.setMinSize(600, 400);
        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        //Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);

        //Arranging all the nodes in the grid
        gridPane.add(firstNumberLabel, 0, 0);
        gridPane.add(firstNumberField, 1, 0);

        gridPane.add(secondNumberLabel, 0, 1);
        gridPane.add(secondNumberField, 1, 1);

        gridPane.add(resultLabel, 0, 2);
        gridPane.add(resultField, 1, 2);

        // Creating an HBox for buttons
        HBox buttonBox = new HBox(50); // 50 pixels spacing
        buttonBox.setAlignment(Pos.CENTER); // Center buttons horizontally
        buttonBox.getChildren().addAll(addButton, clearButton); // Add buttons to the HBox

        // Add the buttonBox to the gridPane
        gridPane.add(buttonBox, 0, 3, 2, 1); // Span 2 columns
    }

    @Override
    public void add() {
        addButton.setOnAction(action -> {
            try {
                // Get user input
                UserInput userInput = new UserInput(firstNumberField, secondNumberField);
                int firstNum = userInput.convertFirstNumberTextFieldToInt();
                int secondNum = userInput.convertSecondNumberTextFieldToInt();

                // Perform addition and display result
                int sum = firstNum + secondNum;
                resultField.setText(String.valueOf(sum));

                System.out.println(
                        " User Display: " +
                        userInput.getFirstNumber().getText() +
                        " + " +
                        userInput.getSecondNumber().getText() +
                        " = " + sum
                );
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid integer
                // Needs to be here or else the code will not run
                resultField.setText("Invalid input");
            }
        });
    }

    @Override
    public void clear() {
        clearButton.setOnAction(action -> {
            firstNumberField.setText("");
            secondNumberField.setText("");
            resultField.setText("");
        });
    }
}