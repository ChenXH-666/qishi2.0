package com.chinasoft.ui;

import com.chinasoft.obj.MapData;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayView extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JButton replayBtn;
    private JButton firstBtn;
    private JButton prevBtn;
    private JButton nextBtn;
    private JButton lastBtn;
    private JButton musicBtn;
    private JButton exitBtn;
    private GamePanel game;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PlayView frame = new PlayView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public PlayView() {
        setTitle("开始玩游戏了");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(10, 10, 800, 635);
        setFont(new Font("微软雅黑", Font.PLAIN, 20));

        contentPane = new JPanel();
        contentPane.setBorder(new LineBorder(Color.GREEN));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // 设置游戏的区域
        game = new GamePanel();
        contentPane.add(game);

        // 游戏面板
        JPanel game = new JPanel();
        game.setBackground(Color.GRAY);
        game.setBounds(0, 0, 600, 600);
        game.setVisible(true);
        contentPane.add(game);

        // 按钮区域面板
        JPanel btnArea = new JPanel();
        btnArea.setBackground(Color.LIGHT_GRAY);
        btnArea.setBounds(602, 0, 180, 600);
        btnArea.setVisible(true);
        btnArea.setLayout(null);
        btnArea.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        contentPane.add(btnArea);

        // 添加按钮
        replayBtn = new JButton("重  来");
        replayBtn.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        btnArea.add(replayBtn);
        replayBtn.setBounds(40, 50, 100, 25);
        replayBtn.addActionListener(this);

        firstBtn = new JButton("第 1 关");
        firstBtn.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        btnArea.add(firstBtn);
        firstBtn.setBounds(40, 130, 100, 25);
        firstBtn.addActionListener(this);

        prevBtn = new JButton("上一关");
        prevBtn.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        btnArea.add(prevBtn);
        prevBtn.setBounds(40, 210, 100, 25);
        prevBtn.addActionListener(this);

        nextBtn = new JButton("下一关");
        nextBtn.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        btnArea.add(nextBtn);
        nextBtn.setBounds(40, 290, 100, 25);
        nextBtn.addActionListener(this);

        lastBtn = new JButton("最终关");
        lastBtn.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        btnArea.add(lastBtn);
        lastBtn.setBounds(40, 370, 100, 25);
        lastBtn.addActionListener(this);

        musicBtn = new JButton("音  乐");
        musicBtn.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        btnArea.add(musicBtn);
        musicBtn.setBounds(40, 450, 100, 25);
        musicBtn.addActionListener(this);

        exitBtn = new JButton("退  出");
        exitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        btnArea.add(exitBtn);
        exitBtn.setBounds(40, 530, 100, 25);
        exitBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == replayBtn) {
            System.out.println("重来按钮被点击");
            // 处理重来按钮的逻辑
            int level = game.getData().getLevel();
            game.initLevel(level);
        } else if (e.getSource() == firstBtn) {
            System.out.println("第1关按钮被点击");
            // 处理第1关按钮的逻辑
            game.initLevel(1);
        } else if (e.getSource() == prevBtn) {
            System.out.println("上一关按钮被点击");
            // 处理上一关按钮的逻辑
            int level = game.getData().getLevel();
            if (level > 1) {
                level--;
                game.initLevel(level);
            } else {
                JOptionPane.showMessageDialog(this, "已经到了  第一关了  ！");
            }
        } else if (e.getSource() == nextBtn) {
            System.out.println("下一关按钮被点击");
            // 处理下一关按钮的逻辑
            boolean next = game.nextLevel();
            if (next == false) {
                JOptionPane.showMessageDialog(this, "已经到了 最后一关了 ！");
            }
        } else if (e.getSource() == lastBtn) {
            System.out.println("最终关按钮被点击");
            // 处理最终关按钮的逻辑
            game.initLevel(MapData.MAX_LEVEL);
        } else if (e.getSource() == musicBtn) {
            System.out.println("音乐按钮被点击");
            // 处理音乐按钮的逻辑
        } else if (e.getSource() == exitBtn) {
            System.out.println("退出按钮被点击");
            System.exit(0);  // 退出应用程序
        }
        game.requestFocusInWindow();
    }
}