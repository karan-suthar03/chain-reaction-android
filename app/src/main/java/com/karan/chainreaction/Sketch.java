package com.karan.chainreaction;

import android.util.Log;

import processing.core.PApplet;

public class Sketch extends PApplet {

    int widths;
    int heights;
    private Integer cols;
    private MData[][] matrix;
    private Golas[][] dBalls;
    private Integer rows;
    Integer gap;

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
        heights = gap*rows;
        Log.i("ok",gap*cols+","+width+","+heights+","+height);
        matrix = new MData[cols][rows];
        dBalls = new Golas[cols][rows];
        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < cols; i++) {
                matrix[i][j] = new MData();
                dBalls[i][j] = new Golas(i,j);
            }
        }
        frameRate(120);
    }

    @Override
    public void draw() {
        lights();
        background(0);
        camera((float) width /2, (float) heights /2, (float) (widths*1.75), (float) width /2, (float) heights /2, 0, 0, 1, 0);
        drawLines();
        drawBalls();
    }

    private void drawBalls() {
        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < cols; i++) {
                dBalls[i][j].show(matrix[i][j].balls,this);
            }
        }
    }

    private void drawLines() {
        stroke(255);
        for (int j = 0; j <= rows; j++) {
            for (int i = 0; i <= cols; i++) {
                line(i * gap,0, i * gap, (gap * rows));
                line(i * gap,0,-gap, i * gap, (gap * rows),-gap);
                line(0,j * gap, gap*cols, j*gap);
                line(0,j * gap,-gap, gap*cols, j*gap,-gap);
                line(i*gap,j*gap,0,i*gap,j*gap,-gap);
            }
        }
    }

    @Override
    public void mousePressed() {
        Log.i("hey",frameRate+" ");
        int i = floor((float)mouseX/gap);
        int j = floor((float)(mouseY-((height-heights)/2))/gap);
        if((i>=0 && i< cols)&&(j>=0 && j< rows)){
            matrix[i][j].balls++;
        }
    }

}