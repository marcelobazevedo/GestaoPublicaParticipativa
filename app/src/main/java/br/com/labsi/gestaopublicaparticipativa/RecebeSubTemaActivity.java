package br.com.labsi.gestaopublicaparticipativa;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.TextView;

import java.util.regex.Pattern;

/**
 * Created by Marcelo on 27/01/2015.
 */
public class RecebeSubTemaActivity extends Activity {
private Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recebe_subtema);

        Intent intent = getIntent();
        if(intent!=null){
            Bundle params = intent.getExtras();
            if(params!=null){
                String codigo = params.getString("codigo");

                TextView codigoTV = (TextView) findViewById(R.id.codigo1);

                codigoTV.setText("Codigo:" + codigo);
            }
        }
    }




}
