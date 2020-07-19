package dev.Akiba.rulesmasher.display;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import javax.swing.JFrame;

public class Display {

    private JFrame frame;
    private Canvas canvas;

    private String title;
    private int width, height;

    // Initialize window
    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    private void createDisplay() {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public Frame getFrame() {
        return this.frame;
    }
}
