package edu.galileo.consultagithub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import edu.galileo.consultagithub.adapter.RepositorioAdapter;
import edu.galileo.consultagithub.data.Repositorio;
import edu.galileo.consultagithub.utility.WebUtility;

public class RepositorioActivity extends AppCompatActivity {
    private ListView lista;
    private TextView mensaje;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repositorio_layout);
        lista = findViewById(R.id.lstRepositorio);
        mensaje = findViewById(R.id.txtMensaje);
        WebUtility.getRepositorios(getIntent().getStringExtra("User"), this);
        //lista.setAdapter(new RepositorioAdapter(this, repos));
    }

    public void cargaDatos(List<Repositorio> list) {
        if (list != null) {
            lista.setAdapter(new RepositorioAdapter(this, list));
            lista.setVisibility(View.VISIBLE);
            mensaje.setVisibility(View.GONE);
        } else {
            lista.setVisibility(View.GONE);
            mensaje.setVisibility(View.VISIBLE);
        }
    }
}
