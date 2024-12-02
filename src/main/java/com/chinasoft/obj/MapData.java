package com.chinasoft.obj;

import com.chinasoft.enumdef.Direction;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapData {
    public static final int WIDTH = 20;
    public static final int MAX_LEVEL = 50;
    private int[][]  map = new int[WIDTH][WIDTH];
    private int level;
    private Man man;
    private List<Box> boxList = new ArrayList<Box>();

    public MapData() {
        level = 1;
        loadMap(level);
        man = getManFromMap();
        initBoxes();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public Man getManFromMap() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (map[i][j] == 5) {
                    Man man = new Man();
                    man.setManX(j);
                    man.setManY(i);
                    man.setMap(map);
                    man.setData(this);
                    return man;
                }
            }
        }
        return null;
    }

    public Man getMan() {
        return man;
    }

    public void setMan(Man man) {
        this.man = man;
    }

    public void loadMap(int level) {
        FileReader fr = null;
        BufferedReader reader = null;
        initMap();
        try {
            fr = new FileReader(MapData.class.getResource("/maps/" + level + ".map").getPath());
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

    public boolean nextLevel() {
        if (level == MAX_LEVEL) {
            return false;
        }
        level ++;
        loadMap(level);
        man = getManFromMap();
        return true;
    }


    public void initBoxes() {
        boxList.clear();
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (map[i][j] == 3) {
                    Box box = new Box(j, i, map);
                    box.setContains(2);
                    boxList.add(box);
                }
                if (map[i][j] == 9) {
                    Box box = new Box(j, i, map);
                    box.setContains(4);
                    boxList.add(box);
                }
            }
        }
    }

    public Box getBoxByDirection(Direction d) {
        int x = man.getManX();
        int y = man.getManY();
        System.out.println("man x = " + x + " y= " + y);
        switch(d) {
            case Right:
                x++;
                break;
            case Left:
                x--;
                break;
            case Down:
                y++;
                break;
            case Up:
                y--;
                break;
            default:
                break;
        }
        return findBox(x, y);
    }

    public Box findBox(int x, int y) {
        System.out.println("x = " + x + " y= " + y);
        // showMymap();
        for (Box b : boxList) {
            if (b.match(x, y)) {
                return b;
            }
        }
        return null;
    }

    public boolean isWin() {
        boolean winFlag = true;
        for (Box b : boxList) {
            if (b.isOk() == false) {
                winFlag = false;
                return winFlag;
            }
        }
        return winFlag;
    }

    private void initMap() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < WIDTH; j++) {
                map[i][j] = 0;
            }
        }
    }
}
