# Basic Calcultor App
### Code By Drei Abmab

<p>For CCC102 Project involving JavaFx GUI.  </p>
<p>The executable file is located in this folder location ```out/artifacts/MyJavaFxApp_jar```</p>


Note:

<p>Make sure to download javaFx 19 onwards, or the app may not run as intended. </p>
<p>And the dependancies (such as the libraries used) are in their repective files, but are not shown in this md. </p>


Code:

App.java - runs the code in an executable file. 
``` 
public class App {
    public static void main(String[] args){
        Main.main(args);
    }
}

```

Main.java - main class that loads the content of the calculator app 
``` 
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        CalculatorAppLayout calculatorAppLayout = new CalculatorAppLayout();
        GridPane gridPane = calculatorAppLayout.createAndGetPositionedLayout(); // Retrieve the GridPane
        calculatorAppLayout.add();
        calculatorAppLayout.clear();
        calculatorAppLayout.credits();

        //Creating a scene object
        Scene scene = new Scene(gridPane);
        //Setting title to the Stage
        stage.setTitle("JavaFx Project");
        //Adding scene to the stage
        stage.setScene(scene);
        //Displaying the contents of the stage
        stage.show();
    }

    public static void main(String args[]){
        launch(args);
    }
}
``` 

Display.java - interface that shows the components of the calculator
```
public interface Display {
    void createFrontEndElements();
    void positionLayout();
    void add();
    void clear();
}
```

UserInput.java - gets and sets user input for better scalability 
```
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
```

CalculatorAppLayout.java - Contains all the elemnts of the app and how those are positiones and function
```
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
```
