package sample.strategy;

import javafx.scene.image.Image;

public class RookValidation implements Validation {

    public Image image;

    @Override
    public boolean validate(int row, int column, int actRow, int actColumn) {
        return row == actRow || column == actColumn;
    }
}
