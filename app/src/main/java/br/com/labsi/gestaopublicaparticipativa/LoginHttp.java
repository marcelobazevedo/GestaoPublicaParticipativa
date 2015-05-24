package br.com.labsi.gestaopublicaparticipativa;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Marcelo on 08/02/2015.
 */
public class LoginHttp {

    public static final String URL_LOGAR = "http://www.ase-jf.com.br/gpp/logar.php";


    public String validarLoginRest(String email) {

        //email = "marcelobazevedo@gmail.com";
        String resultado = "";
        if (email != "") {

            try {
                URL url = new URL(URL_LOGAR);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedInputStream buffer = new BufferedInputStream(connection.getInputStream());

                resultado = converterToString(buffer);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }


    private static String converterToString(InputStream in) {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(in));

        StringBuilder builder = new StringBuilder();
        String linha = null;
        try {
            while ((linha = buffer.readLine()) != null) {
                builder.append(linha);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
