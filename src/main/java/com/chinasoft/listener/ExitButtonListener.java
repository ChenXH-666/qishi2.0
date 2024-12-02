package com.chinasoft.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ExitButtonListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("游戏退出... !");
        System.exit(0);
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
