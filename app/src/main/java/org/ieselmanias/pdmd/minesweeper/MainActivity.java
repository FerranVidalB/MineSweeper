package org.ieselmanias.pdmd.minesweeper;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int rows;
    private int cols;
    BombMatrix bombMatrix;
    private MineButton[][] buttons;
    private boolean gameOver=false;
    int[][] bombsMatrix;
    TextView tvWin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button bRestart=findViewById(R.id.bRestart);
        bRestart.setBackgroundResource(R.drawable.smiley);
        bRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restart();

            }
        });
        tvWin = findViewById(R.id.tvWin);
        initGame();

    }

    private void restart() {
        GridLayout grid = findViewById(R.id.gridGame);
        grid.removeAllViews();
        buttons=null;
        bombMatrix=null;
        bombsMatrix=null;
        gameOver=false;
        initGame();
    }

    private void initGame() {
        tvWin.setText("Hola");
        rows = Singleton.getInstance().getNumRows();
        cols = Singleton.getInstance().getNumCols();
        buttons= new MineButton[rows][cols];
        bombMatrix = new BombMatrix();
        generateButtons();
    }

    private void generateButtons() {
        GridLayout grid = findViewById(R.id.gridGame);
        grid.setRowCount(rows);
        grid.setColumnCount(cols);
        bombsMatrix = bombMatrix.getMatrix();
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                FrameLayout frameLayout = new FrameLayout(this);
                ImageView imageView = new ImageView(this);

                if(bombsMatrix[y][x] != -1) {
                    imageView.setImageResource(R.drawable.back);
                }else{
                    imageView.setImageResource(R.drawable.backwithbomb);
                }
                frameLayout.addView(imageView);






                TextView number = new TextView(this);
                number.setTextSize(number.getTextSize());
                if (bombsMatrix[y][x] != -1) {
                    if (bombsMatrix[y][x] == 0) {
                        number.setText("");
                    } else {
                        number.setText(bombsMatrix[y][x] + "");
                    }
                } else {
                    number.setText("");
                }
                frameLayout.addView(number);

                MineButton mineButton = new MineButton(this, y, x);
                buttons[y][x]=mineButton;
                frameLayout.addView(mineButton);
                grid.addView(frameLayout);

                mineButton.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if(!gameOver) {
                            MineButton mb = (MineButton) v;
                            switch (mb.getState()) {
                                case CLOSED:
                                    mb.setState(ButtonState.FLAG);
                                    break;
                                case FLAG:
                                    mb.setState(ButtonState.QUESTION);
                                    break;
                                case QUESTION:
                                    mb.setState(ButtonState.OPEN);
                                    break;
                            }
                        }
                        return true;
                    }
                });
                mineButton.setOnClickListener(new View.OnClickListener() {
                    MineButton mb;
                    @Override
                    public void onClick(View v) {
                        if(!gameOver) {
                            mb = (MineButton) v;
                            if (mb.getState().equals(ButtonState.CLOSED)) {
                                mb.setState(ButtonState.OPEN);
                                if (bombMatrix.getMatrix()[mb.getRow()][mb.getCol()] == -1) {
                                    mb.setVisibility(View.INVISIBLE);
                                    gameOver();
                                } else {

                                    if (bombMatrix.getMatrix()[mb.getRow()][mb.getCol()] == 0) {
                                        limpiarRecursivo(mb.getRow(), mb.getCol());
                                    } else {
                                        mb.setVisibility(View.INVISIBLE);
                                    }
                                    if(checkWin()){

                                            tvWin.setText("YOU WIN!");
                                            gameOver();


                                    }
                                }

                            }

                        }
                    }

                    private void limpiarRecursivo(int y, int x) {
                        buttons[y][x].setVisibility(View.INVISIBLE);
                        buttons[y][x].setState(ButtonState.OPEN);
                        if(bombMatrix.getMatrix()[y][x] == 0) {


                            if (y != 0 && x != 0 && buttons[y - 1][x - 1].getVisibility() == View.VISIBLE) {

                                limpiarRecursivo(y - 1, x - 1);
                            }

                            if (y != 0  && buttons[y - 1][x].getVisibility() == View.VISIBLE) {

                                limpiarRecursivo(y - 1, x);
                            }
                            if (y != 0 && x != cols - 1 && buttons[y - 1][x + 1].getVisibility() == View.VISIBLE) {

                                limpiarRecursivo(y - 1, x + 1);
                            }
                            if (x != 0 &&  buttons[y][x - 1].getVisibility() == View.VISIBLE) {

                                limpiarRecursivo(y, x - 1);
                            }
                            if (x != cols - 1  && buttons[y][x + 1].getVisibility() == View.VISIBLE) {

                                limpiarRecursivo(y, x + 1);
                            }
                            if (x != 0 && y != rows - 1 && buttons[y + 1][x - 1].getVisibility() == View.VISIBLE) {

                                limpiarRecursivo(y + 1, x - 1);
                            }
                            if (y != rows - 1 && buttons[y + 1][x].getVisibility() == View.VISIBLE) {

                                limpiarRecursivo(y + 1, x);
                            }
                            if (x != cols - 1 && y != rows - 1  && buttons[y + 1][x + 1].getVisibility() == View.VISIBLE) {

                                limpiarRecursivo(y + 1, x + 1);
                            }
                        }







                    }
                });


            }
        }
    }

    private boolean checkWin() {

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if(bombMatrix.getMatrix()[y][x] != -1 && buttons[y][x].getState()==ButtonState.CLOSED){
                    return false;

                }
            }
        }
        return true;

    }

    private void gameOver() {
        gameOver=true;
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if(bombMatrix.getMatrix()[y][x] == -1){
                    buttons[y][x].setVisibility(View.INVISIBLE);

                }
            }
        }
    }
    @Override
    public boolean
    onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true ;
    }

    @Override
    public boolean
    onOptionsItemSelected(MenuItem item) {
        switch
                (item.getItemId()) {
            case R.id.action_settings:

                lanzarSettings();

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void lanzarSettings() {
        Intent i = new Intent(this, Settings.class);
        startActivity(i);
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        restart();
    }

}
