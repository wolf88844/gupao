package com.gupao.mydemo.ball;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @ClassName BallPanel
 * @Author LIUHANPENG
 * @Date 2019/11/20 0020 16:33
 **/
public class BallPanel extends JPanel {
    public void add(Ball b) {
        balls.add(b);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Ball b : balls) {
            g2.fill(b.getShape());
        }
    }

    private ArrayList<Ball> balls = new ArrayList<>();
}
