package br.com.labsi.gestaopublicaparticipativa;

import android.app.Application;

/**
 * Created by Marcelo on 04/08/2015.
 */
public class TodaAplicacao extends Application {
    private  String idUsuario;
    private String email;
    private String nomeUsuario;
    private String nome;
    private String sexo;

    @Override
    public void onCreate() {
       super.onCreate();
    }


    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
