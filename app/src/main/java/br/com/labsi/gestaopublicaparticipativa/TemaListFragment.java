package br.com.labsi.gestaopublicaparticipativa;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.labsi.gestaopublicaparticipativa.dto.SubTemaDTO;

/**
 * Created by Marcelo on 25/01/2015.
 */
public class TemaListFragment extends Fragment {
    LivrosTask mTask;
    List<SubTemaDTO> mLivros;
    ListView mListView;
    TextView mTextMensagem;
    ProgressBar mProgressBar;
    ArrayAdapter<SubTemaDTO> mAdapter;

    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        getEmail();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_subtema_list, null);
        mTextMensagem = (TextView) layout.findViewById(android.R.id.empty);
        mProgressBar = (ProgressBar) layout.findViewById(R.id.progressBar);
        mListView = (ListView) layout.findViewById(R.id.list);
        mListView.setEmptyView(mTextMensagem);
        return layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mLivros == null) {
            mLivros = new ArrayList<SubTemaDTO>();
        }
        mAdapter = new ArrayAdapter<SubTemaDTO>(getActivity(), android.R.layout.simple_list_item_1, mLivros);
        mListView.setAdapter(mAdapter);
        if (mTask == null) {
            if (SubTemaHttp.temConexao(getActivity())) {
                iniciarDownload();
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

             Intent intent = new Intent(getActivity(), RecebeSubTemaActivity.class);
                intent.putExtra("codigo", subTemaDTO.getIdSubTema().toString());
                startActivity(intent);

//gera um toast
                //Toast toast = Toast.makeText(getActivity(), subTemaDTO.getIdSubTema().toString(), Toast.LENGTH_SHORT);
                //toast.show();
            }
        });


    }


    private void exibirProgress(boolean exibir) {
        if (exibir) {
            mTextMensagem.setText("Carregando os tipos de denúncias...");
        }
        mTextMensagem.setVisibility(exibir ? View.VISIBLE : View.GONE);
        mProgressBar.setVisibility(exibir ? View.VISIBLE : View.GONE);
    }

    public void iniciarDownload() {
        if (mTask == null || mTask.getStatus() != AsyncTask.Status.RUNNING) {
            mTask = new LivrosTask();
            mTask.execute();
        }
    }

    class LivrosTask extends AsyncTask<Void, Void, List<SubTemaDTO>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            exibirProgress(true);
        }

        @Override
        protected List<SubTemaDTO> doInBackground(Void... strings) {
            return SubTemaHttp.carregarLivrosJson();

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
                mTextMensagem.setText("Falha ao obter temas");
            }
        }
    }



    private String getEmail() {
        try {
            AccountManager accountManager = AccountManager.get(getActivity());
            Account[] accounts = accountManager.getAccountsByType("com.google");
            if (accounts.length > 0) {
                Account account = accounts[0];
                return account.name;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
