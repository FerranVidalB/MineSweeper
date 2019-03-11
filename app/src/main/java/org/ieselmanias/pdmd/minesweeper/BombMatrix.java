package org.ieselmanias.pdmd.minesweeper;

import java.util.Random;

public class BombMatrix {

    private int[][] matrix;
    private int rows;
    private int cols;
    private int nBombs;

    public BombMatrix() {

        rows = Singleton.getInstance().getNumRows();
        cols = Singleton.getInstance().getNumCols();
        nBombs = Singleton.getInstance().getNumBombs();
        matrix = new int[rows][cols];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                matrix[y][x] = 0;
            }
        }
        generateBombs();
    }

    private void generateBombs() {


        int i = 0;
        while (i < nBombs) {
            Random r = new Random();
            int rowRand = r.nextInt(rows - 0);
            int colRand = r.nextInt(cols - 0);
            if (matrix[rowRand][colRand] != -1) {
                matrix[rowRand][colRand] = -1;
                i++;
            }

        }
        fillNearCells();
    }

    private void fillNearCells() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {

                if (matrix[y][x] != -1) {

                    matrix[y][x] =  y!=0    && x!=0  &&     matrix[y - 1][x - 1] == -1  ? matrix[y][x] + 1 : matrix[y][x];
                    matrix[y][x] =  y!=0    &&              matrix[y - 1][x] == -1      ? matrix[y][x] + 1 : matrix[y][x];
                    matrix[y][x] =  y!=0    && x!=cols-1 && matrix[y - 1][x + 1] == -1  ? matrix[y][x] + 1 : matrix[y][x];
                    matrix[y][x] = x!=0                         && matrix[y][x - 1] == -1     ? matrix[y][x] + 1 : matrix[y][x];
                    matrix[y][x] = x!=cols-1    && matrix[y][x + 1] == -1                     ? matrix[y][x] + 1 : matrix[y][x];
                    matrix[y][x] = x!=0         && y!=rows-1    && matrix[y + 1][x - 1] == -1 ? matrix[y][x] + 1 : matrix[y][x];
                    matrix[y][x] = y!=rows-1                    && matrix[y + 1][x] == -1     ? matrix[y][x] + 1 : matrix[y][x];
                    matrix[y][x] = x!=cols-1    && y!=rows-1    && matrix[y + 1][x + 1] == -1 ? matrix[y][x] + 1 : matrix[y][x];
                }


            }
        }
    }

    public int[][] getMatrix() {
        return matrix;
    }

}
