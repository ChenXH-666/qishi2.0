package com.chinasoft.listener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.chinasoft.ui.PlayView;

public class StartButtonListener implements MouseListener {

    private Frame start;

    public StartButtonListener(Frame startFrame) {
        start = startFrame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("开始玩游戏了... !");
        PlayView playView = new PlayView();
        playView.setVisible(true);
        start.dispose();
        // System.exit(0);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
