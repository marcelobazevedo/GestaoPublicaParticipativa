package br.com.labsi.gestaopublicaparticipativa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import android.widget.Toast;

import br.com.labsi.gestaopublicaparticipativa.dto.TemaDTO;

/**
 * Created by Marcelo on 19/07/2015.
 */
public class SubTemaMainActivity2  extends android.support.v4.app.FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitu_main_subtema);
        SubTemaListFragmentURL frag1 = new SubTemaListFragmentURL();
        frag1.setArguments(getIntent().getExtras());
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.frag1, frag1,"SubTemaListFragmentURL");
        ft.commit();


    }
}
