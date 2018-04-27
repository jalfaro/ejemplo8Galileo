package edu.galileo.consultagithub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtUsuario;
    private Button btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        txtUsuario = findViewById(R.id.txtUser);
        btnBuscar = findViewById(R.id.btnBusqueda);

        btnBuscar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnBusqueda) {
            Intent intent = new Intent(this, RepositorioActivity.class);
            intent.putExtra("User", txtUsuario.getText().toString());
            startActivity(intent);

        }
    }
}
