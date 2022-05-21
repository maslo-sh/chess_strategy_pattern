package sample.strategy;

import javafx.scene.image.Image;

public class QueenValidation implements Validation {

    public Image image;

    public QueenValidation() {

    }

    @Override
    public boolean validate(int row, int column, int actRow, int actColumn) {
        return row == actRow || column == actColumn || Math.abs(row - actRow) == Math.abs(column - actColumn);
    }
}
