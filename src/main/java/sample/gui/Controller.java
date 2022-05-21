package sample.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sample.strategy.*;


public class Controller {

    public Controller() {
    }

    private StackPane fieldWithFigure;
    private StackPane fieldToMoveFigure;

    @FXML
    private GridPane chessboard;

    @FXML
    private Button knightButton;

    @FXML
    private Button rookButton;

    @FXML
    private Button queenButton;

    @FXML
    private Button bishopButton;

    @FXML
    private Button kingButton;

    @FXML
    private Button viewTrigger;

    private Image image;
    private ImageView imageView;

    private int row;
    private int column;

    private Validation validation;

    private Mode mode;

    public void initialize() {
        this.mode = Mode.LIGHT;
        this.imageView = new ImageView();
        this.validation = new KnightValidation();
        Color color = Color.BLACK;
        int amountOfFields = 0;
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                Field pane = new Field();
                pane.setPrefWidth(60);
                pane.setPrefHeight(60);
                pane.setAlignment(Pos.CENTER);
                pane.setRow(i);
                pane.setColumn(j);
                if (j != 0){
                    if (color.equals(Color.BLACK)){
                        color = Color.WHITE;
                    }

                    else if(color.equals(Color.WHITE)){
                        color = Color.BLACK;
                    }
                }
                pane.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
                pane.setColor(color);
                if (i == 0 && j == 0) {
                    row = 0;
                    column = 0;
                    imageView.setFitWidth(57);
                    imageView.setFitHeight(57);
                    changeImage("knight.png");
                    pane.getChildren().add(imageView);
                    fieldWithFigure = pane;
                }
                chessboard.add(pane, j, i);
                amountOfFields++;
            }
        }

        chessboard.getChildren().stream().forEach(element -> element.setOnMouseClicked(mouseEvent -> {
            Integer chosenRow = GridPane.getRowIndex((Node) mouseEvent.getTarget());
            Integer chosenColumn = GridPane.getColumnIndex((Node) mouseEvent.getTarget());

            if (chosenColumn == null && chosenRow == null) {
                return;
            }

            fieldToMoveFigure = (Field) mouseEvent.getTarget();
            if (fieldToMoveFigure.equals(fieldWithFigure)) {
                return;
            }
            if (validation.validate(row, column, chosenRow, chosenColumn)) {
                row = chosenRow;
                column = chosenColumn;
                fieldToMoveFigure.getChildren().removeAll();
                fieldToMoveFigure.getChildren().add(imageView);
                ((Field) imageView.getParent()).getChildren().removeAll();
                fieldWithFigure = (Field) mouseEvent.getTarget();
                fieldToMoveFigure = null;
            } else {
                Runnable highlightField = () -> {
                    BackgroundFill remColor = fieldToMoveFigure.getBackground().getFills().get(0);
                    for (byte loopVar = 0; loopVar <= 1; loopVar++) {
                        try {
                            fieldToMoveFigure.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                            Thread.sleep(50);
                            fieldToMoveFigure.setBackground(new Background(remColor));
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    fieldToMoveFigure.setBackground(new Background(remColor));
                };

                Thread t = new Thread(highlightField);
                t.start();
            }
        }));

    }

    @FXML
    public void setKnightValidation() {
        System.out.println("Figure switched to Knight");
        validation = new KnightValidation();
        changeImage("knight.png");
    }

    @FXML
    public void setRookValidation() {
        System.out.println("Figure switched to Rook");
        validation = new RookValidation();
        changeImage("rook.png");
    }

    @FXML
    public void setQueenValidation() {
        System.out.println("Figure switched to Queen");
        validation = new QueenValidation();
        changeImage("queen.png");
    }

    @FXML
    public void setBishopValidation() {
        System.out.println("Figure switched to Bishop");
        validation = new BishopValidation();
        changeImage("bishop.png");
    }

    @FXML
    public void setKingValidation() {
        System.out.println("Figure switched to King");
        validation = new KingValidation();
        changeImage("king.png");
    }

    private void changeImage(String path) {
        String convertedPath = this.getClass().getResource("/" + path).toString();
        image = new Image(convertedPath);
        imageView.setImage(image);
    }

    @FXML
    private void changeView(ActionEvent actionEvent) {
        viewTrigger.setText(viewTrigger.getText().equals("PAPER") ? "CLASSIC" : "PAPER");
        Mode remMode;
        if (mode.equals(Mode.LIGHT)) {
            mode = Mode.DARK;
            remMode = Mode.LIGHT;
        } else {
            mode = Mode.LIGHT;
            remMode = Mode.DARK;
        }

        for (Node node : chessboard.getChildren()){
            if (node instanceof Field) {
                if (((Field) node).getColor().equals(remMode.lightFieldColor)){
                    ((Field) node).setColor(mode.lightFieldColor);
                }
                else if (((Field) node).getColor().equals(remMode.darkFieldColor)){
                    ((Field) node).setColor(mode.darkFieldColor);
                }
                ((Field) node).setBackground(new Background(new BackgroundFill(((Field) node).getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
    }

}
