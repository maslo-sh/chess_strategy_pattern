package sample.gui;

import javafx.scene.paint.Color;

public enum Mode {
    DARK(Color.BROWN, Color.BEIGE), LIGHT(Color.BLACK, Color.WHITE);

    public Color darkFieldColor;
    public Color lightFieldColor;

    Mode(Color darkFieldColor, Color lightFieldColor){
        this.darkFieldColor = darkFieldColor;
        this.lightFieldColor = lightFieldColor;
    }
}
