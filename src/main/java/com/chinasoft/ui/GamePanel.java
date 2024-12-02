package com.chinasoft.ui;

import com.chinasoft.enumdef.Direction;
import com.chinasoft.obj.Man;
import com.chinasoft.obj.MapData;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements KeyListener {

    private static final long serialVersionUID = 4580018968485363291L;

    //private Image[] bases = new Image[10];
    List<Image> bases = new ArrayList<>();
    private MapData data;

    private final int WIDTH = 20;
    private boolean manFlag = true;
    private int manX, manY;
    //int[][] map = new int[WIDTH][WIDTH];

    public GamePanel() {
        this.setBackground(Color.GRAY);
        // this.setBounds(0, 0, 1000, 1000);  // 笔记本电脑
        this.setBounds(0, 0, 600, 600);
        initBases();
        this.setVisible(true);
        //manX = 8;
        //manY = 8;

        data = new MapData();
        data.loadMap(data.getLevel());
        data.setMan(data.getMan());

        // repaint();
        // this.setVisible(true);
        // drawPerson(3, 3);

        this.setFocusable(true);
        this.addKeyListener(this);
        this.setVisible(true);
        this.requestFocus();


        repaint();
    }

    public void initBases() {
        for (int i = 0; i < 10; i++) {
            bases.add(Toolkit.getDefaultToolkit().getImage(GamePanel.class.getResource("/image/scene/" + i + ".gif")));
        }
    }

    @Override
    public void paint(Graphics g) {
        int[][] map = data.getMap();
        for (int y = 0; y < WIDTH; y++) {
            for (int x = 0; x < WIDTH; x++) {
                int index = map[y][x];
                //g.drawImage(bases[0], x*50, y*50, this);  // 笔记本电脑
                switch (index){
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                        g.drawImage(bases.get(0), x*30, y*30, 30, 30, this);
                    default:
                        g.drawImage(bases.get(index), x*30, y*30, 30, 30, this);
                        break;
                }
                //g.drawImage(bases.get(index), x*30, y*30, 30, 30, this);
            }
        }
//        if (manFlag) {
//            g.drawImage(bases.get(5), manX * 30, manY * 30, this);  // 笔记本电脑
//        }

        // g.drawLine(10, 10, 200, 200);
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }


    @Override
    public void keyPressed(KeyEvent e) {
        Man man = data.getMan();
        System.out.println(e.getKeyCode());
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP:
                man.move(Direction.Up);
                repaint();
                break;
            case KeyEvent.VK_DOWN:
                man.move(Direction.Down);
                repaint();
                break;
            case KeyEvent.VK_LEFT:
                man.move(Direction.Left);
                repaint();
                break;
            case KeyEvent.VK_RIGHT:
                man.move(Direction.Right);
                repaint();
                break;
            default:
                break;
        }

        if (data.isWin() == true) {
            if (data.getLevel() == MapData.MAX_LEVEL) {
                JOptionPane.showMessageDialog(this, " 已经是最后一关了 ！");
            } else {
                String msg = "是否要进入下一关？";
                UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("宋体", Font.ITALIC, 15)));
                int confirm = JOptionPane.showConfirmDialog(this, msg, "过关", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    nextLevel();
                } else {
                    // null
                    return;
                }
            }
        }
    }

    /*
    @Override
    public void keyPressed(KeyEvent e) {
        Man man = data.getMan();
        System.out.println(e.getKeyCode());
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP:
                man.moveUp();
                repaint();
                break;
            case KeyEvent.VK_DOWN:
                man.moveDown();
                System.out.println("man Y is " + manY);
                repaint();
                break;
            case KeyEvent.VK_LEFT:
                man.moveLeft();
                repaint();
                break;
            case KeyEvent.VK_RIGHT:
                man.moveRight();
                System.out.println("man X is " + manY);
                repaint();
                break;
            default:
                break;
        }
    }
    */

    public boolean nextLevel() {
        boolean ret = data.nextLevel();
        if(ret){ //self
            initLevel(data.getLevel());
        }
        repaint();
        return ret;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /*
    private void moveRight() {
        if (manX < 19) {
            map[manY][manX] = 0;
            manX ++;
            map[manY][manX] = 7;
        }
        System.out.println("man X is " + manY);
    }

    private void moveLeft() {
        if(manX > 0) {
            map[manY][manX] = 0;
            manX --;
            map[manY][manX] = 6;
        }
    }

    private void moveDown() {
        if (manY < 19) {
            map[manY][manX] = 0;
            manY ++;
            map[manY][manX] = 5;
        }
        System.out.println("man Y is " + manY);
    }

    private void moveUp() {
        if(manY > 0) {
            map[manY][manX] = 0;
            manY --;
            map[manY][manX] = 8;
        }
    }
    */

    /*
    public void loadMap(int level) {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            fr = new FileReader(GamePanel.class.getResource("/maps/" + level + ".map").getPath());
            reader = new BufferedReader(fr);
            String line = null;
            int i = 0;
            while( (line = reader.readLine()) != null) {
                char[] arr = line.toCharArray();
                for(int j = 0; j < arr.length; j++) {
                    map[i][j] = Integer.parseInt("" + arr[j]);
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void getManXY() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (map[i][j] == 5) {
                    manX = j;
                    manY = i;
                    return;
                }
            }
        }
    }
    */

    public void initLevel(int level){
        data.setLevel(level);
        data.loadMap(data.getLevel());

        data.setMan(data.getMan());
        data.initBoxes(); //self
        repaint();
    }

    public MapData getData(){
        return data;
    }

}
