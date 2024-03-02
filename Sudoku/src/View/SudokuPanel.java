package View;

import Constants.ColorConfig;
import Constants.FontConfig;
import Constants.SizeFrame;
import Model.Coordinates;

import javax.swing.*;
import java.awt.*;

public class SudokuPanel extends JPanel {
    private Coordinates currentFocus;
    public final int TYPE_SUDOKU_SIZE;
    private JLabel[][] matrixKey;

    private static final int NOT_FOCUS = -1;
    public SudokuPanel(int typeSudokuSize) {
        currentFocus = new Coordinates(NOT_FOCUS, NOT_FOCUS);
        TYPE_SUDOKU_SIZE = typeSudokuSize;
        matrixKey = new KeyLabel[TYPE_SUDOKU_SIZE][TYPE_SUDOKU_SIZE];
        int rgb, rgb2;
        for (int i = 0; i < TYPE_SUDOKU_SIZE; ++i) {
            for (int j = 0; j < TYPE_SUDOKU_SIZE; ++j) {
                matrixKey[i][j] = new KeyLabel(new Coordinates(i, j));
                matrixKey[i][j].setOpaque(true);
                rgb = (i / 3) % 2 == 0 ? ColorConfig.COLOR_KEY_RGB : ColorConfig.COLOR_KEY2_RGB;
                rgb2 = rgb == ColorConfig.COLOR_KEY_RGB ? ColorConfig.COLOR_KEY2_RGB : ColorConfig.COLOR_KEY_RGB;
                if (j < 3 || j > 5)
                    matrixKey[i][j].setBackground(new Color(rgb));
                else
                    matrixKey[i][j].setBackground(new Color(rgb2));
            }
        }
        initSetting();
    }
    private void initSetting() {
        setLayout(new GridLayout(TYPE_SUDOKU_SIZE, TYPE_SUDOKU_SIZE,
                SizeFrame.HGAP_GRIDLAYOUT, SizeFrame.VGAP_GRIDLAYOUT));
    }

    public void generateSudokuBoard(int[][] matrix) {
        JLabel e;
        for (int i = 0; i < TYPE_SUDOKU_SIZE; ++i) {
            for (int j = 0; j < TYPE_SUDOKU_SIZE; ++j) {
                this.add(matrixKey[i][j]);
            }
        }
    }
}
