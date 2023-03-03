package org.example.paisesdeeuropa;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DatosActivity extends AppCompatActivity {
    public static final String EXTRA_COUNTRY_NAME = "com.example.countryapp.COUNTRY_NAME";

    private TextView nameTextView;
    private ImageView flagImageView;
    private TextView capitalTextView;
    private TextView populationTextView;
    private TextView surfaceTextView;
    private TextView continentTextView;

    private CountryDatabaseHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        dbHelper = new CountryDatabaseHelper(this);

        nameTextView = findViewById(R.id.ciudad);
        flagImageView = findViewById(R.id.flag);
        capitalTextView = findViewById(R.id.capital);
        populationTextView = findViewById(R.id.ciudadanos);
        surfaceTextView = findViewById(R.id.superficie);
        continentTextView = findViewById(R.id.continente);

        Intent intent = getIntent();
        String countryName = intent.getStringExtra(EXTRA_COUNTRY_NAME);

        Pais country = dbHelper.getCountryByName(countryName);

        if (country != null) {
            nameTextView.setText(country.getName());
            flagImageView.setImageResource(country.getFlagResourceId());
            capitalTextView.setText("Capital: " + country.getCapital());
            populationTextView.setText("Population: " + country.getPopulation());
            surfaceTextView.setText("Surface: " + country.getSurface() + " km²");
            continentTextView.setText("Continent: " + country.getContinent());
        }
    }
}
