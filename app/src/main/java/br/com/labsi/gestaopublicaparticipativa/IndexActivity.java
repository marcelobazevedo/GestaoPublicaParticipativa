package br.com.labsi.gestaopublicaparticipativa;

/**
 * Created by Marcelo on 18/01/2015.
 */
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class IndexActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ActionBar actionBar = getActionBar();



        Button botaoLogar = (Button) findViewById(R.id.botaoLogar);
        Button botaoCadastrar = (Button) findViewById(R.id.botaoCadastrar);
        Button botaoAnonimo = (Button) findViewById(R.id.botaoAnonimo);
        Button botaoListarTodos = (Button) findViewById(R.id.botaoListarTodos);
        Button botaoLogarComEmail = (Button)findViewById(R.id.botaoLogarComEmail);


        botaoLogarComEmail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(IndexActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(IndexActivity.this, CadastrarActivity.class);
                startActivity(i);
            }
        });

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(IndexActivity.this, CadastrarActivity.class);
                startActivity(i);
            }
        });

        botaoAnonimo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(IndexActivity.this, DenunciaAnonimaActivity3.class);
                startActivity(i);
            }
        });

        botaoListarTodos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(IndexActivity.this, AllReportsActivity.class);
                startActivity(i);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_index, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        int idMenuItem = item.getItemId();
        switch (idMenuItem) {
            case R.id.mapa:
                Intent it = new Intent(IndexActivity.this, MainActivity.class);
                startActivity(it);
                //finish();
                break;

            case R.id.filtrar:
                Intent its = new Intent(IndexActivity.this, FiltrarActivity.class);
                startActivity(its);
                //finish();
                break;
        }

        return true;
    }

}
