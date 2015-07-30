package br.com.labsi.gestaopublicaparticipativa;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.labsi.gestaopublicaparticipativa.dto.SubTemaDTO;
import br.com.labsi.gestaopublicaparticipativa.dto.TemaDTO;

/**
 * Created by Marcelo on 25/01/2015.
 */
//public class SubTemaListFragmentURL extends Fragment {
public class SubTemaListFragmentURL extends android.support.v4.app.Fragment {

    LivrosTask mTask;
    List<SubTemaDTO> mLivros;
    ListView mListView;
    TextView mTextMensagem;
    ProgressBar mProgressBar;
    ArrayAdapter<SubTemaDTO> mAdapter;

    public Integer codigoid;
    public String endereco;

    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_subtema_list, null);
        mTextMensagem = (TextView) layout.findViewById(android.R.id.empty);
        mProgressBar = (ProgressBar) layout.findViewById(R.id.progressBar);
        mListView = (ListView) layout.findViewById(R.id.list);
        mListView.setEmptyView(mTextMensagem);

        codigoid = getArguments().getInt("codigo");
        endereco="http://www.ase-jf.com.br/gpp/listasubtema.php?tema=".concat(String.valueOf(codigoid));

        if (mLivros == null) {
            mLivros = new ArrayList<SubTemaDTO>();
        }
        mAdapter = new ArrayAdapter<SubTemaDTO>(getActivity(), android.R.layout.simple_list_item_1, mLivros);
        mListView.setAdapter(mAdapter);
        if (mTask == null) {
            if (SubTemaHttp.temConexao(getActivity())) {
                iniciarDownload(endereco);
            } else {
                mTextMensagem.setText("Sem conexão");
            }
        } else if (mTask.getStatus() == AsyncTask.Status.RUNNING) {
            exibirProgress(true);
        }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SubTemaDTO tema = new SubTemaDTO();
                SubTemaDTO subTemaDTO = (SubTemaDTO) parent.getItemAtPosition(position);

                Intent intent = new Intent(getActivity(), DenunciaAnonimaActivity3.class);
                intent.putExtra("codigo", subTemaDTO.getIdSubTema().toString());
                startActivity(intent);

//gera um toast
                //Toast toast = Toast.makeText(getActivity(), subTemaDTO.getIdSubTema().toString(), Toast.LENGTH_SHORT);
                //toast.show();
            }
        });

        return layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    private void exibirProgress(boolean exibir) {
        if (exibir) {
            mTextMensagem.setText("Carregando os tipos de denúncias...");
        }
        mTextMensagem.setVisibility(exibir ? View.VISIBLE : View.GONE);
        mProgressBar.setVisibility(exibir ? View.VISIBLE : View.GONE);
    }

    public void iniciarDownload(String end) {
        if (mTask == null || mTask.getStatus() != AsyncTask.Status.RUNNING) {
            mTask = new LivrosTask();
            mTask.execute(end);
         }
    }

    class LivrosTask extends AsyncTask<String, Void, List<SubTemaDTO>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            exibirProgress(true);
        }

        @Override
        protected List<SubTemaDTO> doInBackground(String...params) {
           return SubTemaHttp.carregarLivrosJson(params[0]);

        }

        @Override
        protected void onPostExecute(List<SubTemaDTO> livros) {
            super.onPostExecute(livros);
            exibirProgress(false);
            if (livros != null) {
                mLivros.clear();
                mLivros.addAll(livros);
                mAdapter.notifyDataSetChanged();
            } else {
                mTextMensagem.setText("Falha ao obter subtemas");
            }
        }
    }

}
