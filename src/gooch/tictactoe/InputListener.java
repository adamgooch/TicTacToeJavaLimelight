package gooch.tictactoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class InputListener {
    private ArrayList<ActionListener> listeners = new ArrayList<ActionListener>();

    public InputListener(Board board) {

    }

    protected void notifyListeners() {
        for(ActionListener listener : listeners) {
            listener.actionPerformed(new ActionEvent(
                    this, ActionEvent.ACTION_PERFORMED, "Input Received"));
        }
    }

    public void addActionListener(Object listener) {
        listeners.add((ActionListener)listener);
    }
}
