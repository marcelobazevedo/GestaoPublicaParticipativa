package br.com.labsi.gestaopublicaparticipativa;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Marcelo on 21/01/2015.
 */
public class SubTemaJSONParser {
    /** Recebe um JSONObject e retorna uma lista */

    public List<HashMap<String,String>> parse(JSONObject jObject){

        JSONArray jSubtemas = null;
        try {
            /** Recebe todos os elemnto do array Subtema */
            jSubtemas = jObject.getJSONArray("subtemas");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        /** Invoking getMarkers with the array of json object
         * where each json object represent a marker
         */
        return getSubtemas(jSubtemas);
    }


    private List<HashMap<String, String>> getSubtemas(JSONArray jSubtemas){
        int subtemasCount = jSubtemas.length();
        //int markersCount = 1;
        List<HashMap<String, String>> subtemasList = new ArrayList<HashMap<String,String>>();
        HashMap<String, String> subtema = null;

        /** Taking each marker, parses and adds to list object */
        for(int i=0; i<subtemasCount;i++){
            try {
                /** Call getMarker with marker JSON object to parse the marker */
                subtema = getSubtema((JSONObject)jSubtemas.get(i));
                subtemasList.add(subtema);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }

        return subtemasList;
    }

    /** Parsing the Marker JSON object */
    private HashMap<String, String> getSubtema(JSONObject jSubtema){

        HashMap<String, String> subtema = new HashMap<String, String>();
        String idSubtema = "-NA-";
        String nomeSubtema ="-NA-";

        //  String nome="";


        try {
            // Extracting latitude, if available
            if(!jSubtema.isNull("idSubtema")){
                idSubtema = jSubtema.getString("idSubtema");
            }

            // Extracting longitude, if available
            if(!jSubtema.isNull("nomeSubtema")){
                nomeSubtema = jSubtema.getString("nomeSubtema");
            }
/*
            // Extracting longitude, if available
            if(!jMarker.isNull("nomeSubtema")){
                nome = jMarker.getString("nomeSubtema");
            }
*/
            subtema.put("idSubtema", idSubtema);
            subtema.put("nomeSubtema", nomeSubtema);
            //      marker.put("nomeSubtema", nome);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return subtema;
    }
}
