package com.example.shapeassignment;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    private Pane root;
    private Circle circle;
    private Rectangle rectangle;
    private Polygon triangle;
    private int currentShapeIndex;

    private double offsetX, offsetY;

    private Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE};
    private int currentColorIndex = 0;

    public void start(Stage primaryStage) {
        root = new Pane();

        circle = new Circle(100, 200, 50, Color.BLUE);
        rectangle = new Rectangle(200, 150, 100, 100);
        triangle = new Polygon(350, 200, 450, 200, 400, 100);
        triangle.setFill(Color.GREEN);

        root.getChildren().addAll(circle, rectangle, triangle);
        rectangle.setVisible(false);
        triangle.setVisible(false);

        setEventHandlers(circle);
        setEventHandlers(rectangle);
        setEventHandlers(triangle);

        Button previousButton = new Button("Previous");
        previousButton.setLayoutX(50);
        previousButton.setLayoutY(20);
        previousButton.setOnAction(event -> showPreviousShape());
        root.getChildren().add(previousButton);

        Button nextButton = new Button("Next");
        nextButton.setLayoutX(150);
        nextButton.setLayoutY(20);
        nextButton.setOnAction(event -> showNextShape());
        root.getChildren().add(nextButton);

        Button changeBackgroundButton = new Button("Change Background");
        changeBackgroundButton.setLayoutX(250);
        changeBackgroundButton.setLayoutY(20);
        changeBackgroundButton.setOnAction(event -> changeBackgroundColor());
        root.getChildren().add(changeBackgroundButton);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Shape Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setEventHandlers(Shape shape) {

        shape.setOnMousePressed(event -> {
            offsetX = event.getSceneX() - shape.getLayoutX();
            offsetY = event.getSceneY() - shape.getLayoutY();
            shape.toFront();
        });

        shape.setOnMouseDragged(event -> {
            shape.setLayoutX(event.getSceneX() - offsetX);
            shape.setLayoutY(event.getSceneY() - offsetY);
        });
    }

    private void showPreviousShape() {
        switch (currentShapeIndex) {
            case 0:
                circle.setVisible(false);
                triangle.setVisible(true);
                currentShapeIndex = 2;
                break;
            case 1:
                rectangle.setVisible(false);
                circle.setVisible(true);
                currentShapeIndex = 0;
                break;
            case 2:
                triangle.setVisible(false);
                rectangle.setVisible(true);
                currentShapeIndex = 1;
                break;
        }
    }

    private void showNextShape() {
        switch (currentShapeIndex) {
            case 0:
                circle.setVisible(false);
                rectangle.setVisible(true);
                currentShapeIndex = 1;
                break;
            case 1:
                rectangle.setVisible(false);
                triangle.setVisible(true);
                currentShapeIndex = 2;
                break;
            case 2:
                triangle.setVisible(false);
                circle.setVisible(true);
                currentShapeIndex = 0;
                break;
        }
    }

    private void changeBackgroundColor() {
        switch (currentShapeIndex) {
            case 0:
                circle.setFill(colors[currentColorIndex++ % colors.length]);
                break;
            case 1:
                rectangle.setFill(colors[currentColorIndex++ % colors.length]);
                break;
            case 2:
                triangle.setFill(colors[currentColorIndex++ % colors.length]);
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
