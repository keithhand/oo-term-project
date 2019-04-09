package Controller;

import java.util.EventObject;

public class InputEvent {

    public static final int MOUSE_PRESSED = 0;
    public static final int MOUSE_MOVED = 1;
    public static final int KEY_PRESSED = 2;

    public EventObject event;
    public int type;
}
