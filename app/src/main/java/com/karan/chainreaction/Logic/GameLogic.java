package com.karan.chainreaction.Logic;

import com.karan.chainreaction.MData;
import com.karan.chainreaction.Sketch;

import java.util.ArrayList;

public class GameLogic {
    static ArrayList<Moves> moves = new ArrayList<>();
    static int rows;
    public static boolean hoy = false;

    static MData[][] dupMatrix;
    static int cols;
    static Sketch sketch;
    public GameLogic(int rows, int cols, Sketch sketch){
        GameLogic.rows = rows;
        GameLogic.cols = cols;
        GameLogic.sketch = sketch;
    }

    public static void draw() {
        if(showMoves() && hoy){
            sketch.matrix = dupMatrix;
            evel();
        }
    }
    static boolean showMoves() {
        if(moves.isEmpty()){
            return true;
        }else{
            moves.removeIf(Moves::update);
            return false;
        }
    }

    static int critical(int x, int y){
        if((x == 0 && y == 0) || (x == rows-1 && y == cols-1) || (x == 0 && y == cols-1) || (x == rows-1 && y == 0)){
            return 2;
        }
        if(x == 0 || y == 0 || x == rows-1 || y == cols-1){
            return 3;
        }
        return 4;
    }
    static MData[][] dupArr(MData[][] matrix){
        MData[][] dup = new MData[matrix.length][matrix[1].length];
        for(int i = 0;i<cols;i++){
            for(int j = 0;j<rows;j++){
                dup[i][j] = new MData(matrix[i][j].balls,matrix[i][j].player);
            }
        }
        return dup;
    }
    public static void evel() {
        dupMatrix = dupArr(sketch.matrix);
        hoy = false;
        for(int i = 0;i<dupMatrix.length;i++){
            for(int j = 0;j<dupMatrix[i].length;j++){
                if(critical(j,i)<=sketch.matrix[i][j].balls){
                    hoy = true;
                    if (i - 1 >= 0) {
                        moves.add(new Moves(i,j,dupMatrix[i][j].player,"left",sketch));
                        dupMatrix[i][j].balls--;
                        sketch.matrix[i][j].balls--;
                        dupMatrix[i - 1][j].balls++;
                        dupMatrix[i - 1][j].player = sketch.matrix[i][j].player;
                    }
                    if (j - 1 >= 0) {
                        moves.add(new Moves(i,j,dupMatrix[i][j].player,"up",sketch));
                        dupMatrix[i][j].balls--;
                        sketch.matrix[i][j].balls--;
                        dupMatrix[i][j - 1].balls++;
                        dupMatrix[i][j - 1].player = sketch.matrix[i][j].player;
                    }
                    if (i + 1 < sketch.matrix.length) {
                        moves.add(new Moves(i,j,dupMatrix[i][j].player,"right",sketch));
                        dupMatrix[i][j].balls--;
                        sketch.matrix[i][j].balls--;
                        dupMatrix[i  + 1][j].balls++;
                        dupMatrix[i  + 1][j].player = sketch.matrix[i][j].player;
                    }
                    if (j + 1 < sketch.matrix[i].length) {
                        moves.add(new Moves(i,j,dupMatrix[i][j].player,"down",sketch));
                        dupMatrix[i][j].balls--;
                        sketch.matrix[i][j].balls--;
                        dupMatrix[i][j + 1].balls++;
                        dupMatrix[i][j + 1].player = sketch.matrix[i][j].player;
                    }
                }
            }
        }
    }
}
