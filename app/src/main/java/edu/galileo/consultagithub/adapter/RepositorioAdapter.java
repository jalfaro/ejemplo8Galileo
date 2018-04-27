package edu.galileo.consultagithub.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.galileo.consultagithub.R;
import edu.galileo.consultagithub.data.Repositorio;

public class RepositorioAdapter extends ArrayAdapter<Repositorio> {
    private List<Repositorio> list;
    private Context context;

    public RepositorioAdapter(@NonNull Context context,  @NonNull List<Repositorio> objects) {
        super(context, R.layout.row_repositorio_layout, objects);
        this.list = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.row_repositorio_layout, null);
        }
        if (list.get(position) != null) {
            ImageView foto = v.findViewById(R.id.imgOwner);
            ImageView lupita = v.findViewById(R.id.img_web);
            TextView nombreRepo = v.findViewById(R.id.nombre_repo);
            TextView nombreDueno = v.findViewById(R.id.nombre_dueno);
            TextView fecha = v.findViewById(R.id.fecha_repo);
            nombreRepo.setText(list.get(position).getNombreRepositorio());
            nombreDueno.setText(list.get(position).getNombreDueno());
            fecha.setText(list.get(position).getFechaCreacion());
            Picasso.get().load(list.get(position).getUrlAvatar()).into(foto);
            lupita.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(list.get(position).getUrlRepositorio()));
                    context.startActivity(intent);
                }
            });
        }

        return v;
    }
}
