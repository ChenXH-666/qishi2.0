package com.chinasoft.obj;

import com.chinasoft.enumdef.Direction;
import com.chinasoft.enumdef.Way;

public class Man {
    private int manX;
    private int manY;
    private int[][] map;
    private int manBk = 2;

    public MapData getData() {
        return data;
    }

    public void setData(MapData data) {
        this.data = data;
    }

    MapData data;



    public int getManX() {
        return manX;
    }
    public void setManX(int manX) {
        this.manX = manX;
    }
    public int getManY() {
        return manY;
    }
    public void setManY(int manY) {
        this.manY = manY;
    }
    public int[][] getMap() {
        return map;
    }
    public void setMap(int[][] map) {
        this.map = map;
    }
    public int getManBk() {
        return manBk;
    }
    public void setManBk(int manBk) {
        this.manBk = manBk;
    }

    public void move(Direction d) {
        Way way = pushOrMove(d);
        System.out.println("way is " + way);
        if (way == Way.Move) {
            if(canMove(d)) {
                realMove(d);
            }
        } else {
            if(canPush(d)) {
                realPush(d);
            }
        }
    }

    private boolean canMove(Direction d) {
        Integer obj = getDirectObj(d, 1);
        if (obj == 1 || obj == null) {
            return false;
        } else {
            return true;
        }
    }

    private boolean canPush(Direction d) {
        Integer obj = getDirectObj(d, 2);
        if (obj == null) {
            return false;
        }
        if (obj == 2 || obj == 4) {
            return true;
        } else {
            return false;
        }
    }


    private void realPush(Direction d) {
        if (canPush(d)) {
            Box box = data.getBoxByDirection(d);
            box.move(d);
            this.realMove(d);
        }
    }

    private Way pushOrMove(Direction dire) {
        Integer obj = getDirectObj(dire, 1);
        //  3 是箱子， 9 就有金子的箱子
        if (obj == 3 || obj == 9) {
            return Way.Push;
        } else {
            return Way.Move;
        }
    }

    public Integer getDirectObj(Direction d, int num) {
        int x = manX;
        int y = manY;
        Integer obj = null;

        switch (d) {
            case Right:
                x = x + num;
                if (x >= MapData.WIDTH) {
                    return obj;
                }
                break;
            case Left:
                x = x - num;
                if (x < 0) {
                    return obj;
                }
                break;
            case Down:
                y = y + num;
                if (y >= MapData.WIDTH) {
                    return obj;
                }
                break;
            case Up:
                y = y - num;
                if (y < 0) {
                    return obj;
                }
                break;
            default:
                System.out.println("unknow situation, please debug it");
                break;
        }
        return map[y][x];
    }


    private void realMove(Direction d) {
        map[manY][manX] = manBk;
        switch(d) {
            case Right:
                map[manY][manX] = manBk;
                // 坐标移动
                manX ++;
                // 备份目标位置
                manBk = map[manY][manX];
                // 人移动
                map[manY][manX] = 7;
                break;
            case Up:
                map[manY][manX] = manBk;
                manY --;
                manBk = map[manY][manX];
                map[manY][manX] = 8;
                break;
            case Down:
                map[manY][manX] = manBk;
                manY ++;
                manBk = map[manY][manX];
                map[manY][manX] = 5;
                break;
            case Left:
                map[manY][manX] = manBk;
                manX --;
                manBk = map[manY][manX];
                map[manY][manX] = 6;
                break;
            default:
                System.out.println("unkonw direction in realMove " + d);
                break;
        }
    }



    /*
    public void moveRight() {
        if (manX < 19) {
            // 恢复当前位置
            map[manY][manX] = manBk;
            // 坐标移动
            manX ++;
            // 备份目标位置
            manBk = map[manY][manX];
            // 人移动
            map[manY][manX] = 7;
        }
        System.out.println("man X is " + manY);
    }

    public void moveUp() {
        if(manY > 0) {
            map[manY][manX] = manBk;
            manY --;
            manBk = map[manY][manX];
            map[manY][manX] = 8;
        }
    }

    public void moveDown() {
        if (manY < 19) {
            map[manY][manX] = manBk;
            manY ++;
            manBk = map[manY][manX];
            map[manY][manX] = 5;
        }
        System.out.println("man Y is " + manY);
    }

    public void moveLeft() {
        if(manX > 0) {
            map[manY][manX] = manBk;
            manX --;
            manBk = map[manY][manX];
            map[manY][manX] = 6;
        }
    }
     */
}
