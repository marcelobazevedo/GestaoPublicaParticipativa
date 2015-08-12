package br.com.labsi.gestaopublicaparticipativa;

/**
 * Created by Marcelo on 18/01/2015.
 */

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;




//public class DenunciaAnonimaActivity3 extends FragmentActivity implements EditNameDialog.EditNameDialogListener {
public class DenunciaAnonimaActivity3 extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    GoogleMap mGoogleMap;
    private SupportMapFragment mapFragment;
    private GoogleApiClient mGoogleApiClient;

    public String idUsuario;
    public String idSubTema="";
    public LatLng coordenadas;




    public DenunciaAnonimaActivity3() {
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getActionBar();
       // actionBar.setTitle("GP2M");

             TodaAplicacao todaAplicacao=(TodaAplicacao)getApplicationContext();
             if(todaAplicacao.getIdUsuario()!="")
             {
                 idUsuario=todaAplicacao.getIdUsuario();
             }else{
                 idUsuario="0";
             }

           // Toast toast = Toast.makeText(DenunciaAnonimaActivity3.this, "esperando o usuario "+todaAplicacao.getIdUsuario(), Toast.LENGTH_LONG);
            //toast.show();


        Intent intent = getIntent();
        if(intent!=null){
            Bundle params = intent.getExtras();
            if(params!=null){
                idSubTema = params.getString("codigo");

            }
        }


        // Getting reference to SupportMapFragment
        SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.map, fragment);
        transaction.commit();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();



        // Creating GoogleMap from SupportMapFragment
        OnMapReadyCallback callback = new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mGoogleMap = googleMap;

                googleMap.setMyLocationEnabled(true);
               // location = googleMap.getMyLocation();
               // CameraUpdate position = CameraUpdateFactory.newLatLngZoom(location, 15);



                // Setting OnClickEvent listener for the GoogleMap
                mGoogleMap.setOnMapClickListener(new OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latlng) {
                        addMarker(latlng);
                        coordenadas = latlng;
                        showEditDialog();
                        // sendToServer(latlng); envia para o servidor
                    }
                });

                googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        marker.remove();
                    }
                });
                // Starting locations retrieve task
                //new RetrieveTask().execute();
            }
        };
        fragment.getMapAsync(callback);
    }
    @Override
    public void onMapReady(GoogleMap mGoogleMap) {
       // Log.d(TAG, "onMapReady: " + mGoogleMap);
        this.mGoogleMap = mGoogleMap;

        // Configura o tipo do mapa
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
    @Override
    protected void onStart() {
        super.onStart();
        // Conecta no Google Play Services
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        // Desconecta
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle bundle) {

        Location l = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);

       // Atualiza a localizacao do mapa
        setMapLocation(l);
    }
    @Override
    public void onConnectionSuspended(int cause) {
        Toast toast = Toast.makeText(DenunciaAnonimaActivity3.this, "Conexao Interrompida", Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast toast = Toast.makeText(DenunciaAnonimaActivity3.this, "Erro ao conctar", Toast.LENGTH_LONG);
        toast.show();
    }

    // Atualiza a coordenada do mapa
    private void setMapLocation(Location l) {
        if (mGoogleMap != null && l != null) {
            LatLng latLng = new LatLng(l.getLatitude(), l.getLongitude());
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, 15);
            mGoogleMap.animateCamera(update);

            //Log.d(TAG, "setMapLocation: " + l);
            //TextView text = (TextView) findViewById(R.id.text);
            //text.setText(String.format("Lat/Lnt %f/%f, provider: %s", l.getLatitude(), l.getLongitude(), l.getProvider()));
/*
            // Desenha uma bolinha vermelha
            CircleOptions circle = new CircleOptions().center(latLng);
            circle.fillColor(Color.RED);
            circle.radius(25); // Em metros
            mGoogleMap.clear();
            mGoogleMap.addCircle(circle);
            */
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        int idMenuItem = item.getItemId();
        switch (idMenuItem) {
            case R.id.filtro:
                Intent it = new Intent(DenunciaAnonimaActivity3.this, FiltrarActivity.class);
                startActivity(it);
                // finish();
                break;

            case R.id.denunciar:
                Intent its = new Intent(DenunciaAnonimaActivity3.this, IndexActivity.class);
                startActivity(its);
                // finish();
                break;
        }

        return true;
    }

    // Adicionando o marker no GoogleMap
    private void addMarker(LatLng latlng) {

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latlng);
        markerOptions.title(latlng.latitude + "," + latlng.longitude);
        mGoogleMap.addMarker(markerOptions);

    }

    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        EditNameDialog editNameDialog = new EditNameDialog();
        editNameDialog.show(fm, "fragment_edit_name");
    }


    public void onFinishEditDialog(String inputText) {
        Toast.makeText(this, "Hi, " + inputText, Toast.LENGTH_SHORT).show();
    }

    public void excluirMarker(){
        mGoogleMap.clear();
    }


    //public void sendToServer(LatLng latlng) {
        public void sendToServer(String observacao) {
        String ltd=Double.toString(coordenadas.latitude);
        String lgt=Double.toString(coordenadas.longitude);
        String idusuario = idUsuario;
        String idsubtema=idSubTema;
        String obs=observacao;
        new SaveTask().execute(ltd, lgt, idusuario, idsubtema, obs);
    }

    // Background thread to save the location in remove MySQL server
    private class SaveTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            String lat = params[0];
            String lng = params[1];
            String id = params[2];
            String idstm = params[3];
            String obsv = params[4];

            String strUrl = "http://www.ase-jf.com.br/gpp/save.php";

            URL url = null;
            try {
                url = new URL(strUrl);

                HttpURLConnection connection = (HttpURLConnection) url
                        .openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                        connection.getOutputStream());

                outputStreamWriter.write("lat=" + lat + "&lng=" + lng + "&idusuario=" + id + "&idsubtema=" + idstm + "&obs=" + obsv);
                outputStreamWriter.flush();
                outputStreamWriter.close();

                InputStream iStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(iStream));

                StringBuffer sb = new StringBuffer();

                String line = "";

                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }

                reader.close();
                iStream.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

    }
/////////////////////////////////////////////////////////////////////////////AQUI PRA BAIXO LE OS DADOS E POPULA O MAPA///////////////////////////////////////////////////////
    // Background task to retrieve locations from remote mysql server

    private class RetrieveTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            String strUrl = "http://www.ase-jf.com.br/gpp/retrieve.php";

            URL url = null;
            StringBuffer sb = new StringBuffer();
            try {
                url = new URL(strUrl);
                HttpURLConnection connection = (HttpURLConnection) url
                        .openConnection();
                connection.connect();
                InputStream iStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(iStream));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }

                reader.close();
                iStream.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            new ParserTask().execute(result);
        }

    }

    // Background thread to parse the JSON data retrieved from MySQL server
    private class ParserTask extends
            AsyncTask<String, Void, List<HashMap<String, String>>> {
        @Override
        protected List<HashMap<String, String>> doInBackground(String... params) {
            MarkerJSONParser markerParser = new MarkerJSONParser();
            JSONObject json = null;
            try {
                json = new JSONObject(params[0]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            List<HashMap<String, String>> markersList = markerParser
                    .parse(json);
            return markersList;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> result) {
            for (int i = 0; i < result.size(); i++) {
                HashMap<String, String> marker = result.get(i);
                LatLng latlng = new LatLng(
                        Double.parseDouble(marker.get("latitude")),
                        Double.parseDouble(marker.get("longitude")));
                //Double.parseDouble(marker.get("latitude")),
                //Double.parseDouble(marker.get("longitude")));
                addMarker(latlng);
            }
        }
    }


}

