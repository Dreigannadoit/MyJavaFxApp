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
    private Button subButton;
    private Button mulButton;
    private Button divButton;
    private Button clearButton;
    private GridPane gridPane;
    private Text author;

    public GridPane createAndGetPositionedLayout() {
        // set-up Front-End Display and return grid layout
        createFrontEndElements();
        positionLayout();
        return gridPane;
    }

    public void buttonAssignment(Button button){
        // Get user input
        UserInput userInput = new UserInput(firstNumberField, secondNumberField);

        // Perform operation based on the button's text
        final String[] buttonText = {button.getText()};

        button.setOnAction(action -> {
            try {
                String result = "";
                String operation = "";

                switch (buttonText[0]) {
                    case "Add":
                        operation = "Add";
                        result = userInput.sumOfInput();
                        break;
                    case "Subtract":
                        operation = "Subtract";
                        result = userInput.subOfInput();
                        break;
                    case "Multiply":
                        operation = "Multiply";
                        result = userInput.mulOfInput();
                        break;
                    case "Divide":
                        operation = "Divide";
                        result = userInput.divOfInput();
                        break;
                    default:
                        break;
                }

                // Display result
                resultField.setText( result );

                System.out.println( userInput.getFirstNumber().getText() );
                System.out.println( userInput.getSecondNumber().getText() );
                System.out.println( operation );
                System.out.println( "= " + result + "\n");

            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid integer
                // Needs to be here or else the code will not run
                if( firstNumberField.getText().isEmpty() && !secondNumberField.getText().isEmpty() ){
                    resultField.setText("Enter First Number");
                } else if( !firstNumberField.getText().isEmpty() && secondNumberField.getText().isEmpty() ) {
                    resultField.setText("Enter Second Number");
                } else if( firstNumberField.getText().isEmpty() && secondNumberField.getText().isEmpty() ){
                    resultField.setText("Enter Fields");
                } else{
                    resultField.setText("Invalid input");
                }
            }
        });
    }

    public void userClicksAdd(){ buttonAssignment(addButton);}
    public void userClicksSub(){ buttonAssignment(subButton);}
    public void userClicksMul(){ buttonAssignment(mulButton); }
    public void userClicksDiv(){ buttonAssignment(divButton); }

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
        subButton = new Button("Subtract");
        mulButton = new Button("Multiply");
        divButton = new Button("Divide");
        clearButton = new Button("Clear");

        author = new Text("Robert Bamba");
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
        HBox buttonBox = new HBox(10); // 50 pixels spacing
        buttonBox.setAlignment(Pos.CENTER); // Center buttons horizontally
        buttonBox.getChildren().addAll(addButton, subButton, mulButton, divButton, clearButton); // Add buttons to the HBox

        // Add the buttonBox to the gridPane
        gridPane.add(buttonBox, 0, 3, 2, 1); // Span 2 columns
        gridPane.add(author, 0, 5);
    }

    @Override
    public void operations() {
        userClicksAdd();
        userClicksSub();
        userClicksMul();
        userClicksDiv();
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