package sample.strategy;

import javafx.scene.image.Image;

public class KingValidation implements Validation {

    public Image image;

    @Override
    public boolean validate(int row, int column, int actRow, int actColumn) {
        return Math.abs(row - actRow) <= 1 && Math.abs(column - actColumn) <= 1;
    }
}
