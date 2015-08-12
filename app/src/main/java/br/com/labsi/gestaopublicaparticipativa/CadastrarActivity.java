package br.com.labsi.gestaopublicaparticipativa;

/**
 * Created by Marcelo on 18/01/2015.
 **/
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import br.com.labsi.gestaopublicaparticipativa.bo.LoginBO;
import br.com.labsi.gestaopublicaparticipativa.fragment.CadastrarFragment;
import br.com.labsi.gestaopublicaparticipativa.util.MensagemUtil;
import br.com.labsi.gestaopublicaparticipativa.util.Utils;

public class CadastrarActivity extends android.support.v4.app.FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        CadastrarFragment frag1 = (CadastrarFragment) fm.findFragmentById(R.id.frag1);


    }
}
