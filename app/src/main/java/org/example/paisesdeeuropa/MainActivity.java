package org.example.paisesdeeuropa;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button bEsp,bRus,bPb,bAl,bIng;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bEsp = (Button) findViewById(R.id.buttonEspaña);
        bRus = (Button) findViewById(R.id.buttonRusia);
        bPb = (Button) findViewById(R.id.buttonPaisesBajos);
        bAl = (Button) findViewById(R.id.buttonAlemania);
        bIng = (Button) findViewById(R.id.buttonInglaterra);

        bEsp.setOnClickListener(this);
        bRus.setOnClickListener(this);
        bPb.setOnClickListener(this);
        bAl.setOnClickListener(this);
        bIng.setOnClickListener(this);


    }
    @Override
    public void onClick(View view){
        Intent intent;
        switch (view.getId()){

            case R.id.buttonEspaña:
                intent = new Intent(getApplicationContext(),DataActivity.class);
                intent.putExtra("pais","España");
                startActivity(intent);
                break;
            case R.id.buttonRusia:
                intent = new Intent(getApplicationContext(),DataActivity.class);
                intent.putExtra("pais","Rusia");
                startActivity(intent);
                break;
            case R.id.buttonPaisesBajos:
                intent = new Intent(getApplicationContext(),DataActivity.class);
                intent.putExtra("pais","Paises Bajos");
                startActivity(intent);
                break;
            case R.id.buttonAlemania:
                intent = new Intent(getApplicationContext(),DataActivity.class);
                intent.putExtra("pais","Alemania");
                startActivity(intent);
                break;
            case R.id.buttonInglaterra:
                intent = new Intent(getApplicationContext(),DataActivity.class);
                intent.putExtra("pais","Inglaterra");
                startActivity(intent);
                break;
            default:
                try {
                    throw new Exception("Unknown click");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}