package Controller;

import Constants.Message;
import Constants.OptionConfig;
import View.OptionPanel;
import View.SudokuPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonAction implements ActionListener {
    private SudokuPanel sp;
    public ButtonAction(SudokuPanel sp) {
        this.sp = sp;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String ac = e.getActionCommand();
        if (ac.equals(OptionConfig.NEW_GAME)) {
            if (sp.isTheFirstTimePLayGame()) {
                sp.setTheFirstTimePLayGame(false);
                sp.resetGame();
                sp.startSudoku();
            }
            else {
                int state = JOptionPane.showConfirmDialog(sp, Message.MSG_NEW_GAME);
                if (state == JOptionPane.YES_OPTION) {
                    sp.resetGame();
                    sp.startSudoku();
                }
            }
        }
        else if (ac.equals(OptionConfig.SOLUTION)) {
            int state = JOptionPane.showConfirmDialog(sp, Message.MSG_SOLUTION);
            if (state == JOptionPane.YES_OPTION)
                sp.solveSudoku();
        }
        else {
            sp.setTextToMatrix(ac);
        }
    }
}
