package View;

import Model.Coordinates;

import javax.swing.*;
import java.awt.*;

public class SudokuPanel extends JPanel {
    private Coordinates currentFocus;
    public final int TYPE_SUDOKU_SIZE;

    private static final int NOT_FOCUS = -1;
    public SudokuPanel(int typeSudokuSize) {
        currentFocus = new Coordinates(NOT_FOCUS, NOT_FOCUS);
        TYPE_SUDOKU_SIZE = typeSudokuSize;
        initSetting();
    }
    private void initSetting() {
        setLayout(new GridLayout(TYPE_SUDOKU_SIZE, TYPE_SUDOKU_SIZE));
    }
    public void generateSudokuBoard(int[][] matrix) {
        int n = TYPE_SUDOKU_SIZE * TYPE_SUDOKU_SIZE;
        for (int i = 0; i < n; ++i) {
            var e = new JLabel(Integer.toString(i));
            this.add(e);
        }
    }
}
