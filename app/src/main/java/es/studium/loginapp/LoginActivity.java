package es.studium.loginapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class LoginActivity extends AppCompatActivity {

    // variables
    private EditText etUsuario;
    private EditText etClave;
    private SwitchCompat swGuardar;
    private Button btnAcceder;
    private SharedPreferences preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 1. Enlazar componentes con el XML
        etUsuario = findViewById(R.id.etUsuario);
        etClave = findViewById(R.id.etClave);
        swGuardar = findViewById(R.id.swGuardar);
        btnAcceder = findViewById(R.id.btnAcceder);

        // 2. Inicializar SharedPreferences
        preferencias = getSharedPreferences("Login Credentials", Context.MODE_PRIVATE);

        // 3. Comprobar si ya hay credenciales guardadas para saltar el login
        if (preferencias.getBoolean("guardado", false)) {
            irAPantallaPrincipal();
        }

        // 4. Configurar el evento click del botón
        btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarLogin();
            }
        });
    }

    private void validarLogin() {
        String usuarioIngresado = etUsuario.getText().toString();
        String claveIngresada = etClave.getText().toString();

        // Credencialess Nombre  y DNI con letra
        String miNombre = "Gonzalo";
        String miDNI = "45999760j";

        if (usuarioIngresado.equals(miNombre) && claveIngresada.equals(miDNI)) {
            // Si el switch está activo, guardamos la sesión
            if (swGuardar.isChecked()) {
                SharedPreferences.Editor editor = preferencias.edit();
                editor.putBoolean("guardado", true);
                editor.putString("usuario", usuarioIngresado);
                editor.apply();
            }
            irAPantallaPrincipal();
        } else {
            // Mensaje de error si fallan las credenciales
            Toast.makeText(this, getString(R.string.error_login), Toast.LENGTH_SHORT).show();
        }
    }

    private void irAPantallaPrincipal() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Finaliza LoginActivity para que no se pueda volver atrás con el botón retroceso
    }
}