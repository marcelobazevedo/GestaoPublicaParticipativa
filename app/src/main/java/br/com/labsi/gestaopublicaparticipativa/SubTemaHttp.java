package br.com.labsi.gestaopublicaparticipativa;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.labsi.gestaopublicaparticipativa.dto.SubTemaDTO;

/**
 * Created by Marcelo on 24/01/2015.
 */
public class SubTemaHttp {
    public static final String LIVROS_URL_JSON =
            "http://www.ase-jf.com.br/gpp/listasubtema.php";


    private static HttpURLConnection connectar(String urlArquivo) throws IOException {
        final int SEGUNDOS = 1000;
        URL url = new URL(urlArquivo);
        HttpURLConnection conexao = (HttpURLConnection)url.openConnection();
        conexao.setReadTimeout(10 * SEGUNDOS);
        conexao.setConnectTimeout(15 * SEGUNDOS);
        conexao.setRequestMethod("GET");
        conexao.setDoInput(true);
        conexao.setDoOutput(false);
        conexao.connect();
        return conexao;
    }

    public static boolean temConexao(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager)
                ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }


    public static List<SubTemaDTO> carregarLivrosJson() {
        try {
            HttpURLConnection conexao = connectar(LIVROS_URL_JSON);
            int resposta = conexao.getResponseCode();
            if (resposta == HttpURLConnection.HTTP_OK) {
                InputStream is = conexao.getInputStream();
                JSONObject json = new JSONObject(bytesParaString(is));
                return lerJsonLivros(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<SubTemaDTO> lerJsonLivros(JSONObject json) throws JSONException {
        List<SubTemaDTO> listaDeLivros = new ArrayList<SubTemaDTO>();
        String categoriaAtual;
     //   JSONArray jsonNovatec = json.getJSONArray("subtemas");
      //  for (int i = 0; i < jsonNovatec.length(); i++) {
        //    JSONObject jsonCategoria = jsonNovatec.getJSONObject(i);
           // categoriaAtual = jsonCategoria.getString("categoria");
            JSONArray jsonLivros = json.getJSONArray("subtemas");
            for (int j = 0; j < jsonLivros.length(); j++) {
                JSONObject jsonLivro = jsonLivros.getJSONObject(j);
                SubTemaDTO subTemaDTO = new SubTemaDTO();
                subTemaDTO.setIdSubTema(jsonLivro.getInt("idSubtema"));
                   subTemaDTO.setNomeSubTema(jsonLivro.getString("nomeSubtema"));
                listaDeLivros.add(subTemaDTO);
            }
   //     }
        return listaDeLivros;
    }
    private static String bytesParaString(InputStream is) throws IOException {
        byte[] buffer = new byte[1024];
// O bufferzao vai armazenar todos os bytes lidos
        ByteArrayOutputStream bufferzao = new ByteArrayOutputStream();
// precisamos saber quantos bytes foram lidos
        int bytesLidos;
// Vamos lendo de 1KB por vez...
        while ((bytesLidos = is.read(buffer)) != -1) {
// copiando a quantidade de bytes lidos do buffer para o bufferzão
            bufferzao.write(buffer, 0, bytesLidos);
        }
        return new String(bufferzao.toByteArray(), "UTF-8");
    }

}
