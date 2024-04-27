# Basic Calcultor App
### Code By Drei Abmab
### App version 2.1

<hr />

<br />

## Intro
<p>For CCC102 Project involving JavaFx GUI.  </p>
<p>The executable file is located in this folder location</p>  

```
out/artifacts/MyJavaFxApp_jar 
```

<br />

## Version Download 
- [ Ver 1.0 ](https://drive.google.com/file/d/12309q9GcGekpfBg7vEBgjezmbgyDrTb5/view?usp=sharing) 
- [ Ver 2.0 ](https://drive.google.com/file/d/18Yd9BJRmehhzJDtJTSwcH1rzZ1V8AzRK/view?usp=sharing)

<br />

### Note:

<p>Make sure to download JavaFx sdk 19 onwards, or the app may not run as intended. </p>

[Download JavaFX SDK](https://gluonhq.com/products/javafx/)

<p>And the dependencies (such as the libraries used) are in the respective files, but are not shown in this md. </p>

<br />

## Code:
  
App.java 
- runs the code in an executable file.
- This part is not needed for the code to run properly becuase the main file can independently run. But is required if we want the executable file to run.

``` 
public class App {
    public static void main(String[] args){
        Main.main(args);
    }
}
```
<br />
  
Main.java - main class that loads the content of the calculator app 
``` 
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        CalculatorAppLayout calculatorAppLayout = new CalculatorAppLayout();
        GridPane gridPane = calculatorAppLayout.createAndGetPositionedLayout(); // Retrieve the GridPane
        calculatorAppLayout.operations();
        calculatorAppLayout.clear();

        //Creating a scene object
        Scene scene = new Scene(gridPane);
        //Setting title to the Stage
        stage.setTitle("JavaFx Project");
        //Adding scene to the stage
        stage.setScene(scene);
        //Displaying the contents of the stage
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
``` 
<br />
  
Display.java - interface that shows the components of the calculator
```
public interface Display {
    void createFrontEndElements();
    void positionLayout();
    void operations();
    void clear();
}
``` 
<br />
  
UserInput.java - gets and sets user input for better scalability 
```
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

``` 
<br />
  
CalculatorAppLayout.java - Contains all the elemnts of the app and how those are positions and function, should implement Display
```
public class CalculatorAppLayout implements Display {
```
```
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
```
```
    public GridPane createAndGetPositionedLayout() {
        // set-up Front-End Display and return grid layout
        createFrontEndElements();
        positionLayout();
        return gridPane;
    }

    public void buttonAssignment(Button button){
        button.setOnAction(action -> {
            try {
                // Get user input
                UserInput userInput = new UserInput(firstNumberField, secondNumberField);
                // Perform operation based on the button's text
                String buttonText = button.getText();
                String result = "";
                switch (buttonText) {
                    case "Add":
                        result = userInput.sumOfInput();
                        break;
                    case "Subtract":
                        result = userInput.subOfInput();
                        break;
                    case "Multiply":
                        result = userInput.mulOfInput();
                        break;
                    case "Divide":
                        result = userInput.divOfInput();
                        break;
                    default:
                        break;
                }
                // Display result
                resultField.setText(result);

            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid integer
                // Needs to be here or else the code will not run
                resultField.setText("Invalid input");
            }
        });
    }

    public void userClicksAdd(){ buttonAssignment(addButton);}
    public void userClicksSub(){ buttonAssignment(subButton);}
    public void userClicksMul(){ buttonAssignment(mulButton); }
    public void userClicksDiv(){ buttonAssignment(divButton); }
```
```
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
```
```
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
```
```
    @Override
    public void operations() {
        userClicksAdd();
        userClicksSub();
        userClicksMul();
        userClicksDiv();
    }
```
```
    @Override
    public void clear() {
        clearButton.setOnAction(action -> {
            firstNumberField.setText("");
            secondNumberField.setText("");
            resultField.setText("");
        });
    }
```
```
}
```
