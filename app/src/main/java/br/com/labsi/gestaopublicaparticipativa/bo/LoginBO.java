package br.com.labsi.gestaopublicaparticipativa.bo;

/**
 * Created by Marcelo on 18/01/2015.
 */

import android.content.Context;
import br.com.labsi.gestaopublicaparticipativa.R;

public class LoginBO {
    private Context context;

    public LoginBO (Context context){
        this.context=context;
    }

    public String validarLogin(String login, String senha) {


        if (login == null || login.equals("")) {

            return context.getString(R.string.msg_login_obg);
        } else if (senha == null || senha.equals("")) {

            return context.getString(R.string.msg_senha_obg);
        }
        return null;
    }

}

