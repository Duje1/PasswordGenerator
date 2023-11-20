package com.duje.passwordgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {


    private EditText etLength;
    private Button btnGenerate;
    private TextView tvResult;

    //Brojevi 48 - 57
    //Velika Slova 65 - 90

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etLength = findViewById(R.id.etLength);
        btnGenerate = findViewById(R.id.btnGenerate);
        tvResult = findViewById(R.id.tvResult);

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(Integer.parseInt(etLength.getText().toString())<=0 || Integer.parseInt(etLength.getText().toString())>1000){
                        tvResult.setText(R.string.exceptionErr);
                    }else{
                        tvResult.setText(GenerateCharacters().toString());
                    }
                } catch (Exception e)
                {
                    tvResult.setText(R.string.exceptionErr);
                }
            }
        });

    }

    public int getRandomNumber(int min, int max) {
        int randNum = (int) ((Math.random() * (max - min)) + min);
        //Izbaci medjuznakove
        while((randNum >90) && (randNum < 97))
        {
            randNum = (int) ((Math.random() * (max - min)) + min);
        }
        return randNum;
    }

    private String GenerateCharacters() {
        String password = "";
        char asciiValue;
        for (int i = 0; i < Integer.parseInt(etLength.getText().toString()); i++)
        {
            if(Math.random() > 0.5)
                asciiValue = (char) getRandomNumber(65,122);//Velika slova
            else
                asciiValue = (char) getRandomNumber(48,57);//Brojevi
            password += Character.toString(asciiValue);
        }
        return password;
    }
}