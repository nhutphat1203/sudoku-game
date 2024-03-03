package View;

import Constants.*;
import Controller.ButtonAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class OptionPanel extends JPanel {
    private final SudokuView view;

    public OptionPanel(SudokuView sv) {
        this.view = sv;
        this.setLayout(new GridLayout(SizeFrame.OPTIONPANEL_ROWS, SizeFrame.OPTIONPANEL_COLS));
        initOptions();
    }
    private void initOptions() {
        ActionListener bl = new ButtonAction(view.getSudokuPanel());
        Button b;
        for (String str : SudokuConfig.KEY_SUDOKU9X9) {
            b = new Button(str);
            b.setFont(FontConfig.KEY_SUDOKU);
            b.setPreferredSize(SizeFrame.OPTIONPANEL_SUDOKU9X9_BUTTON_DIMENSION);
            this.add(b);
            b.addActionListener(bl);
        }
        // Button new game
        Button start = new Button(OptionConfig.NEW_GAME);
        start.setFont(FontConfig.KEY_SUDOKU);
        start.setForeground(Color.RED);
        start.setPreferredSize(SizeFrame.OPTIONPANEL_SUDOKU9X9_BUTTON_DIMENSION);
        // Button solution
        Button autoPLay = new Button(OptionConfig.SOLUTION);
        autoPLay.setFont(FontConfig.KEY_SUDOKU);
        autoPLay.setForeground(Color.GREEN);
        autoPLay.setPreferredSize(SizeFrame.OPTIONPANEL_SUDOKU9X9_BUTTON_DIMENSION);
        // Button levels
        Button level = new Button(OptionConfig.LEVEL);
        level.setFont(FontConfig.KEY_SUDOKU);
        level.setForeground(Color.CYAN);
        level.setPreferredSize(SizeFrame.OPTIONPANEL_SUDOKU9X9_BUTTON_DIMENSION);

        this.add(start);
        this.add(autoPLay);
        this.add(level);
        start.addActionListener(bl);
        autoPLay.addActionListener(bl);
        level.addActionListener(bl);
    }
}
