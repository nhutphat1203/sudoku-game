package View.Event;

import View.KeyLabel;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

public class KeyAction extends MouseInputAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
        KeyLabel k = (KeyLabel) e.getSource();
        System.out.println(k.getCoordinates());
    }

}
