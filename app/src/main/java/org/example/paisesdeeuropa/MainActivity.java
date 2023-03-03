package org.example.paisesdeeuropa;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private CountryDatabaseHelper dbHelper;
    private Spinner countrySpinner;
    private Button showDetailsButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new CountryDatabaseHelper(this);

        countrySpinner = findViewById(R.id.spinner);
        showDetailsButton = findViewById(R.id.bnDetalle);
        dbHelper.insertCountry("ALEMANIA", R.drawable.alemania, "BERLIN", 83200000, 357.588, "EUROPA");
        dbHelper.insertCountry("AUSTRIA", R.drawable.austria, "VIENA", 8956000, 83.871, "EUROPA");
        dbHelper.insertCountry("AZERBAYAN", R.drawable.azerbayan, "BAKÚ", 10140000, 87, "EUROPA");
        dbHelper.insertCountry("BELGICA", R.drawable.bruselas, "BRUSELAS", 11590000, 0, "EUROPA"); // If the population is not available, you can insert 0
        dbHelper.insertCountry("CROACIA", R.drawable.croacia, "ZAGREB", 3899000, 56.594, "EUROPA");
        dbHelper.insertCountry("DINAMARCA", R.drawable.dinamarca, "COPENHAGUE", 5857000, 43.000, "EUROPA");
        dbHelper.insertCountry("ESPAÑA", R.drawable.espa_a, "MADRID", 47420000, 505.990, "EUROPA");


        setSpinner();

        showDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pais selectedCountry = (Pais) countrySpinner.getSelectedItem();
                Intent intent = new Intent(MainActivity.this, DatosActivity.class);
                intent.putExtra(DatosActivity.EXTRA_COUNTRY_NAME, selectedCountry.getName());
                startActivity(intent);
            }
        });

    }

    public void setSpinner() {
        List<Pais> countries = dbHelper.getCountries();

        ArrayAdapter<Pais> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_country) {
            //intent
            Intent intent = new Intent(getApplicationContext(),Add.class);
            startActivityForResult(intent,1);
            return true;
        } else if (id == R.id.action_edit_country) {
            Intent intent = new Intent(getApplicationContext(),Add.class);
            startActivityForResult(intent,1);
            return true;
        } else if (id == R.id.action_delete_country) {
            if(countrySpinner.getSelectedItem()!=null) {
                deletePais();
            }else{
                System.out.println("null");
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deletePais() {
        // Get the selected country from the spinner
        final Pais country = (Pais) countrySpinner.getSelectedItem();

        // Build the dialog to confirm deletion
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Country");
        builder.setMessage("Are you sure you want to delete " + country.getName() + "?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Delete the country from the database
                dbHelper.deleteCountry(country.getName());

                // Remove the country from the spinner
                ArrayAdapter<Pais> adapter = (ArrayAdapter<Pais>) countrySpinner.getAdapter();
                adapter.remove(country);
            }
        });
        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                setSpinner();
            }
        }
    }



}