package View;

import Constants.FontConfig;
import Constants.OptionConfig;
import Constants.SizeFrame;
import Constants.SudokuConfig;
import Controller.ButtonAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class OptionPanel extends JPanel {
    private SudokuView view;
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
        Button start = new Button(OptionConfig.NEW_GAME);
        start.setFont(FontConfig.KEY_SUDOKU);
        start.setForeground(Color.RED);
        start.setPreferredSize(SizeFrame.OPTIONPANEL_SUDOKU9X9_BUTTON_DIMENSION);
        Button autoPLay = new Button(OptionConfig.SOLUTION);
        autoPLay.setFont(FontConfig.KEY_SUDOKU);
        autoPLay.setForeground(Color.GREEN);
        autoPLay.setPreferredSize(SizeFrame.OPTIONPANEL_SUDOKU9X9_BUTTON_DIMENSION);
        this.add(start);
        this.add(autoPLay);
        start.addActionListener(bl);
        autoPLay.addActionListener(bl);
    }
}
