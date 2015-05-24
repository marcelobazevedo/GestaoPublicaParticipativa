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

public class FiltrarActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtro_activity);
        ActionBar actionBar = getActionBar();
     //   actionBar.setTitle("GP2M");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_filtrar, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        int idMenuItem = item.getItemId();
        switch (idMenuItem) {
            case R.id.mapa:
                Intent it = new Intent(FiltrarActivity.this, MainActivity.class);
                startActivity(it);
                //finish();
                break;

            case R.id.denunciar:
                Intent its = new Intent(FiltrarActivity.this, IndexActivity.class);
                startActivity(its);
                //finish();
                break;
        }

        return true;
    }
}

