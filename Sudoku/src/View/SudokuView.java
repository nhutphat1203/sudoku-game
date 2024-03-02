package View;

import Constants.SizeFrame;
import Constants.SudokuConfig;

import javax.swing.*;
import java.awt.*;

public class SudokuView extends JFrame {
    private JPanel mainPane;
    private JPanel sudokuPane;
    private JPanel optionPane;
    public SudokuView() {
        mainPane = new JPanel();
        sudokuPane = new SudokuPanel(SudokuConfig.SUDOKU9X9_SIZE);
        optionPane = new JPanel();
        initGameMenu();
    }
    public final void initGameMenu() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        /*
        *Chỉnh sửa thông số của frame chính
        */
        this.setTitle("Sudoku Game");
        this.setMinimumSize(new Dimension(SizeFrame.WIDTH, SizeFrame.HEIGHT));
        this.setLocationRelativeTo((Component) null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        /*
        *Chỉnh sửa thông số mainPane
        */
        mainPane.setLayout(new BorderLayout());
        mainPane.add(sudokuPane, BorderLayout.CENTER);
        mainPane.add(optionPane, BorderLayout.EAST);
        /*
        *Chỉnh sửa sudokuPane
        */

        /*
        * Chỉnh sửa optionPane
        */
        this.setContentPane(mainPane);
        this.setVisible(true);
    }
}
