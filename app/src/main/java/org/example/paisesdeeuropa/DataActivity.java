package org.example.paisesdeeuropa;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DataActivity  extends AppCompatActivity {
    TextView tvPoblacion,tvSuperficie;
    ImageView ivPais;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        Bundle extras = getIntent().getExtras();
        String pais = extras.getString("pais");
        tvPoblacion= (TextView) findViewById(R.id.textViewPoblacion);
        tvSuperficie= (TextView) findViewById(R.id.textViewSuperficie);
        ivPais = (ImageView) findViewById(R.id.imageViewPais);

        switch (pais){
            case "España":
                    tvPoblacion.setText("47.000.000");
                    tvSuperficie.setText("505.990 km²");
                    ivPais.setImageResource(R.drawable.europa_mapa_espana_resaltado_ilustracion_vector_292608_1047);
                break;
            case "Rusia":
                tvPoblacion.setText("145.558.000");
                tvSuperficie.setText("17.098.250 Km2");
                ivPais.setImageResource(R.drawable.rusia_en_el_mapa_de_europa_2b80hey);
                break;
            case "Paises Bajos":
                tvPoblacion.setText("17.193.499");
                tvSuperficie.setText("41.543 km²");
                ivPais.setImageResource(R.drawable.grande_mapa_de_ubicacion_de_los_paises_bajos_en_europa);
                break;
            case"Alemania":
                tvPoblacion.setText("83.237.124");
                tvSuperficie.setText("357.590 Km2");
                ivPais.setImageResource(R.drawable._8855462_un_mapa_abstracto_de_europa_con_el_pa_s_de_alemania_en_los_pa_ses_europeos_rojo_y_otros_en_blanco);
                break;
            case "Inglaterra":
                tvPoblacion.setText("56.286.961");
                tvSuperficie.setText("130.395 km²");
                ivPais.setImageResource(R.drawable.grayscale_silhouette_with_europe_map_and_england_in_red_color_vector);
                break;
            default:
                try {
                    throw new Exception("Unknown pais");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
