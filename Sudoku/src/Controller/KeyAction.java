package Controller;

import View.KeyLabel;
import View.SudokuPanel;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

public class KeyAction extends MouseInputAdapter {
    private final SudokuPanel sp;
    public KeyAction(SudokuPanel sp) {
        this.sp = sp;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        KeyLabel k = (KeyLabel) e.getSource();
        sp.changeColorAfterClicked(k.getCoordinates());
        System.out.println(k.getCoordinates());
    }

}
