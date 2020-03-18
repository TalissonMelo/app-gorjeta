package com.talissonmelo.gorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText valor;
    private TextView porcentagem;
    private  TextView gorjeta;
    private TextView total;
    private SeekBar seekBarGorjeta;

    private double valorPorcentagem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valor = findViewById(R.id.textValor);
        porcentagem = findViewById(R.id.textPorcentagem);
        gorjeta = findViewById(R.id.textGorjeta);
        total = findViewById(R.id.textTotal);
        seekBarGorjeta = findViewById(R.id.see);

        //Adicionar Listener SeekBar
        seekBarGorjeta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                valorPorcentagem = seekBarGorjeta.getProgress();
                porcentagem.setText( Math.round(valorPorcentagem) + " %");
                calcular();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void calcular(){

        String valorRecuperado = valor.getText().toString();
        if(valorRecuperado == null || valorRecuperado.equals("")){
            Toast.makeText(getApplicationContext(),
                    "Digite um valor primeiro!.", Toast.LENGTH_LONG
                    ).show();
        }else{

            //Converter string valorRecuperado para Double
            double valorDigitado = Double.parseDouble(valorRecuperado);

            //Calcular a gorjeta
            double gorjetaTotal = valorDigitado * (valorPorcentagem/100);

            //exibe o valor Gorjeta
            gorjeta.setText("R$ " + String.format("%.2f",gorjetaTotal));

            //exibe o valor Total
            double valorTotal = gorjetaTotal + valorDigitado;
            total.setText("R$ " + String.format("%.2f",valorTotal));

        }

    }
}
