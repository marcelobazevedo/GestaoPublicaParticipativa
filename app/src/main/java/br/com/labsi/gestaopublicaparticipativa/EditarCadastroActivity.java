package br.com.labsi.gestaopublicaparticipativa;

/**
 * Created by Marcelo on 18/01/2015.
 **/
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import br.com.labsi.gestaopublicaparticipativa.fragment.CadastrarFragment;
import br.com.labsi.gestaopublicaparticipativa.fragment.EditarCadastroFragment;

public class EditarCadastroActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cadastro);
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        EditarCadastroFragment frag1 = (EditarCadastroFragment) fm.findFragmentById(R.id.frag1);


    }
}
