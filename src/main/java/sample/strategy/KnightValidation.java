package sample.strategy;

import javafx.scene.image.Image;

public class KnightValidation implements Validation {

    public Image image;

    @Override
    public boolean validate(int row, int column, int actRow, int actColumn) {
        return (Math.abs(row - actRow) == 2 && Math.abs(column - actColumn) == 1) || (Math.abs(row - actRow) == 1 && Math.abs(column - actColumn) == 2);
    }

}
