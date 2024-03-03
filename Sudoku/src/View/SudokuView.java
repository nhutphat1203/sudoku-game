package View;

import Constants.ColorConfig;
import Constants.FontConfig;
import Constants.SizeFrame;
import Constants.SudokuConfig;

import javax.swing.*;
import java.awt.*;

public class SudokuView extends JFrame {
    final private JPanel mainPanel;
    private final SudokuPanel sudokuPanel;
    private final JPanel wrapSudokuPanel;
    private final OptionPanel optionPanel;
    private final JPanel northPanel;
    private final JPanel southPanel;
    private JPanel westPanel;
    private JLabel messageLabel;
    public SudokuView() {
        mainPanel = new JPanel();
        sudokuPanel = new SudokuPanel(SudokuConfig.SUDOKU9X9_SIZE, this);
        optionPanel = new OptionPanel(this);
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        /*
        *Chỉnh sửa thông số của frame chính
        */
        this.setTitle("Sudoku Game");
        this.setMinimumSize(new Dimension(SizeFrame.WIDTH, SizeFrame.HEIGHT));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        /*
        * Chỉnh wrapSudokuPanel
        */

        wrapSudokuPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        wrapSudokuPanel.setLayout(new BorderLayout());
        wrapSudokuPanel.add(sudokuPanel, BorderLayout.CENTER);
        wrapSudokuPanel.setBackground(new Color(0xF9F2F2));
        /*
        * Chỉnh sửa thông số mainPane
        */
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(wrapSudokuPanel, BorderLayout.CENTER);
        mainPanel.add(optionPanel, BorderLayout.EAST);
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        mainPanel.add(westPanel, BorderLayout.WEST);

        /*
        * Chỉnh sửa sudokuPane
        */
            sudokuPanel.generateSudokuBoard();
        /*
        * Chỉnh sửa optionPane
        */

        // Message label
        messageLabel = new JLabel();
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setVerticalAlignment(SwingConstants.CENTER);
        messageLabel.setFont(FontConfig.KEY_SUDOKU);
        messageLabel.setForeground(new Color(ColorConfig.COLOR_MESSAGE_RGB));
        messageLabel.setBackground(Color.LIGHT_GRAY);
        messageLabel.setOpaque(true);

        this.optionPanel.add(messageLabel);
        this.setContentPane(mainPanel);
        this.setVisible(true);
    }

    public SudokuPanel getSudokuPanel() {
        return sudokuPanel;
    }

    public void setMessage(String message) {
        this.messageLabel.setText(message);
    }
}
