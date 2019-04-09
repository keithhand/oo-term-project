package View;

import Controller.KeyEventListener;
import Controller.Main;
import Controller.MouseEventListener;

import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyWindow extends JFrame {

    public MyCanvas canvas;
    public JButton startButton;
    public JButton quitButton;

    public void init() {
        setSize(672, 864);
        setLocation(500, 100);
        setTitle("Game Engine");

        canvas = new MyCanvas();

        MouseEventListener listener = new MouseEventListener();
        canvas.addMouseListener(listener);
        canvas.addMouseMotionListener(listener);

        KeyEventListener keyEventListener = new KeyEventListener();
        canvas.addKeyListener(keyEventListener);
        canvas.setFocusable(true);

        var cp = getContentPane();
        cp.add(BorderLayout.CENTER, canvas);

        startButton = new JButton("Start");
        startButton.setFocusable(false);
        quitButton = new JButton("Quit");
        quitButton.setFocusable(false);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(quitButton);
        cp.add(BorderLayout.SOUTH, buttonPanel);

        // anonymous class, lambda
        startButton.addActionListener((e) -> {
            if (!Main.instructions) {
                startButton.setText("Next");
                Main.instructions = true;
            } else if (!Main.running) {
                Main.running = true;
                buttonPanel.remove(startButton);
                buttonPanel.revalidate();
                buttonPanel.repaint();
            }
        });
        quitButton.addActionListener((e) -> {
            System.exit(0);
        });
    }
}
