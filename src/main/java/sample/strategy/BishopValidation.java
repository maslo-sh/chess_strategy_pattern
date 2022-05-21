package sample.strategy;

import javafx.scene.image.Image;

public class BishopValidation implements Validation {

    public Image image;

    @Override
    public boolean validate(int row, int column, int actRow, int actColumn) {
        return Math.abs(row - actRow) == Math.abs(column - actColumn);
    }
}
