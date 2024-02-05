package com.karan.chainreaction;

public class Golas{
    int i,j;
    public Golas(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public void show(int balls, Sketch sketch) {
        if(balls == 1){
            sketch.pushMatrix();
            sketch.translate(i*sketch.gap+((float) sketch.gap /2),j*sketch.gap+((float) sketch.gap /2), (float) -sketch.gap /2);
            sketch.fill(255,0,0);
            sketch.noStroke();
            sketch.sphereDetail(8);
            sketch.sphere((float) sketch.gap /4);
            sketch.popMatrix();
        }else if(balls>1){
            sketch.pushMatrix();
            sketch.translate(i*sketch.gap+((float) sketch.gap /2),j*sketch.gap+((float) sketch.gap /2), (float) -sketch.gap /2);
            sketch.fill(255,0,0);
            sketch.noStroke();
            sketch.sphereDetail(7);
            for(int i = 0;i<=balls;i++){
                sketch.pushMatrix();
                sketch.rotateZ(sketch.PI*2/balls*i);
                sketch.translate((float) (sketch.gap /6),0);
                sketch.sphere((float) ( sketch.gap /4));
                sketch.popMatrix();
            }
            sketch.popMatrix();
        }
    }
}
