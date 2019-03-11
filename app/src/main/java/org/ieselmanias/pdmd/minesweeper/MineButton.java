package org.ieselmanias.pdmd.minesweeper;

import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;


public class MineButton extends android.support.v7.widget.AppCompatImageButton {


    public static final int WIDTH=45;
    public static final int HEIGHT =45;


    private ButtonState state;
    private   int col;
    private   int row;

    public MineButton(Context context,  int r, int c) {
        super(context);

        this.col=c;
        this.row=r;
        state=ButtonState.CLOSED;
        final float scale= getContext().getResources().getDisplayMetrics().density;
        int width = (int)(WIDTH*scale);
        int height = (int)(HEIGHT*scale);
        setAlpha(0.5f);

        setLayoutParams(new FrameLayout.LayoutParams(width,height));
        setBackgroundDrawable(getResources().getDrawable(R.drawable.boton));




        setOnTouchListener(new OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()== MotionEvent.ACTION_DOWN) {
                    setBackgroundDrawable(getResources().getDrawable(R.drawable.boton_pressed));
                }else{
                    setBackgroundDrawable(getResources().getDrawable(R.drawable.boton));
                }
                return false;
            }

        });
    }
    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public ButtonState getState() {
        return state;
    }


    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
    public void setState(ButtonState state) {
        this.state = state;
        switch (state) {
            case FLAG:
                setImageDrawable(getResources().getDrawable(R.drawable.flag));
                setPadding(5,5,5,5);
                setScaleType(ScaleType.FIT_XY);
                break;
            case QUESTION:
                setImageDrawable(getResources().getDrawable(R.drawable.question));
                setPadding(5,5,5,5);
                setScaleType(ScaleType.FIT_XY);
                break;
            default:
                setImageBitmap(null);
        }
    }

}
