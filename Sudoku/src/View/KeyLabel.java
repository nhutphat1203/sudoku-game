package View;

import Constants.FontConfig;
import Model.Coordinates;

import javax.swing.*;
import java.awt.*;

public class KeyLabel extends JLabel {
    private Coordinates coordinates;
    public KeyLabel(Coordinates coordinates) {
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setForeground(Color.BLACK);
        setFont(FontConfig.KEY_SUDOKU);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
