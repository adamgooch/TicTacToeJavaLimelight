package gooch.tictactoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class InputReceiver {
    private static final String INPUT_RECEIVED = "Input Received";
    private ArrayList<ActionListener> listeners = new ArrayList<ActionListener>();
    protected Board board;

    public InputReceiver(Board board) {
        this.board = board;
    }

    protected void notifyListeners() {
        for(ActionListener listener : listeners) {
            listener.actionPerformed(new ActionEvent(
                    this, ActionEvent.ACTION_PERFORMED, INPUT_RECEIVED));
        }
    }

    public void addActionListener(Object listener) {
        listeners.add((ActionListener)listener);
    }
}
