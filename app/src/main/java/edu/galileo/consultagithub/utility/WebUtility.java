package edu.galileo.consultagithub.utility;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import edu.galileo.consultagithub.RepositorioActivity;
import edu.galileo.consultagithub.WebApplication;
import edu.galileo.consultagithub.data.Repositorio;

public class WebUtility {


    public static void getRepositorios(String usuario, final Context context) {
        String url = "https://api.github.com/users/" + usuario + "/repos";
        StringRequest solicitud = new StringRequest(Request.Method.GET, url, new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                List<Repositorio> lista;
                //Toast.makeText(context, (String) response, Toast.LENGTH_LONG).show();
                lista = getListFromJson((String) response);
                ((RepositorioActivity)context).cargaDatos(lista);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ((RepositorioActivity) context).finish();
                Toast.makeText(context, "Error en la comunicacion", Toast.LENGTH_LONG).show();
            }
        });
        ((WebApplication) context.getApplicationContext()).getQueue().add(solicitud);
    }

    public static List<Repositorio> getListFromJson(String json) {
        List<Repositorio> list = null;
        Repositorio item;
        try {
            JSONArray array = new JSONArray(json);
            if (array.length() > 0) {
                list = new ArrayList<Repositorio>();
                for (int i = 0; i < array.length(); i ++) {
                    item = new Repositorio();
                    item.setFechaCreacion(array.getJSONObject(i).getString("created_at"));
                    item.setNombreDueno(array.getJSONObject(i).getJSONObject("owner").getString("login"));
                    item.setNombreRepositorio(array.getJSONObject(i).getString("name"));
                    item.setUrlAvatar(array.getJSONObject(i).getJSONObject("owner").getString("avatar_url"));
                    item.setUrlRepositorio(array.getJSONObject(i).getString("html_url"));
                    list.add(item);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
