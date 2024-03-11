package View;

import Constants.ColorConfig;
import Constants.LevelGame;
import Constants.SizeFrame;
import Constants.SudokuConfig;
import Controller.KeyAction;
import Model.Coordinates;
import Model.GameState;
import Model.Sudoku9x9;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuPanel extends JPanel {
    private Coordinates currentFocus;
    public final int TYPE_SUDOKU_SIZE;
    private final JLabel[][] matrixKey;

    private boolean isTheFirstTimePLayGame = true;
    private static final int NOT_FOCUS = -1;
    private final Sudoku9x9 modelSudoku;
    private GameState state;
    private Enum<LevelGame> levelGame;
    private final SudokuView sv;
    private final List<Integer> listShuffle;

    public SudokuPanel(int typeSudokuSize, SudokuView sv) {
        this.sv = sv;
        levelGame = LevelGame.EASY;
        modelSudoku = new Sudoku9x9();
        MouseInputAdapter mia = new KeyAction(this);
        currentFocus = new Coordinates(NOT_FOCUS, NOT_FOCUS);
        TYPE_SUDOKU_SIZE = typeSudokuSize;
        matrixKey = new KeyLabel[TYPE_SUDOKU_SIZE][TYPE_SUDOKU_SIZE];
        int rgb, rgb2, i, j;
        JLabel current;
        for (i = 0; i < TYPE_SUDOKU_SIZE; ++i) {
            for (j = 0; j < TYPE_SUDOKU_SIZE; ++j) {
                current = new KeyLabel(new Coordinates(i, j));
                current.setOpaque(true);
                current.addMouseListener(mia);
                current.setForeground(ColorConfig.COLOR_KEY_NORMAL);
                rgb = (i / 3) % 2 == 0 ? ColorConfig.COLOR_KEY_RGB : ColorConfig.COLOR_KEY2_RGB;
                rgb2 = rgb == ColorConfig.COLOR_KEY_RGB ? ColorConfig.COLOR_KEY2_RGB : ColorConfig.COLOR_KEY_RGB;
                if (j < 3 || j > 5)
                    current.setBackground(new Color(rgb));
                else
                    current.setBackground(new Color(rgb2));
                matrixKey[i][j] = current;
            }
        }
        final int max = SudokuConfig.SUDOKU9X9_SIZE * SudokuConfig.SUDOKU9X9_SIZE;
        listShuffle = new ArrayList<>(max);
        for (i = 0; i < max; ++i)
            listShuffle.add(i);
        initSetting();
    }
    public boolean isTheFirstTimePLayGame() {
        return isTheFirstTimePLayGame;
    }
    public void setTheFirstTimePLayGame(boolean theFirstTimePLayGame) {
        isTheFirstTimePLayGame = theFirstTimePLayGame;
    }

    private void initSetting() {
        setLayout(new GridLayout(TYPE_SUDOKU_SIZE, TYPE_SUDOKU_SIZE,
                SizeFrame.HGAP_GRIDLAYOUT, SizeFrame.VGAP_GRIDLAYOUT));
    }
    public void generateSudokuBoard() {
        int i, j;
        for (i = 0; i < TYPE_SUDOKU_SIZE; ++i) {
            for (j = 0; j < TYPE_SUDOKU_SIZE; ++j) {
                this.add(matrixKey[i][j]);
            }
        }
    }
    private boolean canMoveFocus(Coordinates c) {
        return (c.getX() != NOT_FOCUS && c.getY() != NOT_FOCUS) || !currentFocus.equals(c);
    }
    public void changeColorAfterClicked(Coordinates c) {
        if (!canMoveFocus(c))
            return;
        int x = c.getX(), y = c.getY();
        if (canMoveFocus(currentFocus))
            restoreColor(currentFocus);
        int i, j;
        for (i = 0; i < TYPE_SUDOKU_SIZE; ++i) {
            matrixKey[x][i].setBackground(new Color(ColorConfig.COLOR_KEY_CLICKED_RGB));
            matrixKey[i][y].setBackground(new Color(ColorConfig.COLOR_KEY_CLICKED_RGB));
        }
        i = (x / SudokuConfig.SMALL_BOX_SIZE) * SudokuConfig.SMALL_BOX_SIZE;
        int k = (y / SudokuConfig.SMALL_BOX_SIZE) * SudokuConfig.SMALL_BOX_SIZE;
        final int n = i + SudokuConfig.SMALL_BOX_SIZE;
        final int m = k + SudokuConfig.SMALL_BOX_SIZE;
        for (; i < n; ++i)
            for (j = k; j < m; ++j)
                matrixKey[i][j].setBackground(new Color(ColorConfig.COLOR_KEY_CLICKED_RGB));
        matrixKey[c.getX()][c.getY()].setBackground(new Color(ColorConfig.COLOR_KEY_CLICKED_CENTER));
        currentFocus = c;
    }
    private void resetColor() {
        int rgb, rgb2, i, j;
        JLabel current;
        for (i = 0; i < TYPE_SUDOKU_SIZE; ++i) {
            for (j = 0; j < TYPE_SUDOKU_SIZE; ++j) {
                current = matrixKey[i][j];
                current.setForeground(ColorConfig.COLOR_KEY_NORMAL);
                rgb = (i / 3) % 2 == 0 ? ColorConfig.COLOR_KEY_RGB : ColorConfig.COLOR_KEY2_RGB;
                rgb2 = rgb == ColorConfig.COLOR_KEY_RGB ? ColorConfig.COLOR_KEY2_RGB : ColorConfig.COLOR_KEY_RGB;
                if (j < 3 || j > 5)
                    current.setBackground(new Color(rgb));
                else
                    current.setBackground(new Color(rgb2));
            }
        }
        currentFocus = new Coordinates(NOT_FOCUS, NOT_FOCUS);
    }
    private void resetText() {
        int i, j;
        for (i = 0; i < TYPE_SUDOKU_SIZE; ++i)
            for (j = 0; j < TYPE_SUDOKU_SIZE; ++j)
                matrixKey[i][j].setText("");
    }
    public void resetGame() {
        resetColor();
        resetText();
    }

    private void restoreColor(Coordinates c) {
        int x = c.getX(), y = c.getY(), i, k;
        for (i = 0; i < TYPE_SUDOKU_SIZE; ++i) {
            matrixKey[x][i].setBackground(currentCoordinatesColor(x, i));
            matrixKey[i][y].setBackground(currentCoordinatesColor(i, y));
        }
        i = (x / SudokuConfig.SMALL_BOX_SIZE) * SudokuConfig.SMALL_BOX_SIZE;
        k = (y / SudokuConfig.SMALL_BOX_SIZE) * SudokuConfig.SMALL_BOX_SIZE;
        final int n = i + SudokuConfig.SMALL_BOX_SIZE;
        final int m = k + SudokuConfig.SMALL_BOX_SIZE;
        for (; i < n; ++i)
            for (int j = k; j < m; ++j)
                matrixKey[i][j].setBackground(currentCoordinatesColor(i, j));
    }
    private Color currentCoordinatesColor(int x, int y) {
        if (x / 3 % 2 == 0)
            return y < 3 || y > 5 ? new Color(ColorConfig.COLOR_KEY_RGB) : new Color(ColorConfig.COLOR_KEY2_RGB);
        else
            return y < 3 || y > 5 ? new Color(ColorConfig.COLOR_KEY2_RGB) : new Color(ColorConfig.COLOR_KEY_RGB);
    }
    public boolean setTextToMatrix(String num) {
        if (isCurrentFocusEmptyCell() && modelSudoku.isCorrectNumber(currentFocus, num)) {
            matrixKey[currentFocus.getX()][currentFocus.getY()].setText(num);
            matrixKey[currentFocus.getX()][currentFocus.getY()].setForeground(new Color(ColorConfig.COLOR_KEY_CORRECT_RGB));
            state.increaseCount();
            return true;
        }
        return  false;
    }
    public void startSudoku() {
        modelSudoku.generateASudoku();
        final int max = SudokuConfig.SUDOKU9X9_SIZE * SudokuConfig.SUDOKU9X9_SIZE;
        int i, x, y;
        Collections.shuffle(listShuffle);
        int lv;
        if (levelGame == LevelGame.EASY)
            lv = SudokuConfig.LEVEL_EASY;
        else if (levelGame == LevelGame.MEDIUM)
            lv = SudokuConfig.LEVEL_MEDIUM;
        else
            lv = SudokuConfig.LEVEL_HARD;
        state = new GameState(lv, max);
        for (i = 0; i < lv; ++i) {
            x = listShuffle.get(i) / SudokuConfig.SUDOKU9X9_SIZE;
            y = listShuffle.get(i) % SudokuConfig.SUDOKU9X9_SIZE;
            matrixKey[x][y].setText(modelSudoku.numberInCoordinates(x, y));
        }
    }
    public void solveSudoku() {
        int i, j;
        JLabel current;
        for (i = 0; i < TYPE_SUDOKU_SIZE; ++i) {
            for (j = 0; j < TYPE_SUDOKU_SIZE; ++j) {
                current = matrixKey[i][j];
                if (current.getText().isEmpty()) {
                    current.setText(modelSudoku.numberInCoordinates(i, j));
                    current.setForeground(new Color(ColorConfig.COLOR_KEY_CORRECT_RGB));
                }
            }
        }
    }

    public void changeLevelGame(Enum<LevelGame> levelChange) {
        this.levelGame = levelChange;
    }
    public void setMessage(String message) {
        sv.setMessage(message);
    }
    public boolean isPlayerWinGame() {
        return state.isEndGame();
    }
    public boolean isCurrentFocusEmptyCell() {
        if (currentFocus.getX() == NOT_FOCUS || currentFocus.getY() == NOT_FOCUS)
            return false;
        return matrixKey[currentFocus.getX()][currentFocus.getY()].getText().isEmpty();
    }
}
