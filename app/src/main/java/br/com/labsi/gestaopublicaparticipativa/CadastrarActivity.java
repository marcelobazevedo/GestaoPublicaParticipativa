package br.com.labsi.gestaopublicaparticipativa;

/**
 * Created by Marcelo on 18/01/2015.
 */
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import br.com.labsi.gestaopublicaparticipativa.bo.LoginBO;
import br.com.labsi.gestaopublicaparticipativa.util.MensagemUtil;

public class CadastrarActivity extends FragmentActivity {

    private LoginBO loginBO;
    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtDataNascimento;
    private EditText edtsenha;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        edtNome = (EditText)findViewById(R.id.edt_nome);
        edtEmail= (EditText) findViewById(R.id.edt_email);
        edtDataNascimento= (EditText) findViewById(R.id.edt_data_nascimento);
        edtsenha= (EditText) findViewById(R.id.edt_senha);

    }

    public void cadastrar(View view) {
        new LoadingAsync().execute();
    }


    private class LoadingAsync extends AsyncTask<Void, Void, String> {

        private ProgressDialog progressDialog = new ProgressDialog(
                CadastrarActivity.this);

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Carregando...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            String nome = edtNome.getText().toString();
            String email = edtEmail.getText().toString();
            String datadenascimeto = edtDataNascimento.getText().toString();
            String senha = edtsenha.getText().toString();

            loginBO = new LoginBO(CadastrarActivity.this);

            return loginBO.validarLogin(nome, email);// faz a validacao dos campos, falta o de dn e senha



        }

        @Override
        protected void onPostExecute(String msg) {
            progressDialog.dismiss();
            if (msg==null) {
                Intent i = new Intent(CadastrarActivity.this,
                        CadastrarActivity.class);
                startActivity(i);
                finish();
            }else {
                MensagemUtil.addMsg(CadastrarActivity.this, msg);
            }
        }
    }
}
