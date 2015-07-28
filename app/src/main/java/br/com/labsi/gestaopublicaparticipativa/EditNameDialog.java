package br.com.labsi.gestaopublicaparticipativa;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by Marcelo on 26/07/2015.
 */
public class EditNameDialog extends DialogFragment {

    public interface EditNameDialogListener {
        void onFinishEditDialog(String inputText);
    }

    private EditText mEditText;

    public EditNameDialog() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_name, container);
        mEditText = (EditText) view.findViewById(R.id.txt_your_name);
        getDialog().setTitle("Descreva a sua denúncia");


        Button btExit = (Button) view.findViewById(R.id.btExit);
        btExit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
                DenunciaAnonimaActivity3 denuncia = (DenunciaAnonimaActivity3) getActivity();
                denuncia.excluirMarker();

            }
        });
        Button btEnter = (Button) view.findViewById(R.id.btEnter);
        btEnter.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EditNameDialogListener activity = (EditNameDialogListener) getActivity();
                //activity.onFinishEditDialog(mEditText.getText().toString());
                String obs = mEditText.getText().toString();
                DenunciaAnonimaActivity3 denuncia = (DenunciaAnonimaActivity3) getActivity();
                denuncia.sendToServer(obs);
                dismiss();
            }
        });
        // ja exibe o teclado
        /*
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        mEditText.setOnEditorActionListener(this);
*/
        return view;
    }


}
