package br.com.labsi.gestaopublicaparticipativa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Marcelo on 25/01/2015.
 */
public class TemaMainActivity2 extends android.support.v4.app.FragmentActivity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_tema);
            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
            TemaListFragment frag1 = (TemaListFragment) fm.findFragmentById(R.id.frag1);


        }
    }
