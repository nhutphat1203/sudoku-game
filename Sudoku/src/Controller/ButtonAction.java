package Controller;

import Constants.LevelGame;
import Constants.Message;
import Constants.OptionConfig;
import View.SudokuPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonAction implements ActionListener {
    private final SudokuPanel sp;
    public ButtonAction(SudokuPanel sp) {
        this.sp = sp;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String ac = e.getActionCommand();
        switch (ac) {
            case OptionConfig.NEW_GAME -> {
                if (sp.isTheFirstTimePLayGame()) {
                    sp.setTheFirstTimePLayGame(false);
                    sp.resetGame();
                    sp.setMessage("");
                    sp.startSudoku();
                } else {
                    int state = JOptionPane.showConfirmDialog(sp, Message.MSG_NEW_GAME);
                    if (state == JOptionPane.YES_OPTION) {
                        sp.resetGame();
                        sp.setMessage("");
                        sp.startSudoku();
                    }
                }
            }
            case OptionConfig.SOLUTION -> {
                int state = JOptionPane.showConfirmDialog(sp, Message.MSG_SOLUTION);
                if (state == JOptionPane.YES_OPTION) {
                    sp.solveSudoku();
                    sp.setMessage("");
                }
            }
            case OptionConfig.LEVEL -> {
                Object[] options = new String[]{
                        LevelGame.EASY.toString(),
                        LevelGame.MEDIUM.toString(),
                        LevelGame.HARD.toString()
                };
                int choose = JOptionPane.showOptionDialog(sp, "Choose level",
                        "Options", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, options, options[0]);
                switch (choose) {
                    case 0 -> sp.changeLevelGame(LevelGame.EASY);
                    case 1 -> sp.changeLevelGame(LevelGame.MEDIUM);
                    case 2 -> sp.changeLevelGame(LevelGame.HARD);
                }
                if (choose != -1) {
                    sp.resetGame();
                    sp.setMessage("");
                    sp.startSudoku();
                }
            }
            default -> {
                if (sp.setTextToMatrix(ac)) {
                    if (sp.isPlayerWinGame()) {
                        sp.setMessage(Message.MSG_WIN);
                        JOptionPane.showOptionDialog(
                                sp,
                                "Congratulations! You have won the sudoku game!",
                                "Win", JOptionPane.YES_NO_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null, null, null
                        );
                    } else
                        sp.setMessage(Message.MSG_NICE);
                } else {
                    if (!sp.isTheFirstTimePLayGame())
                        sp.setMessage(Message.MSG_WRONG);
                }
            }
        }
    }
}
