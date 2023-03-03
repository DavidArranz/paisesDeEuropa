package org.example.paisesdeeuropa;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Edit extends AppCompatActivity {

    private EditText nameEditText, capitalEditText, populationEditText, surfaceAreaEditText, continentEditText;
    private Button saveButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        nameEditText = findViewById(R.id.edit_text_name);
        capitalEditText = findViewById(R.id.edit_text_capital);
        populationEditText = findViewById(R.id.edit_text_population);
        surfaceAreaEditText = findViewById(R.id.edit_text_surface_area);
        continentEditText = findViewById(R.id.edit_text_continent);
        saveButton = findViewById(R.id.button_save);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String capital = capitalEditText.getText().toString();
                int population = Integer.parseInt(populationEditText.getText().toString());
                double surfaceArea = Double.parseDouble(surfaceAreaEditText.getText().toString());
                String continent = continentEditText.getText().toString();

                CountryDatabaseHelper dbHelper = new CountryDatabaseHelper(getApplicationContext());

                // insert the data into the database
                dbHelper.insertCountry(name, 0, capital, population, surfaceArea, continent);

                dbHelper.close();
                Intent resultIntent = new Intent();
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
