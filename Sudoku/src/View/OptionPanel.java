package View;

import Constants.SizeFrame;
import Constants.SudokuConfig;

import javax.swing.*;
import java.awt.*;

public class OptionPanel extends JPanel {

    public OptionPanel() {
        this.setLayout(new GridLayout(SizeFrame.OPTIONPANEL_ROWS, SizeFrame.OPTIONPANEL_COLS));
        initOptions();
    }
    private void initOptions() {
        Button b;
        for (String str : SudokuConfig.KEY_SUDOKU9X9) {
            b = new Button(str);
            b.setPreferredSize(SizeFrame.OPTIONPANEL_SUDOKU9X9_BUTTON_DIMENSION);
            this.add(b);
        }
    }
}
