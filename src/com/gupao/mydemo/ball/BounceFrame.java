package com.gupao.mydemo.ball;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ClassName BounceFrame
 * @Author LIUHANPENG
 * @Date 2019/11/20 0020 16:34
 **/
public class BounceFrame extends JFrame{

    public BounceFrame(){
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        setTitle("Bounce");

        panel = new BallPanel();
        add(panel,BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel,"Start",new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                addBall();
            }
        });
        addButton(buttonPanel,"Close",new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(buttonPanel,BorderLayout.SOUTH);
    }

    public void addButton(Container c, String title, ActionListener listener){
        JButton button = new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }

    public void addBall(){
        Ball ball = new Ball();
        panel.add(ball);
        for(int i=1;i<=STEPS;i++){
            ball.move(panel.getBounds());
            panel.paint(panel.getGraphics());
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private BallPanel panel;
    public static final int DEFAULT_WIDTH=450;
    public static final int DEFAULT_HEIGHT = 350;
    public static final int STEPS = 10000;
    public static final int DELAY = 3;

}
