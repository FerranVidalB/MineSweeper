package org.ieselmanias.pdmd.minesweeper;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    int intRows;
    int sc;
    int sr;
    int sb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
         EditText nCols = findViewById(R.id.nCols);

        nCols.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(!s.toString().equals(""))
                        sc = Integer.parseInt(s.toString());


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        EditText nRows = findViewById(R.id.nCols);
        nRows.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().equals(""))
                    sr=Integer.parseInt(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        EditText nBombs = findViewById(R.id.nBombs);
        nBombs.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().equals(""))
                    sb=Integer.parseInt(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Button bAceptar = findViewById(R.id.bAceptar);
        bAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sc>=5 && sc <=50 && sr>=5 && sr <=50 && sb>=5 && sb <=50){
                    Singleton.getInstance().setNumRows(sr);
                    Singleton.getInstance().setNumCols(sc);
                    Singleton.getInstance().setNumBombs(sb);
                    finish();

                }else{
                    TextView textView = findViewById(R.id.textView4);
                    textView.setText("Please enter valid numbers");
                }
            }
        });
        Button bCancelar = findViewById(R.id.bCancellar);
        bCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });
    }
}
