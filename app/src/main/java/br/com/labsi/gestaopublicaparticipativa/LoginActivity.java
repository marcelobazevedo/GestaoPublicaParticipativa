package br.com.labsi.gestaopublicaparticipativa;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by Marcelo on 28/01/2015.
 */
public class LoginActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //   final TextView accountsData = (TextView) findViewById(R.id.accounts);
          LoginAsync loginAsync = new LoginAsync();
        String params[] = new String[3];
        params[0] = exibirEmail();
        loginAsync.execute(params);
    }

    public String exibirEmail(){
        String possibleEmail = "";
        try {
            Account[] accounts = AccountManager.get(this).getAccountsByType("com.google");
            for (Account account : accounts) {
                possibleEmail = account.name;
            }
        } catch (Exception e) {
            Log.i("Exception", "Exception:" + e);
        }
       // Toast toast = Toast.makeText(LoginActivity.this, possibleEmail, Toast.LENGTH_LONG);
        //toast.show();
        return  possibleEmail;
    }

    private class LoginAsync extends AsyncTask<String, Void, String> {
        private ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Logando...");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String resultado) {
            progressDialog.dismiss();
            if (resultado!="") {
                Intent intent = new Intent(LoginActivity.this, TemaMainActivity2.class);
                startActivity(intent);
                //  Log.i("Exception", "achou " + possibleEmail + "o número é " + numero);
            } else {
                // Log.i("Exception", "não achou " + possibleEmail + " o número é " + numero);
            }
        }

        @Override
        protected String doInBackground(String... params) {
                String email="";
                String codigo="";
                email = params[0];
            if(email != ""){
                LoginHttp loginHttp = new LoginHttp();
                codigo=loginHttp.validarLoginRest(email);
            }
            return codigo;
        }
    }


}