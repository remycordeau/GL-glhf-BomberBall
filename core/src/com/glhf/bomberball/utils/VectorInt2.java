package com.glhf.bomberball.utils;

public class VectorInt2 {
    public int x;
    public int y;

    public VectorInt2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double dst(int x, int y) {
        final int x_d = x - this.x;
        final int y_d = y - this.y;
        return Math.sqrt(x_d * x_d + y_d * y_d);
    }

    public boolean equals(int x, int y) {
        return this.x==x && this.y==y;
    }
}
