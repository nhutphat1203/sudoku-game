package View;

import Constants.SizeFrame;
import Constants.SudokuConfig;

import javax.swing.*;
import java.awt.*;

public class SudokuView extends JFrame {
    private JPanel mainPanel;
    private SudokuPanel sudokuPanel;
    private JPanel wrapSudokuPanel;
    private OptionPanel optionPanel;
    private JPanel northPanel;
    private JPanel southPanel;
    private JPanel westPanel;
    public SudokuView() {
        mainPanel = new JPanel();
        sudokuPanel = new SudokuPanel(SudokuConfig.SUDOKU9X9_SIZE);
        optionPanel = new OptionPanel();
        northPanel = new JPanel();
        southPanel = new JPanel();
        westPanel = new JPanel();
        westPanel = new JPanel();
        wrapSudokuPanel = new JPanel();
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
        * Chỉnh wrapSudokuPanel
        */

        wrapSudokuPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        wrapSudokuPanel.setLayout(new BorderLayout());
        wrapSudokuPanel.add(sudokuPanel, BorderLayout.CENTER);
        wrapSudokuPanel.setBackground(new Color(0xF9F2F2));
        /*
        *Chỉnh sửa thông số mainPane
        */
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(wrapSudokuPanel, BorderLayout.CENTER);
        mainPanel.add(optionPanel, BorderLayout.EAST);
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        mainPanel.add(westPanel, BorderLayout.WEST);

        /*
        *Chỉnh sửa sudokuPane
        */
            sudokuPanel.generateSudokuBoard(null);
        /*
        * Chỉnh sửa optionPane
        */

        this.setContentPane(mainPanel);
        this.setVisible(true);
    }
}
