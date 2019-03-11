package org.ieselmanias.pdmd.minesweeper;

class Singleton {
    private static final Singleton ourInstance = new Singleton();



    private int numRows,numCols;
    private int numBombs;
    static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {
        numRows=8;
        numCols=8;
        numBombs=10;
    }
    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }

    public int getNumBombs() {
        return numBombs;
    }

    public void setNumBombs(int numBombs) {
        this.numBombs = numBombs;
    }
}
