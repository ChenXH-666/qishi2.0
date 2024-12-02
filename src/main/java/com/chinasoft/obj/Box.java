package com.chinasoft.obj;

import com.chinasoft.enumdef.Direction;

public class Box {
    private int x;
    private int y;
    private int[][] map;
    private int contains = 2;

    public Box(int x, int y, int[][] map) {
        this.x = x;
        this.y = y;
        this.map = map;
    }

    public boolean match(int x, int y) {
        if (this.x == x && this.y == y) {
            return true;
        } else {
            return false;
        }

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public int getContains() {
        return contains;
    }

    public void setContains(int contains) {
        this.contains = contains;
    }

    public void move(Direction d) {
        switch(d) {
            case Right:
                map[y][x] = contains;
                contains = map[y][x + 1];
                x++;
                break;
            case Left:
                map[y][x] = contains;
                contains = map[y][x - 1];
                x--;
                break;
            case Down:
                map[y][x] = contains;
                contains = map[y + 1][x];
                y++;
                break;
            case Up:
                map[y][x] = contains;
                contains = map[y - 1][x];
                y--;
                break;
            default:
                System.out.println("unkown move in box");
                break;
        }
        if (isOk()) {
            map[y][x] = 9;
        } else {
            map[y][x] = 3;
        }
    }

    public boolean isOk() {
        // 如果箱子里面 有金子， 返回  ok
        if (contains == 4) {
            return true;
        }
        return false;
    }
}
