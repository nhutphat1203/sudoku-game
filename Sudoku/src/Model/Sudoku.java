package Model;

public abstract class Sudoku {
    protected char[][] sudokuBoard;

    protected Sudoku(char[][] sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }

    protected char[][] getSudokuBoard() {
        return sudokuBoard;
    }

    protected void setSudokuBoard(char[][] sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }

    protected abstract void generateASudoku();

}
