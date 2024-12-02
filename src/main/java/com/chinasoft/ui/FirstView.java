package com.chinasoft.ui;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.chinasoft.listener.ExitButtonListener;
import com.chinasoft.listener.StartButtonListener;

public class FirstView {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FirstView window = new FirstView();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public FirstView() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(FirstView.class.getResource("/image/person01.png")));
        frame.setBounds(100, 100, 812, 655);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("骑士夺宝");
        frame.getContentPane().setLayout(null);

        JLabel lblExit = new JLabel("New label");
        lblExit.setIcon(new ImageIcon(FirstView.class.getResource("/image/exit-bt01.png")));
        lblExit.setBounds(304, 358, 186, 42);
        lblExit.addMouseListener(new ExitButtonListener());
        frame.getContentPane().add(lblExit);

        JLabel lblStart = new JLabel("New label");
        lblStart.setIcon(new ImageIcon(FirstView.class.getResource("/image/start02.png")));
        lblStart.setBounds(304, 258, 186, 42);
        lblStart.addMouseListener(new StartButtonListener(frame));
        frame.getContentPane().add(lblStart);

        JLabel lblMainIcon = new JLabel("New label");
        lblMainIcon.setIcon(new ImageIcon(FirstView.class.getResource("/image/2.png")));
        lblMainIcon.setBounds(256, 61, 299, 132);
        frame.getContentPane().add(lblMainIcon);

        JLabel lbkBack = new JLabel("New label");
        lbkBack.setIcon(new ImageIcon(FirstView.class.getResource("/image/guanka.png")));
        lbkBack.setBounds(0, 0, 790, 600);
        frame.getContentPane().add(lbkBack);
    }
}
