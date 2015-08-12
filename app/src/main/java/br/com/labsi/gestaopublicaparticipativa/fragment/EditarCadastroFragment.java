package br.com.labsi.gestaopublicaparticipativa.fragment;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import br.com.labsi.gestaopublicaparticipativa.IndexActivity;
import br.com.labsi.gestaopublicaparticipativa.R;
import br.com.labsi.gestaopublicaparticipativa.TemaHttp;
import br.com.labsi.gestaopublicaparticipativa.UsuarioHttp;
import br.com.labsi.gestaopublicaparticipativa.bo.LoginBO;
import br.com.labsi.gestaopublicaparticipativa.dto.TemaDTO;
import br.com.labsi.gestaopublicaparticipativa.dto.UsuarioDTO;
import br.com.labsi.gestaopublicaparticipativa.util.MensagemUtil;


public class EditarCadastroFragment extends android.support.v4.app.Fragment {
    UsuarioTask mTask;
    List<UsuarioDTO> mUsuarios;
    TextView mTextMensagem;
    ProgressBar mProgressBar;
    ArrayAdapter<UsuarioDTO> mAdapter;

    private LoginBO loginBO;
    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtsenha;
    private UsuarioDTO usuarioDTO;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //UsuarioDTO usuarioDTO = new UsuarioDTO();
        View layout = inflater.inflate(R.layout.fragment_editar_cadastro, null);
        edtNome = (EditText)layout.findViewById(R.id.edt_nome);
        edtEmail= (EditText) layout.findViewById(R.id.edt_email);
        edtEmail.setText(usuarioDTO.getEmail());
        edtsenha= (EditText) layout.findViewById(R.id.edt_senha);
        mTextMensagem = (TextView) layout.findViewById(android.R.id.empty);
        mProgressBar = (ProgressBar) layout.findViewById(R.id.progressBar);

        Button btCadastrar = (Button) layout.findViewById(R.id.btn_cadastrar);
        btCadastrar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
                // dismiss();
            }
        });

        return layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mTask == null) {
            if (UsuarioHttp.temConexao(getActivity())) {
                iniciarDownload();
            } else {
                mTextMensagem.setText("Sem conexão");
            }
        } else if (mTask.getStatus() == AsyncTask.Status.RUNNING) {
            exibirProgress(true);
        }
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
private void exibirProgress(boolean exibir) {
    if (exibir) {
        mTextMensagem.setText("Carregando os dados do usuário...");
    }
    mTextMensagem.setVisibility(exibir ? View.VISIBLE : View.GONE);
    mProgressBar.setVisibility(exibir ? View.VISIBLE : View.GONE);
}

    public void iniciarDownload() {
        if (mTask == null || mTask.getStatus() != AsyncTask.Status.RUNNING) {
            mTask = new UsuarioTask();
            mTask.execute();
        }
    }


    class UsuarioTask extends AsyncTask<Void, Void, List<UsuarioDTO>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            exibirProgress(true);
        }

        @Override
        protected List<UsuarioDTO> doInBackground(Void... strings) {
            return UsuarioHttp.carregarLivrosJson();

        }

        @Override
        protected void onPostExecute(List<UsuarioDTO> livros) {
            super.onPostExecute(livros);
            exibirProgress(false);
            if (livros != null) {
//                mUsuarios.clear();
//                mUsuarios.addAll(livros);
              //  mAdapter.notifyDataSetChanged();
            } else {
                mTextMensagem.setText("Falha ao obter temas");
            }
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
    public void cadastrar(View view) {
        new LoadingAsync().execute();
    }
*/
public void cadastrar() {
    new LoadingAsync().execute();
}

    private class LoadingAsync extends AsyncTask<Void, Void, String> {

        private ProgressDialog progressDialog = new ProgressDialog(
                getActivity());

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Cadastrando...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {

            String nome = edtNome.getText().toString();
            String email = edtEmail.getText().toString();
            String senha = edtsenha.getText().toString();
            loginBO = new LoginBO(getActivity());

            // return loginBO.validarLogin(nome, senha);// faz a validacao dos campos, falta o de dn e senha
            if(loginBO.validarLogin(nome, senha)==null){
                String strUrl = "http://www.ase-jf.com.br/gpp/salvarusuario.php";

                URL url = null;
                try {
                    url = new URL(strUrl);

                    HttpURLConnection connection = (HttpURLConnection) url
                            .openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                            connection.getOutputStream());

                    outputStreamWriter.write("nome=" + nome + "&email=" + email + "&senha=" + senha);
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
            }else{
                return "nao posso logar";
            }


        }

        @Override
        protected void onPostExecute(String msg) {
            progressDialog.dismiss();
            if (msg==null) {
                Intent i = new Intent(getActivity(),
                        IndexActivity.class);
                startActivity(i);
                //finish(); depois verificar isso aqui
            }else {
                MensagemUtil.addMsg(getActivity(), msg);

            }
        }
    }
}
