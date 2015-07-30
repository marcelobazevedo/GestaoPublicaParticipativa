package br.com.labsi.gestaopublicaparticipativa;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Marcelo on 30/07/2015.
 */
public class UsuarioJSONParser {
    /** Recebe um JSONObject e retorna uma lista */

    public List<HashMap<String,String>> parse(JSONObject jObject){

        JSONArray jUsuarios = null;
        try {
            /** Recebe todos os elemnto do array usuario */
            jUsuarios = jObject.getJSONArray("usuario");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        /** Invoking getMarkers with the array of json object
         * where each json object represent a marker
         */
        return getUsuarios(jUsuarios);
    }


    private List<HashMap<String, String>> getUsuarios(JSONArray jUsuarios){
        int usuariosCount = jUsuarios.length();
        //int markersCount = 1;
        List<HashMap<String, String>> usuariosList = new ArrayList<HashMap<String,String>>();
        HashMap<String, String> usuario = null;

        /** Taking each marker, parses and adds to list object */
        for(int i=0; i<usuariosCount;i++){
            try {
                /** Call getMarker with marker JSON object to parse the marker */
                usuario = getUsuario((JSONObject) jUsuarios.get(i));
                usuariosList.add(usuario);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }

        return usuariosList;
    }

    /** Parsing the Marker JSON object */
    private HashMap<String, String> getUsuario(JSONObject jUsuario){

        HashMap<String, String> usuario = new HashMap<String, String>();
        String idUsuario = "";
        String email ="";
        String nomeUsuario = "";
        String nome ="";
        String sexo = "";




        try {
            // Extracting latitude, if available
            if(!jUsuario.isNull("idUsuario")){
                idUsuario = jUsuario.getString("idUsuario");
            }

            // Extracting longitude, if available
            if(!jUsuario.isNull("email")){
                email = jUsuario.getString("email");
            }
            // Extracting longitude, if available
            if(!jUsuario.isNull("nomeUsuario")){
                nomeUsuario = jUsuario.getString("nomeUsuario");
            }
            // Extracting longitude, if available
            if(!jUsuario.isNull("nome")){
                nome = jUsuario.getString("nome");
            }
            // Extracting longitude, if available
            if(!jUsuario.isNull("sexo")){
                sexo = jUsuario.getString("sexo");
            }

            usuario.put("idUsuario", idUsuario);
            usuario.put("email", email);
            usuario.put("nomeUsuario", nomeUsuario);
            usuario.put("nome", nome);
            usuario.put("sexo", sexo);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return usuario;
    }
}
