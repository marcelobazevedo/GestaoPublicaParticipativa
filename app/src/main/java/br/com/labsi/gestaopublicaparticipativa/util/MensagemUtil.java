package br.com.labsi.gestaopublicaparticipativa.util;

/**
 * Created by Marcelo on 18/01/2015.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface.OnClickListener;
import android.widget.Toast;

public class MensagemUtil {

    //
    public static void addMsg(Activity activity, String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();

    }

    // metodo de criacao de mensagens com mensagem ok
    public static void addMsgOk(Activity activity, String titulo, String msg,
                                int icone) {
        AlertDialog.Builder builderDialog = new AlertDialog.Builder(activity);
        builderDialog.setTitle(titulo);
        builderDialog.setMessage(msg);
        builderDialog.setNeutralButton("OK", null);
        builderDialog.setIcon(icone);
        builderDialog.show();

    }
    //metodo para a criacao de uma mensagem de dialogos com opcao de sim e nao
    public static void addMsgConfirm(Activity activity, String titulo,
                                     String msg, int icone, OnClickListener listener) {
        AlertDialog.Builder builderDialog = new AlertDialog.Builder(activity);
        builderDialog.setTitle(titulo);
        builderDialog.setMessage(msg);
        builderDialog.setPositiveButton("Sim", listener);
        builderDialog.setNegativeButton("Não", null);
        builderDialog.setIcon(icone);
        builderDialog.show();

    }
}

