package org.calculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

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

    public static void main(String[] args){
        launch(args);
    }
}