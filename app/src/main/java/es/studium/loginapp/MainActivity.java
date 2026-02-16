package es.studium.loginapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnBorrar;
    private SharedPreferences preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBorrar = findViewById(R.id.btnBorrar);

        // Accedemos al archivo de preferencias
        preferencias = getSharedPreferences("Login Credentials", Context.MODE_PRIVATE);

        // Configuración del botón para borrar credenciales
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Borramos los datos almacenados
                SharedPreferences.Editor editor = preferencias.edit();
                editor.clear(); // Limpia todo el contenido del archivo
                editor.apply();

                // mensaje de confirmación "Datos eliminados"
                Toast.makeText(MainActivity.this, getString(R.string.toast_eliminados), Toast.LENGTH_SHORT).show();

                // Cerramos esta pantalla para regresar al Login automáticamente
                finish();
            }
        });
    }
}