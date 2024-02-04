package com.karan.chainreaction;

import android.util.Log;

import processing.core.PApplet;

public class Sketch extends PApplet {

    private final int widths;
    private final int heights;
    private Integer cols;
    private Integer rows;
    private Float gap2;
    private Integer gap;

    public Sketch(int widths, int heights, Integer rows) {
        this.widths = widths;
        this.heights = heights;
        this.rows = rows;
    }

    @Override
    public void settings() {
        size(this.widths, this.heights, P3D);
    }

    @Override
    public void setup() {
        cols = (int) (rows * 0.6);
        gap = this.width / cols;
        gap2 = (float) ((height-(gap*rows))/2);
        Log.i("ok",widths+","+width+","+heights+","+height);
    }

    @Override
    public void draw() {
        lights();
        background(0);
        translate((float) width /2, (float) height /2);
        camera((float) width /2, (float) height /2, 1893, (float) width /2, (float) height /2, 0, 0, 1, 0);
        drawLines();
        pushMatrix();
        translate(widths/2,heights/2,-gap/2);
        fill(255,0,0);
        noStroke();
        sphereDetail(8);
        sphere(gap/3);
        popMatrix();
    }

    private void drawLines() {
        stroke(255);
        for (int i = 0; i <= cols; i++) {
            line(i * gap,gap2, i * gap, (gap * rows)+gap2);
            line(i * gap,gap2,-gap, i * gap, (gap * rows)+gap2,-gap);
        }
        for (int i = 0; i <= rows; i++) {
            line(0,i * gap+gap2, gap*cols, i*gap+gap2);
            line(0,i * gap+gap2,-gap, gap*cols, i*gap+gap2,-gap);
        }
    }
}