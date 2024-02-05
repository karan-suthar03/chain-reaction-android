package com.karan.chainreaction.Logic;

import com.karan.chainreaction.Sketch;

public class Moves {
    int i;
    int j;
    int player;
    String dir;
    Sketch sketch;
    int[] position;
    public Moves(int i, int j, int player, String dir,Sketch sketch) {
        this.i = i;
        this.j = j;
        this.position = new int[]{sketch.gap / 2 + (i * sketch.gap), sketch.gap / 2 + (j * sketch.gap)};
        this.dir = dir;
        this.player = player;
        this.sketch = sketch;
    }
    boolean update(){
        switch (this.dir){
            case "up":
                return up();
            case "down":
                return down();
            case "left":
                return left();
            case "right":
                return right();
        }
        return false;
    }
    boolean up(){
        show();
        if (sketch.gap/2 + (this.j * sketch.gap) - this.position[1] > sketch.gap) {
            return true;
        } else {
            this.position[1] -= sketch.gap/25;
            return false;
        }
    }

    boolean down(){
        show();
        if (sketch.gap/2 + (this.j * sketch.gap) - this.position[1] < -sketch.gap) {
            return true;
        } else {
            this.position[1] += sketch.gap/25;
            return false;
        }
    }
    boolean left(){
        show();
        if (sketch.gap/2 + (this.i * sketch.gap) - this.position[0] > sketch.gap) {
            return true;
        } else {
            this.position[0] -= sketch.gap/25;
            return false;
        }
    }
    boolean right(){
        show();
        if (sketch.gap/2 + (this.i * sketch.gap) - this.position[0] < -sketch.gap) {
            return true;
        } else {
            this.position[0] += sketch.gap/25;
            return false;
        }
    }
    void show(){
        sketch.pushMatrix();
        sketch.translate(position[0],position[1], (float) -sketch.gap /2);
        sketch.fill(255,0,0);
        sketch.noStroke();
        sketch.sphereDetail(8);
        sketch.sphere((float) sketch.gap /4);
        sketch.popMatrix();
    }
}
