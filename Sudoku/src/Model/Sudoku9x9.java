package Model;

import Constants.SudokuConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sudoku9x9 extends Sudoku{
    private final int[] rows, cols, boxes;
    private static final char EMPTY = '\0';
    private static final int LAST_INDEX = 80;
    public Sudoku9x9() {
        super(new char[SudokuConfig.SUDOKU9X9_SIZE][SudokuConfig.SUDOKU9X9_SIZE]);
        rows = new int[SudokuConfig.SUDOKU9X9_SIZE];
        cols = new int[SudokuConfig.SUDOKU9X9_SIZE];
        boxes = new int[SudokuConfig.SUDOKU9X9_SIZE];
    }

    private void mark(int key, int i, int j) {
        int bit = 1 << (key - 1);
        rows[i] ^= bit;
        cols[j] ^= bit;
        boxes[(i / SudokuConfig.SMALL_BOX_SIZE) * SudokuConfig.SMALL_BOX_SIZE + j / SudokuConfig.SMALL_BOX_SIZE] ^= bit;
    }
    private void generateABox() {
        List<Integer> A = new ArrayList<>(SudokuConfig.SUDOKU9X9_SIZE);
        int i, j, index = 0, key;
        for (i = 1; i <= SudokuConfig.SUDOKU9X9_SIZE; ++i)
            A.add(i);
        Collections.shuffle(A);
        for (i = 0; i < SudokuConfig.SMALL_BOX_SIZE; ++i) {
            for (j = 0; j < SudokuConfig.SMALL_BOX_SIZE; ++j) {
                key = A.get(index++);
                sudokuBoard[i][j] = (char)(key + '0');
                mark(key, i, j);
            }
        }
    }

    private int takeNumberExistRepresentByBit(int i, int j) {
        return rows[i] | cols[j] | boxes[((i / 3) * 3) + (j / 3)];
    }
    private boolean generateASudokuHelper(int index) {
        if (index > LAST_INDEX)
            return true;
        while (sudokuBoard[index / SudokuConfig.SUDOKU9X9_SIZE][index % SudokuConfig.SUDOKU9X9_SIZE] != EMPTY)
            ++index;
        int x = index / SudokuConfig.SUDOKU9X9_SIZE, y = index % SudokuConfig.SUDOKU9X9_SIZE;
        int rpbit = takeNumberExistRepresentByBit(x, y), k;
        for (k = 1; k <= SudokuConfig.SUDOKU9X9_SIZE; ++k, rpbit >>= 1) {
            if ((rpbit & 1) == 1)
                continue;
            sudokuBoard[x][y] = (char)(k + '0');
            mark(k, x, y);
            if (generateASudokuHelper(index + 1))
                return true;
            mark(k, x, y);
            sudokuBoard[x][y] = EMPTY;
        }
        return false;
    }
    @Override
    public void generateASudoku() {
        int i;
        for (i = 0; i < sudokuBoard.length; ++i) {
            Arrays.fill(sudokuBoard[i], EMPTY);
        }
        Arrays.fill(rows, 0);
        Arrays.fill(cols, 0);
        Arrays.fill(boxes, 0);
        generateABox();
        generateASudokuHelper(0);
    }

    public boolean isCorrectNumber(Coordinates c, String number) {
        return String.valueOf(sudokuBoard[c.getX()][c.getY()]).equals(number);
    }
    public String numberInCoordinates(int x, int y) {
        return Character.toString(sudokuBoard[x][y]);
    }
}
